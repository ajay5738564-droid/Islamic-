package com.example.data.repository

enum class AzanCategory(val titleUrdu: String, val titleEnglish: String) {
    SUNNI_HANAFI("اہلِ سنت / حنفی و شافعی اذانیں", "Sunni / Hanafi & Shafi'i Azan"),
    SHIA_JAFARI("فقہ جعفریہ (شیعہ) اذان", "Shia / Jafari Azan"),
    RINGTONE_ALERT("مختصر نماز رنگ ٹونز و تکبیرات", "Short Prayer Ringtones & Takbeer")
}

data class AzanVoiceItem(
    val id: String,
    val titleUrdu: String,
    val titleEnglish: String,
    val muazzinUrdu: String,
    val audioUrl: String,
    val category: AzanCategory = AzanCategory.SUNNI_HANAFI,
    val description: String = ""
)

object AzanAudioRepository {

    val AZAN_VOICES = listOf(
        // 1. Sunni / Hanafi / Shafi'i
        AzanVoiceItem(
            id = "Makkah",
            titleUrdu = "اذانِ مکہ مکرمہ (مسجد الحرام)",
            titleEnglish = "Makkah Al-Mukarramah (Haram)",
            muazzinUrdu = "الشیخ علی احمد ملا",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_makkah.mp3",
            category = AzanCategory.SUNNI_HANAFI,
            description = "مسجد الحرام مکہ کی روایتی اور روح پرور اذان"
        ),
        AzanVoiceItem(
            id = "Madinah",
            titleUrdu = "اذانِ مدینہ منورہ (مسجد نبوی ﷺ)",
            titleEnglish = "Madinah Al-Munawwarah (Nabawi)",
            muazzinUrdu = "الشیخ عصام بخاری",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_madinah.mp3",
            category = AzanCategory.SUNNI_HANAFI,
            description = "گنبد خضریٰ اور مسجد نبوی شریف کی دلنشین اذان"
        ),
        AzanVoiceItem(
            id = "AlAqsa",
            titleUrdu = "اذانِ مسجدِ اقصیٰ (القدس الشریف)",
            titleEnglish = "Al-Aqsa Mosque (Jerusalem)",
            muazzinUrdu = "موذنِ مسجدِ اقصیٰ",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_aqsa.mp3",
            category = AzanCategory.SUNNI_HANAFI,
            description = "قبلہ اول مسجد اقصیٰ کی تاریخی اذان"
        ),
        AzanVoiceItem(
            id = "Egypt",
            titleUrdu = "اذانِ مصر (الشیخ عبدالباسط)",
            titleEnglish = "Egypt (Sheikh Abdul Basit)",
            muazzinUrdu = "الشیخ عبدالباسط عبدالصمد",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_abdulbaset.mp3",
            category = AzanCategory.SUNNI_HANAFI,
            description = "عالمِ اسلام کے عظیم قاری الشیخ عبدالباسط کی پرسوز آواز"
        ),
        AzanVoiceItem(
            id = "Mishary",
            titleUrdu = "اذانِ مشاری راشد العفاسی",
            titleEnglish = "Mishary Rashid Alafasy",
            muazzinUrdu = "الشیخ مشاری راشد العفاسی",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_mishary.mp3",
            category = AzanCategory.SUNNI_HANAFI,
            description = "کویت کے مشہور قاری مشاری راشد کی مسحور کن اذان"
        ),
        AzanVoiceItem(
            id = "FajrSpecial",
            titleUrdu = "فجر کی خصوصی اذان (الصلاة خیر من النوم)",
            titleEnglish = "Fajr Special (As-Salatu Khayr)",
            muazzinUrdu = "موذنِ حرم مکی",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_fajr.mp3",
            category = AzanCategory.SUNNI_HANAFI,
            description = "نماز نیند سے بہتر ہے کے کلمات کے ساتھ فجر اذان"
        ),

        // 2. Shia / Jafari
        AzanVoiceItem(
            id = "ShiaJafari",
            titleUrdu = "اذانِ فقہ جعفریہ (شیعہ اذان)",
            titleEnglish = "Shia / Jafari Azan",
            muazzinUrdu = "اذان با اشہد ان علیا ولی اللہ",
            audioUrl = "https://ia800301.us.archive.org/25/items/AdhanShia/AdhanShia.mp3",
            category = AzanCategory.SHIA_JAFARI,
            description = "حی علی خیر العمل اور شہادتِ ولایتِ علیؑ کے ساتھ"
        ),

        // 3. Short Ringtones & Takbeer Alerts
        AzanVoiceItem(
            id = "TakbeerRingtone",
            titleUrdu = "تکبیراتِ نماز رنگ ٹون (اللہ اکبر)",
            titleEnglish = "Takbeerat Prayer Ringtone",
            muazzinUrdu = "اللہ اکبر اللہ اکبر",
            audioUrl = "https://everyayah.com/data/Alafasy_128kbps/001001.mp3",
            category = AzanCategory.RINGTONE_ALERT,
            description = "مختصر اور خوبصورت تکبیر کی رنگ ٹون"
        ),
        AzanVoiceItem(
            id = "MakkahShort",
            titleUrdu = "مختصر اذان رنگ ٹون (Short Alert)",
            titleEnglish = "Short Azan Ringtone Alert",
            muazzinUrdu = "مختصر تکبیر و اذاں",
            audioUrl = "https://download.quranicaudio.com/adhan/adhan_makkah.mp3",
            category = AzanCategory.RINGTONE_ALERT,
            description = "دفتر اور سفر کے لیے تیز و مختصر اذان رنگ ٹون"
        )
    )

    const val FAJR_AZAN_URL = "https://download.quranicaudio.com/adhan/adhan_fajr.mp3"

    fun getAudioUrl(voiceId: String, isFajr: Boolean = false): String {
        if (isFajr && (voiceId == "Makkah" || voiceId == "FajrSpecial")) {
            return FAJR_AZAN_URL
        }
        return AZAN_VOICES.firstOrNull { it.id.equals(voiceId, ignoreCase = true) }?.audioUrl
            ?: AZAN_VOICES.first().audioUrl
    }
}
