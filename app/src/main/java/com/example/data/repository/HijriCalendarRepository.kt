package com.example.data.repository

import com.example.data.model.HijriDateInfo
import com.example.data.model.IslamicEvent
import java.util.Calendar

object HijriCalendarRepository {

    val ISLAMIC_MONTHS_EN = listOf(
        "Muharram", "Safar", "Rabi' al-Awwal", "Rabi' al-Thani",
        "Jumada al-Awwal", "Jumada al-Thani", "Rajab", "Sha'ban",
        "Ramadan", "Shawwal", "Dhu al-Qi'dah", "Dhu al-Hijjah"
    )

    val ISLAMIC_MONTHS_AR = listOf(
        "محرّم", "صفر", "ربيع الأول", "ربيع الثاني",
        "جمادى الأولى", "جمادى الآخرة", "رجب", "شعبان",
        "رمضان", "شوّال", "ذو القعدة", "ذو الحجّة"
    )

    fun getHijriDate(calendar: Calendar, adjustmentDays: Int = 0): HijriDateInfo {
        val cal = calendar.clone() as Calendar
        cal.add(Calendar.DAY_OF_MONTH, adjustmentDays)

        val y = cal.get(Calendar.YEAR)
        val m = cal.get(Calendar.MONTH) + 1
        val d = cal.get(Calendar.DAY_OF_MONTH)

        // Standard Kuweit / Umm Al Qura algorithmic conversion
        var jd = (1461 * (y + 4800 + (m - 14) / 12)) / 4 +
                (367 * (m - 2 - 12 * ((m - 14) / 12))) / 12 -
                (3 * ((y + 4900 + (m - 14) / 12) / 100)) / 4 +
                d - 32075

        val l = jd - 1948440 + 10632
        val n = (l - 1) / 10631
        val l2 = l - 10631 * n + 354
        val j = ((10985 - l2) / 5316) * ((50 * l2) / 17719) + (l2 / 5670) * ((43 * l2) / 15238)
        val l3 = l2 - ((30 - j) / 15) * ((17719 * j) / 50) - (j / 16) * ((15238 * j) / 43) + 29
        val hijriMonth = (24 * l3) / 709
        val hijriDay = l3 - (709 * hijriMonth) / 24
        val hijriYear = 30 * n + j - 30

        val safeMonth = (hijriMonth - 1).coerceIn(0, 11)
        val safeDay = hijriDay.coerceIn(1, 30)

        val monthNameEn = ISLAMIC_MONTHS_EN[safeMonth]
        val monthNameAr = ISLAMIC_MONTHS_AR[safeMonth]

        return HijriDateInfo(
            day = safeDay,
            month = safeMonth + 1,
            monthNameArabic = monthNameAr,
            monthNameEnglish = monthNameEn,
            year = hijriYear,
            formatted = "$safeDay $monthNameEn $hijriYear AH",
            formattedArabic = "$safeDay $monthNameAr $hijriYear هـ"
        )
    }

    fun getFormattedHijriDate(calendar: Calendar, adjustmentDays: Int = 0): String {
        return getHijriDate(calendar, adjustmentDays).formatted
    }

    fun getMajorIslamicEvents(): List<IslamicEvent> {
        return listOf(
            IslamicEvent(
                id = "islamic_new_year",
                title = "Islamic New Year (1448 AH)",
                titleArabic = "رأس السنة الهجرية",
                hijriDay = 1,
                hijriMonth = 1,
                monthName = "Muharram",
                estimatedDateGregorian = "June 2026",
                description = "Marks the first day of Muharram and the historic Hijrah migration of Prophet Muhammad (PBUH) from Makkah to Madinah.",
                isMajor = true
            ),
            IslamicEvent(
                id = "ashura",
                title = "Day of Ashura",
                titleArabic = "يوم عاشوراء",
                hijriDay = 10,
                hijriMonth = 1,
                monthName = "Muharram",
                estimatedDateGregorian = "June 2026",
                description = "Day of blessed fasting remembering Prophet Musa's (AS) deliverance and salvation of Bani Israel from Pharaoh.",
                isMajor = true
            ),
            IslamicEvent(
                id = "mawlid",
                title = "Mawlid an-Nabi",
                titleArabic = "المولد النبوي الشريف",
                hijriDay = 12,
                hijriMonth = 3,
                monthName = "Rabi' al-Awwal",
                estimatedDateGregorian = "August 2026",
                description = "Commemoration of the birth of our beloved Prophet Muhammad (peace be upon him).",
                isMajor = false
            ),
            IslamicEvent(
                id = "isra_miraj",
                title = "Al-Isra wal-Mi'raj",
                titleArabic = "الإسراء والمعراج",
                hijriDay = 27,
                hijriMonth = 7,
                monthName = "Rajab",
                estimatedDateGregorian = "December 2026",
                description = "The miraculous Night Journey of the Prophet (PBUH) to Jerusalem and ascension to the Heavens where 5 daily prayers were prescribed.",
                isMajor = true
            ),
            IslamicEvent(
                id = "nisfu_shaban",
                title = "Laylat al-Bara'ah (15th Sha'ban)",
                titleArabic = "ليلة النصف من شعبان",
                hijriDay = 15,
                hijriMonth = 8,
                monthName = "Sha'ban",
                estimatedDateGregorian = "January 2027",
                description = "The blessed Night of Forgiveness preceding the arrival of the holy month of Ramadan.",
                isMajor = false
            ),
            IslamicEvent(
                id = "ramadan_start",
                title = "Start of Blessed Ramadan",
                titleArabic = "بداية شهر رمضان المبارك",
                hijriDay = 1,
                hijriMonth = 9,
                monthName = "Ramadan",
                estimatedDateGregorian = "February 2027",
                description = "The commencement of the sacred month of daily fasting, nightly Taraweeh, intense Quran recitation, and charity.",
                isMajor = true
            ),
            IslamicEvent(
                id = "laylat_qadr",
                title = "Laylat al-Qadr (Night of Power)",
                titleArabic = "ليلة القدر",
                hijriDay = 27,
                hijriMonth = 9,
                monthName = "Ramadan",
                estimatedDateGregorian = "March 2027",
                description = "The Night of Destiny, better than a thousand months, during which the Holy Quran was first revealed.",
                isMajor = true
            ),
            IslamicEvent(
                id = "eid_fitr",
                title = "Eid al-Fitr",
                titleArabic = "عيد الفطر المبارك",
                hijriDay = 1,
                hijriMonth = 10,
                monthName = "Shawwal",
                estimatedDateGregorian = "March 2027",
                description = "The joyous festival of breaking the fast celebrated globally by the Muslim Ummah following Ramadan.",
                isMajor = true
            ),
            IslamicEvent(
                id = "day_arafah",
                title = "Day of Arafah",
                titleArabic = "يوم عرفة",
                hijriDay = 9,
                hijriMonth = 12,
                monthName = "Dhu al-Hijjah",
                estimatedDateGregorian = "May 2027",
                description = "The pinnacle of the Hajj pilgrimage where millions stand on Mount Arafat in heartfelt dua and repentance.",
                isMajor = true
            ),
            IslamicEvent(
                id = "eid_adha",
                title = "Eid al-Adha",
                titleArabic = "عيد الأضحى المبارك",
                hijriDay = 10,
                hijriMonth = 12,
                monthName = "Dhu al-Hijjah",
                estimatedDateGregorian = "May 2027",
                description = "The Festival of Sacrifice honoring the obedience and devotion of Prophet Ibrahim (AS) and Ismail (AS).",
                isMajor = true
            )
        )
    }
}
