package com.example.data.repository

data class AzanVoiceItem(
    val id: String,
    val titleUrdu: String,
    val titleEnglish: String,
    val muazzinUrdu: String,
    val audioUrl: String
)

object AzanAudioRepository {

    val AZAN_VOICES = listOf(
        AzanVoiceItem(
            id = "Makkah",
            titleUrdu = "اذانِ مکہ مکرمہ (مسجد الحرام)",
            titleEnglish = "Makkah Al-Mukarramah (Haram)",
            muazzinUrdu = "الشیخ علی احمد ملا",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_makkah.mp3"
        ),
        AzanVoiceItem(
            id = "Madinah",
            titleUrdu = "اذانِ مدینہ منورہ (مسجد نبوی ﷺ)",
            titleEnglish = "Madinah Al-Munawwarah (Nabawi)",
            muazzinUrdu = "الشیخ عصام بخاری",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_madinah.mp3"
        ),
        AzanVoiceItem(
            id = "AlAqsa",
            titleUrdu = "اذانِ مسجدِ اقصیٰ (القدس الشریف)",
            titleEnglish = "Al-Aqsa Mosque (Jerusalem)",
            muazzinUrdu = "موذنِ مسجدِ اقصیٰ",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_aqsa.mp3"
        ),
        AzanVoiceItem(
            id = "Egypt",
            titleUrdu = "اذانِ مصر (الشیخ عبدالباسط)",
            titleEnglish = "Egypt (Sheikh Abdul Basit)",
            muazzinUrdu = "الشیخ عبدالباسط عبدالصمد",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_abdulbaset.mp3"
        ),
        AzanVoiceItem(
            id = "Traditional",
            titleUrdu = "اذانِ مشاری راشد العفاسی",
            titleEnglish = "Mishary Rashid Alafasy",
            muazzinUrdu = "الشیخ مشاری راشد العفاسی",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_mishary.mp3"
        )
    )

    const val FAJR_AZAN_URL = "https://download.quranicaudio.com/adhan/adhan_fajr.mp3"

    fun getAudioUrl(voiceId: String, isFajr: Boolean = false): String {
        if (isFajr) return FAJR_AZAN_URL
        return AZAN_VOICES.firstOrNull { it.id.equals(voiceId, ignoreCase = true) }?.audioUrl
            ?: AZAN_VOICES.first().audioUrl
    }
}
