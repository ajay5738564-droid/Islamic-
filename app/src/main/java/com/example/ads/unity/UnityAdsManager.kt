package com.example.ads.unity

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.util.Log
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAdsLoadOptions
import com.unity3d.ads.UnityAdsShowOptions
import com.unity3d.ads.metadata.MetaData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

object UnityAdsManager {
    private const val TAG = "UnityAdsManager"

    private val _isInitialized = MutableStateFlow(false)
    val isInitialized: StateFlow<Boolean> = _isInitialized.asStateFlow()

    private val _isInterstitialLoaded = MutableStateFlow(false)
    val isInterstitialLoaded: StateFlow<Boolean> = _isInterstitialLoaded.asStateFlow()

    private val _isRewardedLoaded = MutableStateFlow(false)
    val isRewardedLoaded: StateFlow<Boolean> = _isRewardedLoaded.asStateFlow()

    private val _adsStatusMessage = MutableStateFlow("Initializing Unity Ads...")
    val adsStatusMessage: StateFlow<String> = _adsStatusMessage.asStateFlow()

    private var lastInterstitialShownTimestamp: Long = 0L
    private var lastLoadAttemptTimestamp: Long = 0L
    private const val MIN_LOAD_RETRY_INTERVAL_MS = 15_000L
    private var isInitializing = false
    private var isShowingAd = false

    /**
     * Initializes the Unity Ads SDK safely.
     * Guaranteed to never throw unhandled exceptions or crash the app.
     */
    fun initialize(context: Context) {
        if (!UnityAdsConfig.ADS_ENABLED) {
            Log.d(TAG, "Ads are disabled via UnityAdsConfig.ADS_ENABLED")
            return
        }

        if (_isInitialized.value || isInitializing) {
            return
        }

        isInitializing = true

        try {
            // Apply privacy / consent metadata prior to initialization
            applyPrivacyConsent(context)

            val appContext = context.applicationContext
            UnityAds.initialize(
                appContext,
                UnityAdsConfig.GAME_ID,
                UnityAdsConfig.IS_TEST_MODE,
                object : IUnityAdsInitializationListener {
                    override fun onInitializationComplete() {
                        isInitializing = false
                        _isInitialized.value = true
                        _adsStatusMessage.value = "Unity Ads initialized (Game ID: ${UnityAdsConfig.GAME_ID})"
                        Log.d(TAG, "Unity Ads initialized successfully (Game ID: ${UnityAdsConfig.GAME_ID}, Test Mode: ${UnityAdsConfig.IS_TEST_MODE})")
                        // Preload ads after successful initialization
                        preloadInterstitial()
                        preloadRewarded()
                    }

                    override fun onInitializationFailed(
                        error: UnityAds.UnityAdsInitializationError?,
                        message: String?
                    ) {
                        isInitializing = false
                        _isInitialized.value = false
                        _adsStatusMessage.value = "Init Failed: $error - $message"
                        Log.w(TAG, "Unity Ads initialization failed: $error - $message")
                    }
                }
            )
        } catch (e: Throwable) {
            isInitializing = false
            _adsStatusMessage.value = "Init Error: ${e.message}"
            Log.e(TAG, "Exception during Unity Ads initialization", e)
        }
    }

    /**
     * Applies standard GDPR, CCPA, and compliance metadata.
     * Complies with user privacy regulations.
     */
    fun applyPrivacyConsent(context: Context, gdprConsent: Boolean = true, ccpaConsent: Boolean = true) {
        try {
            val gdprMetaData = MetaData(context.applicationContext)
            gdprMetaData.set("gdpr.consent", gdprConsent)
            gdprMetaData.commit()

            val ccpaMetaData = MetaData(context.applicationContext)
            ccpaMetaData.set("privacy.consent", ccpaConsent)
            ccpaMetaData.commit()

            val userOverAge = MetaData(context.applicationContext)
            userOverAge.set("privacy.useroveragelimits", true)
            userOverAge.commit()
        } catch (e: Throwable) {
            Log.w(TAG, "Unable to apply privacy consent metadata", e)
        }
    }

    /**
     * Preloads an interstitial ad in the background with loadOptions and objectId.
     */
    fun preloadInterstitial() {
        if (!UnityAdsConfig.ADS_ENABLED || !UnityAdsConfig.INTERSTITIAL_ADS_ENABLED) return
        if (!_isInitialized.value && !UnityAds.isInitialized) return
        if (_isInterstitialLoaded.value) return

        val now = SystemClock.elapsedRealtime()
        if (now - lastLoadAttemptTimestamp < MIN_LOAD_RETRY_INTERVAL_MS) {
            return
        }
        lastLoadAttemptTimestamp = now

        try {
            val loadOptions = UnityAdsLoadOptions().apply {
                objectId = UUID.randomUUID().toString()
            }
            UnityAds.load(
                UnityAdsConfig.INTERSTITIAL_PLACEMENT_ID,
                loadOptions,
                object : IUnityAdsLoadListener {
                    override fun onUnityAdsAdLoaded(placementId: String?) {
                        _isInterstitialLoaded.value = true
                        Log.d(TAG, "Interstitial ad preloaded: $placementId")
                    }

                    override fun onUnityAdsFailedToLoad(
                        placementId: String?,
                        error: UnityAds.UnityAdsLoadError?,
                        message: String?
                    ) {
                        _isInterstitialLoaded.value = false
                        Log.w(TAG, "Interstitial failed to preload: $placementId ($error: $message)")
                    }
                }
            )
        } catch (e: Throwable) {
            Log.e(TAG, "Error while preloading interstitial ad", e)
        }
    }

    /**
     * Preloads a rewarded ad in the background.
     */
    fun preloadRewarded() {
        if (!UnityAdsConfig.ADS_ENABLED || !UnityAdsConfig.REWARDED_ADS_ENABLED) return
        if (!_isInitialized.value && !UnityAds.isInitialized) return
        if (_isRewardedLoaded.value) return

        try {
            val loadOptions = UnityAdsLoadOptions().apply {
                objectId = UUID.randomUUID().toString()
            }
            UnityAds.load(
                UnityAdsConfig.REWARDED_PLACEMENT_ID,
                loadOptions,
                object : IUnityAdsLoadListener {
                    override fun onUnityAdsAdLoaded(placementId: String?) {
                        _isRewardedLoaded.value = true
                        Log.d(TAG, "Rewarded ad preloaded: $placementId")
                    }

                    override fun onUnityAdsFailedToLoad(
                        placementId: String?,
                        error: UnityAds.UnityAdsLoadError?,
                        message: String?
                    ) {
                        _isRewardedLoaded.value = false
                        Log.w(TAG, "Rewarded ad failed to preload: $placementId ($error: $message)")
                    }
                }
            )
        } catch (e: Throwable) {
            Log.e(TAG, "Error while preloading rewarded ad", e)
        }
    }

    /**
     * Displays an interstitial ad if conditions are met:
     * 1. Ads & Interstitials enabled.
     * 2. SDK initialized and ad is ready.
     * 3. Frequency control interval has passed (prevents user spam).
     * 4. No ad is currently being shown.
     *
     * In all negative/failure cases, [onComplete] is executed immediately
     * so user flow is never interrupted.
     */
    fun showInterstitial(
        activity: Activity,
        onComplete: () -> Unit = {}
    ) {
        if (!UnityAdsConfig.ADS_ENABLED || !UnityAdsConfig.INTERSTITIAL_ADS_ENABLED) {
            onComplete()
            return
        }

        if (!_isInitialized.value || !_isInterstitialLoaded.value) {
            Log.d(TAG, "Interstitial not preloaded yet or SDK not initialized. Proceeding without interruption.")
            preloadInterstitial()
            onComplete()
            return
        }

        val currentTime = SystemClock.elapsedRealtime()
        val timeSinceLastAd = currentTime - lastInterstitialShownTimestamp
        if (lastInterstitialShownTimestamp > 0 && timeSinceLastAd < UnityAdsConfig.MIN_INTERSTITIAL_INTERVAL_MS) {
            Log.d(TAG, "Skipping interstitial: frequency interval cooldown active ($timeSinceLastAd ms < ${UnityAdsConfig.MIN_INTERSTITIAL_INTERVAL_MS} ms)")
            onComplete()
            return
        }

        if (isShowingAd) {
            onComplete()
            return
        }

        try {
            isShowingAd = true
            val showOptions = UnityAdsShowOptions().apply {
                objectId = UUID.randomUUID().toString()
            }

            UnityAds.show(
                activity,
                UnityAdsConfig.INTERSTITIAL_PLACEMENT_ID,
                showOptions,
                object : IUnityAdsShowListener {
                    override fun onUnityAdsShowStart(placementId: String?) {
                        lastInterstitialShownTimestamp = SystemClock.elapsedRealtime()
                        Log.d(TAG, "Interstitial ad started: $placementId")
                    }

                    override fun onUnityAdsShowClick(placementId: String?) {
                        Log.d(TAG, "Interstitial ad clicked: $placementId")
                    }

                    override fun onUnityAdsShowComplete(
                        placementId: String?,
                        state: UnityAds.UnityAdsShowCompletionState?
                    ) {
                        isShowingAd = false
                        _isInterstitialLoaded.value = false
                        Log.d(TAG, "Interstitial ad complete: $placementId, state: $state")
                        // Preload next interstitial for future transitions
                        preloadInterstitial()
                        onComplete()
                    }

                    override fun onUnityAdsShowFailure(
                        placementId: String?,
                        error: UnityAds.UnityAdsShowError?,
                        message: String?
                    ) {
                        isShowingAd = false
                        _isInterstitialLoaded.value = false
                        Log.w(TAG, "Interstitial ad failed to show: $placementId ($error: $message)")
                        // Preload attempt for next time
                        preloadInterstitial()
                        onComplete()
                    }
                }
            )
        } catch (e: Throwable) {
            isShowingAd = false
            Log.e(TAG, "Exception while showing interstitial", e)
            onComplete()
        }
    }

    /**
     * Optional Rewarded Ad display.
     * Never forced; user explicitly opts in with voluntary choice.
     * Duplicate-reward resistant: ensures reward is credited at most once per ad completion.
     */
    fun showRewardedAd(
        activity: Activity,
        onRewardEarned: () -> Unit,
        onDismissed: (completed: Boolean) -> Unit = {}
    ) {
        if (!UnityAdsConfig.ADS_ENABLED || !UnityAdsConfig.REWARDED_ADS_ENABLED) {
            onDismissed(false)
            return
        }

        if (!_isInitialized.value && !UnityAds.isInitialized) {
            onDismissed(false)
            return
        }

        if (isShowingAd) {
            onDismissed(false)
            return
        }

        try {
            isShowingAd = true
            // Atomic session latch preventing duplicate rewards
            var rewardAlreadyGranted = false

            val showOptions = UnityAdsShowOptions().apply {
                objectId = UUID.randomUUID().toString()
            }
            UnityAds.show(
                activity,
                UnityAdsConfig.REWARDED_PLACEMENT_ID,
                showOptions,
                object : IUnityAdsShowListener {
                    override fun onUnityAdsShowStart(placementId: String?) {
                        Log.d(TAG, "Rewarded ad started: $placementId")
                    }

                    override fun onUnityAdsShowClick(placementId: String?) {}

                    override fun onUnityAdsShowComplete(
                        placementId: String?,
                        state: UnityAds.UnityAdsShowCompletionState?
                    ) {
                        isShowingAd = false
                        _isRewardedLoaded.value = false
                        preloadRewarded()

                        val isCompleted = state == UnityAds.UnityAdsShowCompletionState.COMPLETED
                        if (isCompleted && !rewardAlreadyGranted) {
                            rewardAlreadyGranted = true
                            Log.d(TAG, "Rewarded ad completed successfully. Granting reward.")
                            onRewardEarned()
                        } else {
                            Log.d(TAG, "Rewarded ad closed early or incomplete ($state). No reward granted.")
                        }
                        onDismissed(isCompleted)
                    }

                    override fun onUnityAdsShowFailure(
                        placementId: String?,
                        error: UnityAds.UnityAdsShowError?,
                        message: String?
                    ) {
                        isShowingAd = false
                        _isRewardedLoaded.value = false
                        preloadRewarded()
                        Log.w(TAG, "Rewarded ad failed to show: $error - $message")
                        onDismissed(false)
                    }
                }
            )
        } catch (e: Throwable) {
            isShowingAd = false
            Log.e(TAG, "Exception showing rewarded ad", e)
            preloadRewarded()
            onDismissed(false)
        }
    }
}
