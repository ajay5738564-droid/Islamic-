package com.example.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.data.ads.AdConfig
import com.example.data.ads.AdManager
import com.example.data.repository.AppLanguage
import com.example.ui.theme.GoldAccent
import com.google.android.gms.ads.*

/**
 * Responsive Google AdMob Banner Composable.
 *
 * Adheres strictly to Islamic reverence principles:
 * - Blocked completely in devotional/holy screens (Quran reading, Prayer, Qibla, Tasbeeh)
 * - Uses official test banner unit ID during development
 * - Automatically handles load failures gracefully without UI distortion or crash
 */
@Composable
fun AdMobBannerView(
    screenContext: String,
    modifier: Modifier = Modifier,
    currentLanguage: AppLanguage = AppLanguage.ENGLISH
) {
    val isPreview = LocalInspectionMode.current
    val config by AdManager.adConfig.collectAsState()
    var isAdLoaded by remember { mutableStateOf(false) }
    var adFailedToLoad by remember { mutableStateOf(false) }

    val canShow = remember(screenContext, config) {
        AdManager.canShowAdInContext(screenContext)
    }

    if (!canShow || !config.bannerAdsEnabled) return

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("admob_banner_$screenContext"),
        contentAlignment = Alignment.Center
    ) {
        if (!isPreview && !adFailedToLoad) {
            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { context: Context ->
                    AdView(context).apply {
                        setAdSize(AdSize.BANNER)
                        adUnitId = AdConfig.BANNER_AD_UNIT_ID
                        adListener = object : AdListener() {
                            override fun onAdLoaded() {
                                isAdLoaded = true
                                adFailedToLoad = false
                            }

                            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                isAdLoaded = false
                                adFailedToLoad = true
                            }
                        }
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
        }

        // Fallback display if ad is loading, in preview, or failed to load cleanly
        if (adFailedToLoad || !isAdLoaded) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(GoldAccent.copy(alpha = 0.2f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.URDU -> "اشتہار (Ad)"
                                    AppLanguage.HINDI -> "प्रायोजित (Ad)"
                                    AppLanguage.ARABIC -> "إعلان"
                                    else -> "AdMob Test Banner"
                                },
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Text(
                            text = "Islamic App Partner Community Notice",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
