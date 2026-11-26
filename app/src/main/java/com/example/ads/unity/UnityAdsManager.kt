package com.example.ads.unity

import android.app.Activity
import android.content.Context
import android.util.Log
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAdsShowOptions
import com.unity3d.services.banners.BannerView

/**
 * Direct Unity Ads integration for the Android app.
 * Production mode requests live inventory using the configured Unity Game ID and placements.
 * Ensure privacy consent and store disclosures are completed for all target regions.
 */
object UnityAdsManager {
    const val GAME_ID = "800365917"
    const val BANNER_PLACEMENT = "BP_Banner_Android"
    const val INTERSTITIAL_PLACEMENT = "BP_Interstitial_Android"
    const val REWARDED_PLACEMENT = "BP_Rewarded_Android"

    private const val TAG = "UnityAdsManager"
    private const val TEST_MODE = false

    @Volatile private var initialized = false
    private val pendingBannerLoads = mutableListOf<BannerView>()
    @Volatile private var interstitialReady = false
    @Volatile private var rewardedReady = false
    @Volatile private var interstitialLoading = false
    @Volatile private var rewardedLoading = false
    @Volatile private var lastInterstitialShownAt = 0L
    private const val INTERSTITIAL_COOLDOWN_MS = 10 * 60 * 1000L

    @Synchronized
    fun initialize(context: Context) {
        if (initialized || UnityAds.isInitialized) {
            initialized = true
            loadPendingBanners()
            preloadInterstitial()
            preloadRewarded()
            return
        }

        UnityAds.initialize(
            context.applicationContext,
            GAME_ID,
            TEST_MODE,
            object : IUnityAdsInitializationListener {
                override fun onInitializationComplete() {
                    initialized = true
                    Log.i(TAG, "Unity Ads initialized")
                    loadPendingBanners()
                    preloadInterstitial()
                    preloadRewarded()
                }

                override fun onInitializationFailed(
                    error: UnityAds.UnityAdsInitializationError,
                    message: String
                ) {
                    initialized = false
                    Log.e(TAG, "Initialization failed: $error - $message")
                }
            }
        )
    }

    @Synchronized
    fun loadBannerWhenReady(banner: BannerView) {
        if (initialized || UnityAds.isInitialized) {
            initialized = true
            banner.load()
        } else {
            if (!pendingBannerLoads.contains(banner)) pendingBannerLoads.add(banner)
            Log.i(TAG, "Banner queued until Unity Ads initialization completes")
        }
    }

    @Synchronized
    private fun loadPendingBanners() {
        val queued = pendingBannerLoads.toList()
        pendingBannerLoads.clear()
        queued.forEach { banner ->
            try {
                banner.load()
                Log.i(TAG, "Queued banner load requested")
            } catch (error: Exception) {
                Log.e(TAG, "Queued banner load failed", error)
            }
        }
    }

    @Synchronized
    fun preloadInterstitial() {
        if (!initialized || interstitialReady || interstitialLoading) return
        interstitialLoading = true
        UnityAds.load(INTERSTITIAL_PLACEMENT, object : IUnityAdsLoadListener {
            override fun onUnityAdsAdLoaded(placementId: String) {
                interstitialLoading = false
                interstitialReady = true
                Log.i(TAG, "Interstitial loaded")
            }

            override fun onUnityAdsFailedToLoad(
                placementId: String,
                error: UnityAds.UnityAdsLoadError,
                message: String
            ) {
                interstitialLoading = false
                interstitialReady = false
                Log.w(TAG, "Interstitial load failed: $error - $message")
            }
        })
    }

    fun showInterstitial(activity: Activity, onFinished: (() -> Unit)? = null) {
        val now = System.currentTimeMillis()
        if (now - lastInterstitialShownAt < INTERSTITIAL_COOLDOWN_MS) return
        if (!initialized || !interstitialReady) {
            preloadInterstitial()
            onFinished?.invoke()
            return
        }

        interstitialReady = false
        lastInterstitialShownAt = now
        UnityAds.show(activity, INTERSTITIAL_PLACEMENT, UnityAdsShowOptions(), object : IUnityAdsShowListener {
            override fun onUnityAdsShowFailure(
                placementId: String,
                error: UnityAds.UnityAdsShowError,
                message: String
            ) {
                Log.w(TAG, "Interstitial show failed: $error - $message")
                preloadInterstitial()
                onFinished?.invoke()
            }

            override fun onUnityAdsShowStart(placementId: String) = Unit
            override fun onUnityAdsShowClick(placementId: String) = Unit

            override fun onUnityAdsShowComplete(
                placementId: String,
                state: UnityAds.UnityAdsShowCompletionState
            ) {
                preloadInterstitial()
                onFinished?.invoke()
            }
        })
    }

    @Synchronized
    fun preloadRewarded() {
        if (!initialized || rewardedReady || rewardedLoading) return
        rewardedLoading = true
        UnityAds.load(REWARDED_PLACEMENT, object : IUnityAdsLoadListener {
            override fun onUnityAdsAdLoaded(placementId: String) {
                rewardedLoading = false
                rewardedReady = true
                Log.i(TAG, "Rewarded ad loaded")
            }

            override fun onUnityAdsFailedToLoad(
                placementId: String,
                error: UnityAds.UnityAdsLoadError,
                message: String
            ) {
                rewardedLoading = false
                rewardedReady = false
                Log.w(TAG, "Rewarded load failed: $error - $message")
            }
        })
    }

    fun showRewarded(
        activity: Activity,
        onRewardEarned: () -> Unit,
        onUnavailable: () -> Unit = {}
    ) {
        if (!initialized || !rewardedReady) {
            preloadRewarded()
            onUnavailable()
            return
        }

        rewardedReady = false
        var rewardGranted = false
        UnityAds.show(activity, REWARDED_PLACEMENT, UnityAdsShowOptions(), object : IUnityAdsShowListener {
            override fun onUnityAdsShowFailure(
                placementId: String,
                error: UnityAds.UnityAdsShowError,
                message: String
            ) {
                Log.w(TAG, "Rewarded show failed: $error - $message")
                preloadRewarded()
                onUnavailable()
            }

            override fun onUnityAdsShowStart(placementId: String) = Unit
            override fun onUnityAdsShowClick(placementId: String) = Unit

            override fun onUnityAdsShowComplete(
                placementId: String,
                state: UnityAds.UnityAdsShowCompletionState
            ) {
                if (state == UnityAds.UnityAdsShowCompletionState.COMPLETED && !rewardGranted) {
                    rewardGranted = true
                    onRewardEarned()
                }
                preloadRewarded()
            }
        })
    }
}
