package com.example.ui.util

import com.example.data.model.PrayerName
import com.example.data.repository.AppLanguage

object AppStrings {

    fun appTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "دینوفا اسلامی گائیڈ"
        AppLanguage.HINDI -> "दीनोवा इस्लामिक गाइड"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Deenova Islamic Guide"
    }

    fun tabHome(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "ہوم"
        AppLanguage.HINDI -> "होम"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Home"
    }

    fun tabQuran(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "قرآن پاک"
        AppLanguage.HINDI -> "क़ुरआन पाक"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Quran"
    }

    fun tabPrayer(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "نماز"
        AppLanguage.HINDI -> "नमाज़"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Prayer"
    }

    fun tabExplore(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "اسلامی معلومات"
        AppLanguage.HINDI -> "इस्लामी ज्ञान"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Explore"
    }

    fun tabSettings(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "ترتیبات"
        AppLanguage.HINDI -> "सेटिंग्स"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Settings"
    }

    // Exit Dialog
    fun exitDialogTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "ایپ سے باہر نکلیں؟"
        AppLanguage.HINDI -> "ऐप से बाहर निकलें?"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Exit Application?"
    }

    fun exitDialogMessage(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "کیا آپ واقعی دینوفا اسلامی ایپ بند کرنا چاہتے ہیں؟"
        AppLanguage.HINDI -> "क्या आप वाकई दीनोवा इस्लामिक ऐप बंद करना चाहते हैं?"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Are you sure you want to close Deenova Islamic App?"
    }

    fun exitDialogConfirm(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "ہاں، بند کریں"
        AppLanguage.HINDI -> "हाँ, बंद करें"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Exit App"
    }

    fun exitDialogCancel(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "نہیں، جاری رکھیں"
        AppLanguage.HINDI -> "रद्द करें"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Cancel"
    }

    // Language Dialog
    fun selectLanguageTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "زبان تبدیل کریں (Select Language)"
        AppLanguage.HINDI -> "भाषा चुनें (Select Language)"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Select Language (زبان / भाषा)"
    }

    // Prayer names
    fun prayerName(prayer: PrayerName, lang: AppLanguage): String = when (lang) {
        AppLanguage.URDU -> prayer.displayNameUrdu
        AppLanguage.HINDI -> when (prayer) {
            PrayerName.FAJR -> "फ़ज्र"
            PrayerName.SUNRISE -> "तुलू-ए-आफ़्ताब"
            PrayerName.DHUHR -> "ज़ुहर"
            PrayerName.ASR -> "असर"
            PrayerName.MAGHRIB -> "मग़रिब"
            PrayerName.ISHA -> "इशा"
        }
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> prayer.displayName
    }

    // Common Buttons & Labels
    fun back(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "واپس"
        AppLanguage.HINDI -> "पीछे"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Back"
    }

    fun searchPlaceholder(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "تلاش کریں..."
        AppLanguage.HINDI -> "खोजें..."
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Search..."
    }

    fun todayNamazTracker(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "آج کی نمازوں کا ریکارڈ (ٹریکر)"
        AppLanguage.HINDI -> "आज की ५ नमाज़ों का ट्रैकर"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Today's 5 Prayers Tracker"
    }

    fun completedPrayers(completed: Int, lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "$completed میں سے ۵ نمازیں ادا کی گئیں"
        AppLanguage.HINDI -> "५ में से $completed नमाज़ें पूरी हुईं"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "$completed of 5 prayers completed"
    }

    fun quickFeatures(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "فوری اسلامی خدمات و معلومات"
        AppLanguage.HINDI -> "प्रमुख इस्लामी सुविधाएं व ज्ञान"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Quick Islamic Features & Knowledge"
    }

    fun verseOfTheDay(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "آج کی منتخب آیت"
        AppLanguage.HINDI -> "आज की आयत"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Verse of the Day"
    }

    fun duaOfTheDay(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "آج کی مسنون دعا"
        AppLanguage.HINDI -> "आज की मसनून दुआ"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Daily Supplication (Dua)"
    }

    fun hadithOfTheDay(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "آج کی حدیثِ مبارکہ"
        AppLanguage.HINDI -> "आज की हदीस शरीफ़"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Hadith of the Day"
    }

    fun upcomingEvents(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "آنے والے اہم اسلامی ایام"
        AppLanguage.HINDI -> "आने वाले मुख्य इस्लामी दिन"
        AppLanguage.ENGLISH, AppLanguage.ARABIC -> "Upcoming Islamic Occasions"
    }
}
