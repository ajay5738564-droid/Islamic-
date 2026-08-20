package com.example.data.model

data class HijriDateInfo(
    val day: Int,
    val month: Int,
    val monthNameArabic: String,
    val monthNameEnglish: String,
    val monthNameUrdu: String = "",
    val year: Int,
    val formatted: String,
    val formattedUrdu: String = formatted,
    val formattedArabic: String
)

data class IslamicEvent(
    val id: String,
    val title: String,
    val titleArabic: String,
    val titleUrdu: String = "",
    val hijriDay: Int,
    val hijriMonth: Int,
    val monthName: String,
    val monthNameUrdu: String = monthName,
    val estimatedDateGregorian: String,
    val description: String,
    val descriptionUrdu: String = "",
    val isMajor: Boolean = false
)
