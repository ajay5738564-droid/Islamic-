package com.example.ads.unity

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.unity3d.services.banners.BannerErrorInfo
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize

/**
 * Responsive, safe Jetpack Compose wrapper for Unity Banner Ads.
 *
 * Safety & Respect features:
 * - Only renders when ads are enabled & SDK is initialized.
 * - Handles offline / failure gracefully without occupying empty jarring space.
 * - Cleans up BannerView upon disposal (prevents memory leaks).
 * - Safe touch area with padding, strictly isolated from religious content.
 */
@Composable
fun UnityBannerAd(
    modifier: Modifier = Modifier,
    placementId: String = UnityAdsConfig.BANNER_PLACEMENT_ID
) {
    val isPreview = LocalInspectionMode.current
    if (isPreview || !UnityAdsConfig.ADS_ENABLED || !UnityAdsConfig.BANNER_ADS_ENABLED) {
        return
    }

    val context = LocalContext.current
    val activity = context as? Activity ?: return
    val isInitialized by UnityAdsManager.isInitialized.collectAsState()

    if (!isInitialized) {
        return
    }

    var isAdLoaded by remember { mutableStateOf(false) }
    var bannerViewRef by remember { mutableStateOf<BannerView?>(null) }

    DisposableEffect(placementId) {
        onDispose {
            try {
                bannerViewRef?.destroy()
                bannerViewRef = null
            } catch (e: Throwable) {
                Log.w("UnityBannerAd", "Error destroying banner view", e)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(vertical = if (isAdLoaded) 6.dp else 0.dp),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isAdLoaded) 50.dp else 0.dp),
            factory = { ctx ->
                val bannerView = BannerView(activity, placementId, UnityBannerSize(320, 50)).apply {
                    // Use software layer rendering to prevent Mesa GPU rendernode errors on emulators/headless containers
                    setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, null)
                }
                bannerViewRef = bannerView

                bannerView.listener = object : BannerView.IListener {
                    override fun onBannerLoaded(bannerAdView: BannerView?) {
                        isAdLoaded = true
                        Log.d("UnityBannerAd", "Banner loaded successfully: $placementId")
                    }

                    override fun onBannerShown(bannerAdView: BannerView?) {
                        Log.d("UnityBannerAd", "Banner shown: $placementId")
                    }

                    override fun onBannerFailedToLoad(
                        bannerAdView: BannerView?,
                        errorInfo: BannerErrorInfo?
                    ) {
                        isAdLoaded = false
                        Log.d("UnityBannerAd", "Banner failed to load: ${errorInfo?.errorMessage}")
                    }

                    override fun onBannerClick(bannerAdView: BannerView?) {
                        Log.d("UnityBannerAd", "Banner clicked: $placementId")
                    }

                    override fun onBannerLeftApplication(bannerAdView: BannerView?) {
                        Log.d("UnityBannerAd", "Banner left application: $placementId")
                    }
                }

                try {
                    val loadOptions = com.unity3d.ads.UnityAdsLoadOptions().apply {
                        objectId = java.util.UUID.randomUUID().toString()
                    }
                    bannerView.load(loadOptions)
                } catch (e: Throwable) {
                    Log.e("UnityBannerAd", "Error calling bannerView.load()", e)
                }

                bannerView
            }
        )
    }
}
