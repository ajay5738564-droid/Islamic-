package com.example.data.model

data class HadithItem(
    val id: String,
    val collectionName: String,
    val hadithNumber: String,
    val topicEnglish: String,
    val topicHindi: String,
    val narrator: String,
    val arabicText: String,
    val englishTranslation: String,
    val hindiTranslation: String,
    val urduTranslation: String,
    val grade: String, // "Sahih", "Hasan"
    val reference: String,
    val explanation: String = ""
)
