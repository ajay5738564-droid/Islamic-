package com.example.data.model

data class TasbeehPreset(
    val id: String,
    val arabicText: String,
    val transliteration: String,
    val englishMeaning: String,
    val hindiMeaning: String,
    val defaultTarget: Int = 33
)

data class TasbeehSession(
    val id: Long = 0,
    val zikrTitle: String,
    val arabicText: String,
    val count: Int,
    val target: Int,
    val dateFormatted: String,
    val timestamp: Long = System.currentTimeMillis()
)
