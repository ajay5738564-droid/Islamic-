package com.example.data.model

data class Surah(
    val number: Int,
    val nameArabic: String,
    val nameEnglish: String,
    val englishMeaning: String,
    val revelationType: String, // "Meccan" or "Medinan"
    val numberOfAyahs: Int,
    val startingJuz: Int
)

data class Ayah(
    val number: Int, // Global ayah index (1-6236)
    val surahNumber: Int,
    val numberInSurah: Int,
    val textArabic: String,
    val textEnglish: String,
    val textHindi: String = "",
    val textUrdu: String = "",
    val transliteration: String = "",
    val juz: Int = 1,
    val audioUrl: String = ""
)

data class SurahDetail(
    val surah: Surah,
    val ayahs: List<Ayah>
)

enum class BookmarkType {
    AYAH,
    QURAN_AYAH,
    QURAN_SURAH,
    DUA,
    HADITH,
    ARTICLE
}

data class BookmarkItem(
    val id: String,
    val type: BookmarkType,
    val title: String,
    val subtitle: String,
    val arabicText: String = "",
    val reference: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
