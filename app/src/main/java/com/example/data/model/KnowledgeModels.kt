package com.example.data.model

enum class KnowledgeCategory(
    val displayName: String,
    val displayNameUrdu: String,
    val displayNameHindi: String,
    val iconName: String
) {
    SIX_KALIMAS("6 Kalimas of Islam", "چھ کلمے مع ترجمہ", "इस्लाम के ६ कलमे", "kalima"),
    PILLARS("Pillars of Islam", "اسلام کے ۵ ارکان", "इस्लाम के ५ अरकान", "pillars"),
    FAITH("Articles of Faith", "ایمان کے ۶ ارکان", "ईमान के ६ अरकान", "faith"),
    SEERAH("Seerat-un-Nabi ﷺ", "سیرت النبی ﷺ", "सीरत-उन-नबी ﷺ", "seerah"),
    PROPHETS("Stories of Prophets (Qisas)", "قصص الانبیاء علیہم السلام", "नबियों के किस्से (क़िससुल अंबिया)", "prophets"),
    TAHARAT("Purity, Wudu & Ghusl", "طہارت، وضو اور غسل", "पाकीज़गी, वुज़ू और ग़ुस्ल", "taharat"),
    JANAZAH_EID("Funeral & Eid Prayers", "نمازِ جنازہ اور عیدین", "जनाज़ा और ईद की नमाज़", "janazah"),
    RAMADAN_FASTING("Fasting & Ramadan Rules", "روزے کے احکام و مسائل", "रोज़े के अहकाम व मसाइल", "ramadan"),
    DAILY_AZKAR("Morning & Evening Azkar", "صبح و شام کے مسنون اذکار", "सुबह व शाम के अज़कार", "azkar"),
    MANNERS("Islamic Etiquette & Sunnah", "اسلامی آداب و سنتیں", "इस्लामी आदाब व सुन्नतें", "manners"),
    BEGINNERS("Beginner's Wudu & Salah", "وضو اور نماز کا طریقہ", "शुरुआती वुज़ू और नमाज़", "beginners"),
    NAMES_OF_ALLAH("99 Names of Allah", "اللہ کے ۹۹ نام", "अल्लाह के ९९ नाम", "names"),
    GLOSSARY("Islamic Terms Glossary", "اسلامی اصطلاحات", "इस्लामी शब्दावली", "glossary")
}

data class AllahName(
    val number: Int,
    val nameArabic: String,
    val transliteration: String,
    val meaningUrdu: String,
    val meaningHindi: String = "",
    val meaningEnglish: String,
    val benefitUrdu: String,
    val benefitHindi: String = ""
)

data class KnowledgeArticle(
    val id: String,
    val category: KnowledgeCategory,
    val title: String,
    val titleUrdu: String = title,
    val titleHindi: String = title,
    val titleArabic: String = "",
    val summary: String,
    val summaryUrdu: String = summary,
    val summaryHindi: String = summary,
    val sections: List<ArticleSection>,
    val reference: String
)

data class ArticleSection(
    val heading: String,
    val headingUrdu: String = heading,
    val headingHindi: String = heading,
    val body: String,
    val bodyUrdu: String = body,
    val bodyHindi: String = body,
    val arabicQuote: String? = null,
    val translationQuote: String? = null
)
