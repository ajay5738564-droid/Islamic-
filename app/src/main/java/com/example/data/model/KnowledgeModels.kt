package com.example.data.model

enum class KnowledgeCategory(val displayName: String, val iconName: String) {
    PILLARS("Pillars of Islam", "pillars"),
    FAITH("Articles of Faith", "faith"),
    PROPHETS("Stories of Prophets", "prophets"),
    SEERAH("Seerah of Prophet (PBUH)", "seerah"),
    SAHABAH("The Companions (Sahabah)", "sahabah"),
    MANNERS("Islamic Etiquette (Adab)", "manners"),
    BEGINNERS("Beginner's Wudu & Salah", "beginners"),
    GLOSSARY("Islamic Terms Glossary", "glossary")
}

data class KnowledgeArticle(
    val id: String,
    val category: KnowledgeCategory,
    val title: String,
    val titleArabic: String = "",
    val summary: String,
    val sections: List<ArticleSection>,
    val reference: String
)

data class ArticleSection(
    val heading: String,
    val body: String,
    val arabicQuote: String? = null,
    val translationQuote: String? = null
)
