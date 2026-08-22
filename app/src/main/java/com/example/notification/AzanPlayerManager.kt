package com.example.notification

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.PowerManager
import android.util.Log
import com.example.data.repository.AzanAudioRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object AzanPlayerManager {

    private const val TAG = "AzanPlayerManager"

    private var mediaPlayer: MediaPlayer? = null
    private var wakeLock: PowerManager.WakeLock? = null

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _activePrayerName = MutableStateFlow<String?>(null)
    val activePrayerName: StateFlow<String?> = _activePrayerName.asStateFlow()

    private val _currentPlayingVoice = MutableStateFlow<String?>(null)
    val currentPlayingVoice: StateFlow<String?> = _currentPlayingVoice.asStateFlow()

    private val playerScope = CoroutineScope(Dispatchers.Main + Job())

    @Synchronized
    fun playAzan(
        context: Context,
        voiceId: String = "Makkah",
        prayerName: String = "نماز",
        isFajr: Boolean = false,
        onCompletion: () -> Unit = {}
    ) {
        stopAzan()

        val audioUrl = AzanAudioRepository.getAudioUrl(voiceId, isFajr)
        _activePrayerName.value = prayerName
        _currentPlayingVoice.value = voiceId

        try {
            // Acquire wake lock to play while device screen is locked or idle
            val powerManager = context.getSystemService(Context.POWER_SERVICE) as? PowerManager
            wakeLock = powerManager?.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK,
                "Deenova:AzanWakeLock"
            )?.apply {
                acquire(10 * 60 * 1000L) // 10 minutes max
            }

            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setLegacyStreamType(AudioManager.STREAM_ALARM)
                        .build()
                )

                setDataSource(context, Uri.parse(audioUrl))

                setOnPreparedListener { mp ->
                    try {
                        mp.start()
                        _isPlaying.value = true
                        Log.d(TAG, "Azan started playing for $prayerName ($voiceId)")
                    } catch (e: Exception) {
                        Log.e(TAG, "Error starting MediaPlayer", e)
                        playFallbackAlarmSound(context)
                    }
                }

                setOnCompletionListener {
                    Log.d(TAG, "Azan playback completed")
                    stopAzan()
                    onCompletion()
                }

                setOnErrorListener { _, what, extra ->
                    Log.w(TAG, "Azan playback error (what: $what, extra: $extra). Playing fallback alarm tone...")
                    stopAzan()
                    playFallbackAlarmSound(context)
                    true
                }

                prepareAsync()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize Azan audio player, playing fallback sound", e)
            playFallbackAlarmSound(context)
        }
    }

    private fun playFallbackAlarmSound(context: Context) {
        try {
            val alertUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                setDataSource(context, alertUri)
                setOnPreparedListener { it.start(); _isPlaying.value = true }
                setOnCompletionListener { stopAzan() }
                prepareAsync()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Fallback sound error", e)
            stopAzan()
        }
    }

    @Synchronized
    fun stopAzan() {
        try {
            mediaPlayer?.let { mp ->
                if (mp.isPlaying) {
                    mp.stop()
                }
                mp.reset()
                mp.release()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error stopping azan", e)
        } finally {
            mediaPlayer = null
            _isPlaying.value = false
            _activePrayerName.value = null
            _currentPlayingVoice.value = null

            try {
                if (wakeLock?.isHeld == true) {
                    wakeLock?.release()
                }
            } catch (e: Exception) {}
            wakeLock = null
        }
    }
}
