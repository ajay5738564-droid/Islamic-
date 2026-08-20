package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    val id: String,
    val type: String, // "QURAN_AYAH", "QURAN_SURAH", "DUA", "HADITH", "ARTICLE"
    val title: String,
    val subtitle: String,
    val arabicText: String,
    val reference: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "tasbeeh_history")
data class TasbeehHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val zikrTitle: String,
    val arabicText: String,
    val count: Int,
    val target: Int,
    val dateFormatted: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "offline_ayahs", primaryKeys = ["surahNumber", "numberInSurah"])
data class OfflineAyahEntity(
    val surahNumber: Int,
    val numberInSurah: Int,
    val numberGlobal: Int,
    val textArabic: String,
    val textEnglish: String,
    val textHindi: String,
    val textUrdu: String,
    val transliteration: String,
    val juz: Int,
    val audioUrl: String
)
