package com.example.ui

import android.app.Application
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.BookmarkEntity
import com.example.data.local.DeenovaDatabase
import com.example.data.local.TasbeehHistoryEntity
import com.example.data.model.AsrJuristic
import com.example.data.model.Ayah
import com.example.data.model.BookmarkItem
import com.example.data.model.BookmarkType
import com.example.data.model.CalculationMethod
import com.example.data.model.CityLocation
import com.example.data.model.DailyPrayerSchedule
import com.example.data.model.DuaItem
import com.example.data.model.HadithItem
import com.example.data.model.HijriDateInfo
import com.example.data.model.IslamicEvent
import com.example.data.model.KnowledgeArticle
import com.example.data.model.NaatCategory
import com.example.data.model.NaatItem
import com.example.data.model.PrayerName
import com.example.data.model.Surah
import com.example.data.model.TasbeehPreset
import com.example.data.model.ZakatCalculationResult
import com.example.data.model.ZakatInput
import com.example.data.repository.AppLanguage
import com.example.data.repository.ThemeMode
import com.example.data.repository.DuaRepository
import com.example.data.repository.HadithRepository
import com.example.data.repository.HijriCalendarRepository
import com.example.data.repository.IslamicKnowledgeRepository
import com.example.data.repository.NaatRepository
import com.example.data.repository.PrayerCalculationRepository
import com.example.data.repository.QuranRepository
import com.example.data.repository.TasbeehRepository
import com.example.data.repository.UserPreferencesRepository
import com.example.data.repository.ZakatRepository
import com.example.location.LocationHelper
import com.example.location.QiblaSensorManager
import com.example.notification.AzanAlarmScheduler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.abs

enum class NavigationTab {
    HOME,
    QURAN,
    PRAYER,
    EXPLORE,
    MORE
}

enum class ExploreSubScreen {
    MAIN_MENU,
    NAMAZ_GUIDE,
    NAAT_PLAYER,
    NAMES_OF_ALLAH,
    BABY_NAMES,
    AZAN_SETTINGS,
    DUA_LIST,
    HADITH_LIST,
    TASBEEH,
    QIBLA,
    ISLAMIC_CALENDAR,
    RAMADAN_DASHBOARD,
    HAJJ_UMRAH_GUIDE,
    ZAKAT_CALCULATOR,
    ISLAMIC_KNOWLEDGE,
    SOURCES
}

enum class AudioPlaybackState {
    IDLE,
    BUFFERING,
    PLAYING,
    PAUSED
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val database = DeenovaDatabase.getDatabase(application)
    val preferences = UserPreferencesRepository(application)
    val sensorManager = QiblaSensorManager(application)

    // Navigation & UI States
    private val _currentTab = MutableStateFlow(NavigationTab.HOME)
    val currentTab: StateFlow<NavigationTab> = _currentTab.asStateFlow()

    private val _exploreSubScreen = MutableStateFlow(ExploreSubScreen.MAIN_MENU)
    val exploreSubScreen: StateFlow<ExploreSubScreen> = _exploreSubScreen.asStateFlow()

    // Quran State
    private val _selectedSurah = MutableStateFlow<Surah?>(null)
    val selectedSurah: StateFlow<Surah?> = _selectedSurah.asStateFlow()
    private val _selectedJuz = MutableStateFlow<Int?>(null)
    val selectedJuz: StateFlow<Int?> = _selectedJuz.asStateFlow()

    private val _currentSurahAyahs = MutableStateFlow<List<Ayah>>(emptyList())
    val currentSurahAyahs: StateFlow<List<Ayah>> = _currentSurahAyahs.asStateFlow()

    private val _isSurahLoading = MutableStateFlow(false)
    val isSurahLoading: StateFlow<Boolean> = _isSurahLoading.asStateFlow()

    private val _quranSearchQuery = MutableStateFlow("")
    val quranSearchQuery: StateFlow<String> = _quranSearchQuery.asStateFlow()

    // Naat & Hamd Player State
    private var naatMediaPlayer: MediaPlayer? = null
    private val _selectedNaatCategory = MutableStateFlow(NaatCategory.ALL)
    val selectedNaatCategory: StateFlow<NaatCategory> = _selectedNaatCategory.asStateFlow()

    private val _naatSearchQuery = MutableStateFlow("")
    val naatSearchQuery: StateFlow<String> = _naatSearchQuery.asStateFlow()

    private val _currentPlayingNaat = MutableStateFlow<NaatItem?>(null)
    val currentPlayingNaat: StateFlow<NaatItem?> = _currentPlayingNaat.asStateFlow()

    private val _selectedNaatForDetails = MutableStateFlow<NaatItem?>(null)
    val selectedNaatForDetails: StateFlow<NaatItem?> = _selectedNaatForDetails.asStateFlow()

    private val _naatPlaybackState = MutableStateFlow(AudioPlaybackState.IDLE)
    val naatPlaybackState: StateFlow<AudioPlaybackState> = _naatPlaybackState.asStateFlow()

    private val _naatProgressSeconds = MutableStateFlow(0)
    val naatProgressSeconds: StateFlow<Int> = _naatProgressSeconds.asStateFlow()

    private val _naatTotalSeconds = MutableStateFlow(1)
    val naatTotalSeconds: StateFlow<Int> = _naatTotalSeconds.asStateFlow()

    private var naatProgressJob: Job? = null

    // Global Search State
    private val _globalSearchQuery = MutableStateFlow("")
    val globalSearchQuery: StateFlow<String> = _globalSearchQuery.asStateFlow()

    // Quran Audio Playback
    private var mediaPlayer: MediaPlayer? = null
    private val _currentPlayingAyah = MutableStateFlow<Ayah?>(null)
    val currentPlayingAyah: StateFlow<Ayah?> = _currentPlayingAyah.asStateFlow()

    private val _audioPlaybackState = MutableStateFlow(AudioPlaybackState.IDLE)
    val audioPlaybackState: StateFlow<AudioPlaybackState> = _audioPlaybackState.asStateFlow()

    // Tasbeeh State
    private val _selectedPreset = MutableStateFlow(TasbeehRepository.PRESET_ZIKRS.first())
    val selectedPreset: StateFlow<TasbeehPreset> = _selectedPreset.asStateFlow()

    private val _customZikrText = MutableStateFlow("")
    val customZikrText: StateFlow<String> = _customZikrText.asStateFlow()

    private val _tasbeehCount = MutableStateFlow(0)
    val tasbeehCount: StateFlow<Int> = _tasbeehCount.asStateFlow()

    private val _tasbeehTarget = MutableStateFlow(33)
    val tasbeehTarget: StateFlow<Int> = _tasbeehTarget.asStateFlow()

    private val _tasbeehDailyTotal = MutableStateFlow(0)
    val tasbeehDailyTotal: StateFlow<Int> = _tasbeehDailyTotal.asStateFlow()

    // Dua State
    private val _selectedDuaCategory = MutableStateFlow<String?>(null)
    val selectedDuaCategory: StateFlow<String?> = _selectedDuaCategory.asStateFlow()

    private val _duaSearchQuery = MutableStateFlow("")
    val duaSearchQuery: StateFlow<String> = _duaSearchQuery.asStateFlow()

    // Knowledge State
    private val _selectedKnowledgeArticle = MutableStateFlow<KnowledgeArticle?>(null)
    val selectedKnowledgeArticle: StateFlow<KnowledgeArticle?> = _selectedKnowledgeArticle.asStateFlow()

    // Zakat State
    private val _zakatInput = MutableStateFlow(ZakatInput())
    val zakatInput: StateFlow<ZakatInput> = _zakatInput.asStateFlow()

    private val _zakatResult = MutableStateFlow(ZakatRepository.calculateZakat(ZakatInput()))
    val zakatResult: StateFlow<ZakatCalculationResult> = _zakatResult.asStateFlow()

    // Location & Calculation Preferences
    val appLanguage = preferences.languageFlow.stateIn(viewModelScope, SharingStarted.Eagerly, AppLanguage.URDU)
    val prayerMethod = preferences.calculationMethodFlow.stateIn(viewModelScope, SharingStarted.Eagerly, CalculationMethod.KARACHI)
    val asrMadhab = preferences.asrMadhabFlow.stateIn(viewModelScope, SharingStarted.Eagerly, AsrJuristic.HANAFI)
    val cityName = preferences.selectedCityFlow.stateIn(viewModelScope, SharingStarted.Eagerly, "New Delhi")
    val countryName = preferences.selectedCountryFlow.stateIn(viewModelScope, SharingStarted.Eagerly, "India")
    val latitude = preferences.latitudeFlow.stateIn(viewModelScope, SharingStarted.Eagerly, 28.6139)
    val longitude = preferences.longitudeFlow.stateIn(viewModelScope, SharingStarted.Eagerly, 77.2090)
    val tzOffset = preferences.tzOffsetFlow.stateIn(viewModelScope, SharingStarted.Eagerly, 5.5)
    val hijriAdjustment = preferences.hijriAdjustmentFlow.stateIn(viewModelScope, SharingStarted.Eagerly, 0)
    val arabicFontSize = preferences.arabicFontSizeFlow.stateIn(viewModelScope, SharingStarted.Eagerly, 26)
    val tasbeehVibrate = preferences.tasbeehVibrateFlow.stateIn(viewModelScope, SharingStarted.Eagerly, true)
    val themeMode = preferences.themeModeFlow.stateIn(viewModelScope, SharingStarted.Eagerly, ThemeMode.SYSTEM)
    val isSupporter = preferences.supporterStatusFlow.stateIn(viewModelScope, SharingStarted.Eagerly, false)
    val rewardPoints = preferences.rewardPointsFlow.stateIn(viewModelScope, SharingStarted.Eagerly, 0)
    val unlockedQuranJuz = preferences.unlockedQuranJuzFlow.stateIn(viewModelScope, SharingStarted.Eagerly, setOf(1))

    fun isJuzUnlocked(juzNumber: Int): Boolean = juzNumber == 1 || juzNumber in unlockedQuranJuz.value

    fun unlockJuz(juzNumber: Int, onUnlocked: (() -> Unit)? = null) {
        viewModelScope.launch {
            preferences.unlockQuranJuz(juzNumber)
            onUnlocked?.invoke()
        }
    }

    fun claimAdReward(points: Int = 100) {
        viewModelScope.launch {
            preferences.grantAdReward(points)
        }
    }

    // Prayer Alarms
    val notifyFajr = preferences.notifyFajrFlow.stateIn(viewModelScope, SharingStarted.Eagerly, true)
    val notifyDhuhr = preferences.notifyDhuhrFlow.stateIn(viewModelScope, SharingStarted.Eagerly, true)
    val notifyAsr = preferences.notifyAsrFlow.stateIn(viewModelScope, SharingStarted.Eagerly, true)
    val notifyMaghrib = preferences.notifyMaghribFlow.stateIn(viewModelScope, SharingStarted.Eagerly, true)
    val notifyIsha = preferences.notifyIshaFlow.stateIn(viewModelScope, SharingStarted.Eagerly, true)
    val azanVoice = preferences.azanVoiceFlow.stateIn(viewModelScope, SharingStarted.Eagerly, "Makkah")

    // Daily 5-Prayer Tracker
    val todayDateString: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    val prayerTrackerState = preferences.getDailyPrayerTrackerFlow(todayDateString)
        .stateIn(viewModelScope, SharingStarted.Eagerly, com.example.data.model.DailyPrayerTrackerState(todayDateString))

    fun isSurahUnlocked(surahNumber: Int): Boolean {
        return true
    }

    fun togglePrayerTracked(prayerName: PrayerName) {
        viewModelScope.launch {
            preferences.togglePrayerDone(prayerName, todayDateString)
            triggerHapticFeedback()
        }
    }

    fun setLanguage(language: AppLanguage) {
        viewModelScope.launch {
            preferences.setLanguage(language)
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch {
            preferences.setThemeMode(mode)
            triggerHapticFeedback()
        }
    }

    // Bookmarks Flow
    val allBookmarks = database.bookmarkDao().getAllBookmarks()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val tasbeehHistory = database.tasbeehDao().getAllHistory()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Prayer Schedule State
    private val _dailyPrayerSchedule = MutableStateFlow<DailyPrayerSchedule?>(null)
    val dailyPrayerSchedule: StateFlow<DailyPrayerSchedule?> = _dailyPrayerSchedule.asStateFlow()

    // Qibla Angle & Distance
    private val _qiblaBearing = MutableStateFlow(0.0)
    val qiblaBearing: StateFlow<Double> = _qiblaBearing.asStateFlow()

    private val _distanceToKaabaKm = MutableStateFlow(0.0)
    val distanceToKaabaKm: StateFlow<Double> = _distanceToKaabaKm.asStateFlow()

    private val _isAlignedWithQibla = MutableStateFlow(false)
    val isAlignedWithQibla: StateFlow<Boolean> = _isAlignedWithQibla.asStateFlow()

    private var countdownTimerJob: Job? = null

    init {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            // Download Quran Juz text in the background once internet is available;
            // saved ayahs remain readable from Room when the device is offline later.
            QuranRepository.cacheAllJuzForOffline(database)
        }
        updatePrayerSchedule()
        startPeriodicScheduleUpdates()
        sensorManager.startListening()
        monitorQiblaAlignment()
    }

    fun setTab(tab: NavigationTab) {
        _currentTab.value = tab
    }

    fun setExploreSubScreen(screen: ExploreSubScreen) {
        _exploreSubScreen.value = screen
    }

    fun setQuranSearch(query: String) {
        _quranSearchQuery.value = query
    }

    fun setGlobalSearch(query: String) {
        _globalSearchQuery.value = query
    }

    fun selectSurah(surah: Surah) {
        _selectedJuz.value = null
        _selectedSurah.value = surah
        loadAyahsForSurah(surah.number)
    }

    fun selectJuz(juzNumber: Int, justUnlocked: Boolean = false) {
        if (juzNumber !in 1..30) return
        _selectedSurah.value = null
        _selectedJuz.value = juzNumber
        viewModelScope.launch {
            _isSurahLoading.value = true
            _currentSurahAyahs.value = QuranRepository.getJuzAyahs(database, juzNumber)
            _isSurahLoading.value = false
        }
    }

    fun clearSelectedSurah() {
        _selectedSurah.value = null
        _selectedJuz.value = null
        _currentSurahAyahs.value = emptyList()
        stopAudio()
    }

    fun loadAyahsForSurah(surahNumber: Int) {
        viewModelScope.launch {
            _isSurahLoading.value = true
            val ayahs = QuranRepository.getSurahAyahs(database, surahNumber)
            _currentSurahAyahs.value = ayahs
            _isSurahLoading.value = false
        }
    }

    // Audio Player Controls
    fun playAyahAudio(ayah: Ayah) {
        if (ayah.audioUrl.isBlank()) return

        if (_currentPlayingAyah.value?.number == ayah.number && _audioPlaybackState.value == AudioPlaybackState.PLAYING) {
            pauseAudio()
            return
        }

        if (_currentPlayingAyah.value?.number == ayah.number && _audioPlaybackState.value == AudioPlaybackState.PAUSED) {
            resumeAudio()
            return
        }

        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(ayah.audioUrl)
                _currentPlayingAyah.value = ayah
                _audioPlaybackState.value = AudioPlaybackState.BUFFERING

                setOnPreparedListener { mp ->
                    mp.start()
                    _audioPlaybackState.value = AudioPlaybackState.PLAYING
                }

                setOnCompletionListener {
                    _audioPlaybackState.value = AudioPlaybackState.IDLE
                    _currentPlayingAyah.value = null
                    // Auto play next verse if available
                    playNextAyahInSurah(ayah)
                }

                setOnErrorListener { _, _, _ ->
                    _audioPlaybackState.value = AudioPlaybackState.IDLE
                    _currentPlayingAyah.value = null
                    true
                }

                prepareAsync()
            }
        } catch (e: Exception) {
            _audioPlaybackState.value = AudioPlaybackState.IDLE
            _currentPlayingAyah.value = null
        }
    }

    private fun playNextAyahInSurah(current: Ayah) {
        val ayahs = _currentSurahAyahs.value
        val currentIndex = ayahs.indexOfFirst { it.number == current.number }
        if (currentIndex != -1 && currentIndex < ayahs.size - 1) {
            playAyahAudio(ayahs[currentIndex + 1])
        }
    }

    fun pauseAudio() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                _audioPlaybackState.value = AudioPlaybackState.PAUSED
            }
        }
    }

    fun resumeAudio() {
        mediaPlayer?.let {
            it.start()
            _audioPlaybackState.value = AudioPlaybackState.PLAYING
        }
    }

    fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        _audioPlaybackState.value = AudioPlaybackState.IDLE
        _currentPlayingAyah.value = null
    }

    // Bookmark Toggling
    fun toggleBookmark(item: BookmarkEntity) {
        viewModelScope.launch {
            val exists = allBookmarks.value.any { it.id == item.id }
            if (exists) {
                database.bookmarkDao().deleteBookmark(item.id)
            } else {
                database.bookmarkDao().insertBookmark(item)
            }
        }
    }

    fun isItemBookmarked(id: String): Boolean {
        return allBookmarks.value.any { it.id == id }
    }

    // Tasbeeh Logic
    fun selectTasbeehPreset(preset: TasbeehPreset) {
        _selectedPreset.value = preset
        _customZikrText.value = ""
        _tasbeehTarget.value = preset.defaultTarget
        _tasbeehCount.value = 0
    }

    fun setCustomZikr(text: String) {
        _customZikrText.value = text
        _tasbeehCount.value = 0
    }

    fun incrementTasbeeh() {
        val newCount = _tasbeehCount.value + 1
        _tasbeehCount.value = newCount
        _tasbeehDailyTotal.value += 1

        triggerHapticFeedback()

        // Check target completion
        if (newCount == _tasbeehTarget.value) {
            triggerTargetReachedHaptic()
            // Save session to Room
            val currentTitle = if (_customZikrText.value.isNotBlank()) _customZikrText.value else _selectedPreset.value.transliteration
            val currentArabic = if (_customZikrText.value.isNotBlank()) "" else _selectedPreset.value.arabicText
            val sdf = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
            viewModelScope.launch {
                database.tasbeehDao().insertSession(
                    TasbeehHistoryEntity(
                        zikrTitle = currentTitle,
                        arabicText = currentArabic,
                        count = newCount,
                        target = _tasbeehTarget.value,
                        dateFormatted = sdf.format(Date())
                    )
                )
            }
        }
    }

    fun resetTasbeeh() {
        _tasbeehCount.value = 0
    }

    fun setTasbeehTarget(target: Int) {
        _tasbeehTarget.value = target
    }

    fun triggerHapticFeedback() {
        if (!tasbeehVibrate.value) return
        try {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val manager = getApplication<Application>().getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                manager?.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                getApplication<Application>().getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(25)
            }
        } catch (e: Exception) {}
    }

    private fun triggerTargetReachedHaptic() {
        if (!tasbeehVibrate.value) return
        try {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val manager = getApplication<Application>().getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                manager?.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                getApplication<Application>().getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createWaveform(longArrayOf(0, 100, 50, 150), -1))
            }
        } catch (e: Exception) {}
    }

    // Dua Section Logic
    fun selectDuaCategory(categoryId: String?) {
        _selectedDuaCategory.value = categoryId
    }

    fun setDuaSearch(query: String) {
        _duaSearchQuery.value = query
    }

    // Naat Player Section Logic
    fun selectNaatCategory(category: NaatCategory) {
        _selectedNaatCategory.value = category
    }

    fun setNaatSearch(query: String) {
        _naatSearchQuery.value = query
    }

    fun selectNaatForDetails(item: NaatItem?) {
        _selectedNaatForDetails.value = item
    }

    fun playNaat(item: NaatItem) {
        if (_currentPlayingNaat.value?.id == item.id && _naatPlaybackState.value == AudioPlaybackState.PLAYING) {
            pauseNaat()
            return
        }
        if (_currentPlayingNaat.value?.id == item.id && _naatPlaybackState.value == AudioPlaybackState.PAUSED) {
            resumeNaat()
            return
        }
        // Catalogue-only entries intentionally have no audio until a verified recording is supplied.
        if (item.audioUrl.isBlank()) return

        // Stop Quran audio if playing to prevent collision
        stopAudio()

        try {
            naatMediaPlayer?.release()
            naatProgressJob?.cancel()
            _naatProgressSeconds.value = 0
            _currentPlayingNaat.value = item
            _naatPlaybackState.value = AudioPlaybackState.BUFFERING

            naatMediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(item.audioUrl)

                setOnPreparedListener { mp ->
                    mp.start()
                    _naatPlaybackState.value = AudioPlaybackState.PLAYING
                    _naatTotalSeconds.value = (mp.duration / 1000).coerceAtLeast(1)
                    startNaatProgressTracker()
                }

                setOnCompletionListener {
                    _naatPlaybackState.value = AudioPlaybackState.IDLE
                    _naatProgressSeconds.value = 0
                    naatProgressJob?.cancel()
                }

                setOnErrorListener { _, _, _ ->
                    _naatPlaybackState.value = AudioPlaybackState.IDLE
                    naatProgressJob?.cancel()
                    true
                }

                prepareAsync()
            }
        } catch (e: Exception) {
            _naatPlaybackState.value = AudioPlaybackState.IDLE
        }
    }

    fun pauseNaat() {
        naatMediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                _naatPlaybackState.value = AudioPlaybackState.PAUSED
            }
        }
    }

    fun resumeNaat() {
        naatMediaPlayer?.let {
            it.start()
            _naatPlaybackState.value = AudioPlaybackState.PLAYING
            startNaatProgressTracker()
        }
    }

    fun seekNaat(seconds: Int) {
        naatMediaPlayer?.let {
            it.seekTo(seconds * 1000)
            _naatProgressSeconds.value = seconds
        }
    }

    fun stopNaat() {
        naatProgressJob?.cancel()
        naatMediaPlayer?.stop()
        naatMediaPlayer?.release()
        naatMediaPlayer = null
        _naatPlaybackState.value = AudioPlaybackState.IDLE
        _currentPlayingNaat.value = null
        _naatProgressSeconds.value = 0
    }

    private fun startNaatProgressTracker() {
        naatProgressJob?.cancel()
        naatProgressJob = viewModelScope.launch {
            while (_naatPlaybackState.value == AudioPlaybackState.PLAYING) {
                naatMediaPlayer?.let { mp ->
                    if (mp.isPlaying) {
                        _naatProgressSeconds.value = mp.currentPosition / 1000
                    }
                }
                delay(1000)
            }
        }
    }

    // Azan Alarms & Audio Logic
    val isAzanAudioPlaying: StateFlow<Boolean> = com.example.notification.AzanPlayerManager.isPlaying
    val currentPlayingAzanVoice: StateFlow<String?> = com.example.notification.AzanPlayerManager.currentPlayingVoice

    fun togglePrayerAlarm(prayerName: PrayerName) {
        viewModelScope.launch {
            preferences.togglePrayerAlarm(prayerName)
            syncPrayerAlarms()
        }
    }

    fun setAzanVoice(voice: String) {
        viewModelScope.launch {
            preferences.setAzanVoice(voice)
            syncPrayerAlarms()
        }
    }

    fun playAzanVoicePreview(context: Context, voiceId: String) {
        com.example.notification.AzanPlayerManager.playAzan(
            context = context,
            voiceId = voiceId,
            prayerName = "ٹیسٹ اذان",
            isFajr = false
        )
    }

    fun stopAzanAudio() {
        com.example.notification.AzanPlayerManager.stopAzan()
    }

    fun testAzanAlarm(context: Context, prayerName: PrayerName) {
        val schedule = _dailyPrayerSchedule.value ?: return
        val prayer = schedule.prayers.firstOrNull { it.name == prayerName } ?: return
        val receiver = com.example.notification.AzanNotificationReceiver()
        val intent = android.content.Intent().apply {
            action = com.example.notification.AzanAlarmScheduler.ACTION_AZAN_ALARM
            putExtra(com.example.notification.AzanNotificationReceiver.EXTRA_PRAYER_KEY, prayerName.name)
            putExtra(com.example.notification.AzanNotificationReceiver.EXTRA_PRAYER_NAME, prayerName.displayNameUrdu)
            putExtra(com.example.notification.AzanNotificationReceiver.EXTRA_PRAYER_TIME, prayer.timeFormatted)
            putExtra(com.example.notification.AzanNotificationReceiver.EXTRA_CITY_NAME, cityName.value)
            putExtra(com.example.notification.AzanNotificationReceiver.EXTRA_AZAN_VOICE, azanVoice.value)
        }
        receiver.onReceive(context, intent)
    }

    private var lastScheduledAlarmSignature: String? = null

    private fun syncPrayerAlarms() {
        val schedule = _dailyPrayerSchedule.value ?: return
        val enabledPrayers = mutableSetOf<PrayerName>()
        if (notifyFajr.value) enabledPrayers.add(PrayerName.FAJR)
        if (notifyDhuhr.value) enabledPrayers.add(PrayerName.DHUHR)
        if (notifyAsr.value) enabledPrayers.add(PrayerName.ASR)
        if (notifyMaghrib.value) enabledPrayers.add(PrayerName.MAGHRIB)
        if (notifyIsha.value) enabledPrayers.add(PrayerName.ISHA)

        // The countdown refreshes every second; avoid resetting exact alarms every second.
        // Include the local date so the next day's alarms are scheduled after midnight.
        val alarmSignature = listOf(
            schedule.dateGregorian,
            cityName.value,
            azanVoice.value,
            enabledPrayers.map { it.name }.sorted().joinToString(","),
            schedule.prayers.joinToString("|") { "${it.name.name}:${it.timeFormatted}" }
        ).joinToString("::")
        if (alarmSignature == lastScheduledAlarmSignature) return

        AzanAlarmScheduler.schedulePrayerAlarms(
            context = getApplication(),
            schedule = schedule,
            cityName = cityName.value,
            enabledPrayers = enabledPrayers,
            // Use the requested Madinah adhan for scheduled prayer alarms.
            azanVoice = "Madinah"
        )
        lastScheduledAlarmSignature = alarmSignature
    }

    // Knowledge Section Logic
    fun selectKnowledgeArticle(article: KnowledgeArticle?) {
        _selectedKnowledgeArticle.value = article
    }

    // Zakat Calculation
    fun updateZakatInput(newInput: ZakatInput) {
        _zakatInput.value = newInput
        _zakatResult.value = ZakatRepository.calculateZakat(newInput)
    }

    // Prayer Schedule Updates
    fun updatePrayerSchedule() {
        val lat = latitude.value
        val lon = longitude.value
        val tz = tzOffset.value
        val city = cityName.value
        val method = prayerMethod.value
        val madhab = asrMadhab.value

        val now = Calendar.getInstance()
        val schedule = PrayerCalculationRepository.getDailySchedule(now, lat, lon, tz, city, method, madhab)
        _dailyPrayerSchedule.value = schedule

        _qiblaBearing.value = PrayerCalculationRepository.calculateQiblaDirection(lat, lon)
        _distanceToKaabaKm.value = PrayerCalculationRepository.calculateDistanceToKaabaKm(lat, lon)

        syncPrayerAlarms()
    }

    private fun startPeriodicScheduleUpdates() {
        countdownTimerJob?.cancel()
        countdownTimerJob = viewModelScope.launch {
            while (true) {
                updatePrayerSchedule()
                delay(1000) // update countdown every second
            }
        }
    }

    private fun monitorQiblaAlignment() {
        viewModelScope.launch {
            sensorManager.azimuthFlow.collect { azimuth ->
                val qibla = _qiblaBearing.value.toFloat()
                var diff = abs(azimuth - qibla)
                if (diff > 180f) diff = 360f - diff
                val aligned = diff <= 4f
                if (aligned && !_isAlignedWithQibla.value) {
                    triggerHapticFeedback()
                }
                _isAlignedWithQibla.value = aligned
            }
        }
    }

    // Location Selection
    fun selectCity(city: CityLocation) {
        viewModelScope.launch {
            preferences.setCity(city.name, city.country, city.latitude, city.longitude, city.timeZoneOffsetHours)
            updatePrayerSchedule()
        }
    }

    fun requestGpsLocation(context: Context) {
        viewModelScope.launch {
            val location = LocationHelper.getCurrentLocation(context)
            if (location != null) {
                val tzHours = (java.util.TimeZone.getDefault().rawOffset / (1000.0 * 60 * 60))
                preferences.setCoordinates(location.latitude, location.longitude, tzHours, "My Location (GPS)")
                updatePrayerSchedule()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopAudio()
        sensorManager.stopListening()
        countdownTimerJob?.cancel()
    }
}
