package com.example.data.ads

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Respectful Islamic Advertising Architecture
 *
 * Section 20 of Islamic App Specification:
 * - Central configuration for all ad units
 * - Strict religious content protection:
 *   - NEVER show ads during Quran verses reading or between pages
 *   - NEVER show ads during prayer times / Azan alerts
 *   - NEVER show ads during Qibla compass usage
 *   - NEVER show ads inside Tasbeeh digital counter
 *   - NEVER show ads during Dua or Hadith recitations
 *   - NEVER show ads during audio recitation
 * - Ads are shown only in non-religious utility areas (e.g. Explore bottom)
 * - Always clearly labeled as "Ad" / "Sponsored" / "اشتہار" / "विज्ञापन"
 * - Inappropriate / offensive / non-halal ad blocking enabled
 */
data class AdPlacementConfig(
    val adsEnabled: Boolean = true,
    val testMode: Boolean = true,
    val bannerAdsEnabled: Boolean = true,
    val nativeAdsEnabled: Boolean = true,
    val interstitialAdsEnabled: Boolean = false, // Disabled by default for maximum reverence
    val allowPersonalizedAds: Boolean = false,
    val adBlockedCategories: List<String> = listOf(
        "Gambling", "Adult", "Alcohol", "Dating", "Misleading", "Interest/Riba loans"
    )
)

data class IslamicAdContent(
    val id: String,
    val title: String,
    val subtitle: String,
    val actionText: String,
    val tag: String = "Sponsored",
    val destinationUrl: String = "https://deenova.app"
)

object AdManager {

    private val _adConfig = MutableStateFlow(AdPlacementConfig())
    val adConfig: StateFlow<AdPlacementConfig> = _adConfig.asStateFlow()

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

    fun toggleAds(enabled: Boolean) {
        _adConfig.value = _adConfig.value.copy(adsEnabled = enabled)
    }

    fun canShowAdInContext(screenContext: String): Boolean {
        if (!_adConfig.value.adsEnabled) return false

        // STRICT POLICY: Strict blacklist of religious devotion contexts
        val forbiddenContexts = setOf(
            "quran_reader",
            "quran_audio",
            "prayer_times",
            "azan_alarm",
            "qibla_compass",
            "tasbeeh_counter",
            "dua_reading",
            "hadith_reading",
            "namaz_guide_steps"
        )

        return screenContext !in forbiddenContexts
    }
}
