package com.example.ads.unity

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize

@Composable
fun UnityBannerAd() {
    val context = LocalContext.current
    val activity = context as? Activity ?: return
    val banner = remember(activity) {
        val density = activity.resources.displayMetrics.density
        BannerView(
            activity,
            UnityAdsManager.BANNER_PLACEMENT,
            UnityBannerSize((320 * density).toInt(), (50 * density).toInt())
        )
    }

    DisposableEffect(banner) {
        banner.load()
        onDispose { banner.destroy() }
    }

    AndroidView(
        factory = { banner },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )
}
