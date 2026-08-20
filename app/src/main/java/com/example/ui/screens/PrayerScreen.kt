package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DailyPrayerSchedule
import com.example.data.model.PrayerName
import com.example.data.model.PrayerTimeSlot
import com.example.data.repository.HijriCalendarRepository
import com.example.ui.ExploreSubScreen
import com.example.ui.MainViewModel
import com.example.ui.NavigationTab
import com.example.ui.components.DailyNamazTrackerCard
import com.example.ui.components.NextPrayerCountdownCard
import com.example.ui.theme.ArabicTextStyle
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun PrayerScreen(
    viewModel: MainViewModel,
    onOpenLocationPicker: () -> Unit,
    modifier: Modifier = Modifier
) {
    val schedule by viewModel.dailyPrayerSchedule.collectAsState()
    val trackerState by viewModel.prayerTrackerState.collectAsState()
    val cityName by viewModel.cityName.collectAsState()
    val countryName by viewModel.countryName.collectAsState()
    val prayerMethod by viewModel.prayerMethod.collectAsState()
    val asrMadhab by viewModel.asrMadhab.collectAsState()
    val qiblaBearing by viewModel.qiblaBearing.collectAsState()
    val distanceKm by viewModel.distanceToKaabaKm.collectAsState()

    val now = Calendar.getInstance()
    val hijriInfo = HijriCalendarRepository.getHijriDate(now)
    val dateStr = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault()).format(Date())

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("prayer_screen_lazy_column"),
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "اوقاتِ نماز اور ۵ وقت کی نماز",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "$cityName, $countryName",
                                style = MaterialTheme.typography.bodyMedium,
                                color = GoldLight
                            )
                        }

                        IconButton(
                            onClick = onOpenLocationPicker,
                            modifier = Modifier.testTag("prayer_change_location_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.EditLocationAlt,
                                contentDescription = "مقام تبدیل کریں",
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${hijriInfo.formatted} • $dateStr",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }

        // Active Countdown Card
        item {
            Box(modifier = Modifier.padding(16.dp)) {
                NextPrayerCountdownCard(
                    schedule = schedule,
                    onViewTimetableClick = {}
                )
            }
        }

        // Daily 5-Prayer Tracker Card
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                DailyNamazTrackerCard(
                    trackerState = trackerState,
                    onTogglePrayer = { viewModel.togglePrayerTracked(it) },
                    onOpenFullGuide = {
                        viewModel.setTab(NavigationTab.EXPLORE)
                        viewModel.setExploreSubScreen(ExploreSubScreen.NAMAZ_GUIDE)
                    }
                )
            }
        }

        // Today's Detailed Prayer Rows
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "آج کے اوقاتِ نماز",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                TextButton(
                    onClick = {
                        viewModel.setTab(NavigationTab.EXPLORE)
                        viewModel.setExploreSubScreen(ExploreSubScreen.NAMAZ_GUIDE)
                    }
                ) {
                    Text("مکمل طریقہ و رکعتیں", color = EmeraldPrimary, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        if (schedule != null) {
            items(schedule!!.prayers.size) { index ->
                val slot = schedule!!.prayers[index]
                DetailedPrayerRow(slot = slot)
            }
        }

        // 5 Times Namaz Guide Feature Banner Card
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .testTag("prayer_namaz_guide_banner"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Mosque,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "۵ وقت کی نماز کی رکعتیں اور طریقہ",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "فجر، ظہر، عصر، مغرب اور عشاء کی سنتیں، فرائض، وتر، ثناء، تشہد، درود ابراہیمی اور دعائے قنوت مع ترجمہ۔",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    FilledTonalButton(
                        onClick = {
                            viewModel.setTab(NavigationTab.EXPLORE)
                            viewModel.setExploreSubScreen(ExploreSubScreen.NAMAZ_GUIDE)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("۵ وقت کی نماز کی کتاب اور گائیڈ کھولیں")
                    }
                }
            }
        }

        // Qibla Direction Fast Action Card
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            modifier = Modifier.size(46.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Explore,
                                    contentDescription = "قبلہ نما",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Column {
                            Text(
                                text = "قبلہ کی سمت: ${String.format(Locale.US, "%.1f°", qiblaBearing)}",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "خانہ کعبہ کا فاصلہ: ${String.format(Locale.US, "%,.0f km", distanceKm)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    FilledTonalButton(
                        onClick = {
                            viewModel.setTab(NavigationTab.EXPLORE)
                            viewModel.setExploreSubScreen(ExploreSubScreen.QIBLA)
                        },
                        modifier = Modifier.testTag("open_qibla_compass_btn")
                    ) {
                        Text("قطب نما")
                    }
                }
            }
        }

        // Calculation Method Info
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "طریقہ حساب اور فقہی ترتیبات",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "• حساب کا طریقہ: ${prayerMethod.displayName}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "• نمازِ عصر کا فقہی مسلک: ${asrMadhab.displayName}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun DetailedPrayerRow(
    slot: PrayerTimeSlot,
    modifier: Modifier = Modifier
) {
    var notificationEnabled by remember { mutableStateOf(true) }

    val isNext = slot.isNext
    val isCurrent = slot.isCurrent

    val prayerNameUrdu = when (slot.name) {
        PrayerName.FAJR -> "فجر"
        PrayerName.SUNRISE -> "طلوع آفتاب"
        PrayerName.DHUHR -> "ظہر"
        PrayerName.ASR -> "عصر"
        PrayerName.MAGHRIB -> "مغرب"
        PrayerName.ISHA -> "عشاء"
    }

    val cardColor = when {
        isNext -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
        isCurrent -> MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        else -> MaterialTheme.colorScheme.surface
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .testTag("prayer_row_${slot.name.name}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isNext) 2.dp else 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = if (isNext) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.size(38.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        val icon = when (slot.name) {
                            PrayerName.FAJR -> Icons.Default.Brightness5
                            PrayerName.SUNRISE -> Icons.Default.WbSunny
                            PrayerName.DHUHR -> Icons.Default.Brightness7
                            PrayerName.ASR -> Icons.Default.Brightness6
                            PrayerName.MAGHRIB -> Icons.Default.Bedtime
                            PrayerName.ISHA -> Icons.Default.NightsStay
                        }
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = if (isNext) Color.White else MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "$prayerNameUrdu (${slot.name.displayName})",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = if (isNext) FontWeight.Bold else FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (isNext) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.primary
                            ) {
                                Text(
                                    text = "اگلی نماز",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                    fontSize = 9.sp
                                )
                            }
                        }
                    }
                    Text(
                        text = slot.name.arabicName,
                        style = ArabicTextStyle.copy(fontSize = 14.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = slot.timeFormatted,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isNext) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )

                if (slot.name != PrayerName.SUNRISE) {
                    IconButton(
                        onClick = { notificationEnabled = !notificationEnabled },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (notificationEnabled) Icons.Outlined.NotificationsActive else Icons.Outlined.Notifications,
                            contentDescription = "آذان الرٹ",
                            tint = if (notificationEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

