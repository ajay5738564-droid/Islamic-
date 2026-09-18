package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AsrJuristic
import com.example.data.model.CalculationMethod
import com.example.data.repository.AppLanguage
import com.example.ui.MainViewModel
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    viewModel: MainViewModel,
    onOpenLocationPicker: () -> Unit,
    onOpenBookmarks: () -> Unit,
    modifier: Modifier = Modifier,
    onOpenLanguagePicker: () -> Unit = {}
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val currentLanguage by viewModel.appLanguage.collectAsState()
    val themeMode by viewModel.themeMode.collectAsState()
    val calculationMethod by viewModel.prayerMethod.collectAsState()
    val asrMadhab by viewModel.asrMadhab.collectAsState()
    val cityName by viewModel.cityName.collectAsState()
    val countryName by viewModel.countryName.collectAsState()
    val hijriAdjustment by viewModel.hijriAdjustment.collectAsState()
    val arabicFontSize by viewModel.arabicFontSize.collectAsState()
    val tasbeehVibrate by viewModel.tasbeehVibrate.collectAsState()
    val isSupporter by viewModel.isSupporter.collectAsState()
    val rewardPoints by viewModel.rewardPoints.collectAsState()

    var showMethodDialog by remember { mutableStateOf(false) }
    var showMadhabDialog by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }
    var showRewardPromptDialog by remember { mutableStateOf(false) }
    var showRewardResultDialog by remember { mutableStateOf(false) }
    var rewardResultMessage by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("settings_screen_lazy_column"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Header
        item {
            Surface(
                color = MaterialTheme.colorScheme.primary,
                tonalElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.URDU -> "ترتیبات اور ترجیحات (الإعدادات)"
                            AppLanguage.HINDI -> "सेटिंग्स व प्राथमिकताएं"
                            else -> "Settings & Preferences"
                        },
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.URDU -> "طریقہ حساب، زبان، فونٹ سائز اور ذاتی ترتیبات"
                            AppLanguage.HINDI -> "गणना विधि, भाषा, फ़ॉन्ट आकार व निजी सेटिंग्स"
                            else -> "Calculation methods, language, font size & custom preferences"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = GoldLight
                    )
                }
            }
        }

        // Section: 5 Times Namaz & Tracker
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = when (currentLanguage) {
                    AppLanguage.URDU -> "۵ وقت کی نماز اور اذان الارم"
                    AppLanguage.HINDI -> "५ वक़्त की नमाज़ व अज़ान अलार्म"
                    else -> "5 Daily Prayers & Azan Alarms"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
            )
        }

        item {
            SettingsItemCard(
                title = when (currentLanguage) {
                    AppLanguage.URDU -> "۵ وقت کی نماز کا مکمل طریقہ اور رکعتیں"
                    AppLanguage.HINDI -> "५ वक़्त की नमाज़ का पूरा तरीक़ा व रकअतें"
                    else -> "5 Daily Prayers Guide & Rak'ahs"
                },
                subtitle = when (currentLanguage) {
                    AppLanguage.URDU -> "فجر، ظہر، عصر، مغرب اور عشاء کی رکعتیں، دعائیں اور وضو"
                    AppLanguage.HINDI -> "फ़ज्र, ज़ुहर, असर, मग़रिब व इशा की रकअतें, दुआएं व वुज़ू"
                    else -> "Fajr, Dhuhr, Asr, Maghrib & Isha breakdown"
                },
                icon = Icons.Default.Mosque,
                testTag = "setting_namaz_guide_item",
                onClick = {
                    viewModel.setTab(com.example.ui.NavigationTab.EXPLORE)
                    viewModel.setExploreSubScreen(com.example.ui.ExploreSubScreen.NAMAZ_GUIDE)
                }
            )
        }

        item {
            SettingsItemCard(
                title = when (currentLanguage) {
                    AppLanguage.URDU -> "۵ وقت اذان و الارم نوٹیفکیشن ترتیبات"
                    AppLanguage.HINDI -> "५ वक़्त अज़ान व अलार्म सेटिंग्स"
                    else -> "5-Time Azan Alarm & Notification Settings"
                },
                subtitle = when (currentLanguage) {
                    AppLanguage.URDU -> "فجر، ظہر، عصر، مغرب اور عشاء پر اذان اور الارم"
                    AppLanguage.HINDI -> "सभी नमाज़ों पर अज़ान की आवाज़ व अलार्म"
                    else -> "Configure full Azan audio and alarms"
                },
                icon = Icons.Default.NotificationsActive,
                testTag = "setting_azan_alarms_item",
                onClick = {
                    viewModel.setTab(com.example.ui.NavigationTab.EXPLORE)
                    viewModel.setExploreSubScreen(com.example.ui.ExploreSubScreen.AZAN_SETTINGS)
                }
            )
        }

        // Section: Language & Display
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = when (currentLanguage) {
                    AppLanguage.URDU -> "زبان اور ظاہری وضع (Theme & Language)"
                    AppLanguage.HINDI -> "भाषा व थीम सेटिंग्स"
                    else -> "Language & Appearance"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
            )
        }

        item {
            SettingsItemCard(
                title = when (currentLanguage) {
                    AppLanguage.URDU -> "ایپ کی زبان (Application Language)"
                    AppLanguage.HINDI -> "ऐप की भाषा (Language)"
                    else -> "Application Language"
                },
                subtitle = "${currentLanguage.displayName} (${currentLanguage.nativeName}) - اردو • हिन्दी • English",
                icon = Icons.Default.Language,
                testTag = "setting_language_item",
                onClick = onOpenLanguagePicker
            )
        }

        item {
            SettingsItemCard(
                title = when (currentLanguage) {
                    AppLanguage.URDU -> "ڈارک موڈ / لائٹ موڈ (Theme Mode)"
                    AppLanguage.HINDI -> "डार्क मोड / लाइट मोड (Theme)"
                    else -> "Theme Mode (Dark / Light Mode)"
                },
                subtitle = when (themeMode) {
                    com.example.data.repository.ThemeMode.SYSTEM -> when (currentLanguage) {
                        AppLanguage.URDU -> "سسٹم ڈیفالٹ (خودکار)"
                        AppLanguage.HINDI -> "सिस्टम डिफ़ॉल्ट (ऑटो)"
                        else -> "System Default (Auto)"
                    }
                    com.example.data.repository.ThemeMode.LIGHT -> when (currentLanguage) {
                        AppLanguage.URDU -> "روشن تھیم (Light Mode)"
                        AppLanguage.HINDI -> "लाइट मोड (Light)"
                        else -> "Light Mode"
                    }
                    com.example.data.repository.ThemeMode.DARK -> when (currentLanguage) {
                        AppLanguage.URDU -> "تاریک تھیم (Dark Mode)"
                        AppLanguage.HINDI -> "डार्क मोड (Dark)"
                        else -> "Dark Mode"
                    }
                },
                icon = when (themeMode) {
                    com.example.data.repository.ThemeMode.DARK -> Icons.Default.DarkMode
                    com.example.data.repository.ThemeMode.LIGHT -> Icons.Default.LightMode
                    com.example.data.repository.ThemeMode.SYSTEM -> Icons.Default.BrightnessAuto
                },
                testTag = "setting_theme_mode_item",
                onClick = { showThemeDialog = true }
            )
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "قرآنی عربی فونٹ کا سائز",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "${arabicFontSize}sp",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Slider(
                        value = arabicFontSize.toFloat(),
                        onValueChange = { coroutineScope.launch { viewModel.preferences.setArabicFontSize(it.toInt()) } },
                        valueRange = 20f..38f,
                        steps = 8,
                        modifier = Modifier.testTag("arabic_font_slider")
                    )
                }
            }
        }

        // Section: Dhikr & Bookmarks
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "تسبیح اور ذاتی بک مارکس",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
            )
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "تسبیح وائبریشن (Haptic Feedback)",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "ہر دانے پر اور ۱۰۰ مکمل ہونے پر وائبریشن",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = tasbeehVibrate,
                        onCheckedChange = { coroutineScope.launch { viewModel.preferences.setTasbeehVibrate(it) } },
                        modifier = Modifier.testTag("tasbeeh_vibrate_switch")
                    )
                }
            }
        }

        item {
            SettingsItemCard(
                title = when (currentLanguage) {
                    AppLanguage.URDU -> "محفوظ شدہ بُک مارکس"
                    AppLanguage.HINDI -> "सहेजे गए बुकमार्क"
                    AppLanguage.ARABIC -> "العلامات المرجعية المحفوظة"
                    else -> "Saved Bookmarks"
                },
                subtitle = "View saved Quran verses, Duas & Hadiths",
                icon = Icons.Default.Bookmark,
                testTag = "setting_bookmarks_item",
                onClick = onOpenBookmarks
            )
        }

        // Section: Optional Support & Rewarded Ads
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = when (currentLanguage) {
                    AppLanguage.URDU -> "ایپ کی معاونت اور انعام"
                    AppLanguage.HINDI -> "ऐप सपोर्ट व रिवॉर्ड"
                    else -> "Support App & Earn Rewards"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
            )
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = if (isSupporter) Icons.Default.VolunteerActivism else Icons.Default.CardGiftcard,
                                    contentDescription = "Reward",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.URDU -> "معاونت کا انعام (Supporter Reward)"
                                    AppLanguage.HINDI -> "विज्ञापन देखकर Reward प्राप्त करें"
                                    else -> "Support App & Earn Reward"
                                },
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (isSupporter) {
                                    when (currentLanguage) {
                                        AppLanguage.URDU -> "فعال معاون بیج حاصل شدہ • $rewardPoints انعامی پوائنٹس"
                                        AppLanguage.HINDI -> "सक्रिय समर्थक बैज प्राप्त • $rewardPoints रिवॉर्ड अंक"
                                        else -> "Supporter Badge Unlocked • $rewardPoints Points"
                                    }
                                } else {
                                    when (currentLanguage) {
                                        AppLanguage.URDU -> "مفت اسلامی ایپ کو سپورٹ کرنے کے لیے ویڈیو دیکھیں"
                                        AppLanguage.HINDI -> "मुफ़्त ऐप को सपोर्ट करने हेतु वीडियो विज्ञापन देखें"
                                        else -> "Watch an optional short video ad to support our Islamic app"
                                    }
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Button(
                        onClick = { showRewardPromptDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("watch_rewarded_ad_button"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayCircleOutline,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.URDU -> "اشتہار دیکھ کر انعام حاصل کریں (+100)"
                                AppLanguage.HINDI -> "विज्ञापन देखकर Reward प्राप्त करें (+100)"
                                else -> "Watch Ad to Earn Reward (+100)"
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Unity Ads Diagnostics Info
                    val adsStatus by com.example.ads.unity.UnityAdsManager.adsStatusMessage.collectAsState()
                    val isAdsInit by com.example.ads.unity.UnityAdsManager.isInitialized.collectAsState()
                    val isInterstitialReady by com.example.ads.unity.UnityAdsManager.isInterstitialLoaded.collectAsState()
                    val isRewardedReady by com.example.ads.unity.UnityAdsManager.isRewardedLoaded.collectAsState()

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Ads Status: $adsStatus",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isAdsInit) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "SDK: ${if (isAdsInit) "Active" else "Connecting..."} • Interstitial: ${if (isInterstitialReady) "Ready" else "Preloading"} • Rewarded: ${if (isRewardedReady) "Ready" else "Preloading"}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Section: About & Guidelines
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "About & Guidelines",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
            )
        }

        item {
            SettingsItemCard(
                title = "Sources & Verified References",
                subtitle = "Tanzil, King Fahd Mushaf, Bukhari, Muslim, Hisnul Muslim",
                icon = Icons.Default.MenuBook,
                testTag = "setting_sources_item",
                onClick = {
                    viewModel.setTab(com.example.ui.NavigationTab.EXPLORE)
                    viewModel.setExploreSubScreen(com.example.ui.ExploreSubScreen.SOURCES)
                }
            )
        }

        item {
            SettingsItemCard(
                title = "About Islamic App & Authenticity",
                subtitle = "Verified Islamic sources, offline-first privacy & credits",
                icon = Icons.Default.Info,
                testTag = "setting_about_item",
                onClick = { showAboutDialog = true }
            )
        }

        // Unity Banner Ad
        item {
            com.example.ads.unity.UnityBannerAd()
        }
    }

    // Calculation Method Dialog
    if (showMethodDialog) {
        AlertDialog(
            onDismissRequest = { showMethodDialog = false },
            title = { Text("Prayer Calculation Method") },
            text = {
                LazyColumn(modifier = Modifier.heightIn(max = 350.dp)) {
                    items(CalculationMethod.values().toList()) { method ->
                        ListItem(
                            headlineContent = { Text(method.displayName, fontWeight = FontWeight.Bold) },
                            trailingContent = {
                                if (calculationMethod == method) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                }
                            },
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    viewModel.preferences.setCalculationMethod(method)
                                    viewModel.updatePrayerSchedule()
                                }
                                showMethodDialog = false
                            }
                        )
                        HorizontalDivider()
                    }
                }
            },
            confirmButton = { TextButton(onClick = { showMethodDialog = false }) { Text("Cancel") } }
        )
    }

    // Asr Madhab Dialog
    if (showMadhabDialog) {
        AlertDialog(
            onDismissRequest = { showMadhabDialog = false },
            title = { Text("Asr Juristic Method") },
            text = {
                Column {
                    AsrJuristic.values().forEach { madhab ->
                        ListItem(
                            headlineContent = { Text(madhab.displayName, fontWeight = FontWeight.Bold) },
                            trailingContent = {
                                if (asrMadhab == madhab) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                }
                            },
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    viewModel.preferences.setAsrMadhab(madhab)
                                    viewModel.updatePrayerSchedule()
                                }
                                showMadhabDialog = false
                            }
                        )
                    }
                }
            },
            confirmButton = { TextButton(onClick = { showMadhabDialog = false }) { Text("Cancel") } }
        )
    }

    // Theme Selection Dialog (Dark / Light Mode)
    if (showThemeDialog) {
        AlertDialog(
            onDismissRequest = { showThemeDialog = false },
            title = {
                Text(
                    when (currentLanguage) {
                        AppLanguage.URDU -> "تھیم کا انتخاب کریں (Appearance)"
                        AppLanguage.HINDI -> "थीम चुनें (Appearance)"
                        else -> "Select Theme Mode"
                    }
                )
            },
            text = {
                Column {
                    com.example.data.repository.ThemeMode.values().forEach { mode ->
                        ListItem(
                            headlineContent = {
                                Text(
                                    text = when (currentLanguage) {
                                        AppLanguage.URDU -> mode.titleUrdu
                                        AppLanguage.HINDI -> mode.titleHindi
                                        else -> mode.titleEnglish
                                    },
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            leadingContent = {
                                Icon(
                                    imageVector = when (mode) {
                                        com.example.data.repository.ThemeMode.DARK -> Icons.Default.DarkMode
                                        com.example.data.repository.ThemeMode.LIGHT -> Icons.Default.LightMode
                                        com.example.data.repository.ThemeMode.SYSTEM -> Icons.Default.BrightnessAuto
                                    },
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            trailingContent = {
                                if (themeMode == mode) {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            modifier = Modifier.clickable {
                                viewModel.setThemeMode(mode)
                                showThemeDialog = false
                            }
                        )
                        HorizontalDivider()
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showThemeDialog = false }) {
                    Text(
                        when (currentLanguage) {
                            AppLanguage.URDU -> "منسوخ کریں"
                            AppLanguage.HINDI -> "रद्द करें"
                            else -> "Cancel"
                        }
                    )
                }
            }
        )
    }

    // About & Disclaimer Dialog
    if (showAboutDialog) {
        AlertDialog(
            onDismissRequest = { showAboutDialog = false },
            title = { Text("Islamic App • التطبيق الإسلامي (v1.0)") },
            text = {
                Column(modifier = Modifier.heightIn(max = 380.dp)) {
                    Text(
                        text = "Authentic All-in-One Islamic Companion",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Islamic App is designed to serve Muslims worldwide with utmost reverence, verified Quranic texts, authentic graded Hadith, Hisnul Muslim supplications, Qibla direction, and accurate prayer schedules.\n\n" +
                                "• Religious Texts: Authentic Uthmani Quran, Sahih International English, Maulana Jalandhari Urdu, and Farooq-i-Azam Malik Hindi translations.\n" +
                                "• Privacy: All user preferences, bookmarks, and Dhikr history are stored locally on-device. No personal data is sold or exploited.\n" +
                                "• Respectful Ads Policy: Religious devotion screens (Quran, Prayer, Azan, Qibla, Tasbeeh, Duas) are 100% ad-free.\n" +
                                "• Note on Prayer Times: Prayer times are calculated according to established astronomical algorithms. Verify with your local mosque if slight deviations occur.",
                        style = MaterialTheme.typography.bodySmall,
                        lineHeight = 18.sp
                    )
                }
            },
            confirmButton = { TextButton(onClick = { showAboutDialog = false }) { Text("Close") } }
        )
    }

    // Rewarded Ad Confirmation Dialog
    if (showRewardPromptDialog) {
        val activity = context as? android.app.Activity
        AlertDialog(
            onDismissRequest = { showRewardPromptDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.CardGiftcard,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            title = {
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.URDU -> "اشتہار دیکھ کر انعام حاصل کریں"
                        AppLanguage.HINDI -> "विज्ञापन देखकर Reward प्राप्त करें"
                        else -> "Watch Ad to Earn Reward"
                    },
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.URDU -> "کیا آپ ایک مختصر ویڈیو اشتہار دیکھ کر ایپ کو سپورٹ کرنا اور 100 انعامی پوائنٹس حاصل کرنا چاہتے ہیں؟"
                        AppLanguage.HINDI -> "क्या आप एक छोटा विज्ञापन देखकर ऐप को सपोर्ट करना और 100 रिवॉर्ड अंक प्राप्त करना चाहते हैं?"
                        else -> "Would you like to watch a short sponsored video ad to support our free Islamic app and earn 100 supporter points?"
                    }
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showRewardPromptDialog = false
                        if (activity != null) {
                            com.example.ads.unity.UnityAdsManager.showRewardedAd(
                                activity = activity,
                                onRewardEarned = {
                                    viewModel.claimAdReward(100)
                                    rewardResultMessage = when (currentLanguage) {
                                        AppLanguage.URDU -> "ماشاءاللہ! آپ کا انعام کامیابی سے شامل کر دیا گیا۔ 100 پوائنٹس اور معاون بیج فعال ہو گیا۔"
                                        AppLanguage.HINDI -> "माशाअल्लाह! आपका रिवॉर्ड सफलतापूर्वक अनलॉक हो गया। 100 अंक व समर्थक बैज प्राप्त हुआ।"
                                        else -> "Masha'Allah! Your reward was granted successfully. 100 points and supporter status unlocked."
                                    }
                                    showRewardResultDialog = true
                                },
                                onDismissed = { completed ->
                                    if (!completed) {
                                        rewardResultMessage = when (currentLanguage) {
                                            AppLanguage.URDU -> "اشتہار مکمل نہیں ہوا، انعام شامل نہیں کیا گیا۔"
                                            AppLanguage.HINDI -> "विज्ञापन पूरा नहीं हुआ, इसलिए रिवॉर्ड नहीं मिला।"
                                            else -> "Ad was not completed, no reward was granted."
                                        }
                                        showRewardResultDialog = true
                                    }
                                }
                            )
                        }
                    }
                ) {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.URDU -> "اشتہار دیکھیں"
                            AppLanguage.HINDI -> "विज्ञापन देखें"
                            else -> "Watch Ad"
                        }
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showRewardPromptDialog = false }) {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.URDU -> "منسوخ"
                            AppLanguage.HINDI -> "रद्द करें"
                            else -> "Cancel"
                        }
                    )
                }
            }
        )
    }

    // Rewarded Ad Outcome Dialog
    if (showRewardResultDialog) {
        AlertDialog(
            onDismissRequest = { showRewardResultDialog = false },
            title = {
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.URDU -> "انعام کی صورتحال"
                        AppLanguage.HINDI -> "रिवॉर्ड स्थिति"
                        else -> "Reward Status"
                    },
                    fontWeight = FontWeight.Bold
                )
            },
            text = { Text(rewardResultMessage) },
            confirmButton = {
                TextButton(onClick = { showRewardResultDialog = false }) {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.URDU -> "ٹھیک ہے"
                            AppLanguage.HINDI -> "ठीक है"
                            else -> "OK"
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun SettingsItemCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    testTag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable(onClick = onClick)
            .testTag(testTag),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
