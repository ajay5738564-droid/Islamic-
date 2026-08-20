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
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val currentLanguage by viewModel.appLanguage.collectAsState()
    val calculationMethod by viewModel.prayerMethod.collectAsState()
    val asrMadhab by viewModel.asrMadhab.collectAsState()
    val cityName by viewModel.cityName.collectAsState()
    val countryName by viewModel.countryName.collectAsState()
    val hijriAdjustment by viewModel.hijriAdjustment.collectAsState()
    val arabicFontSize by viewModel.arabicFontSize.collectAsState()
    val tasbeehVibrate by viewModel.tasbeehVibrate.collectAsState()

    var showLanguageDialog by remember { mutableStateOf(false) }
    var showMethodDialog by remember { mutableStateOf(false) }
    var showMadhabDialog by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }

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
                        text = "ترتیبات اور ترجیحات (الإعدادات)",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "طریقہ حساب، زبان، فونٹ سائز اور ذاتی ترتیبات",
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
                text = "۵ وقت کی نماز اور عبادات",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
            )
        }

        item {
            SettingsItemCard(
                title = "۵ وقت کی نماز کا مکمل طریقہ اور رکعتیں",
                subtitle = "فجر، ظہر، عصر، مغرب اور عشاء کی رکعتیں، دعائیں اور وضو",
                icon = Icons.Default.Mosque,
                testTag = "setting_namaz_guide_item",
                onClick = {
                    viewModel.setTab(com.example.ui.NavigationTab.EXPLORE)
                    viewModel.setExploreSubScreen(com.example.ui.ExploreSubScreen.NAMAZ_GUIDE)
                }
            )
        }

        // Section: Location & Prayer Calculations
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "اوقاتِ نماز اور مقام کی ترتیبات",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
            )
        }

        item {
            SettingsItemCard(
                title = "منتخب کردہ شہر اور ملک",
                subtitle = "$cityName, $countryName",
                icon = Icons.Default.LocationOn,
                testTag = "setting_location_item",
                onClick = onOpenLocationPicker
            )
        }

        item {
            SettingsItemCard(
                title = "طریقہ حساب (Calculation Method)",
                subtitle = calculationMethod.displayName,
                icon = Icons.Default.Calculate,
                testTag = "setting_calc_method_item",
                onClick = { showMethodDialog = true }
            )
        }

        item {
            SettingsItemCard(
                title = "نمازِ عصر کا فقہی مسلک (Madhab)",
                subtitle = asrMadhab.displayName,
                icon = Icons.Default.Schedule,
                testTag = "setting_asr_madhab_item",
                onClick = { showMadhabDialog = true }
            )
        }

        item {
            SettingsItemCard(
                title = "ہجری تاریخ میں رد و بدل (Adjustment)",
                subtitle = if (hijriAdjustment == 0) "معیاری (0 دن)" else "$hijriAdjustment دن",
                icon = Icons.Default.CalendarMonth,
                testTag = "setting_hijri_adjust_item",
                onClick = {
                    val nextAdj = if (hijriAdjustment >= 2) -2 else hijriAdjustment + 1
                    coroutineScope.launch { viewModel.preferences.setHijriAdjustment(nextAdj) }
                }
            )
        }

        // Section: Language & Display
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "زبان اور قرآنی رسم الخط",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
            )
        }

        item {
            SettingsItemCard(
                title = "ایپ کی زبان (Language)",
                subtitle = "${currentLanguage.displayName} (${currentLanguage.nativeName})",
                icon = Icons.Default.Language,
                testTag = "setting_language_item",
                onClick = { showLanguageDialog = true }
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
                title = "Saved Bookmarks",
                subtitle = "View saved Quran verses, Duas & Hadiths",
                icon = Icons.Default.Bookmark,
                testTag = "setting_bookmarks_item",
                onClick = onOpenBookmarks
            )
        }

        // Section: About & Religious Disclaimer
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
                title = "About Deenova & Religious Accuracy",
                subtitle = "Verified Islamic sources, offline-first privacy & credits",
                icon = Icons.Default.Info,
                testTag = "setting_about_item",
                onClick = { showAboutDialog = true }
            )
        }
    }

    // Language Dialog
    if (showLanguageDialog) {
        AlertDialog(
            onDismissRequest = { showLanguageDialog = false },
            title = { Text("Select Application Language") },
            text = {
                Column {
                    AppLanguage.values().forEach { lang ->
                        ListItem(
                            headlineContent = { Text(lang.displayName, fontWeight = FontWeight.Bold) },
                            supportingContent = { Text(lang.nativeName) },
                            trailingContent = {
                                if (currentLanguage == lang) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                }
                            },
                            modifier = Modifier.clickable {
                                coroutineScope.launch { viewModel.preferences.setLanguage(lang) }
                                showLanguageDialog = false
                            }
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showLanguageDialog = false }) { Text("Cancel") }
            }
        )
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

    // About & Disclaimer Dialog
    if (showAboutDialog) {
        AlertDialog(
            onDismissRequest = { showAboutDialog = false },
            title = { Text("Deenova • دينوفا (v1.0)") },
            text = {
                Column(modifier = Modifier.heightIn(max = 380.dp)) {
                    Text(
                        text = "Authentic All-in-One Islamic Guide",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Deenova is designed to serve Muslims globally with accurate prayer calculations, authentic Quranic verses, graded Hadiths, and verified daily supplications (Hisn al-Muslim).\n\n" +
                                "• Religious Texts: Authentic Uthmani Quran, Sahih International English translations, 40 Hadith of Imam Nawawi, Sahih al-Bukhari.\n" +
                                "• Privacy: All user preferences, bookmarks, and Dhikr history are stored locally on-device.\n" +
                                "• Note on Prayer Times: Prayer times are calculated according to established astronomical algorithms. Verify with your local mosque if slight deviations occur.",
                        style = MaterialTheme.typography.bodySmall,
                        lineHeight = 18.sp
                    )
                }
            },
            confirmButton = { TextButton(onClick = { showAboutDialog = false }) { Text("Close") } }
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
