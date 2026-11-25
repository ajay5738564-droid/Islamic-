package com.example.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.data.model.DailyPrayerSchedule
import com.example.data.model.PrayerName
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object AzanAlarmScheduler {

    const val ACTION_AZAN_ALARM = "com.example.ACTION_AZAN_ALARM"

    fun schedulePrayerAlarms(
        context: Context,
        schedule: DailyPrayerSchedule,
        cityName: String,
        enabledPrayers: Set<PrayerName> = setOf(
            PrayerName.FAJR,
            PrayerName.DHUHR,
            PrayerName.ASR,
            PrayerName.MAGHRIB,
            PrayerName.ISHA
        ),
        azanVoice: String = "Madinah"
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        for (prayer in schedule.prayers) {
            if (prayer.name == PrayerName.SUNRISE) continue
            if (!enabledPrayers.contains(prayer.name)) {
                cancelPrayerAlarm(context, prayer.name)
                continue
            }

            val prayerCal = parseTimeToTodayCalendar(prayer.timeFormatted)
            val now = Calendar.getInstance()

            // If time has already passed today, schedule for tomorrow
            if (prayerCal.before(now)) {
                prayerCal.add(Calendar.DAY_OF_YEAR, 1)
            }

            val intent = Intent(context, AzanNotificationReceiver::class.java).apply {
                action = ACTION_AZAN_ALARM
                putExtra(AzanNotificationReceiver.EXTRA_PRAYER_KEY, prayer.name.name)
                putExtra(AzanNotificationReceiver.EXTRA_PRAYER_NAME, prayer.name.displayNameUrdu)
                putExtra(AzanNotificationReceiver.EXTRA_PRAYER_TIME, prayer.timeFormatted)
                putExtra(AzanNotificationReceiver.EXTRA_CITY_NAME, cityName)
                putExtra(AzanNotificationReceiver.EXTRA_AZAN_VOICE, azanVoice)
            }

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                prayer.name.ordinal + 1000,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        prayerCal.timeInMillis,
                        pendingIntent
                    )
                } else {
                    alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        prayerCal.timeInMillis,
                        pendingIntent
                    )
                }
            } catch (e: SecurityException) {
                // If exact alarm permission not granted on Android 12+, fallback to normal set
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    prayerCal.timeInMillis,
                    pendingIntent
                )
            }
        }
    }

    fun cancelPrayerAlarm(context: Context, prayerName: PrayerName) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AzanNotificationReceiver::class.java).apply {
            action = ACTION_AZAN_ALARM
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            prayerName.ordinal + 1000,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

    private fun parseTimeToTodayCalendar(timeStr: String): Calendar {
        val cal = Calendar.getInstance()
        try {
            val formats = listOf("hh:mm a", "h:mm a", "HH:mm", "H:mm")
            var parsedDate: Date? = null
            for (format in formats) {
                try {
                    val sdf = SimpleDateFormat(format, Locale.US)
                    sdf.isLenient = true
                    parsedDate = sdf.parse(timeStr.trim())
                    if (parsedDate != null) break
                } catch (ignored: Exception) {}
            }

            if (parsedDate != null) {
                val timeCal = Calendar.getInstance().apply { time = parsedDate }
                cal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY))
                cal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE))
                cal.set(Calendar.SECOND, 0)
                cal.set(Calendar.MILLISECOND, 0)
            }
        } catch (e: Exception) {}
        return cal
    }
}
