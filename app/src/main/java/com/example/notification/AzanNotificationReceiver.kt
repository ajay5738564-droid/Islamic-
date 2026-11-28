package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.example.R
import com.example.data.model.PrayerName
import com.example.data.repository.PrayerCalculationRepository
import com.example.data.repository.UserPreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Calendar

class AzanNotificationReceiver : BroadcastReceiver() {

    companion object {
        const val CHANNEL_ID = "azan_prayer_alerts_channel"
        const val CHANNEL_NAME = "Prayer Times & Azan Alarms"
        const val ACTION_STOP_AZAN = "com.example.ACTION_STOP_AZAN"

        const val EXTRA_PRAYER_KEY = "extra_prayer_key"
        const val EXTRA_PRAYER_NAME = "extra_prayer_name"
        const val EXTRA_PRAYER_TIME = "extra_prayer_time"
        const val EXTRA_CITY_NAME = "extra_city_name"
        const val EXTRA_AZAN_VOICE = "extra_azan_voice"
        const val NOTIFICATION_ID = 78601
    }

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_TIME_CHANGED,
            Intent.ACTION_TIMEZONE_CHANGED,
            Intent.ACTION_DATE_CHANGED -> {
                reschedulePrayerAlarms(context)
                return
            }
            ACTION_STOP_AZAN -> {
            AzanPlayerManager.stopAzan()
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.cancel(NOTIFICATION_ID)
                return
            }
            AzanAlarmScheduler.ACTION_AZAN_ALARM -> {
                // Keep the next prayer alarms queued even if the app UI is not running.
                reschedulePrayerAlarms(context)
            }
            else -> return
        }

        val prayerKey = intent.getStringExtra(EXTRA_PRAYER_KEY) ?: "PRAYER"
        val prayerName = intent.getStringExtra(EXTRA_PRAYER_NAME) ?: "نماز"
        val prayerTime = intent.getStringExtra(EXTRA_PRAYER_TIME) ?: ""
        val cityName = intent.getStringExtra(EXTRA_CITY_NAME) ?: ""
        val azanVoice = intent.getStringExtra(EXTRA_AZAN_VOICE) ?: "Madinah"
        val isFajr = prayerKey.equals("FAJR", ignoreCase = true)

        // 1. Play the Azan Audio directly with full alarm volume
        AzanPlayerManager.playAzan(
            context = context,
            voiceId = azanVoice,
            prayerName = prayerName,
            isFajr = isFajr
        )

        // 2. Show the Rich Notification with Azan Control buttons
        showAzanNotification(context, prayerName, prayerTime, cityName, azanVoice)
    }

    private fun reschedulePrayerAlarms(context: Context) {
        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val preferences = UserPreferencesRepository(context.applicationContext)
                val enabledPrayers = mutableSetOf<PrayerName>()
                if (preferences.notifyFajrFlow.first()) enabledPrayers.add(PrayerName.FAJR)
                if (preferences.notifyDhuhrFlow.first()) enabledPrayers.add(PrayerName.DHUHR)
                if (preferences.notifyAsrFlow.first()) enabledPrayers.add(PrayerName.ASR)
                if (preferences.notifyMaghribFlow.first()) enabledPrayers.add(PrayerName.MAGHRIB)
                if (preferences.notifyIshaFlow.first()) enabledPrayers.add(PrayerName.ISHA)

                val cityName = preferences.selectedCityFlow.first()
                val schedule = PrayerCalculationRepository.getDailySchedule(
                    date = Calendar.getInstance(),
                    latitude = preferences.latitudeFlow.first(),
                    longitude = preferences.longitudeFlow.first(),
                    timeZoneOffsetHours = preferences.tzOffsetFlow.first(),
                    cityName = cityName,
                    method = preferences.calculationMethodFlow.first(),
                    asrMadhab = preferences.asrMadhabFlow.first()
                )
                AzanAlarmScheduler.schedulePrayerAlarms(
                    context = context.applicationContext,
                    schedule = schedule,
                    cityName = cityName,
                    enabledPrayers = enabledPrayers,
                    azanVoice = preferences.azanVoiceFlow.first()
                )
            } catch (error: Exception) {
                Log.e("AzanNotificationReceiver", "Could not reschedule prayer alarms", error)
            } finally {
                pendingResult.finish()
            }
        }
    }

    private fun showAzanNotification(
        context: Context,
        prayerName: String,
        prayerTime: String,
        cityName: String,
        azanVoice: String
    ) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create High Importance Notification Channel with Sound and Vibration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_EVENT)
                .build()

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Daily 5-time Azan sound and Salah time alarms"
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 600, 300, 600, 300, 600)
                setSound(defaultSoundUri, audioAttributes)
                lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Open App Intent
        val openAppIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingOpenAppIntent = PendingIntent.getActivity(
            context,
            1001,
            openAppIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Stop Azan Intent
        val stopAzanIntent = Intent(context, AzanNotificationReceiver::class.java).apply {
            action = ACTION_STOP_AZAN
        }
        val pendingStopAzanIntent = PendingIntent.getBroadcast(
            context,
            1002,
            stopAzanIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val title = "🕌 وقتِ $prayerName ($prayerTime)"
        val content = "حَیَّ عَلَی الصَّلٰوةِ - نماز قائم کریں ($cityName)"
        val fullText = "$content\n\nاللہ اکبر اللہ اکبر! نماز کا وقت ہو چکا ہے۔ اپنے رب کے حضور سجدہ ریز ہو جائیے۔\n🔊 اذان آواز: $azanVoice"

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(content)
            .setStyle(NotificationCompat.BigTextStyle().bigText(fullText))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .setContentIntent(pendingOpenAppIntent)
            .setVibrate(longArrayOf(0, 600, 300, 600, 300, 600))
            .addAction(
                android.R.drawable.ic_media_pause,
                "⏹️ اذان بند کریں (Stop)",
                pendingStopAzanIntent
            )
            .addAction(
                android.R.drawable.ic_menu_today,
                "🕌 اوقاتِ نماز (Open)",
                pendingOpenAppIntent
            )
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}
