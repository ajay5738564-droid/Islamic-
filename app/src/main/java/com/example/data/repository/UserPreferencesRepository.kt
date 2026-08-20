package com.example.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.model.AsrJuristic
import com.example.data.model.CalculationMethod
import com.example.data.model.DailyPrayerTrackerState
import com.example.data.model.PrayerName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val Context.dataStore by preferencesDataStore(name = "deenova_prefs")

enum class AppLanguage(val code: String, val displayName: String, val nativeName: String) {
    URDU("ur", "Urdu", "اردو"),
    ENGLISH("en", "English", "English"),
    HINDI("hi", "Hindi", "हिन्दी"),
    ARABIC("ar", "Arabic", "العربية")
}

class UserPreferencesRepository(private val context: Context) {

    private object PreferencesKeys {
        val LANGUAGE = stringPreferencesKey("app_language")
        val CALCULATION_METHOD = stringPreferencesKey("prayer_method")
        val ASR_MADHAB = stringPreferencesKey("asr_madhab")
        val SELECTED_CITY = stringPreferencesKey("selected_city")
        val SELECTED_COUNTRY = stringPreferencesKey("selected_country")
        val LATITUDE = doublePreferencesKey("user_lat")
        val LONGITUDE = doublePreferencesKey("user_lon")
        val TIMEZONE_OFFSET = doublePreferencesKey("tz_offset")
        val USE_AUTO_GPS = booleanPreferencesKey("use_auto_gps")
        val HIJRI_ADJUSTMENT = intPreferencesKey("hijri_adjustment")
        val ARABIC_FONT_SIZE = intPreferencesKey("arabic_font_size")
        val NOTIFY_FAJR = booleanPreferencesKey("notify_fajr")
        val NOTIFY_DHUHR = booleanPreferencesKey("notify_dhuhr")
        val NOTIFY_ASR = booleanPreferencesKey("notify_asr")
        val NOTIFY_MAGHRIB = booleanPreferencesKey("notify_maghrib")
        val NOTIFY_ISHA = booleanPreferencesKey("notify_isha")
        val NOTIFY_DAILY_DUA = booleanPreferencesKey("notify_daily_dua")
        val TASBEEH_VIBRATE = booleanPreferencesKey("tasbeeh_vibrate")
        val TASBEEH_SOUND = booleanPreferencesKey("tasbeeh_sound")
        val DARK_THEME = booleanPreferencesKey("dark_theme")

        fun prayerKey(dateStr: String, prayer: PrayerName) = booleanPreferencesKey("prayer_${dateStr}_${prayer.name}")
    }

    val languageFlow: Flow<AppLanguage> = context.dataStore.data.map { prefs ->
        val code = prefs[PreferencesKeys.LANGUAGE] ?: "ur"
        AppLanguage.values().find { it.code == code } ?: AppLanguage.URDU
    }

    val calculationMethodFlow: Flow<CalculationMethod> = context.dataStore.data.map { prefs ->
        val name = prefs[PreferencesKeys.CALCULATION_METHOD] ?: CalculationMethod.KARACHI.name
        try { CalculationMethod.valueOf(name) } catch (e: Exception) { CalculationMethod.KARACHI }
    }

    val asrMadhabFlow: Flow<AsrJuristic> = context.dataStore.data.map { prefs ->
        val name = prefs[PreferencesKeys.ASR_MADHAB] ?: AsrJuristic.HANAFI.name
        try { AsrJuristic.valueOf(name) } catch (e: Exception) { AsrJuristic.HANAFI }
    }

    val selectedCityFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.SELECTED_CITY] ?: "New Delhi"
    }

    val selectedCountryFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.SELECTED_COUNTRY] ?: "India"
    }

    val latitudeFlow: Flow<Double> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.LATITUDE] ?: 28.6139
    }

    val longitudeFlow: Flow<Double> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.LONGITUDE] ?: 77.2090
    }

    val tzOffsetFlow: Flow<Double> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.TIMEZONE_OFFSET] ?: 5.5
    }

    val useAutoGpsFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.USE_AUTO_GPS] ?: false
    }

    val hijriAdjustmentFlow: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.HIJRI_ADJUSTMENT] ?: 0
    }

    val arabicFontSizeFlow: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.ARABIC_FONT_SIZE] ?: 26
    }

    val tasbeehVibrateFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.TASBEEH_VIBRATE] ?: true
    }

    val tasbeehSoundFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.TASBEEH_SOUND] ?: true
    }

    val notifyDailyDuaFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.NOTIFY_DAILY_DUA] ?: true
    }

    suspend fun setLanguage(language: AppLanguage) {
        context.dataStore.edit { it[PreferencesKeys.LANGUAGE] = language.code }
    }

    suspend fun setCalculationMethod(method: CalculationMethod) {
        context.dataStore.edit { it[PreferencesKeys.CALCULATION_METHOD] = method.name }
    }

    suspend fun setAsrMadhab(madhab: AsrJuristic) {
        context.dataStore.edit { it[PreferencesKeys.ASR_MADHAB] = madhab.name }
    }

    suspend fun setCity(city: String, country: String, lat: Double, lon: Double, tzOffset: Double) {
        context.dataStore.edit {
            it[PreferencesKeys.SELECTED_CITY] = city
            it[PreferencesKeys.SELECTED_COUNTRY] = country
            it[PreferencesKeys.LATITUDE] = lat
            it[PreferencesKeys.LONGITUDE] = lon
            it[PreferencesKeys.TIMEZONE_OFFSET] = tzOffset
            it[PreferencesKeys.USE_AUTO_GPS] = false
        }
    }

    suspend fun setCoordinates(lat: Double, lon: Double, tzOffset: Double, locationName: String) {
        context.dataStore.edit {
            it[PreferencesKeys.LATITUDE] = lat
            it[PreferencesKeys.LONGITUDE] = lon
            it[PreferencesKeys.TIMEZONE_OFFSET] = tzOffset
            it[PreferencesKeys.SELECTED_CITY] = locationName
            it[PreferencesKeys.USE_AUTO_GPS] = true
        }
    }

    suspend fun setHijriAdjustment(adjustment: Int) {
        context.dataStore.edit { it[PreferencesKeys.HIJRI_ADJUSTMENT] = adjustment }
    }

    suspend fun setArabicFontSize(size: Int) {
        context.dataStore.edit { it[PreferencesKeys.ARABIC_FONT_SIZE] = size }
    }

    suspend fun setTasbeehVibrate(enabled: Boolean) {
        context.dataStore.edit { it[PreferencesKeys.TASBEEH_VIBRATE] = enabled }
    }

    suspend fun setTasbeehSound(enabled: Boolean) {
        context.dataStore.edit { it[PreferencesKeys.TASBEEH_SOUND] = enabled }
    }

    fun getDailyPrayerTrackerFlow(dateStr: String): Flow<DailyPrayerTrackerState> {
        return context.dataStore.data.map { prefs ->
            DailyPrayerTrackerState(
                dateStr = dateStr,
                fajrDone = prefs[PreferencesKeys.prayerKey(dateStr, PrayerName.FAJR)] ?: false,
                dhuhrDone = prefs[PreferencesKeys.prayerKey(dateStr, PrayerName.DHUHR)] ?: false,
                asrDone = prefs[PreferencesKeys.prayerKey(dateStr, PrayerName.ASR)] ?: false,
                maghribDone = prefs[PreferencesKeys.prayerKey(dateStr, PrayerName.MAGHRIB)] ?: false,
                ishaDone = prefs[PreferencesKeys.prayerKey(dateStr, PrayerName.ISHA)] ?: false
            )
        }
    }

    suspend fun togglePrayerDone(prayerName: PrayerName, dateStr: String) {
        context.dataStore.edit { prefs ->
            val key = PreferencesKeys.prayerKey(dateStr, prayerName)
            val current = prefs[key] ?: false
            prefs[key] = !current
        }
    }
}
