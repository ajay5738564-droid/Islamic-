package com.example.data.model

enum class RakatType(val labelUrdu: String, val labelEnglish: String) {
    FARZ("فرض", "Farz (Obligatory)"),
    SUNNAT_MUAKKADAH("سنتِ موکدہ", "Sunnat Muakkadah"),
    SUNNAT_GHAIR_MUAKKADAH("سنتِ غیر موکدہ", "Sunnat Ghair Muakkadah"),
    WITR("وتر واجب", "Witr Wajib"),
    NAFIL("نفل", "Nafil")
}

data class RakatBreakdownItem(
    val type: RakatType,
    val count: Int,
    val noteUrdu: String = ""
)

data class NamazPrayerDetail(
    val prayerName: PrayerName,
    val nameUrdu: String,
    val nameArabic: String,
    val totalRakats: Int,
    val breakdownUrdu: String,
    val rakatsList: List<RakatBreakdownItem>,
    val timingUrdu: String,
    val importanceUrdu: String,
    val hadithVirtueUrdu: String,
    val hadithReference: String
)

data class NamazStepItem(
    val stepNumber: Int,
    val stepTitleUrdu: String,
    val stepTitleArabic: String,
    val arabicText: String = "",
    val transliteration: String = "",
    val translationUrdu: String = "",
    val procedureUrdu: String,
    val notesUrdu: String = ""
)

data class DailyPrayerTrackerState(
    val dateStr: String,
    val fajrDone: Boolean = false,
    val dhuhrDone: Boolean = false,
    val asrDone: Boolean = false,
    val maghribDone: Boolean = false,
    val ishaDone: Boolean = false
) {
    val completedCount: Int
        get() = (if (fajrDone) 1 else 0) +
                (if (dhuhrDone) 1 else 0) +
                (if (asrDone) 1 else 0) +
                (if (maghribDone) 1 else 0) +
                (if (ishaDone) 1 else 0)

    val isAllCompleted: Boolean get() = completedCount == 5
}
