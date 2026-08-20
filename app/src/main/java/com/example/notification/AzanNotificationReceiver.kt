package com.example.notification

import android.app.AlarmManager
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
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.example.R
import com.example.data.model.DailyPrayerSchedule
import com.example.data.model.PrayerName
import java.util.Calendar

class AzanNotificationReceiver : BroadcastReceiver() {

    companion object {
        const val CHANNEL_ID = "azan_prayer_alerts_channel"
        const val CHANNEL_NAME = "Prayer Times & Azan Alarms"
        const val EXTRA_PRAYER_NAME = "extra_prayer_name"
        const val EXTRA_PRAYER_TIME = "extra_prayer_time"
        const val EXTRA_CITY_NAME = "extra_city_name"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // Re-schedule alarms on device boot
            return
        }

        val prayerName = intent.getStringExtra(EXTRA_PRAYER_NAME) ?: "نماز"
        val prayerTime = intent.getStringExtra(EXTRA_PRAYER_TIME) ?: ""
        val cityName = intent.getStringExtra(EXTRA_CITY_NAME) ?: ""

        showAzanNotification(context, prayerName, prayerTime, cityName)
    }

    private fun showAzanNotification(
        context: Context,
        prayerName: String,
        prayerTime: String,
        cityName: String
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
                description = "Daily 5-time Azan and Salah time notifications"
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 500, 200, 500, 200, 500)
                setSound(defaultSoundUri, audioAttributes)
                lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            }
            notificationManager.createNotificationChannel(channel)
        }

        val openAppIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            prayerName.hashCode(),
            openAppIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val title = "🕌 وقتِ $prayerName ($prayerTime)"
        val content = "حَیَّ عَلَی الصَّلٰوةِ - نماز قائم کریں ($cityName)"

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(content)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("$content\n\nاللہ اکبر اللہ اکبر! نماز کا وقت ہو چکا ہے۔ اپنے رب کے حضور سجدہ ریز ہو جائیے۔")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setVibrate(longArrayOf(0, 500, 200, 500, 200, 500))
            .build()

        notificationManager.notify(prayerName.hashCode(), notification)
    }
}
