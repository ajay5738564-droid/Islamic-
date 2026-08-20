package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.BookmarkEntity
import com.example.data.model.BookmarkType
import com.example.data.model.Surah
import com.example.data.repository.QuranRepository
import com.example.ui.MainViewModel
import com.example.ui.components.QuranVerseCard
import com.example.ui.theme.ArabicTextStyle
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuranScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val selectedSurah by viewModel.selectedSurah.collectAsState()
    val ayahs by viewModel.currentSurahAyahs.collectAsState()
    val isLoading by viewModel.isSurahLoading.collectAsState()
    val searchQuery by viewModel.quranSearchQuery.collectAsState()
    val currentPlayingAyah by viewModel.currentPlayingAyah.collectAsState()
    val arabicFontSize by viewModel.arabicFontSize.collectAsState()

    var selectedTab by remember { mutableStateOf(0) } // 0: Surah List, 1: Juz List

    if (selectedSurah != null) {
        // Detailed Surah Reading Screen
        val surah = selectedSurah!!
        Column(
            modifier = modifier
                .fillMaxSize()
                .testTag("surah_reading_view")
        ) {
            // Surah Top Bar
            Surface(
                color = MaterialTheme.colorScheme.primary,
                tonalElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { viewModel.clearSelectedSurah() },
                        modifier = Modifier.testTag("back_to_surah_list_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${surah.number}. ${surah.nameEnglish}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "${surah.revelationType} • ${surah.numberOfAyahs} Verses • Juz ${surah.startingJuz}",
                            style = MaterialTheme.typography.labelSmall,
                            color = GoldLight
                        )
                    }

                    // Arabic Name
                    Text(
                        text = surah.nameArabic,
                        style = ArabicTextStyle.copy(fontSize = 20.sp),
                        color = GoldLight,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Bismillah Header (except for Surah At-Tawbah #9)
                    if (surah.number != 9) {
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                                )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                                        style = ArabicTextStyle.copy(fontSize = 24.sp),
                                        color = MaterialTheme.colorScheme.primary,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }

                    items(ayahs, key = { it.number }) { ayah ->
                        val isPlaying = currentPlayingAyah?.number == ayah.number
                        val isBookmarked = viewModel.isItemBookmarked("ayah_${ayah.number}")

                        QuranVerseCard(
                            ayah = ayah,
                            surahName = surah.nameEnglish,
                            isPlaying = isPlaying,
                            isBookmarked = isBookmarked,
                            arabicFontSizeSp = arabicFontSize,
                            onPlayAudioClick = { viewModel.playAyahAudio(ayah) },
                            onBookmarkClick = {
                                viewModel.toggleBookmark(
                                    BookmarkEntity(
                                        id = "ayah_${ayah.number}",
                                        type = BookmarkType.AYAH.name,
                                        title = "Surah ${surah.nameEnglish} : ${ayah.numberInSurah}",
                                        subtitle = ayah.textEnglish,
                                        arabicText = ayah.textArabic,
                                        reference = "Quran ${surah.number}:${ayah.numberInSurah}"
                                    )
                                )
                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }
        }
    } else {
        // Surahs & Juz List View
        val filteredSurahs = remember(searchQuery) {
            if (searchQuery.isBlank()) {
                QuranRepository.ALL_114_SURAHS
            } else {
                QuranRepository.ALL_114_SURAHS.filter {
                    it.nameEnglish.contains(searchQuery, ignoreCase = true) ||
                            it.nameArabic.contains(searchQuery) ||
                            it.englishMeaning.contains(searchQuery, ignoreCase = true) ||
                            it.number.toString() == searchQuery.trim()
                }
            }
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .testTag("quran_list_view")
        ) {
            // Header Bar
            Surface(
                color = MaterialTheme.colorScheme.primary,
                tonalElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = "The Holy Quran (القرآن الكريم)",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "114 Surahs • 30 Juz • Uthmani Arabic Text & Translations",
                        style = MaterialTheme.typography.labelSmall,
                        color = GoldLight
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Search Field
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { viewModel.setQuranSearch(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quran_search_field"),
                        placeholder = { Text("Search Surah name or number...", color = Color.White.copy(alpha = 0.7f)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { viewModel.setQuranSearch("") }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Clear",
                                        tint = Color.White
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
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

            // Tab Selector (Surah / Juz)
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Surahs (114)", fontWeight = FontWeight.SemiBold) },
                    modifier = Modifier.testTag("tab_surahs")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Juz (30 Para)", fontWeight = FontWeight.SemiBold) },
                    modifier = Modifier.testTag("tab_juz")
                )
            }

            if (selectedTab == 0) {
                // Surahs List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredSurahs, key = { it.number }) { surah ->
                        SurahListItemCard(
                            surah = surah,
                            onClick = { viewModel.selectSurah(surah) }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            } else {
                // Juz List (30 Parts)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items((1..30).toList(), key = { it }) { juzNum ->
                        val startingSurah = QuranRepository.ALL_114_SURAHS.find { it.startingJuz == juzNum } ?: QuranRepository.ALL_114_SURAHS.first()
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.selectSurah(startingSurah) }
                                .testTag("juz_item_$juzNum"),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = MaterialTheme.colorScheme.primaryContainer,
                                        modifier = Modifier.size(42.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Text(
                                                text = "$juzNum",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    }

                                    Column {
                                        Text(
                                            text = "Juz $juzNum (Part $juzNum)",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "Starts with Surah ${startingSurah.nameEnglish}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Icon(
                                    imageVector = Icons.Default.ChevronRight,
                                    contentDescription = "Open Juz",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SurahListItemCard(
    surah: Surah,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("surah_item_${surah.number}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Surah Number Diamond/Square Box
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(42.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "${surah.number}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Column {
                    Text(
                        text = surah.nameEnglish,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${surah.englishMeaning} • ${surah.numberOfAyahs} verses",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Arabic Surah Title
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = surah.nameArabic,
                    style = ArabicTextStyle.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = surah.revelationType,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
