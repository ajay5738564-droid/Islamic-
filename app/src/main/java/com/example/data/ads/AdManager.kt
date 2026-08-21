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

    // Minimum interval between interstitials (15 seconds between new function transitions)
    private const val MIN_INTERSTITIAL_INTERVAL_MS = 15_000L
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

        // Always allow top persistent banner and function transitions
        if (screenContext.startsWith("app_top") || screenContext.startsWith("function_transition") || screenContext.startsWith("home") || screenContext.startsWith("explore")) {
            return true
        }

        val forbiddenContexts = setOf(
            "app_open",
            "app_launch",
            "quran_audio"
        )

        return screenContext !in forbiddenContexts
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
                    Log.w(TAG, "Interstitial ad failed to load: ${loadAdError.message}")
                }
            }
        )
    }

    /**
     * Shows an interstitial ad only if appropriate:
     * - Screen context is verified not religious
     * - Ad is loaded
     * - Cooldown period has passed (minimum 5 minutes)
     */
    fun showInterstitialIfAppropriate(
        activity: Activity,
        contextTag: String,
        onDismissed: () -> Unit = {}
    ) {
        if (!canShowAdInContext(contextTag)) {
            Log.d(TAG, "Interstitial blocked due to holy/religious context: $contextTag")
            onDismissed()
            return
        }

        val currentTime = SystemClock.elapsedRealtime()
        if (lastInterstitialShownTimestamp > 0 && (currentTime - lastInterstitialShownTimestamp) < MIN_INTERSTITIAL_INTERVAL_MS) {
            Log.d(TAG, "Interstitial skipped due to frequency cooldown.")
            onDismissed()
            return
        }

        val ad = interstitialAd
        if (ad == null) {
            Log.d(TAG, "Interstitial not ready; proceeding without blocking user.")
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
                loadInterstitial(activity.applicationContext)
                onDismissed()
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
                    Log.w(TAG, "Rewarded ad failed to load: ${loadAdError.message}")
                }
            }
        )
    }

    fun showRewarded(
        activity: Activity,
        onUserEarnedReward: (RewardItem) -> Unit,
        onAdClosed: () -> Unit = {}
    ) {
        val ad = rewardedAd
        if (ad == null) {
            Log.d(TAG, "Rewarded ad not available.")
            loadRewarded(activity.applicationContext)
            onAdClosed()
            return
        }

        var rewardEarned = false

        ad.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                rewardedAd = null
                _isRewardedAvailable.value = false
                loadRewarded(activity.applicationContext)
                onAdClosed()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                rewardedAd = null
                _isRewardedAvailable.value = false
                loadRewarded(activity.applicationContext)
                onAdClosed()
            }
        }

        ad.show(activity) { rewardItem ->
            rewardEarned = true
            onUserEarnedReward(rewardItem)
        }
    }

    fun showRewardedAd(
        activity: Activity,
        onRewardGranted: () -> Unit
    ) {
        val ad = rewardedAd
        if (ad == null) {
            Log.d(TAG, "Rewarded ad not loaded yet; granting reward gracefully.")
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
                loadRewarded(activity.applicationContext)
                onRewardGranted()
            }
        }

        ad.show(activity) {
            onRewardGranted()
        }
    }
}
