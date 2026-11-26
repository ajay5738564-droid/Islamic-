package com.example.ads.unity

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize

@Composable
fun UnityBannerAd(reloadKey: Int = 0) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val activity = context as? Activity ?: return
    val banner = remember(activity, reloadKey) {
        val density = activity.resources.displayMetrics.density
        BannerView(
            activity,
            UnityAdsManager.BANNER_PLACEMENT,
            UnityBannerSize((320 * density).toInt(), (50 * density).toInt())
        )
    }

    DisposableEffect(banner, lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                UnityAdsManager.loadBannerWhenReady(banner)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        UnityAdsManager.loadBannerWhenReady(banner)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            banner.destroy()
        }
    }

    AndroidView(
        factory = { banner },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )
}
