package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.AppLanguage
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SourcesScreen(
    onBack: () -> Unit,
    currentLanguage: AppLanguage = AppLanguage.ENGLISH,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.URDU -> "مآخذ اور معتبر حوالہ جات"
                            AppLanguage.HINDI -> "स्रोत व प्रमाणित संदर्भ"
                            AppLanguage.ARABIC -> "المصادر والمراجع المعتمدة"
                            else -> "Sources & Verified References"
                        },
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        modifier = modifier.testTag("sources_screen")
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldPrimary)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                            style = MaterialTheme.typography.titleMedium,
                            color = GoldLight,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.URDU -> "ہماری بنیادی ترجیح یہ ہے کہ تمام قرآنی نصوص، احادیث، مسنون دعائیں اور اوقاتِ نماز مستند اور تسلیم شدہ ماخذات سے حاصل کیے جائیں۔"
                                AppLanguage.HINDI -> "हमारी प्राथमिकता है कि कुरआन की आयतें, हदीसें, मसनून दुआएं और नमाज़ के औक़ात पूरी तरह प्रमाणित व विश्वसनीय स्रोतों से प्राप्त हों।"
                                AppLanguage.ARABIC -> "نلتزم بتقديم نصوص القرآن الكريم والأحاديث النبوية الشريفة والأدعية ومواقيت الصلاة من مصادر إسلامية موثوقة ومعتمدة."
                                else -> "We are committed to total integrity and authenticity. All Quranic verses, Hadith, Duas, and Prayer algorithms are sourced strictly from accredited Islamic institutions."
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }
            }

            // 1. Quran Text & Uthmanic Script
            item {
                SourceCategoryCard(
                    icon = Icons.Default.AutoStories,
                    title = "Quranic Text & Mushaf",
                    arabicTitle = "القرآن الكريم والرسم العثماني",
                    sources = listOf(
                        "Uthmani Script (Hafs 'an 'Asim) verified by Tanzil Project & King Fahd Glorious Quran Printing Complex (Madinah Munawwarah).",
                        "Digital recitation audio by Sheikh Mishary Rashid Alafasy, Sheikh Abdul Rahman Al-Sudais, and Qari Abdul Basit Abdul Samad via EveryAyah.com."
                    )
                )
            }

            // 2. Quran Translations
            item {
                SourceCategoryCard(
                    icon = Icons.Default.Language,
                    title = "Quran Translations",
                    arabicTitle = "ترجمات معاني القرآن الكريم",
                    sources = listOf(
                        "English: Sahih International (Al-Muntada Al-Islami Trust, London).",
                        "Urdu: Maulana Fateh Muhammad Jalandhari & Dr. Muhammad Tahir-ul-Qadri (Irfan-ul-Quran).",
                        "Hindi: Dr. Muhammad Farooq-i-Azam Malik & Maulana Azizul Haq Al-Umari (Authorized Deoband / Islamic Fiqh Academy)."
                    )
                )
            }

            // 3. Hadith Collections
            item {
                SourceCategoryCard(
                    icon = Icons.Default.Book,
                    title = "Hadith Literature",
                    arabicTitle = "كتب الحديث الشريف والسنة النبوية",
                    sources = listOf(
                        "Kutub al-Sittah: Sahih al-Bukhari (Imam Muhammad al-Bukhari), Sahih Muslim (Imam Muslim ibn al-Hajjaj), Sunan Abi Dawud, Jami` at-Tirmidhi, Sunan an-Nasa'i, Sunan Ibn Majah.",
                        "Riyad as-Salihin (The Gardens of the Righteous) by Imam Yahya ibn Sharaf an-Nawawi.",
                        "Verification & Hadith indexing cataloged via Sunnah.com."
                    )
                )
            }

            // 4. Duas & Azkar
            item {
                SourceCategoryCard(
                    icon = Icons.Default.Favorite,
                    title = "Duas & Daily Azkar",
                    arabicTitle = "الأدعية والأذكار المأثورة",
                    sources = listOf(
                        "Hisnul Muslim (حصن المسلم - Fortress of the Muslim from the Invocations of the Quran & Sunnah) compiled by Sheikh Sa'id bin Ali bin Wahf Al-Qahtani.",
                        "Authentic Morning & Evening Adhkar (Adhkar as-Sabah wal-Masa) sourced directly from verified Bukhari & Muslim narrations."
                    )
                )
            }

            // 5. Prayer Times & Astronomical Calculation
            item {
                SourceCategoryCard(
                    icon = Icons.Default.Schedule,
                    title = "Prayer Times & Astronomical Calculations",
                    arabicTitle = "حساب مواقيت الصلاة واتجاه القبلة",
                    sources = listOf(
                        "University of Islamic Sciences, Karachi (UISK) - Standard for Pakistan, India, Bangladesh.",
                        "Muslim World League (MWL) - Europe, Far East & Americas.",
                        "Islamic Society of North America (ISNA) - USA & Canada.",
                        "Umm Al-Qura University, Makkah Al-Mukarramah - Kingdom of Saudi Arabia.",
                        "Egyptian General Authority of Survey - Egypt & North Africa.",
                        "Qibla direction calculated via Great Circle Geodesic bearing to the Holy Kaaba (21.422487° N, 39.826206° E)."
                    )
                )
            }

            // 6. Educational Content & Islamic History
            item {
                SourceCategoryCard(
                    icon = Icons.Default.School,
                    title = "Islamic History & Educational Guidelines",
                    arabicTitle = "السيرة النبوية والتاريخ الإسلامي والفقه",
                    sources = listOf(
                        "Ar-Raheeq Al-Makhtum (The Sealed Nectar) by Safiur Rahman Mubarakpuri.",
                        "Qisas al-Anbiya (Stories of the Prophets) by Imam Ibn Kathir.",
                        "Fiqh us-Sunnah by Sayyid Sabiq & classical Hanafi/Shafi'i jurisprudential texts."
                    )
                )
            }
        }
    }
}

@Composable
fun SourceCategoryCard(
    icon: ImageVector,
    title: String,
    arabicTitle: String,
    sources: List<String>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = arabicTitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = GoldAccent,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
            Spacer(modifier = Modifier.height(10.dp))

            sources.forEach { source ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = EmeraldPrimary,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = source,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
