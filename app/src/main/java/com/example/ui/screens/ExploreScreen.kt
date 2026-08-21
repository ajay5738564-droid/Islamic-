package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.BookmarkEntity
import com.example.data.model.BookmarkType
import com.example.data.model.KnowledgeArticle
import com.example.data.model.TasbeehPreset
import com.example.data.model.ZakatInput
import com.example.data.repository.DuaRepository
import com.example.data.repository.HadithRepository
import com.example.data.repository.HijriCalendarRepository
import com.example.data.repository.IslamicKnowledgeRepository
import com.example.data.repository.TasbeehRepository
import com.example.data.repository.ZakatRepository
import com.example.ui.ExploreSubScreen
import com.example.ui.MainViewModel
import com.example.ui.components.DuaCard
import com.example.ui.components.HadithCard
import com.example.ui.theme.ArabicTextStyle
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight
import java.util.Calendar
import java.util.Locale
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ExploreScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val subScreen by viewModel.exploreSubScreen.collectAsState()

    when (subScreen) {
        ExploreSubScreen.MAIN_MENU -> ExploreMainMenu(viewModel = viewModel, modifier = modifier)
        ExploreSubScreen.NAMAZ_GUIDE -> NamazGuideScreen(viewModel = viewModel, modifier = modifier)
        ExploreSubScreen.NAAT_PLAYER -> NaatPlayerScreen(viewModel = viewModel, onBack = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }, modifier = modifier)
        ExploreSubScreen.NAMES_OF_ALLAH -> NamesOfAllahScreen(onBack = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }, modifier = modifier)
        ExploreSubScreen.AZAN_SETTINGS -> AzanSettingsScreen(viewModel = viewModel, onBack = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }, modifier = modifier)
        ExploreSubScreen.DUA_LIST -> DuaExplorerView(viewModel = viewModel, modifier = modifier)
        ExploreSubScreen.HADITH_LIST -> HadithExplorerView(viewModel = viewModel, modifier = modifier)
        ExploreSubScreen.TASBEEH -> DigitalTasbeehView(viewModel = viewModel, modifier = modifier)
        ExploreSubScreen.QIBLA -> QiblaCompassView(viewModel = viewModel, modifier = modifier)
        ExploreSubScreen.ISLAMIC_CALENDAR -> IslamicCalendarView(viewModel = viewModel, modifier = modifier)
        ExploreSubScreen.RAMADAN_DASHBOARD -> RamadanDashboardView(viewModel = viewModel, modifier = modifier)
        ExploreSubScreen.HAJJ_UMRAH_GUIDE -> HajjUmrahGuideView(viewModel = viewModel, modifier = modifier)
        ExploreSubScreen.ZAKAT_CALCULATOR -> ZakatCalculatorView(viewModel = viewModel, modifier = modifier)
        ExploreSubScreen.ISLAMIC_KNOWLEDGE -> IslamicKnowledgeView(viewModel = viewModel, modifier = modifier)
        ExploreSubScreen.SOURCES -> SourcesScreen(onBack = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }, currentLanguage = viewModel.appLanguage.collectAsState().value, modifier = modifier)
    }
}

@Composable
fun ExploreMainMenu(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val activity = context as? android.app.Activity

    fun navigateWithAd(screen: ExploreSubScreen) {
        if (activity != null) {
            com.example.data.ads.AdManager.showInterstitialIfAppropriate(
                activity = activity,
                contextTag = "explore_${screen.name.lowercase()}"
            )
        }
        viewModel.setExploreSubScreen(screen)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("explore_main_menu"),
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
                        text = "اسلامی معلومات و خدمات",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "۵ وقت کی نماز، دعائیں، احادیث، تسبیح، قبلہ، زکوٰۃ اور اسلامی تقویم",
                        style = MaterialTheme.typography.bodySmall,
                        color = GoldLight
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "اہم عبادات و خدمات",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
            )
        }

        // Feature Cards
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Naat & Hamd Player Card
                ExploreFeatureCard(
                    title = "نعت و حمد شریف (آڈیو و کلام)",
                    subtitle = "مشہور نعتیں، حمدِ باری تعالیٰ، درود و سلام مع مکمل اشعار اور پلئیر",
                    icon = Icons.Default.Headphones,
                    testTag = "explore_item_naat_player",
                    onClick = { navigateWithAd(ExploreSubScreen.NAAT_PLAYER) }
                )

                // 99 Names of Allah Card
                ExploreFeatureCard(
                    title = "اَسْمَاءُ الْحُسْنَىٰ (اللہ کے ۹۹ مبارک نام)",
                    subtitle = "اللہ تعالیٰ کے صفاتی نام مع اردو معانی، انگریزی تلفظ اور فضائل و برکات",
                    icon = Icons.Default.AutoAwesome,
                    testTag = "explore_item_names_of_allah",
                    onClick = { navigateWithAd(ExploreSubScreen.NAMES_OF_ALLAH) }
                )

                // Azan & Alarm Settings Card
                ExploreFeatureCard(
                    title = "اذان و نماز الارم اور نوٹیفکیشن",
                    subtitle = "۵ وقت کی اذان الرٹس، مکہ و مدینہ کی اذان کی آوازیں اور ٹیسٹ الارم",
                    icon = Icons.Default.NotificationsActive,
                    testTag = "explore_item_azan_settings",
                    onClick = { navigateWithAd(ExploreSubScreen.AZAN_SETTINGS) }
                )

                // Top Prominent 5 Times Namaz Card
                ExploreFeatureCard(
                    title = "۵ وقت کی نماز اور مکمل طریقہ (صلاۃ)",
                    subtitle = "فجر، ظہر، عصر، مغرب اور عشاء کی رکعتیں، دعائیں اور مکمل طریقہ",
                    icon = Icons.Default.Mosque,
                    testTag = "explore_item_namaz_guide",
                    onClick = { navigateWithAd(ExploreSubScreen.NAMAZ_GUIDE) }
                )

                ExploreFeatureCard(
                    title = "مسنون دعائیں اور اذکار (أدعية)",
                    subtitle = "حصن المسلم سے روزمرہ زندگی کی مستند دعائیں اور فضائل",
                    icon = Icons.Default.Favorite,
                    testTag = "explore_item_duas",
                    onClick = { navigateWithAd(ExploreSubScreen.DUA_LIST) }
                )

                ExploreFeatureCard(
                    title = "احادیثِ نبوی ﷺ (الحديث الشريف)",
                    subtitle = "اربعین نووی اور صحیح بخاری کی منتخب صحیح احادیث",
                    icon = Icons.Default.MenuBook,
                    testTag = "explore_item_hadith",
                    onClick = { navigateWithAd(ExploreSubScreen.HADITH_LIST) }
                )

                ExploreFeatureCard(
                    title = "ڈیجیٹل تسبیح و اذکار کاؤنٹر",
                    subtitle = "تسبیحاتِ فاطمی، وائبریشن اور روزانہ اذکار کا مکمل ریکارڈ",
                    icon = Icons.Default.FilterVintage,
                    testTag = "explore_item_tasbeeh",
                    onClick = { navigateWithAd(ExploreSubScreen.TASBEEH) }
                )

                ExploreFeatureCard(
                    title = "قبلہ نما قطب نما (اتجاہ القبلة)",
                    subtitle = "سینسرز کے ذریعے خانہ کعبہ (مکہ مکرمہ) کی سمت کی درست رہنمائی",
                    icon = Icons.Default.CompassCalibration,
                    testTag = "explore_item_qibla",
                    onClick = { navigateWithAd(ExploreSubScreen.QIBLA) }
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "اسلامی تقویم، ارکان و معلومات",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
            )
        }

        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ExploreFeatureCard(
                    title = "اسلامی کیلنڈر و تاریخی ایام (التقويم الهجري)",
                    subtitle = "قمری مہینے، ہجری تاریخ اور اہم اسلامی تاریخی واقعات",
                    icon = Icons.Default.CalendarMonth,
                    testTag = "explore_item_calendar",
                    onClick = { navigateWithAd(ExploreSubScreen.ISLAMIC_CALENDAR) }
                )

                ExploreFeatureCard(
                    title = "رمضان المبارک اور روزے کے احکام",
                    subtitle = "سحر و افطار، روزے کی دعائیں، تراویح اور شبِ قدر کے اعمال",
                    icon = Icons.Default.Nightlight,
                    testTag = "explore_item_ramadan",
                    onClick = { navigateWithAd(ExploreSubScreen.RAMADAN_DASHBOARD) }
                )

                ExploreFeatureCard(
                    title = "حج و عمرہ گائیڈ (قدم بہ قدم)",
                    subtitle = "احرام، طواف، سعی، وقوفِ عرفات اور مناسکِ حج کا مکمل طریقہ",
                    icon = Icons.Default.Mosque,
                    testTag = "explore_item_hajj",
                    onClick = { navigateWithAd(ExploreSubScreen.HAJJ_UMRAH_GUIDE) }
                )

                ExploreFeatureCard(
                    title = "زکوٰۃ کیلکولیٹر (حاسبة الزكاة)",
                    subtitle = "سونے، چاندی، نقدی اور مالِ تجارت پر ۲.۵ فیصد زکوٰۃ کا حساب",
                    icon = Icons.Default.Calculate,
                    testTag = "explore_item_zakat",
                    onClick = { navigateWithAd(ExploreSubScreen.ZAKAT_CALCULATOR) }
                )

                ExploreFeatureCard(
                    title = "اسلامی معلومات و بنیادی ارکان (علمِ دین)",
                    subtitle = "اسلام کے ۵ ارکان، ۶ ایمانیات، وضو اور طہارت کے مسائل",
                    icon = Icons.Default.School,
                    testTag = "explore_item_knowledge",
                    onClick = { navigateWithAd(ExploreSubScreen.ISLAMIC_KNOWLEDGE) }
                )

                ExploreFeatureCard(
                    title = "مآخذ اور معتبر حوالہ جات (Sources & References)",
                    subtitle = "قرآن کریم، احادیث، مسنون دعائیں اور فلکیاتی حسابات کے ماخذات",
                    icon = Icons.Default.MenuBook,
                    testTag = "explore_item_sources",
                    onClick = { viewModel.setExploreSubScreen(ExploreSubScreen.SOURCES) }
                )
            }
        }

        // Respectful Non-Intrusive AdMob Banner
        item {
            Spacer(modifier = Modifier.height(14.dp))
            com.example.ui.components.AdMobBannerView(
                screenContext = "explore_bottom",
                currentLanguage = viewModel.appLanguage.collectAsState().value
            )
        }
    }
}

@Composable
fun ExploreFeatureCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    testTag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag(testTag),
        shape = RoundedCornerShape(16.dp),
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
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
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

// 1. DUA EXPLORER VIEW
@Composable
fun DuaExplorerView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val selectedCategory by viewModel.selectedDuaCategory.collectAsState()
    val searchQuery by viewModel.duaSearchQuery.collectAsState()

    val filteredDuas = remember(selectedCategory, searchQuery) {
        DuaRepository.ALL_DUAS.filter { dua ->
            val matchCategory = selectedCategory == null || dua.categoryId == selectedCategory
            val matchSearch = searchQuery.isBlank() ||
                    dua.titleEnglish.contains(searchQuery, ignoreCase = true) ||
                    dua.englishTranslation.contains(searchQuery, ignoreCase = true) ||
                    dua.hindiTranslation.contains(searchQuery, ignoreCase = true)
            matchCategory && matchSearch
        }
    }

    Column(modifier = modifier.fillMaxSize().testTag("dua_explorer_view")) {
        Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 4.dp) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                    Text(
                        text = "Duas & Supplications (الأدعية)",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.setDuaSearch(it) },
                    modifier = Modifier.fillMaxWidth().testTag("dua_search_input"),
                    placeholder = { Text("Search Duas (e.g. morning, forgiveness, food)...", color = Color.White.copy(alpha = 0.7f)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.White) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setDuaSearch("") }) {
                                Icon(Icons.Default.Close, contentDescription = null, tint = Color.White)
                            }
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = GoldAccent,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.4f),
                        focusedContainerColor = Color.White.copy(alpha = 0.15f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.1f)
                    )
                )
            }
        }

        // Category Chips
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { viewModel.selectDuaCategory(null) },
                    label = { Text("All Duas") }
                )
            }
            items(DuaRepository.CATEGORIES) { cat ->
                FilterChip(
                    selected = selectedCategory == cat.id,
                    onClick = { viewModel.selectDuaCategory(cat.id) },
                    label = { Text(cat.nameEnglish) }
                )
            }
        }

        // Duas List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredDuas, key = { it.id }) { dua ->
                val isBookmarked = viewModel.isItemBookmarked("dua_${dua.id}")
                DuaCard(
                    dua = dua,
                    isBookmarked = isBookmarked,
                    onBookmarkClick = {
                        viewModel.toggleBookmark(
                            BookmarkEntity(
                                id = "dua_${dua.id}",
                                type = BookmarkType.DUA.name,
                                title = dua.titleEnglish,
                                subtitle = dua.englishTranslation,
                                arabicText = dua.arabicText,
                                reference = dua.reference
                            )
                        )
                    }
                )
            }

            item { Spacer(modifier = Modifier.height(90.dp)) }
        }
    }
}

// 2. HADITH EXPLORER VIEW
@Composable
fun HadithExplorerView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().testTag("hadith_explorer_view")) {
        Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 4.dp) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Text(
                    text = "Authentic Hadiths (الحديث)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(HadithRepository.ALL_HADITHS, key = { it.id }) { hadith ->
                val isBookmarked = viewModel.isItemBookmarked("hadith_${hadith.id}")
                HadithCard(
                    hadith = hadith,
                    isBookmarked = isBookmarked,
                    onBookmarkClick = {
                        viewModel.toggleBookmark(
                            BookmarkEntity(
                                id = "hadith_${hadith.id}",
                                type = BookmarkType.HADITH.name,
                                title = hadith.topicEnglish,
                                subtitle = hadith.englishTranslation,
                                arabicText = hadith.arabicText,
                                reference = hadith.reference
                            )
                        )
                    }
                )
            }

            item { Spacer(modifier = Modifier.height(90.dp)) }
        }
    }
}

// 3. DIGITAL TASBEEH VIEW
@Composable
fun DigitalTasbeehView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val count by viewModel.tasbeehCount.collectAsState()
    val target by viewModel.tasbeehTarget.collectAsState()
    val selectedPreset by viewModel.selectedPreset.collectAsState()
    val customText by viewModel.customZikrText.collectAsState()
    val history by viewModel.tasbeehHistory.collectAsState()
    val vibrateEnabled by viewModel.tasbeehVibrate.collectAsState()

    var showHistoryDialog by remember { mutableStateOf(false) }

    val progress = if (target > 0) (count.toFloat() / target).coerceIn(0f, 1f) else 0f

    LazyColumn(
        modifier = modifier.fillMaxSize().testTag("digital_tasbeeh_view"),
        contentPadding = PaddingValues(bottom = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        item {
            Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 4.dp) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                        }
                        Text(
                            text = "Digital Tasbeeh (المسبحة)",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    IconButton(
                        onClick = { showHistoryDialog = true },
                        modifier = Modifier.testTag("tasbeeh_history_btn")
                    ) {
                        Icon(imageVector = Icons.Default.History, contentDescription = "History", tint = Color.White)
                    }
                }
            }
        }

        // Zikr Presets Horizontal Row
        item {
            Spacer(modifier = Modifier.height(14.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(TasbeehRepository.PRESET_ZIKRS) { preset ->
                    FilterChip(
                        selected = selectedPreset.id == preset.id && customText.isBlank(),
                        onClick = { viewModel.selectTasbeehPreset(preset) },
                        label = { Text(preset.transliteration) }
                    )
                }
            }
        }

        // Active Zikr Display Card
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (customText.isNotBlank()) {
                        Text(
                            text = customText,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Text(
                            text = selectedPreset.arabicText,
                            style = ArabicTextStyle.copy(fontSize = 28.sp),
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = selectedPreset.transliteration,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = selectedPreset.englishMeaning,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Large Interactive Counter Bead / Circle Button
        item {
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(240.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.85f)
                            )
                        )
                    )
                    .clickable { viewModel.incrementTasbeeh() }
                    .testTag("tasbeeh_big_tap_button"),
                contentAlignment = Alignment.Center
            ) {
                // Circular Progress Ring
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(220.dp),
                    color = GoldAccent,
                    trackColor = Color.White.copy(alpha = 0.2f),
                    strokeWidth = 8.dp
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "$count",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Target: $target",
                        style = MaterialTheme.typography.titleSmall,
                        color = GoldLight
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "TAP HERE",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.8f),
                        letterSpacing = 1.sp
                    )
                }
            }
        }

        // Target Controls & Reset Row
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { viewModel.resetTasbeeh() },
                    modifier = Modifier.testTag("tasbeeh_reset_btn"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Reset")
                }

                // Target Selector Buttons
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf(33, 99, 100).forEach { tgt ->
                        FilledTonalButton(
                            onClick = { viewModel.setTasbeehTarget(tgt) },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = if (target == tgt) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = if (target == tgt) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        ) {
                            Text("$tgt")
                        }
                    }
                }
            }
        }
    }

    if (showHistoryDialog) {
        AlertDialog(
            onDismissRequest = { showHistoryDialog = false },
            title = { Text("Dhikr History & Logs") },
            text = {
                if (history.isEmpty()) {
                    Text("No completed tasbeeh sessions yet. Complete a target to log history automatically!")
                } else {
                    LazyColumn(modifier = Modifier.heightIn(max = 300.dp)) {
                        items(history) { session ->
                            ListItem(
                                headlineContent = { Text(session.zikrTitle, fontWeight = FontWeight.Bold) },
                                supportingContent = { Text("Count: ${session.count} • ${session.dateFormatted}") }
                            )
                            HorizontalDivider()
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showHistoryDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}

// 4. QIBLA COMPASS VIEW
@Composable
fun QiblaCompassView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val qiblaBearing by viewModel.qiblaBearing.collectAsState()
    val distanceKm by viewModel.distanceToKaabaKm.collectAsState()
    val azimuth by viewModel.sensorManager.azimuthFlow.collectAsState()
    val isAligned by viewModel.isAlignedWithQibla.collectAsState()
    val cityName by viewModel.cityName.collectAsState()

    val animatedAzimuth by animateFloatAsState(targetValue = -azimuth, label = "compass_rotation")

    Column(
        modifier = modifier.fillMaxSize().testTag("qibla_compass_view"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 4.dp) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Text(
                    text = "Qibla Direction (القبلة)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "$cityName → Makkah al-Mukarramah",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Qibla Angle: ${String.format(Locale.US, "%.1f°", qiblaBearing)} • Distance: ${String.format(Locale.US, "%,.0f km", distanceKm)}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Live Alignment Alert Banner
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (isAligned) EmeraldPrimary else MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = if (isAligned) Icons.Default.CheckCircle else Icons.Default.Navigation,
                    contentDescription = null,
                    tint = if (isAligned) Color.White else MaterialTheme.colorScheme.primary
                )
                Text(
                    text = if (isAligned) "ALIGNED WITH QIBLA! (Kaaba)" else "Rotate device until needle points to Kaaba",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isAligned) Color.White else MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Interactive Compass Canvas
        Box(
            modifier = Modifier
                .size(280.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .testTag("compass_dial_box"),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize().rotate(animatedAzimuth)) {
                val center = Offset(size.width / 2, size.height / 2)
                val radius = size.width / 2 - 20.dp.toPx()

                // Draw Outer Dial Ring
                drawCircle(
                    color = Color.Gray.copy(alpha = 0.3f),
                    radius = radius,
                    style = Stroke(width = 4.dp.toPx())
                )

                // Draw Cardinal Direction markers (N, E, S, W)
                val cardinals = listOf("N" to 0, "E" to 90, "S" to 180, "W" to 270)
                cardinals.forEach { (label, angleDeg) ->
                    val angleRad = Math.toRadians((angleDeg - 90).toDouble())
                    val markerX = center.x + (radius - 18.dp.toPx()) * cos(angleRad).toFloat()
                    val markerY = center.y + (radius - 18.dp.toPx()) * sin(angleRad).toFloat()
                    drawCircle(
                        color = if (label == "N") Color.Red else Color.Gray,
                        radius = 4.dp.toPx(),
                        center = Offset(markerX, markerY)
                    )
                }

                // Draw Qibla Pointer Needle towards Makkah angle
                val qiblaAngleRad = Math.toRadians((qiblaBearing - 90).toDouble())
                val tipX = center.x + (radius - 10.dp.toPx()) * cos(qiblaAngleRad).toFloat()
                val tipY = center.y + (radius - 10.dp.toPx()) * sin(qiblaAngleRad).toFloat()

                // Draw Gold Arrow Line to Qibla
                drawLine(
                    color = GoldAccent,
                    start = center,
                    end = Offset(tipX, tipY),
                    strokeWidth = 6.dp.toPx()
                )

                // Draw Kaaba indicator point at tip
                drawCircle(
                    color = EmeraldPrimary,
                    radius = 12.dp.toPx(),
                    center = Offset(tipX, tipY)
                )
            }

            // Center Kaaba Emblem
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(54.dp),
                shadowElevation = 4.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Mosque,
                        contentDescription = "Kaaba",
                        tint = GoldLight,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Device Heading: ${String.format(Locale.US, "%.0f°", azimuth)}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// 5. ISLAMIC CALENDAR VIEW
@Composable
fun IslamicCalendarView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val events = HijriCalendarRepository.getMajorIslamicEvents()
    val now = Calendar.getInstance()
    val hijriInfo = HijriCalendarRepository.getHijriDate(now)

    Column(modifier = modifier.fillMaxSize().testTag("islamic_calendar_view")) {
        Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 4.dp) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "واپس", tint = Color.White)
                }
                Text(
                    text = "اسلامی کیلنڈر (التقويم الهجري)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "آج کی اسلامی و ہجری تاریخ",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = hijriInfo.formattedUrdu,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = hijriInfo.formattedArabic,
                            style = ArabicTextStyle.copy(fontSize = 20.sp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "اہم اسلامی ایام و تاریخی واقعات",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            items(events, key = { it.id }) { event ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
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
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = event.titleUrdu,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = event.title,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                            ) {
                                Text(
                                    text = "${event.hijriDay} ${event.monthNameUrdu}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = event.descriptionUrdu,
                            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(90.dp)) }
        }
    }
}

// 6. RAMADAN DASHBOARD VIEW
@Composable
fun RamadanDashboardView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val schedule by viewModel.dailyPrayerSchedule.collectAsState()

    Column(modifier = modifier.fillMaxSize().testTag("ramadan_dashboard_view")) {
        Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 4.dp) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Text(
                    text = "Ramadan Mubarak (رمضان المبارك)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Fasting Timetable Today Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "Today's Fasting Times (Imsak & Iftar)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Sehri Ends (Fajr)", style = MaterialTheme.typography.bodySmall)
                                Text(
                                    text = schedule?.fajr?.timeFormatted ?: "04:30 AM",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Iftar (Maghrib)", style = MaterialTheme.typography.bodySmall)
                                Text(
                                    text = schedule?.maghrib?.timeFormatted ?: "06:45 PM",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }

            // Duas for Fasting
            item {
                Text(
                    text = "Essential Ramadan Duas",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            items(DuaRepository.ALL_DUAS.filter { it.categoryId == "ramadan" }) { dua ->
                val isBookmarked = viewModel.isItemBookmarked("dua_${dua.id}")
                DuaCard(dua = dua, isBookmarked = isBookmarked, onBookmarkClick = {})
            }

            item { Spacer(modifier = Modifier.height(90.dp)) }
        }
    }
}

// 7. HAJJ & UMRAH GUIDE VIEW
@Composable
fun HajjUmrahGuideView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val rites = listOf(
        "1. Ihram & Niyyah" to "Enter state of purity at Miqat, wear two white unstitched cloths (men) or modest dress (women), and recite Talbiyah.",
        "2. Tawaf (7 Circuits)" to "Perform 7 counter-clockwise circumambulations around the Holy Kaaba starting at the Black Stone (Hajar al-Aswad).",
        "3. Maqam Ibrahim & Zamzam" to "Pray 2 Rak'ahs behind Maqam Ibrahim and drink blessed Zamzam water with sincere supplication.",
        "4. Sa'i between Safa & Marwah" to "Walk 7 laps between Mount Safa and Mount Marwah honoring the devotion of Lady Hajar (AS).",
        "5. Halq or Taqsir" to "Shave (men) or trim one inch of hair (men/women) to complete the Umrah rites.",
        "6. Day of Arafah (Hajj Peak)" to "Stand on the plains of Mount Arafat in heartfelt repentance and dua from midday until sunset (9th Dhu al-Hijjah).",
        "7. Muzdalifah & Mina (Jamarat)" to "Stay overnight at Muzdalifah, collect pebbles, and cast them at the Jamarat pillars in Mina symbolizing rejection of evil."
    )

    Column(modifier = modifier.fillMaxSize().testTag("hajj_umrah_guide_view")) {
        Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 4.dp) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Text(
                    text = "Hajj & Umrah Guide (الحج والعمرة)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Talbiyah Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "لَبَّيْكَ اللَّهُمَّ لَبَّيْكَ، لَبَّيْكَ لَا شَرِيكَ لَكَ لَبَّيْكَ",
                            style = ArabicTextStyle.copy(fontSize = 20.sp),
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "\"Here I am, O Allah, here I am. Here I am, You have no partner, here I am.\"",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            items(rites) { (title, description) ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(90.dp)) }
        }
    }
}

// 8. ZAKAT CALCULATOR VIEW
@Composable
fun ZakatCalculatorView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val zakatInput by viewModel.zakatInput.collectAsState()
    val zakatResult by viewModel.zakatResult.collectAsState()

    Column(modifier = modifier.fillMaxSize().testTag("zakat_calculator_view")) {
        Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 4.dp) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Text(
                    text = "Zakat Calculator (حاسبة الزكاة)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Result Summary Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (zakatResult.meetsNisab) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = if (zakatResult.meetsNisab) "Zakat is Obligatory (2.5%)" else "Wealth is below Nisab Threshold",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Payable Zakat: ${String.format(Locale.US, "$%,.2f", zakatResult.zakatPayable)}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Net Qualifying Wealth: ${String.format(Locale.US, "$%,.2f", zakatResult.netZakatEligibleWealth)} (Nisab: ${String.format(Locale.US, "$%,.2f", zakatResult.nisabValueSilver)})",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Enter Assets & Wealth",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Input Fields
            item {
                OutlinedTextField(
                    value = if (zakatInput.cashInHand == 0.0) "" else zakatInput.cashInHand.toString(),
                    onValueChange = {
                        val num = it.toDoubleOrNull() ?: 0.0
                        viewModel.updateZakatInput(zakatInput.copy(cashInHand = num))
                    },
                    label = { Text("Cash in Hand & Bank Accounts ($)") },
                    modifier = Modifier.fillMaxWidth().testTag("zakat_cash_input"),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = if (zakatInput.goldGrams == 0.0) "" else zakatInput.goldGrams.toString(),
                    onValueChange = {
                        val num = it.toDoubleOrNull() ?: 0.0
                        viewModel.updateZakatInput(zakatInput.copy(goldGrams = num))
                    },
                    label = { Text("Gold Owned (Grams) - [Rate: $75/g]") },
                    modifier = Modifier.fillMaxWidth().testTag("zakat_gold_input"),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = if (zakatInput.silverGrams == 0.0) "" else zakatInput.silverGrams.toString(),
                    onValueChange = {
                        val num = it.toDoubleOrNull() ?: 0.0
                        viewModel.updateZakatInput(zakatInput.copy(silverGrams = num))
                    },
                    label = { Text("Silver Owned (Grams) - [Rate: $1/g]") },
                    modifier = Modifier.fillMaxWidth().testTag("zakat_silver_input"),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = if (zakatInput.businessInventoryValue == 0.0) "" else zakatInput.businessInventoryValue.toString(),
                    onValueChange = {
                        val num = it.toDoubleOrNull() ?: 0.0
                        viewModel.updateZakatInput(zakatInput.copy(businessInventoryValue = num))
                    },
                    label = { Text("Business Stock & Inventory Value ($)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = if (zakatInput.immediateDebts == 0.0) "" else zakatInput.immediateDebts.toString(),
                    onValueChange = {
                        val num = it.toDoubleOrNull() ?: 0.0
                        viewModel.updateZakatInput(zakatInput.copy(immediateDebts = num))
                    },
                    label = { Text("Deductible Immediate Debts & Bills ($)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(90.dp)) }
        }
    }
}

// 9. ISLAMIC KNOWLEDGE VIEW
@Composable
fun IslamicKnowledgeView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val articles = IslamicKnowledgeRepository.ALL_ARTICLES
    val selectedArticle by viewModel.selectedKnowledgeArticle.collectAsState()

    Column(modifier = modifier.fillMaxSize().testTag("islamic_knowledge_view")) {
        Surface(color = MaterialTheme.colorScheme.primary, tonalElevation = 4.dp) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (selectedArticle != null) {
                        viewModel.selectKnowledgeArticle(null)
                    } else {
                        viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU)
                    }
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Text(
                    text = selectedArticle?.title ?: "Islamic Knowledge (علوم إسلامية)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1
                )
            }
        }

        if (selectedArticle != null) {
            val article = selectedArticle!!
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Text(
                        text = article.summary,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                items(article.sections) { sec ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = sec.heading,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            if (sec.arabicQuote != null) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = sec.arabicQuote,
                                    style = ArabicTextStyle.copy(fontSize = 18.sp),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            if (sec.translationQuote != null) {
                                Text(
                                    text = "\"${sec.translationQuote}\"",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = sec.body,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "Source: ${article.reference}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(90.dp))
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(articles) { art ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.selectKnowledgeArticle(art) },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = art.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = art.summary,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 2
                                )
                            }
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(90.dp)) }
            }
        }
    }
}
