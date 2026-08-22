package com.example.ui.components

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView
import com.example.data.ads.AdConfig
import com.example.data.ads.AdManager
import com.example.data.repository.AppLanguage
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

/**
 * Real Google AdMob Banner Composable.
 * Uses ONLY the real AdMob Banner Ad Unit ID: ca-app-pub-6680282505070613/5489378788.
 */
@Composable
fun AdMobBannerView(
    screenContext: String,
    modifier: Modifier = Modifier,
    currentLanguage: AppLanguage = AppLanguage.ENGLISH
) {
    val isPreview = LocalInspectionMode.current
    val config by AdManager.adConfig.collectAsState()

    val canShow = remember(screenContext, config) {
        AdManager.canShowAdInContext(screenContext)
    }

    if (!canShow || !config.bannerAdsEnabled || isPreview) return

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .testTag("admob_banner_$screenContext"),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            factory = { ctx: Context ->
                val frameLayout = FrameLayout(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                }

                val adView = AdView(ctx).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = AdConfig.BANNER_AD_UNIT_ID
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        android.view.Gravity.CENTER
                    )

                    adListener = object : AdListener() {
                        override fun onAdLoaded() {
                            Log.d("AdMobBanner", "Real AdMob banner loaded successfully for unit: $adUnitId")
                        }

                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            Log.w(
                                "AdMobBanner",
                                "AdMob banner request failed (${loadAdError.code}: ${loadAdError.message})."
                            )
                        }

                        override fun onAdOpened() {
                            Log.d("AdMobBanner", "Ad opened by user.")
                        }

                        override fun onAdClicked() {
                            Log.d("AdMobBanner", "Ad clicked.")
                        }
                    }

                    // Request real AdMob Ad
                    loadAd(AdRequest.Builder().build())
                }

                frameLayout.addView(adView)
                frameLayout
            }
        )
    }
}



