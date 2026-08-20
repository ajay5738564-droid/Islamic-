package com.example.data.repository

import com.example.data.model.HijriDateInfo
import com.example.data.model.IslamicEvent
import java.util.Calendar

object HijriCalendarRepository {

    val ISLAMIC_MONTHS_UR = listOf(
        "محرم الحرام", "صفر المظفر", "ربیع الاول", "ربیع الثانی",
        "جمادی الاول", "جمادی الثانی", "رجب المرجب", "شعبان المعظم",
        "رمضان المبارک", "شوال المکرم", "ذی القعدہ", "ذی الحجہ"
    )

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
        val monthNameUr = ISLAMIC_MONTHS_UR[safeMonth]

        return HijriDateInfo(
            day = safeDay,
            month = safeMonth + 1,
            monthNameArabic = monthNameAr,
            monthNameEnglish = monthNameEn,
            monthNameUrdu = monthNameUr,
            year = hijriYear,
            formatted = "$safeDay $monthNameUr $hijriYear ھ",
            formattedUrdu = "$safeDay $monthNameUr $hijriYear ھ",
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
                title = "Islamic New Year",
                titleArabic = "رأس السنة الهجرية",
                titleUrdu = "یکم محرم الحرام - اسلامی سالِ نو",
                hijriDay = 1,
                hijriMonth = 1,
                monthName = "محرم الحرام",
                estimatedDateGregorian = "جون / جولائی",
                description = "Marks the first day of Muharram and the historic Hijrah migration of Prophet Muhammad (PBUH) from Makkah to Madinah.",
                descriptionUrdu = "اسلامی سال کا پہلا مبارک دن، حضرت محمد مصطفیٰ ﷺ کی مکہ مکرمہ سے مدینہ منورہ کی طرف تاریخی ہجرت کی یادگار۔",
                isMajor = true
            ),
            IslamicEvent(
                id = "ashura",
                title = "Day of Ashura (10th Muharram)",
                titleArabic = "يوم عاشوراء",
                titleUrdu = "۱۰ محرم الحرام - یومِ عاشورہ",
                hijriDay = 10,
                hijriMonth = 1,
                monthName = "محرم الحرام",
                estimatedDateGregorian = "جولائی",
                description = "Day of blessed fasting remembering Prophet Musa's (AS) deliverance from Pharaoh and the supreme sacrifice of Hazrat Imam Hussain (RA) at Karbala.",
                descriptionUrdu = "حضرت موسیٰ علیہ السلام کی فرعون سے نجات اور حضرت امام حسین رضی اللہ عنہ و شہدائے کربلا کی عظیم قربانی اور مسنون روزے کا دن۔",
                isMajor = true
            ),
            IslamicEvent(
                id = "mawlid",
                title = "Mawlid an-Nabi (12th Rabi-ul-Awwal)",
                titleArabic = "المولد النبوي الشريف",
                titleUrdu = "۱۲ ربیع الاول - عید میلاد النبی ﷺ",
                hijriDay = 12,
                hijriMonth = 3,
                monthName = "ربیع الاول",
                estimatedDateGregorian = "ستمبر",
                description = "Commemoration of the birth of our beloved Prophet Muhammad (peace and blessings be upon him), the mercy for all creation.",
                descriptionUrdu = "رحمتِ دو عالم، سرورِ کائنات، خاتم النبیین حضرت محمد مصطفیٰ ﷺ کی ولادتِ باسعادت کا پُر نور اور بابرکت دن۔",
                isMajor = true
            ),
            IslamicEvent(
                id = "isra_miraj",
                title = "Al-Isra wal-Mi'raj (27th Rajab)",
                titleArabic = "الإسراء والمعراج",
                titleUrdu = "۲۷ رجب المرجب - شبِ معراج",
                hijriDay = 27,
                hijriMonth = 7,
                monthName = "رجب المرجب",
                estimatedDateGregorian = "فروری",
                description = "The miraculous Night Journey of the Prophet (PBUH) to Masjid Al-Aqsa and ascension through the Heavens where 5 daily prayers were gifted.",
                descriptionUrdu = "نبی کریم ﷺ کا مسجد الحرام سے مسجد اقصیٰ اور پھر آسمانوں کا معجزاتی سفر، جہاں امت کو ۵ وقت کی نمازوں کا عظیم تحفہ ملا۔",
                isMajor = true
            ),
            IslamicEvent(
                id = "nisfu_shaban",
                title = "Laylat al-Bara'ah / Shab-e-Barat (15th Sha'ban)",
                titleArabic = "ليلة النصف من شعبان",
                titleUrdu = "۱۵ شعبان المعظم - شبِ برات",
                hijriDay = 15,
                hijriMonth = 8,
                monthName = "شعبان المعظم",
                estimatedDateGregorian = "فروری / مارچ",
                description = "The blessed Night of Salvation and Forgiveness preceding the arrival of the holy month of Ramadan.",
                descriptionUrdu = "مغفرت اور رحمت کی مقدس رات جس میں اللہ تعالیٰ اپنے بندوں کی توبہ قبول فرما کر گناہوں کو معاف فرماتا ہے۔",
                isMajor = true
            ),
            IslamicEvent(
                id = "ramadan_start",
                title = "Start of Blessed Ramadan (1st Ramadan)",
                titleArabic = "بداية شهر رمضان المبارک",
                titleUrdu = "یکم رمضان المبارک - ماہِ صیام کا آغاز",
                hijriDay = 1,
                hijriMonth = 9,
                monthName = "رمضان المبارک",
                estimatedDateGregorian = "مارچ",
                description = "The commencement of the sacred month of daily fasting, nightly Taraweeh, intense Quran recitation, and charity.",
                descriptionUrdu = "رحمت، مغفرت اور جہنم سے نجات کے مقدس مہینے کا آغاز، فرض روزے اور نمازِ تراویح کی شروعات۔",
                isMajor = true
            ),
            IslamicEvent(
                id = "laylat_qadr",
                title = "Laylat al-Qadr / Night of Power (27th Ramadan)",
                titleArabic = "ليلة القدر المباركة",
                titleUrdu = "۲۷ رمضان المبارک - شبِ قدر (طاق راتیں)",
                hijriDay = 27,
                hijriMonth = 9,
                monthName = "رمضان المبارک",
                estimatedDateGregorian = "اپریل",
                description = "The Night of Destiny, better than a thousand months (83 years), during which the Holy Quran was revealed.",
                descriptionUrdu = "ہزار مہینوں (۸۳ سال) سے افضل مبارک رات، جس میں قرآن مجید نازل ہوا اور فرشتے رحمتیں لے کر اترتے ہیں۔",
                isMajor = true
            ),
            IslamicEvent(
                id = "eid_fitr",
                title = "Eid al-Fitr (1st Shawwal)",
                titleArabic = "عيد الفطر المبارك",
                titleUrdu = "یکم شوال المکرم - عید الفطر",
                hijriDay = 1,
                hijriMonth = 10,
                monthName = "شوال المکرم",
                estimatedDateGregorian = "اپریل",
                description = "The joyous festival of breaking the fast celebrated globally by Muslims following the completion of Ramadan.",
                descriptionUrdu = "ماہِ رمضان کے روزوں اور عبادات کی تکمیل پر اللہ تعالیٰ کی طرف سے امتِ مسلمہ کے لیے شکر گزاری اور خوشی کا انعام۔",
                isMajor = true
            ),
            IslamicEvent(
                id = "day_arafah",
                title = "Day of Arafah (9th Dhu al-Hijjah)",
                titleArabic = "يوم عرفة المبارك",
                titleUrdu = "۹ ذی الحجہ - یومِ عرفہ (حج کا رکنِ اعظم)",
                hijriDay = 9,
                hijriMonth = 12,
                monthName = "ذی الحجہ",
                estimatedDateGregorian = "جون",
                description = "The pinnacle of the Hajj pilgrimage where millions stand on Mount Arafat in heartfelt repentance. Fasting on this day expiates sins of two years.",
                descriptionUrdu = "حج کا سب سے بڑا رکن اور میدانِ عرفات کا مقدس وقوف۔ اس دن کا روزہ پچھلے اور اگلے ایک سال کے گناہوں کا کفارہ بنتا ہے۔",
                isMajor = true
            ),
            IslamicEvent(
                id = "eid_adha",
                title = "Eid al-Adha & Qurbani (10th Dhu al-Hijjah)",
                titleArabic = "عيد الأضحى المبارك",
                titleUrdu = "۱۰ ذی الحجہ - عید الاضحیٰ (سنتِ ابراہیمی)",
                hijriDay = 10,
                hijriMonth = 12,
                monthName = "ذی الحجہ",
                estimatedDateGregorian = "جون",
                description = "The Festival of Sacrifice honoring the supreme obedience and devotion of Prophet Ibrahim (AS) and Ismail (AS).",
                descriptionUrdu = "حضرت ابراہیم علیہ السلام اور حضرت اسماعیل علیہ السلام کی لازوال قربانی کی یاد میں سنتِ ابراہیمی (قربانی) اور عید کا تہوار۔",
                isMajor = true
            ),
            IslamicEvent(
                id = "ayyam_beed",
                title = "Ayyam al-Beed (13th, 14th, 15th)",
                titleArabic = "أيام البيض",
                titleUrdu = "۱۳، ۱۴، ۱۵ قمری تاریخ - ایامِ بیض کے روزے",
                hijriDay = 13,
                hijriMonth = 0,
                monthName = "ہر اسلامی مہینہ",
                estimatedDateGregorian = "ماہانہ",
                description = "Fasting the three white days of each lunar month is equivalent to fasting the entire year.",
                descriptionUrdu = "ہر قمری مہینے کی ۱۳، ۱۴ اور ۱۵ تاریخ کو ۳ مسنون روزے رکھنا پورے سال کے روزوں کے برابر ثواب رکھتا ہے۔",
                isMajor = false
            )
        )
    }
}
