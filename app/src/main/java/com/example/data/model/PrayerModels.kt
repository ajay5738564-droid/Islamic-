package com.example.data.model

enum class PrayerName(val displayName: String, val arabicName: String, val displayNameUrdu: String) {
    FAJR("Fajr", "الفجر", "فجر"),
    SUNRISE("Sunrise", "الشروق", "طلوعِ آفتاب"),
    DHUHR("Dhuhr", "الظهر", "ظہر"),
    ASR("Asr", "العصر", "عصر"),
    MAGHRIB("Maghrib", "المغرب", "مغرب"),
    ISHA("Isha", "العشاء", "عشاء")
}

data class PrayerTimeSlot(
    val name: PrayerName,
    val timeFormatted: String, // "05:12 AM"
    val hour: Int,
    val minute: Int,
    val isCurrent: Boolean = false,
    val isNext: Boolean = false,
    val isPassed: Boolean = false
)

enum class CalculationMethod(val title: String, val description: String) {
    MWL("Muslim World League", "Fajr: 18°, Isha: 17° (Europe, Far East, parts of US)"),
    ISNA("Islamic Society of North America", "Fajr: 15°, Isha: 15° (North America)"),
    EGYPT("Egyptian General Authority", "Fajr: 19.5°, Isha: 17.5° (Africa, Syria, Lebanon)"),
    MAKKAH("Umm al-Qura University, Makkah", "Fajr: 18.5°, Isha: 90 min after Maghrib (Arabian Peninsula)"),
    KARACHI("Univ. of Islamic Sciences, Karachi", "Fajr: 18°, Isha: 18° (Pakistan, India, Bangladesh)"),
    DUBAI("Dubai / UAE", "Fajr: 18.2°, Isha: 18.2° (UAE & Gulf)");

    val displayName: String get() = title
}

enum class AsrJuristic(val title: String, val description: String) {
    STANDARD("Shafi'i, Maliki, Hanbali", "Shadow length equals object length"),
    HANAFI("Hanafi", "Shadow length equals twice the object length");

    val displayName: String get() = title
}

data class CityLocation(
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val timeZoneOffsetHours: Double
)

data class DailyPrayerSchedule(
    val dateGregorian: String,
    val dateHijri: String,
    val locationName: String,
    val prayers: List<PrayerTimeSlot>,
    val nextPrayer: PrayerTimeSlot?,
    val timeUntilNextFormatted: String,
    val countdownSeconds: Long,
    val progressFraction: Float
) {
    val fajr: PrayerTimeSlot? get() = prayers.find { it.name == PrayerName.FAJR }
    val maghrib: PrayerTimeSlot? get() = prayers.find { it.name == PrayerName.MAGHRIB }
}
