package com.example.data.model

enum class NaatCategory(val displayNameUrdu: String, val displayNameEnglish: String) {
    ALL("تمام کلام", "All"),
    HAMD("حمدِ باری تعالیٰ", "Hamd"),
    NAAT("نعتِ رسولِ مقبول ﷺ", "Naat"),
    SALAM("سلام و درود شریف", "Salam & Durood"),
    MANQABAT("منقبت و مناجات", "Manqabat")
}

data class NaatItem(
    val id: String,
    val titleUrdu: String,
    val titleEnglish: String,
    val artistUrdu: String,
    val artistEnglish: String,
    val category: NaatCategory,
    val duration: String,
    val audioUrl: String,
    val lyricsUrdu: List<String>,
    val descriptionUrdu: String = "",
    val isFeatured: Boolean = false
)
