package com.example.data.repository

import com.example.data.model.NaatCategory
import com.example.data.model.NaatItem

object NaatRepository {

    val ALL_NAATS = listOf(
        NaatItem(
            id = "faslon_ko_takalluf",
            titleUrdu = "فاصلوں کو تکلّف ہے ہم سے اگر",
            titleEnglish = "Faslon Ko Takalluf",
            artistUrdu = "قاری وحید ظفر قاسمی",
            artistEnglish = "Qari Waheed Zafar Qasmi",
            category = NaatCategory.NAAT,
            duration = "06:45",
            audioUrl = "https://server8.mp3quran.net/afs/001.mp3",
            lyricsUrdu = listOf(
                "فاصلوں کو تکلّف ہے ہم سے اگر، ہم بھی بے بس نہیں ہیں، بے کس نہیں",
                "ان کو پہنچیں گے ہم بھی دعا کے لیے، اپنے دل کو مدینہ بنا لیں گے ہم",
                "دل کے گوشے میں ہے اک تمنا دبی، ان کی چوکھٹ پہ جا کر سجدہ کریں",
                "ایک دن آئے گا جب کرم ہو گا، ہم بھی طیبہ کے جلوے دیکھیں گے",
                "ان کا روضہ نگاہوں میں بس جائے گا، روح پر ان کا فیضان چھا جائے گا",
                "یا رسولَ اللہ تیری رحمت کی قسم، کوئی مایوس تیرے در سے نہیں لوٹتا"
            ),
            descriptionUrdu = "عظیم الشان اور دل کو چھو لینے والا لازوال نعتیہ کلام",
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
            audioUrl = "https://server8.mp3quran.net/afs/036.mp3",
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
            descriptionUrdu = "عالمِ اسلام کا مشہور ترین اور مقبول درود و سلام",
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
            audioUrl = "https://server8.mp3quran.net/afs/055.mp3",
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
            descriptionUrdu = "اللہ تعالیٰ کی توحید اور شانِ کبریائی کا شاہکار حمدیہ کلام",
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
            audioUrl = "https://server8.mp3quran.net/afs/067.mp3",
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
            descriptionUrdu = "عقیدت و محبت سے لبریز بے مثال نعتیہ کلام",
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
            audioUrl = "https://server8.mp3quran.net/afs/112.mp3",
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
            descriptionUrdu = "خوبصورت اور دلنشین آواز میں نبی پاک ﷺ کی مدح سرائی",
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
            audioUrl = "https://server8.mp3quran.net/afs/113.mp3",
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
            descriptionUrdu = "بین الاقوامی سطح پر مقبول عربی و اردو سلام",
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
            audioUrl = "https://server8.mp3quran.net/afs/114.mp3",
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
            audioUrl = "https://server8.mp3quran.net/afs/108.mp3",
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
    )

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
