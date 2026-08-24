package com.example.data.ads

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Unity Ads & Universal Ad Configuration.
 * Pre-configured and ready for Unity Ads SDK integration.
 */
object AdConfig {
    // Unity Ads Game IDs (Set to your Unity Game ID from Unity Dashboard)
    const val UNITY_GAME_ID_ANDROID = "800361482"
    const val UNITY_TEST_MODE = false

    // Unity Ad Placement IDs
    const val UNITY_BANNER_PLACEMENT = "Banner_Android"
    const val UNITY_INTERSTITIAL_PLACEMENT = "Interstitial_Android"
    const val UNITY_REWARDED_PLACEMENT = "Rewarded_Android"
}

data class AdPlacementConfig(
    val adsEnabled: Boolean = true,
    val bannerAdsEnabled: Boolean = true,
    val interstitialAdsEnabled: Boolean = true,
    val rewardedAdsEnabled: Boolean = true
)

object AdManager {
    private const val TAG = "AdManager"

    private const val MIN_INTERSTITIAL_INTERVAL_MS = 10_000L
    private var lastInterstitialShownTimestamp: Long = 0L

    private val _adConfig = MutableStateFlow(AdPlacementConfig())
    val adConfig: StateFlow<AdPlacementConfig> = _adConfig.asStateFlow()

    private var isInitialized = false

    private val _isRewardedAvailable = MutableStateFlow(true)
    val isRewardedAvailable: StateFlow<Boolean> = _isRewardedAvailable.asStateFlow()

    /**
     * Initializes Ad SDK (e.g., Unity Ads).
     */
    fun initializeAds(context: Context) {
        if (isInitialized) return
        try {
            isInitialized = true
            Log.d(TAG, "Ad system initialized for Unity Ads integration.")
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing Ad SDK", e)
        }
    }

    fun toggleAds(enabled: Boolean) {
        _adConfig.value = _adConfig.value.copy(adsEnabled = enabled)
    }

    fun canShowAdInContext(screenContext: String): Boolean {
        return _adConfig.value.adsEnabled
    }

    fun loadInterstitial(context: Context) {
        // Ready for UnityAds.load(AdConfig.UNITY_INTERSTITIAL_PLACEMENT, ...)
        Log.d(TAG, "Loading interstitial ad...")
    }

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

        lastInterstitialShownTimestamp = SystemClock.elapsedRealtime()
        Log.d(TAG, "Showing interstitial for: $contextTag")
        // Ready for UnityAds.show(activity, AdConfig.UNITY_INTERSTITIAL_PLACEMENT, ...)
        onDismissed()
    }

    fun loadRewarded(context: Context) {
        // Ready for UnityAds.load(AdConfig.UNITY_REWARDED_PLACEMENT, ...)
        Log.d(TAG, "Loading rewarded ad...")
    }

    fun showRewardedAd(
        activity: Activity,
        onRewardGranted: () -> Unit
    ) {
        Log.d(TAG, "Showing rewarded ad...")
        // When Unity rewarded completes, grant reward:
        onRewardGranted()
    }
}
