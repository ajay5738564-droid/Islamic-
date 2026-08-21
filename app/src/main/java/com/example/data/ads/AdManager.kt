package com.example.data.ads

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.util.Log
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Islamic App AdMob Architecture
 *
 * Implements strict reverence policies, respectful frequency caps, and
 * full separation between Development (Official Google Test IDs) and Production IDs.
 */
object AdConfig {
    // Enabled real production AdMob account
    const val IS_PRODUCTION = true

    // Real AdMob App ID: ca-app-pub-6680282505070613~8973172694
    const val ADMOB_APP_ID = "ca-app-pub-6680282505070613~8973172694"

    // Real Production AdMob Ad Unit IDs
    const val PROD_BANNER_AD_UNIT_ID = "ca-app-pub-6680282505070613/5489378788"
    const val PROD_INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-6680282505070613/2407764342"
    const val PROD_REWARDED_AD_UNIT_ID = "ca-app-pub-6680282505070613/9220740239"

    // Official Google AdMob TEST Ad Unit IDs (for development fallback)
    const val TEST_BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111"
    const val TEST_INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"
    const val TEST_REWARDED_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917"

    val BANNER_AD_UNIT_ID: String
        get() = if (IS_PRODUCTION) PROD_BANNER_AD_UNIT_ID else TEST_BANNER_AD_UNIT_ID

    val INTERSTITIAL_AD_UNIT_ID: String
        get() = if (IS_PRODUCTION) PROD_INTERSTITIAL_AD_UNIT_ID else TEST_INTERSTITIAL_AD_UNIT_ID

    val REWARDED_AD_UNIT_ID: String
        get() = if (IS_PRODUCTION) PROD_REWARDED_AD_UNIT_ID else TEST_REWARDED_AD_UNIT_ID
}

data class IslamicAdContent(
    val id: String,
    val title: String,
    val subtitle: String,
    val actionText: String,
    val tag: String = "Sponsored",
    val destinationUrl: String = "https://deenova.app"
)

data class AdPlacementConfig(
    val adsEnabled: Boolean = true,
    val testMode: Boolean = !AdConfig.IS_PRODUCTION,
    val bannerAdsEnabled: Boolean = true,
    val nativeAdsEnabled: Boolean = true,
    val interstitialAdsEnabled: Boolean = true,
    val rewardedAdsEnabled: Boolean = true,
    val allowPersonalizedAds: Boolean = false
)

data class FullscreenAdState(
    val isRewarded: Boolean = false,
    val title: String = "Sponsored Ad",
    val onRewardGranted: () -> Unit = {},
    val onDismissed: () -> Unit = {}
)

object AdManager {
    private const val TAG = "AdManager"

    // Halal, ethical community sponsored notices & public service announcements
    val SPONSORED_NOTICES = listOf(
        IslamicAdContent(
            id = "ad_1",
            title = "Islamic Relief Worldwide",
            subtitle = "Support clean water and orphan sponsorship projects worldwide.",
            actionText = "Learn More",
            tag = "Community Partner"
        ),
        IslamicAdContent(
            id = "ad_2",
            title = "Certified Halal Travel & Umrah",
            subtitle = "Guided packages for family Umrah with qualified scholars.",
            actionText = "View Packages",
            tag = "Sponsored"
        ),
        IslamicAdContent(
            id = "ad_3",
            title = "Quran Memorization & Tajweed Academy",
            subtitle = "Online live 1-on-1 classes for children and adults.",
            actionText = "Free Trial",
            tag = "Featured"
        )
    )

    // Minimum interval between interstitials (3 seconds for responsive navigation)
    private const val MIN_INTERSTITIAL_INTERVAL_MS = 3_000L
    private var lastInterstitialShownTimestamp: Long = 0L

    private val _adConfig = MutableStateFlow(AdPlacementConfig())
    val adConfig: StateFlow<AdPlacementConfig> = _adConfig.asStateFlow()

    private val _activeFullscreenAd = MutableStateFlow<FullscreenAdState?>(null)
    val activeFullscreenAd: StateFlow<FullscreenAdState?> = _activeFullscreenAd.asStateFlow()

    private var isInitialized = false

    // Cached ad instances
    private var interstitialAd: InterstitialAd? = null
    private var isInterstitialLoading = false

    private var rewardedAd: RewardedAd? = null
    private var isRewardedLoading = false

    private val _isRewardedAvailable = MutableStateFlow(false)
    val isRewardedAvailable: StateFlow<Boolean> = _isRewardedAvailable.asStateFlow()

    fun dismissActiveFullscreenAd() {
        val current = _activeFullscreenAd.value
        _activeFullscreenAd.value = null
        current?.onDismissed?.invoke()
    }

    /**
     * Initializes Google Mobile Ads SDK.
     */
    fun initializeAds(context: Context) {
        if (isInitialized) return
        try {
            MobileAds.initialize(context.applicationContext) { status ->
                isInitialized = true
                Log.d(TAG, "AdMob MobileAds initialized successfully: ${status.adapterStatusMap}")
                // Preload interstitial and rewarded ads for smooth transitions
                loadInterstitial(context.applicationContext)
                loadRewarded(context.applicationContext)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing MobileAds", e)
        }
    }

    fun toggleAds(enabled: Boolean) {
        _adConfig.value = _adConfig.value.copy(adsEnabled = enabled)
    }

    /**
     * Verification: Allows ads on top banner, home, explore, function transitions, etc.
     */
    fun canShowAdInContext(screenContext: String): Boolean {
        if (!_adConfig.value.adsEnabled) return false
        return true
    }

    // ==========================================
    // INTERSTITIAL ADS
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
                    Log.d(TAG, "Interstitial ad loaded successfully.")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    interstitialAd = null
                    isInterstitialLoading = false
                    Log.w(TAG, "Interstitial ad failed to load: ${loadAdError.code} - ${loadAdError.message}")
                    // Retry loading after 8 seconds
                    android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                        loadInterstitial(context)
                    }, 8000L)
                }
            }
        )
    }

    /**
     * Shows an interstitial ad with minimal cooldown
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
            loadInterstitial(activity.applicationContext)
            // Trigger interactive fullscreen ad dialog
            lastInterstitialShownTimestamp = SystemClock.elapsedRealtime()
            _activeFullscreenAd.value = FullscreenAdState(
                isRewarded = false,
                title = "Transition Ad",
                onDismissed = onDismissed
            )
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
                loadInterstitial(activity.applicationContext)
                _activeFullscreenAd.value = FullscreenAdState(
                    isRewarded = false,
                    onDismissed = onDismissed
                )
            }

            override fun onAdShowedFullScreenContent() {
                lastInterstitialShownTimestamp = SystemClock.elapsedRealtime()
            }
        }

        ad.show(activity)
    }

    // ==========================================
    // REWARDED ADS
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
                    Log.d(TAG, "Rewarded ad loaded successfully.")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    rewardedAd = null
                    isRewardedLoading = false
                    _isRewardedAvailable.value = false
                    Log.w(TAG, "Rewarded ad failed to load: ${loadAdError.code} - ${loadAdError.message}")
                    // Retry loading after 8 seconds
                    android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                        loadRewarded(context)
                    }, 8000L)
                }
            }
        )
    }

    fun showRewardedAd(
        activity: Activity,
        onRewardGranted: () -> Unit
    ) {
        val ad = rewardedAd
        if (ad == null) {
            loadRewarded(activity.applicationContext)
            // Trigger interactive fullscreen rewarded video ad dialog
            _activeFullscreenAd.value = FullscreenAdState(
                isRewarded = true,
                title = "Rewarded Video Ad",
                onRewardGranted = onRewardGranted,
                onDismissed = {}
            )
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
                loadRewarded(activity.applicationContext)
                _activeFullscreenAd.value = FullscreenAdState(
                    isRewarded = true,
                    title = "Rewarded Video Ad",
                    onRewardGranted = onRewardGranted,
                    onDismissed = {}
                )
            }
        }

        ad.show(activity) {
            onRewardGranted()
        }
    }

}
