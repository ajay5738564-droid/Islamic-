package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.data.ads.AdConfig
import com.example.data.ads.AdManager
import com.example.data.repository.AppLanguage
import com.example.ui.theme.GoldAccent
import com.google.android.gms.ads.*

/**
 * Real Google AdMob Banner Composable.
 * Requests live AdMob banner ads using real Ad Unit ID (ca-app-pub-6680282505070613/5489378788).
 * When AdMob inventory is warming up or returns NO_FILL, seamlessly displays a live interactive sponsored ad banner.
 */
@Composable
fun AdMobBannerView(
    screenContext: String,
    modifier: Modifier = Modifier,
    currentLanguage: AppLanguage = AppLanguage.ENGLISH
) {
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current
    val config by AdManager.adConfig.collectAsState()
    var isAdMobLoaded by remember { mutableStateOf(false) }

    val canShow = remember(screenContext, config) {
        AdManager.canShowAdInContext(screenContext)
    }

    if (!canShow || !config.bannerAdsEnabled || isPreview) return

    val sponsoredAd = remember(screenContext) {
        AdManager.SPONSORED_NOTICES.random()
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .testTag("admob_banner_$screenContext"),
        contentAlignment = Alignment.Center
    ) {
        // Google AdMob Real AdView
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
                            isAdMobLoaded = true
                            Log.d("AdMobBanner", "Real AdMob banner loaded successfully for unit: $adUnitId")
                        }

                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            isAdMobLoaded = false
                            Log.w(
                                "AdMobBanner",
                                "AdMob banner request (${loadAdError.code}: ${loadAdError.message}). Retrying in 12s..."
                            )
                            postDelayed({
                                try {
                                    loadAd(AdRequest.Builder().build())
                                } catch (e: Exception) {
                                    Log.e("AdMobBanner", "Retry exception: ${e.message}")
                                }
                            }, 12000L)
                        }

                        override fun onAdOpened() {
                            Log.d("AdMobBanner", "Ad opened.")
                        }
                    }

                    // Request real AdMob Ad
                    loadAd(AdRequest.Builder().build())
                }

                frameLayout.addView(adView)
                frameLayout
            }
        )

        // Interactive Live Sponsored Banner when Google AdMob is loading or pending inventory fill
        if (!isAdMobLoaded) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .clickable {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sponsoredAd.destinationUrl))
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Log.e("AdMobBanner", "Click redirect: ${e.message}")
                        }
                    },
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                shape = RoundedCornerShape(0.dp),
                tonalElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Campaign,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(3.dp))
                                        .background(GoldAccent.copy(alpha = 0.9f))
                                        .padding(horizontal = 4.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = "Ad",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.Black,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Text(
                                    text = sponsoredAd.title,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            Text(
                                text = sponsoredAd.subtitle,
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 11.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    FilledTonalButton(
                        onClick = {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sponsoredAd.destinationUrl))
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Log.e("AdMobBanner", "Click redirect: ${e.message}")
                            }
                        },
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(
                            text = sponsoredAd.actionText,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            imageVector = Icons.Default.OpenInNew,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }
        }
    }
}


