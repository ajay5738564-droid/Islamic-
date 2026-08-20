package com.example.data.model

data class DuaCategory(
    val id: String,
    val nameEnglish: String,
    val nameHindi: String,
    val nameUrdu: String,
    val iconName: String,
    val totalDuas: Int
)

data class DuaItem(
    val id: String,
    val categoryId: String,
    val titleEnglish: String,
    val titleHindi: String,
    val titleUrdu: String,
    val arabicText: String,
    val transliteration: String,
    val englishTranslation: String,
    val hindiTranslation: String,
    val urduTranslation: String,
    val reference: String,
    val benefit: String = "",
    val recommendedCount: Int = 1
)
