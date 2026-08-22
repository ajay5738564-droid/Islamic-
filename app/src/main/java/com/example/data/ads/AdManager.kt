package com.example.data.ads

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * AdMob Configuration using REAL AdMob IDs exclusively.
 */
object AdConfig {
    // Real AdMob App ID
    const val ADMOB_APP_ID = "ca-app-pub-6680282505070613~8973172694"

    // Real AdMob Ad Unit IDs
    const val BANNER_AD_UNIT_ID = "ca-app-pub-6680282505070613/5489378788"
    const val INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-6680282505070613/2407764342"
    const val REWARDED_AD_UNIT_ID = "ca-app-pub-6680282505070613/9220740239"
}

data class AdPlacementConfig(
    val adsEnabled: Boolean = true,
    val bannerAdsEnabled: Boolean = true,
    val interstitialAdsEnabled: Boolean = true,
    val rewardedAdsEnabled: Boolean = true
)

object AdManager {
    private const val TAG = "AdManager"

    // Minimum interval between interstitials (10 seconds between transitions)
    private const val MIN_INTERSTITIAL_INTERVAL_MS = 10_000L
    private var lastInterstitialShownTimestamp: Long = 0L

    private val _adConfig = MutableStateFlow(AdPlacementConfig())
    val adConfig: StateFlow<AdPlacementConfig> = _adConfig.asStateFlow()

    private var isInitialized = false

    // Cached ad instances
    private var interstitialAd: InterstitialAd? = null
    private var isInterstitialLoading = false

    private var rewardedAd: RewardedAd? = null
    private var isRewardedLoading = false

    private val _isRewardedAvailable = MutableStateFlow(false)
    val isRewardedAvailable: StateFlow<Boolean> = _isRewardedAvailable.asStateFlow()

    /**
     * Initializes Google Mobile Ads SDK.
     */
    fun initializeAds(context: Context) {
        if (isInitialized) return
        try {
            MobileAds.initialize(context.applicationContext) { status ->
                isInitialized = true
                Log.d(TAG, "Google Mobile Ads initialized successfully: ${status.adapterStatusMap}")
                // Preload real interstitial and rewarded ads
                loadInterstitial(context.applicationContext)
                loadRewarded(context.applicationContext)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing Google Mobile Ads SDK", e)
        }
    }

    fun toggleAds(enabled: Boolean) {
        _adConfig.value = _adConfig.value.copy(adsEnabled = enabled)
    }

    fun canShowAdInContext(screenContext: String): Boolean {
        return _adConfig.value.adsEnabled
    }

    // ==========================================
    // INTERSTITIAL ADS (Real Google AdMob SDK)
    // ==========================================

    fun loadInterstitial(context: Context) {
        if (!_adConfig.value.adsEnabled || !_adConfig.value.interstitialAdsEnabled) return
        if (interstitialAd != null || isInterstitialLoading) return

        isInterstitialLoading = true
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            context,
            AdConfig.INTERSTITIAL_AD_UNIT_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    isInterstitialLoading = false
                    Log.d(TAG, "Real Interstitial ad loaded successfully.")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    interstitialAd = null
                    isInterstitialLoading = false
                    Log.w(TAG, "Real Interstitial ad failed to load: ${loadAdError.code} - ${loadAdError.message}")
                }
            }
        )
    }

    /**
     * Shows an interstitial ad only after it has successfully loaded.
     */
    fun showInterstitialIfAppropriate(
        activity: Activity,
        contextTag: String,
        onDismissed: () -> Unit = {}
    ) {
        if (!canShowAdInContext(contextTag)) {
            onDismissed()
            return
        }

        val currentTime = SystemClock.elapsedRealtime()
        if (lastInterstitialShownTimestamp > 0 && (currentTime - lastInterstitialShownTimestamp) < MIN_INTERSTITIAL_INTERVAL_MS) {
            onDismissed()
            return
        }

        val ad = interstitialAd
        if (ad == null) {
            // Not ready yet; request background load for next time and continue smoothly
            loadInterstitial(activity.applicationContext)
            onDismissed()
            return
        }

        ad.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                interstitialAd = null
                lastInterstitialShownTimestamp = SystemClock.elapsedRealtime()
                loadInterstitial(activity.applicationContext)
                onDismissed()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                interstitialAd = null
                Log.w(TAG, "Interstitial failed to show: ${adError.message}")
                loadInterstitial(activity.applicationContext)
                onDismissed()
            }

            override fun onAdShowedFullScreenContent() {
                lastInterstitialShownTimestamp = SystemClock.elapsedRealtime()
                Log.d(TAG, "Real Interstitial ad displayed.")
            }
        }

        ad.show(activity)
    }

    // ==========================================
    // REWARDED ADS (Real Google AdMob SDK)
    // ==========================================

    fun loadRewarded(context: Context) {
        if (!_adConfig.value.adsEnabled || !_adConfig.value.rewardedAdsEnabled) return
        if (rewardedAd != null || isRewardedLoading) return

        isRewardedLoading = true
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(
            context,
            AdConfig.REWARDED_AD_UNIT_ID,
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    rewardedAd = ad
                    isRewardedLoading = false
                    _isRewardedAvailable.value = true
                    Log.d(TAG, "Real Rewarded ad loaded successfully.")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    rewardedAd = null
                    isRewardedLoading = false
                    _isRewardedAvailable.value = false
                    Log.w(TAG, "Real Rewarded ad failed to load: ${loadAdError.code} - ${loadAdError.message}")
                }
            }
        )
    }

    /**
     * Shows a rewarded ad only after it has successfully loaded.
     */
    fun showRewardedAd(
        activity: Activity,
        onRewardGranted: () -> Unit
    ) {
        val ad = rewardedAd
        if (ad == null) {
            // If ad is not ready yet, grant reward so user is not blocked and request preload
            loadRewarded(activity.applicationContext)
            onRewardGranted()
            return
        }

        ad.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                rewardedAd = null
                _isRewardedAvailable.value = false
                loadRewarded(activity.applicationContext)
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                rewardedAd = null
                _isRewardedAvailable.value = false
                Log.w(TAG, "Rewarded ad failed to show: ${adError.message}")
                loadRewarded(activity.applicationContext)
                onRewardGranted()
            }

            override fun onAdShowedFullScreenContent() {
                Log.d(TAG, "Real Rewarded ad displayed.")
            }
        }

        ad.show(activity) { rewardItem ->
            Log.d(TAG, "User earned reward: ${rewardItem.type} ${rewardItem.amount}")
            onRewardGranted()
        }
    }
}
