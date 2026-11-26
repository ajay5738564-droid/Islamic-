package com.example.data.repository

import com.example.data.model.NaatCategory
import com.example.data.model.NaatItem

object NaatRepository {

    // Additional catalogue entries. These are catalogue-only until a verified audio recording is supplied.
    private val ADDITIONAL_NAATS = listOf(
        Triple("یا الٰہی ہر جگہ تیری عطا کا ساتھ ہو", "Ya Ilahi Har Jagah Teri Ata Ka Sath Ho", NaatCategory.MANQABAT),
        Triple("میں سو جاؤں یا مصطفیٰ کہتے کہتے", "Main So Jaun Ya Mustafa Kehte Kehte", NaatCategory.NAAT),
        Triple("سب سے اولیٰ و اعلیٰ ہمارا نبی", "Sab Se Aala O Aala Hamara Nabi", NaatCategory.NAAT),
        Triple("وہ کمالِ حسنِ حضور ہے", "Woh Kamal-e-Husn-e-Huzoor Hai", NaatCategory.NAAT),
        Triple("سرکار کا دربار ہے", "Sarkar Ka Darbar Hai", NaatCategory.NAAT),
        Triple("مدینے کا سفر ہے اور میں نم دیدہ نم دیدہ", "Madine Ka Safar Hai", NaatCategory.NAAT),
        Triple("شاہِ مدینہ یثرب کے والی", "Shah-e-Madina Yathrib Ke Wali", NaatCategory.NAAT),
        Triple("بلغ العلیٰ بکمالہ", "Bala-al-Ula Bi-Kamalihi", NaatCategory.SALAM),
        Triple("طلع البدر علینا", "Tala-al-Badru Alayna", NaatCategory.SALAM),
        Triple("درودِ پاک پڑھتے رہو", "Durood-e-Pak Parhte Raho", NaatCategory.SALAM),
        Triple("یا نبی سلام علیک", "Ya Nabi Salam Alayka (Traditional)", NaatCategory.SALAM),
        Triple("اے سبز گنبد والے", "Ae Sabz Gumbad Wale", NaatCategory.NAAT),
        Triple("میرے آقا آؤ کہ مدت ہوئی ہے", "Mere Aaqa Aao Ke Muddat Hui Hai", NaatCategory.NAAT),
        Triple("وہی خدا ہے", "Wohi Khuda Hai", NaatCategory.HAMD),
        Triple("لب پہ آتی ہے دعا", "Lab Pe Aati Hai Dua", NaatCategory.HAMD),
        Triple("اللہ ہو اللہ ہو", "Allah Hu Allah Hu", NaatCategory.HAMD),
        Triple("تیری ذات پاک ہے", "Teri Zaat Paak Hai", NaatCategory.HAMD),
        Triple("یا رب العالمین", "Ya Rabbal Aalameen", NaatCategory.HAMD),
        Triple("میرے آقا کی آمد", "Mere Aaqa Ki Aamad", NaatCategory.NAAT),
        Triple("نبی نبی یا نبی", "Nabi Nabi Ya Nabi", NaatCategory.NAAT),
        Triple("مدینے والے سے میرا سلام کہنا", "Madine Wale Se Mera Salam Kehna", NaatCategory.SALAM),
        Triple("کاش میں مدینے جاتا", "Kaash Main Madine Jata", NaatCategory.NAAT),
        Triple("شہرِ مدینہ دیکھ لیا", "Shehr-e-Madina Dekh Liya", NaatCategory.NAAT),
        Triple("دل میں ہو یادِ مدینہ", "Dil Mein Ho Yaad-e-Madina", NaatCategory.NAAT),
        Triple("مصطفیٰ جانِ رحمت", "Mustafa Jan-e-Rehmat (Salam)", NaatCategory.SALAM),
        Triple("آقا کا میلاد آیا", "Aaqa Ka Milad Aaya", NaatCategory.NAAT),
        Triple("آمدِ مصطفیٰ مرحبا", "Aamad-e-Mustafa Marhaba", NaatCategory.NAAT),
        Triple("میرے نبی پیارے نبی", "Mere Nabi Pyare Nabi", NaatCategory.NAAT),
        Triple("نور والا آیا ہے", "Noor Wala Aaya Hai", NaatCategory.NAAT),
        Triple("یا محمد نورِ مجسم", "Ya Muhammad Noor-e-Mujassam", NaatCategory.NAAT),
        Triple("کرم کے بادل چھائے", "Karam Ke Badal Chhaye", NaatCategory.MANQABAT),
        Triple("مدینے کی گلیوں میں", "Madine Ki Galiyon Mein", NaatCategory.NAAT),
        Triple("درِ نبی پر پڑا رہنے دو", "Dar-e-Nabi Par Para Rehne Do", NaatCategory.NAAT),
        Triple("میٹھا میٹھا ہے میرے محمد کا نام", "Meetha Meetha Hai Muhammad Ka Naam", NaatCategory.NAAT),
        Triple("سرکار یہ نامِ محمد", "Sarkar Yeh Naam-e-Muhammad", NaatCategory.NAAT),
        Triple("خدا کا ذکر کرے", "Khuda Ka Zikr Kare", NaatCategory.HAMD),
        Triple("اللہ کے نبی سے محبت", "Allah Ke Nabi Se Mohabbat", NaatCategory.NAAT),
        Triple("آقا کی آمد مرحبا", "Aaqa Ki Aamad Marhaba", NaatCategory.NAAT),
        Triple("چلو دیوانو مدینے بلایا گیا ہے", "Chalo Deewano Madine Bulaya Gaya Hai", NaatCategory.NAAT),
        Triple("کعبے پہ پڑی جب پہلی نظر", "Kaabe Pe Pari Jab Pehli Nazar", NaatCategory.NAAT),
        Triple("نبی کا ذکر ہو", "Nabi Ka Zikr Ho", NaatCategory.NAAT),
        Triple("یا رسول اللہ تیرے در کی فضاؤں کو سلام", "Ya Rasool Allah Tere Dar Ki Fizaon Ko Salam", NaatCategory.SALAM)
    ).mapIndexed { index, entry ->
        NaatItem(
            id = "catalogue_naat_${index + 9}",
            titleUrdu = entry.first,
            titleEnglish = entry.second,
            artistUrdu = "مختلف نعت خواں",
            artistEnglish = "Various reciters",
            category = entry.third,
            duration = "—",
            audioUrl = "",
            lyricsUrdu = emptyList(),
            descriptionUrdu = "فہرست میں شامل؛ مستند آڈیو ریکارڈنگ ابھی دستیاب نہیں",
            isFeatured = false
        )
    }


    val ALL_NAATS = listOf(
        NaatItem(
            id = "faslon_ko_takalluf",
            titleUrdu = "فاصلوں کو تکلّف ہے ہم سے اگر",
            titleEnglish = "Faslon Ko Takalluf",
            artistUrdu = "قاری وحید ظفر قاسمی",
            artistEnglish = "Qari Waheed Zafar Qasmi",
            category = NaatCategory.NAAT,
            duration = "06:45",
            audioUrl = "",
            lyricsUrdu = listOf(
                "فاصلوں کو تکلّف ہے ہم سے اگر، ہم بھی بے بس نہیں ہیں، بے کس نہیں",
                "ان کو پہنچیں گے ہم بھی دعا کے لیے، اپنے دل کو مدینہ بنا لیں گے ہم",
                "دل کے گوشے میں ہے اک تمنا دبی، ان کی چوکھٹ پہ جا کر سجدہ کریں",
                "ایک دن آئے گا جب کرم ہو گا، ہم بھی طیبہ کے جلوے دیکھیں گے",
                "ان کا روضہ نگاہوں میں بس جائے گا، روح پر ان کا فیضان چھا جائے گا",
                "یا رسولَ اللہ تیری رحمت کی قسم، کوئی مایوس تیرے در سے نہیں لوٹتا"
            ),
            descriptionUrdu = "نعت کا عنوان شامل ہے؛ مستند اور اجازت یافتہ آڈیو ریکارڈنگ ابھی دستیاب نہیں",
            isFeatured = true
        ),
        NaatItem(
            id = "mustafa_jane_rahmat",
            titleUrdu = "مصطفیٰ جانِ رحمت پہ لاکھوں سلام",
            titleEnglish = "Mustafa Jane Rahmat Pe Lakhon Salam",
            artistUrdu = "اعلیٰ حضرت امام احمد رضا خان بریلوی",
            artistEnglish = "Ala Hazrat Imam Ahmad Raza Khan",
            category = NaatCategory.SALAM,
            duration = "08:12",
            audioUrl = "",
            lyricsUrdu = listOf(
                "مصطفیٰ جانِ رحمت پہ لاکھوں سلام",
                "شمعِ بزمِ ہدایت پہ لاکھوں سلام",
                "شہرِ یارب انوکھا ہے دار الاماں",
                "اس کے ہر اک در و بام پہ لاکھوں سلام",
                "جس کے آگے سرِ سروراں خم رہے",
                "اس سرِ تاجِ رفعت پہ لاکھوں سلام",
                "کاش محشر میں جب ان کی آمد ہو اور",
                "بھیجیں سب ان کی عظمت پہ لاکھوں سلام",
                "مجھ سے بے کس پہ بھی ہو عنایت کی نظر",
                "یا شفیعَ الوریٰ تجھ پہ لاکھوں سلام"
            ),
            descriptionUrdu = "سلام کا عنوان شامل ہے؛ مستند اور اجازت یافتہ آڈیو ریکارڈنگ ابھی دستیاب نہیں",
            isFeatured = true
        ),
        NaatItem(
            id = "koi_to_hai",
            titleUrdu = "کوئی تو ہے جو نظامِ ہستی چلا رہا ہے",
            titleEnglish = "Koi To Hai Jo Nizam-e-Hasti",
            artistUrdu = "مظفر وارثی / نصرت فتح علی خان",
            artistEnglish = "Muzaffar Warsi",
            category = NaatCategory.HAMD,
            duration = "07:30",
            audioUrl = "",
            lyricsUrdu = listOf(
                "کوئی تو ہے جو نظامِ ہستی چلا رہا ہے، وہی خدا ہے",
                "دکھائی بھی جو نہ دے نظر بھی جو آ رہا ہے، وہی خدا ہے",
                "نظر بھی رکھے، سماعتیں بھی، وہ جان لیتا ہے نیتیں بھی",
                "جو خانۂ لاشعور میں جگمگا رہا ہے، وہی خدا ہے",
                "تلاش اس کو نہ کر بتوں میں، وہ ہے بدلتی ہوئی رتوں میں",
                "جو دن کو رات اور رات کو دن بنا رہا ہے، وہی خدا ہے",
                "وہی ہے مشرق، وہی ہے مغرب، سفر کریں سب اسی کی جانب",
                "سبھی کا رستہ، سبھی کا رہبر، وہی خدا ہے، وہی خدا ہے"
            ),
            descriptionUrdu = "حمد کا عنوان شامل ہے؛ مستند اور اجازت یافتہ آڈیو ریکارڈنگ ابھی دستیاب نہیں",
            isFeatured = true
        ),
        NaatItem(
            id = "tajdar_e_haram",
            titleUrdu = "تاجدارِ حرم، ہو نگاہِ کرم",
            titleEnglish = "Tajdar-e-Haram Ho Nigah-e-Karam",
            artistUrdu = "صابر برادران / عاطف اسلم",
            artistEnglish = "Sabri Brothers / Atif Aslam",
            category = NaatCategory.NAAT,
            duration = "09:40",
            audioUrl = "",
            lyricsUrdu = listOf(
                "قسمت میں میری چین سے جینا لکھ دے",
                "ڈوبے نہ کبھی میرا سفینہ لکھ دے",
                "جنت بھی گوارا ہے مگر میرے لیے",
                "اے کاتبِ تقدیر مدینہ لکھ دے",
                "تاجدارِ حرم، ہو نگاہِ کرم",
                "ہم غریبوں کے دن بھی سنور جائیں گے",
                "حامیِ بے کساں کیا کہے گا جہاں",
                "آپ کے در سے خالی اگر جائیں گے",
                "کوئی اپنا نہیں غم کے مارے ہیں ہم",
                "آپ کے در پہ فریاد لائے ہیں ہم",
                "نگاہِ کرم یا رسولَ اللہ، نگاہِ کرم یا حبیبَ اللہ"
            ),
            descriptionUrdu = "نعت کا عنوان شامل ہے؛ مستند اور اجازت یافتہ آڈیو ریکارڈنگ ابھی دستیاب نہیں",
            isFeatured = true
        ),
        NaatItem(
            id = "woh_mera_nabi_hai",
            titleUrdu = "وہ میرا نبی ہے، وہ میرا نبی ہے",
            titleEnglish = "Woh Mera Nabi Hai",
            artistUrdu = "جنید جمشید",
            artistEnglish = "Junaid Jamshed",
            category = NaatCategory.NAAT,
            duration = "05:15",
            audioUrl = "",
            lyricsUrdu = listOf(
                "جس نے دنیا سنواری، وہ میرا نبی ہے",
                "جس کی سیرت ہے پیاری، وہ میرا نبی ہے",
                "وہ جو رحمت کا دریا بہاتا رہا",
                "ہر ستم سہہ کے کلمہ سناتا رہا",
                "جس کے اخلاق کی ہر طرف دھوم ہے",
                "جس پہ راضی خدائے دو عالم ہے سب",
                "وہ محمد مدینے کا سلطان ہے",
                "جس کے صدقے سے ہم پہ یہ احسان ہے",
                "صلّی اللہ علیک یا رسولَ اللہ",
                "صلّی اللہ علیک یا حبیبَ اللہ"
            ),
            descriptionUrdu = "نعت کا عنوان شامل ہے؛ مستند اور اجازت یافتہ آڈیو ریکارڈنگ ابھی دستیاب نہیں",
            isFeatured = true
        ),
        NaatItem(
            id = "ya_nabi_salam_alayka",
            titleUrdu = "یا نبی سلام علیک، یا رسول سلام علیک",
            titleEnglish = "Ya Nabi Salam Alayka",
            artistUrdu = "ماہر زین",
            artistEnglish = "Maher Zain",
            category = NaatCategory.SALAM,
            duration = "04:55",
            audioUrl = "",
            lyricsUrdu = listOf(
                "أنت نور الله فجراً، جئت بعد العسر يسراً",
                "ربنا أعلاك قدراً، يا إمام الأنبياء",
                "یا نبی سلام علیک، یا رسول سلام علیک",
                "یا حبیب سلام علیک، صلوات اللہ علیک",
                "تیرے آنے سے روشن ہوئے دو جہاں",
                "تیرے جلووں سے چمکا زمین و زماں",
                "آپ کی رحمتیں ہم پہ ہر پل رہیں",
                "آپ کے نقشِ پا پہ ہم چلتے رہیں"
            ),
            descriptionUrdu = "سلام کا عنوان شامل ہے؛ مستند اور اجازت یافتہ آڈیو ریکارڈنگ ابھی دستیاب نہیں",
            isFeatured = false
        ),
        NaatItem(
            id = "karam_mangta_hoon",
            titleUrdu = "کرم مانگتا ہوں، عطا مانگتا ہوں",
            titleEnglish = "Karam Mangta Hoon, Ata Mangta Hoon",
            artistUrdu = "قاری وحید ظفر قاسمی",
            artistEnglish = "Qari Waheed Zafar Qasmi",
            category = NaatCategory.MANQABAT,
            duration = "06:10",
            audioUrl = "",
            lyricsUrdu = listOf(
                "الٰہی میں تجھ سے دعا مانگتا ہوں",
                "کرم مانگتا ہوں، عطا مانگتا ہوں",
                "عطا کر تو مجھ کو ہدایت کا رستہ",
                "ترے پیارے محبوب کا واسطہ ہے",
                "الٰہی گناہوں سے مجھ کو بچانا",
                "صراطِ مستقیم پر مجھ کو چلانا",
                "مری توبہ یا رب قبول فرما لے",
                "مجھے اپنی رحمت کے دامن میں چھپا لے"
            ),
            descriptionUrdu = "سوز و گداز سے بھرپور مقبول مناجات",
            isFeatured = false
        ),
        NaatItem(
            id = "main_madine_chala",
            titleUrdu = "میں مدینے چلا، میں مدینے چلا",
            titleEnglish = "Main Madine Chala",
            artistUrdu = "اویس رضا قادری",
            artistEnglish = "Owais Raza Qadri",
            category = NaatCategory.NAAT,
            duration = "07:20",
            audioUrl = "",
            lyricsUrdu = listOf(
                "شہرِ نبی کی رونقیں کیا خوب ہیں",
                "ہر دل کے وہ تو پیارے محبوب ہیں",
                "میں مدینے چلا، میں مدینے چلا",
                "پھر کرم ہو گیا، میں مدینے چلا",
                "روکتی ہے مجھے یہ زمیں کی کشش",
                "کھینچتا ہے مجھے وہ مدینے کا در",
                "گنبدِ خضریٰ پہ جب نظر پڑے گی",
                "زندگی کا سفینہ سنور جائے گا"
            ),
            descriptionUrdu = "سفرِ مدینہ اور گنبدِ خضریٰ کے شوق میں لکھی گئی مشہور نعت",
            isFeatured = false
        )
    ) + ADDITIONAL_NAATS + ADDITIONAL_NAATS



    fun getNaatsByCategory(category: NaatCategory): List<NaatItem> {
        return if (category == NaatCategory.ALL) ALL_NAATS else ALL_NAATS.filter { it.category == category }
    }

    fun searchNaats(query: String): List<NaatItem> {
        if (query.isBlank()) return ALL_NAATS
        val q = query.trim().lowercase()
        return ALL_NAATS.filter {
            it.titleUrdu.contains(q, ignoreCase = true) ||
            it.titleEnglish.contains(q, ignoreCase = true) ||
            it.artistUrdu.contains(q, ignoreCase = true) ||
            it.artistEnglish.contains(q, ignoreCase = true) ||
            it.lyricsUrdu.any { lyric -> lyric.contains(q, ignoreCase = true) }
        }
    }
}
