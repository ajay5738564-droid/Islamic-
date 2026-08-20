package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.data.model.DailyPrayerTrackerState
import com.example.data.model.PrayerName
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight

@Composable
fun DailyNamazTrackerCard(
    trackerState: DailyPrayerTrackerState,
    onTogglePrayer: (PrayerName) -> Unit,
    onOpenFullGuide: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("daily_namaz_tracker_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(EmeraldPrimary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mosque,
                            contentDescription = "۵ وقت کی نماز",
                            tint = EmeraldPrimary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "۵ وقت کی نماز ٹریکر",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "آج کی نمازوں کا اندراج کریں",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Progress Badge
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (trackerState.isAllCompleted) EmeraldPrimary else MaterialTheme.colorScheme.primaryContainer
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (trackerState.isAllCompleted) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = GoldLight,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Text(
                            text = "${trackerState.completedCount} / 5 ادا شدہ",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (trackerState.isAllCompleted) Color.White else MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Linear Progress Bar
            LinearProgressIndicator(
                progress = { trackerState.completedCount / 5f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = EmeraldPrimary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 5 Prayers Check-in Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NamazCheckItem(
                    titleUrdu = "فجر",
                    rakats = "4",
                    isDone = trackerState.fajrDone,
                    onToggle = { onTogglePrayer(PrayerName.FAJR) },
                    testTag = "tracker_fajr"
                )
                NamazCheckItem(
                    titleUrdu = "ظہر",
                    rakats = "12",
                    isDone = trackerState.dhuhrDone,
                    onToggle = { onTogglePrayer(PrayerName.DHUHR) },
                    testTag = "tracker_dhuhr"
                )
                NamazCheckItem(
                    titleUrdu = "عصر",
                    rakats = "8",
                    isDone = trackerState.asrDone,
                    onToggle = { onTogglePrayer(PrayerName.ASR) },
                    testTag = "tracker_asr"
                )
                NamazCheckItem(
                    titleUrdu = "مغرب",
                    rakats = "7",
                    isDone = trackerState.maghribDone,
                    onToggle = { onTogglePrayer(PrayerName.MAGHRIB) },
                    testTag = "tracker_maghrib"
                )
                NamazCheckItem(
                    titleUrdu = "عشاء",
                    rakats = "17",
                    isDone = trackerState.ishaDone,
                    onToggle = { onTogglePrayer(PrayerName.ISHA) },
                    testTag = "tracker_isha"
                )
            }

            if (onOpenFullGuide != null) {
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(6.dp))
                TextButton(
                    onClick = onOpenFullGuide,
                    modifier = Modifier.fillMaxWidth().testTag("btn_open_namaz_guide")
                ) {
                    Text(
                        text = "📖 ۵ وقت کی نماز کا مکمل طریقہ اور رکعتیں دیکھیں",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = EmeraldPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun NamazCheckItem(
    titleUrdu: String,
    rakats: String,
    isDone: Boolean,
    onToggle: () -> Unit,
    testTag: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onToggle)
            .padding(vertical = 4.dp, horizontal = 2.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(
                    if (isDone) EmeraldPrimary else MaterialTheme.colorScheme.surfaceVariant
                )
                .border(
                    width = 1.5.dp,
                    color = if (isDone) EmeraldDark else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isDone) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "$titleUrdu مکمل",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    text = "${rakats} رکعت",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = titleUrdu,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = if (isDone) FontWeight.Bold else FontWeight.Medium,
            color = if (isDone) EmeraldPrimary else MaterialTheme.colorScheme.onSurface
        )
    }
}
