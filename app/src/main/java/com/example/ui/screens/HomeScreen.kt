package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.local.BookmarkEntity
import com.example.data.model.BookmarkType
import com.example.data.repository.DuaRepository
import com.example.data.repository.HadithRepository
import com.example.data.repository.HijriCalendarRepository
import com.example.data.repository.QuranRepository
import com.example.ui.ExploreSubScreen
import com.example.ui.MainViewModel
import com.example.ui.NavigationTab
import com.example.ui.components.DeenovaTopHeader
import com.example.ui.components.DuaCard
import com.example.ui.components.HadithCard
import com.example.ui.components.NextPrayerCountdownCard
import com.example.ui.components.QuranVerseCard
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onOpenLocationPicker: () -> Unit,
    onOpenGlobalSearch: () -> Unit,
    onOpenBookmarks: () -> Unit,
    modifier: Modifier = Modifier,
    onOpenLanguagePicker: () -> Unit = {}
) {
    val schedule by viewModel.dailyPrayerSchedule.collectAsState()
    val trackerState by viewModel.prayerTrackerState.collectAsState()
    val cityName by viewModel.cityName.collectAsState()
    val countryName by viewModel.countryName.collectAsState()
    val hijriAdjustment by viewModel.hijriAdjustment.collectAsState()
    val currentAyahPlaying by viewModel.currentPlayingAyah.collectAsState()
    val arabicFontSize by viewModel.arabicFontSize.collectAsState()
    val currentLanguage by viewModel.appLanguage.collectAsState()

    val now = Calendar.getInstance()
    val hijriInfo = HijriCalendarRepository.getHijriDate(now, hijriAdjustment)
    val gregorianFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault()).format(Date())

    // Daily featured content
    val featuredAyah = QuranRepository.CURATED_OFFLINE_AYAHS[1]?.firstOrNull() ?: QuranRepository.CURATED_OFFLINE_AYAHS[112]?.first()!!
    val featuredDua = DuaRepository.ALL_DUAS.find { it.id == "forg_1" } ?: DuaRepository.ALL_DUAS.first()
    val featuredHadith = HadithRepository.ALL_HADITHS.first()
    val upcomingEvents = HijriCalendarRepository.getMajorIslamicEvents().filter { it.isMajor }.take(2)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen_lazy_column"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Top Islamic Header
        item {
            DeenovaTopHeader(
                gregorianDate = gregorianFormat,
                hijriDate = if (currentLanguage == com.example.data.repository.AppLanguage.URDU) hijriInfo.formattedUrdu else hijriInfo.formatted,
                locationName = "$cityName, $countryName",
                onLocationClick = onOpenLocationPicker,
                onSearchClick = onOpenGlobalSearch,
                onBookmarksClick = onOpenBookmarks,
                onLanguageClick = onOpenLanguagePicker
            )
        }

        // Hero Banner / Decorative Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .testTag("home_hero_banner"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(modifier = Modifier.fillMaxWidth().height(120.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.islamic_hero_banner_1787233783996),
                        contentDescription = "Islamic Mosque Aesthetic",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                                        Color.Transparent
                                    )
                                )
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Column {
                            Text(
                                text = when (currentLanguage) {
                                    com.example.data.repository.AppLanguage.URDU -> "دین و دنیا کی رہنمائی"
                                    com.example.data.repository.AppLanguage.HINDI -> "दीन व दुनिया का मार्गदर्शन"
                                    else -> "Islamic Guidance & Lifestyle"
                                },
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = when (currentLanguage) {
                                    com.example.data.repository.AppLanguage.URDU -> "قرآن پاک، ۵ وقت کی نماز اور مسنون اذکار کے لیے آپ کا ہم سفر"
                                    com.example.data.repository.AppLanguage.HINDI -> "क़ुरआन, ५ वक़्त की नमाज़, अज़ान व मसनून दुआओं का साथी"
                                    else -> "Your complete companion for Quran, 5 Prayers & Azan"
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = GoldLight
                            )
                        }
                    }
                }
            }
        }

        // Next Prayer & Today's Schedule Card
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                NextPrayerCountdownCard(
                    schedule = schedule,
                    onViewTimetableClick = { viewModel.setTab(NavigationTab.PRAYER) }
                )
            }
        }

        // 5 Time Daily Namaz Tracker Card
        item {
            Spacer(modifier = Modifier.height(6.dp))
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                com.example.ui.components.DailyNamazTrackerCard(
                    trackerState = trackerState,
                    onTogglePrayer = { viewModel.togglePrayerTracked(it) },
                    onOpenFullGuide = {
                        viewModel.setTab(NavigationTab.EXPLORE)
                        viewModel.setExploreSubScreen(ExploreSubScreen.NAMAZ_GUIDE)
                    }
                )
            }
        }

        // Quick Access Features Grid
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = com.example.ui.util.AppStrings.quickFeatures(currentLanguage),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 6.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                QuickAccessButton(
                    icon = Icons.Default.Mosque,
                    label = when (currentLanguage) {
                        com.example.data.repository.AppLanguage.URDU -> "۵ وقت نماز"
                        com.example.data.repository.AppLanguage.HINDI -> "५ वक़्त नमाज़"
                        else -> "5 Prayers"
                    },
                    testTag = "quick_namaz_guide",
                    onClick = {
                        viewModel.setTab(NavigationTab.EXPLORE)
                        viewModel.setExploreSubScreen(ExploreSubScreen.NAMAZ_GUIDE)
                    }
                )
                QuickAccessButton(
                    icon = Icons.Default.Headphones,
                    label = when (currentLanguage) {
                        com.example.data.repository.AppLanguage.URDU -> "نعت و حمد"
                        com.example.data.repository.AppLanguage.HINDI -> "नात व हम्द"
                        else -> "Naat & Hamd"
                    },
                    testTag = "quick_naat_player",
                    onClick = {
                        viewModel.setTab(NavigationTab.EXPLORE)
                        viewModel.setExploreSubScreen(ExploreSubScreen.NAAT_PLAYER)
                    }
                )
                QuickAccessButton(
                    icon = Icons.Default.AutoAwesome,
                    label = when (currentLanguage) {
                        com.example.data.repository.AppLanguage.URDU -> "۹۹ نام"
                        com.example.data.repository.AppLanguage.HINDI -> "९९ नाम"
                        else -> "99 Names"
                    },
                    testTag = "quick_names_of_allah",
                    onClick = {
                        viewModel.setTab(NavigationTab.EXPLORE)
                        viewModel.setExploreSubScreen(ExploreSubScreen.NAMES_OF_ALLAH)
                    }
                )
                QuickAccessButton(
                    icon = Icons.Default.NotificationsActive,
                    label = when (currentLanguage) {
                        com.example.data.repository.AppLanguage.URDU -> "اذان الارم"
                        com.example.data.repository.AppLanguage.HINDI -> "अज़ान अलार्म"
                        else -> "Azan Alarm"
                    },
                    testTag = "quick_azan_alarms",
                    onClick = {
                        viewModel.setTab(NavigationTab.EXPLORE)
                        viewModel.setExploreSubScreen(ExploreSubScreen.AZAN_SETTINGS)
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                QuickAccessButton(
                    icon = Icons.Default.AutoStories,
                    label = when (currentLanguage) {
                        com.example.data.repository.AppLanguage.URDU -> "قرآن پاک"
                        com.example.data.repository.AppLanguage.HINDI -> "क़ुरआन पाक"
                        else -> "Quran"
                    },
                    testTag = "quick_quran",
                    onClick = { viewModel.setTab(NavigationTab.QURAN) }
                )
                QuickAccessButton(
                    icon = Icons.Default.CompassCalibration,
                    label = when (currentLanguage) {
                        com.example.data.repository.AppLanguage.URDU -> "قبلہ نما"
                        com.example.data.repository.AppLanguage.HINDI -> "क़िबला रुख"
                        else -> "Qibla"
                    },
                    testTag = "quick_qibla",
                    onClick = {
                        viewModel.setTab(NavigationTab.EXPLORE)
                        viewModel.setExploreSubScreen(ExploreSubScreen.QIBLA)
                    }
                )
                QuickAccessButton(
                    icon = Icons.Default.FilterVintage,
                    label = when (currentLanguage) {
                        com.example.data.repository.AppLanguage.URDU -> "تسبیح"
                        com.example.data.repository.AppLanguage.HINDI -> "तसबीह"
                        else -> "Tasbeeh"
                    },
                    testTag = "quick_tasbeeh",
                    onClick = {
                        viewModel.setTab(NavigationTab.EXPLORE)
                        viewModel.setExploreSubScreen(ExploreSubScreen.TASBEEH)
                    }
                )
                QuickAccessButton(
                    icon = Icons.Default.CalendarMonth,
                    label = when (currentLanguage) {
                        com.example.data.repository.AppLanguage.URDU -> "تقویم"
                        com.example.data.repository.AppLanguage.HINDI -> "कैलेंडर"
                        else -> "Calendar"
                    },
                    testTag = "quick_calendar",
                    onClick = {
                        viewModel.setTab(NavigationTab.EXPLORE)
                        viewModel.setExploreSubScreen(ExploreSubScreen.ISLAMIC_CALENDAR)
                    }
                )
            }
        }

        // Verse of the Day
        item {
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = com.example.ui.util.AppStrings.verseOfTheDay(currentLanguage),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                TextButton(onClick = { viewModel.setTab(NavigationTab.QURAN) }) {
                    Text(
                        text = when (currentLanguage) {
                            com.example.data.repository.AppLanguage.URDU -> "مکمل قرآن"
                            com.example.data.repository.AppLanguage.HINDI -> "पूरा क़ुरआन"
                            else -> "Full Quran"
                        },
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                QuranVerseCard(
                    ayah = featuredAyah,
                    surahName = "سورۃ الفاتحہ",
                    isPlaying = currentAyahPlaying?.number == featuredAyah.number,
                    isBookmarked = viewModel.isItemBookmarked("ayah_${featuredAyah.number}"),
                    arabicFontSizeSp = arabicFontSize,
                    onPlayAudioClick = { viewModel.playAyahAudio(featuredAyah) },
                    onBookmarkClick = {
                        viewModel.toggleBookmark(
                            BookmarkEntity(
                                id = "ayah_${featuredAyah.number}",
                                type = BookmarkType.AYAH.name,
                                title = "Surah Al-Fatihah : ${featuredAyah.numberInSurah}",
                                subtitle = featuredAyah.textEnglish,
                                arabicText = featuredAyah.textArabic,
                                reference = "Quran 1:${featuredAyah.numberInSurah}"
                            )
                        )
                    }
                )
            }
        }

        // Daily Supplication (Dua of the Day)
        item {
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = com.example.ui.util.AppStrings.duaOfTheDay(currentLanguage),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                TextButton(onClick = {
                    viewModel.setTab(NavigationTab.EXPLORE)
                    viewModel.setExploreSubScreen(ExploreSubScreen.DUA_LIST)
                }) {
                    Text(
                        text = when (currentLanguage) {
                            com.example.data.repository.AppLanguage.URDU -> "تمام دعائیں"
                            com.example.data.repository.AppLanguage.HINDI -> "सभी दुआएं"
                            else -> "All Duas"
                        },
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                DuaCard(
                    dua = featuredDua,
                    isBookmarked = viewModel.isItemBookmarked("dua_${featuredDua.id}"),
                    onBookmarkClick = {
                        viewModel.toggleBookmark(
                            BookmarkEntity(
                                id = "dua_${featuredDua.id}",
                                type = BookmarkType.DUA.name,
                                title = featuredDua.titleEnglish,
                                subtitle = featuredDua.englishTranslation,
                                arabicText = featuredDua.arabicText,
                                reference = featuredDua.reference
                            )
                        )
                    }
                )
            }
        }

        // Hadith of the Day
        item {
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = com.example.ui.util.AppStrings.hadithOfTheDay(currentLanguage),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                TextButton(onClick = {
                    viewModel.setTab(NavigationTab.EXPLORE)
                    viewModel.setExploreSubScreen(ExploreSubScreen.HADITH_LIST)
                }) {
                    Text(
                        text = when (currentLanguage) {
                            com.example.data.repository.AppLanguage.URDU -> "تمام احادیث"
                            com.example.data.repository.AppLanguage.HINDI -> "सभी हदीसें"
                            else -> "All Hadiths"
                        },
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                HadithCard(
                    hadith = featuredHadith,
                    isBookmarked = viewModel.isItemBookmarked("hadith_${featuredHadith.id}"),
                    onBookmarkClick = {
                        viewModel.toggleBookmark(
                            BookmarkEntity(
                                id = "hadith_${featuredHadith.id}",
                                type = BookmarkType.HADITH.name,
                                title = featuredHadith.topicEnglish,
                                subtitle = featuredHadith.englishTranslation,
                                arabicText = featuredHadith.arabicText,
                                reference = featuredHadith.reference
                            )
                        )
                    }
                )
            }
        }

        // Upcoming Islamic Events Banner
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = com.example.ui.util.AppStrings.upcomingEvents(currentLanguage),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 6.dp)
            )

            upcomingEvents.forEach { event ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Event,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = event.titleUrdu,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "${event.hijriDay} ${event.monthNameUrdu} (${event.estimatedDateGregorian})",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuickAccessButton(
    icon: ImageVector,
    label: String,
    testTag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp, horizontal = 6.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
            modifier = Modifier.size(54.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp
        )
    }
}
