package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.NamazPrayerDetail
import com.example.data.model.NamazStepItem
import com.example.data.model.RakatBreakdownItem
import com.example.data.model.RakatType
import com.example.data.repository.NamazRepository
import com.example.ui.ExploreSubScreen
import com.example.ui.MainViewModel
import com.example.ui.components.DailyNamazTrackerCard
import com.example.ui.theme.ArabicTextStyle
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight

@Composable
fun NamazGuideScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val trackerState by viewModel.prayerTrackerState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = listOf("۵ نمازوں کی رکعتیں", "طریقہ نماز", "نماز ٹریکر", "وضو اور شرائط")

    Scaffold(
        topBar = {
            Surface(
                color = MaterialTheme.colorScheme.primary,
                tonalElevation = 4.dp
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU) },
                            modifier = Modifier.testTag("btn_namaz_guide_back")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "پیچھے",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Column {
                            Text(
                                text = "۵ وقت کی نماز کا مکمل طریقہ اور تفصیل",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "رکعتیں، شرائط، فرائض، دعائیں اور روزانہ ٹریکر",
                                style = MaterialTheme.typography.bodySmall,
                                color = GoldLight
                            )
                        }
                    }

                    // Tab Row
                    ScrollableTabRow(
                        selectedTabIndex = selectedTab,
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White,
                        edgePadding = 16.dp
                    ) {
                        tabTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTab == index,
                                onClick = { selectedTab = index },
                                text = {
                                    Text(
                                        text = title,
                                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                },
                                modifier = Modifier.testTag("namaz_tab_$index")
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> FivePrayersBreakdownTab(modifier = modifier.padding(innerPadding))
            1 -> StepByStepGuideTab(modifier = modifier.padding(innerPadding))
            2 -> NamazTrackerTab(
                trackerState = trackerState,
                onTogglePrayer = { viewModel.togglePrayerTracked(it) },
                modifier = modifier.padding(innerPadding)
            )
            3 -> WuduAndConditionsTab(modifier = modifier.padding(innerPadding))
        }
    }
}

// TAB 1: 5 Daily Prayers Breakdown
@Composable
fun FivePrayersBreakdownTab(modifier: Modifier = Modifier) {
    val prayers = NamazRepository.FIVE_DAILY_PRAYERS
    val jummah = NamazRepository.JUMMAH_PRAYER_DETAIL

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("five_prayers_breakdown_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = EmeraldPrimary.copy(alpha = 0.1f))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = EmeraldPrimary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "اسلام میں ہر عاقل و بالغ مسلمان پر روزانہ ۵ وقت کی نمازیں فرض ہیں۔ ذیل میں ہر نماز کی رکعتیں، اوقات اور فضائل ملاحظہ فرمائیں۔",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        items(prayers) { prayer ->
            PrayerDetailCard(prayer = prayer)
        }

        item {
            PrayerDetailCard(prayer = jummah, isJummah = true)
        }
    }
}

@Composable
fun PrayerDetailCard(
    prayer: NamazPrayerDetail,
    isJummah: Boolean = false,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable { expanded = !expanded }
            .testTag("prayer_detail_${prayer.prayerName.name}"),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isJummah) GoldAccent.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(if (isJummah) GoldAccent.copy(alpha = 0.2f) else EmeraldPrimary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${prayer.totalRakats}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isJummah) GoldAccent else EmeraldPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = prayer.nameUrdu,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = prayer.nameArabic,
                            style = ArabicTextStyle.copy(fontSize = 15.sp),
                            color = EmeraldDark
                        )
                    }
                }

                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "مزید معلومات"
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Total Rakats Summary Badge
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    text = "کل رکعتیں: ${prayer.breakdownUrdu}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            // Expanded Details
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(modifier = Modifier.padding(top = 14.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "رکعتوں کی تفصیلی تقسیم:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPrimary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    prayer.rakatsList.forEach { rakat ->
                        RakatRow(rakat = rakat)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "وقت:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPrimary
                    )
                    Text(
                        text = prayer.timingUrdu,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "اہمیت و فضیلت:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPrimary
                    )
                    Text(
                        text = prayer.hadithVirtueUrdu,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "حوالہ: ${prayer.hadithReference}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}

@Composable
fun RakatRow(rakat: RakatBreakdownItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = when (rakat.type) {
                    RakatType.FARZ -> EmeraldPrimary.copy(alpha = 0.15f)
                    RakatType.WITR -> GoldAccent.copy(alpha = 0.2f)
                    else -> MaterialTheme.colorScheme.surfaceVariant
                }
            ) {
                Text(
                    text = "${rakat.count} رکعت",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = when (rakat.type) {
                        RakatType.FARZ -> EmeraldPrimary
                        RakatType.WITR -> GoldAccent
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = rakat.type.labelUrdu,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        if (rakat.noteUrdu.isNotBlank()) {
            Text(
                text = rakat.noteUrdu,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// TAB 2: Step-by-Step Namaz Guide
@Composable
fun StepByStepGuideTab(modifier: Modifier = Modifier) {
    val steps = NamazRepository.STEP_BY_STEP_NAMAZ_GUIDE

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("step_by_step_namaz_guide_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "نماز پڑھنے کا مکمل مسنون طریقہ",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "نیت سے لے کر سلام تک تمام مراحل، عربی مسنون کلمات اور اردو ترجمے کے ساتھ۔",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }
            }
        }

        items(steps) { step ->
            NamazStepCard(step = step)
        }
    }
}

@Composable
fun NamazStepCard(step: NamazStepItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("namaz_step_${step.stepNumber}"),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Step Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(EmeraldPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${step.stepNumber}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = step.stepTitleUrdu,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Text(
                    text = step.stepTitleArabic,
                    style = ArabicTextStyle.copy(fontSize = 14.sp),
                    color = EmeraldDark
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Procedure Explanation
            Text(
                text = step.procedureUrdu,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp
            )

            // Arabic Text if available
            if (step.arabicText.isNotBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = EmeraldPrimary.copy(alpha = 0.08f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = step.arabicText,
                            style = ArabicTextStyle.copy(
                                fontSize = 20.sp,
                                lineHeight = 34.sp,
                                textAlign = TextAlign.Right
                            ),
                            color = EmeraldDark,
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (step.transliteration.isNotBlank()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = step.transliteration,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        if (step.translationUrdu.isNotBlank()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "ترجمہ: ${step.translationUrdu}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            if (step.notesUrdu.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "💡 نوٹ: ${step.notesUrdu}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

// TAB 3: Namaz Tracker Tab
@Composable
fun NamazTrackerTab(
    trackerState: com.example.data.model.DailyPrayerTrackerState,
    onTogglePrayer: (com.example.data.model.PrayerName) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("namaz_tracker_tab_view"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            DailyNamazTrackerCard(
                trackerState = trackerState,
                onTogglePrayer = onTogglePrayer
            )
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "نماز کی پابندی کی فضیلت",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "رسول اللہ ﷺ سے پوچھا گیا کہ کون سا عمل سب سے افضل ہے؟ آپ ﷺ نے فرمایا: 'نماز کو اس کے اول وقت میں ادا کرنا۔' (صحیح البخاری)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 22.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "روزانہ اپنی پانچوں نمازوں کو وقت پر ادا کریں اور اس ٹریکر میں نشان لگا کر اپنی استقامت برقرار رکھیں۔",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

// TAB 4: Wudu & Conditions
@Composable
fun WuduAndConditionsTab(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("wudu_and_conditions_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "وضو کا مسنون طریقہ (۹ مراحل)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPrimary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    NamazRepository.WUDU_STEPS_URDU.forEach { step ->
                        Text(
                            text = step,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 4.dp),
                            lineHeight = 22.sp
                        )
                    }
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "نماز کی شرائط اور ارکان (فرائض)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPrimary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    NamazRepository.NAMAZ_CONDITIONS_URDU.forEach { condition ->
                        if (condition.isBlank()) {
                            Spacer(modifier = Modifier.height(8.dp))
                        } else {
                            Text(
                                text = condition,
                                style = if (condition.endsWith(":")) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodyMedium,
                                fontWeight = if (condition.endsWith(":")) FontWeight.Bold else FontWeight.Normal,
                                color = if (condition.endsWith(":")) EmeraldDark else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
