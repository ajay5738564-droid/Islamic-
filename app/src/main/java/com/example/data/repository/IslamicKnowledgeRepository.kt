package com.example.data.repository

import com.example.data.model.ArticleSection
import com.example.data.model.KnowledgeArticle
import com.example.data.model.KnowledgeCategory

object IslamicKnowledgeRepository {

    val ALL_ARTICLES = listOf(
        KnowledgeArticle(
            id = "five_pillars",
            category = KnowledgeCategory.PILLARS,
            title = "The Five Pillars of Islam (أركان الإسلام)",
            titleArabic = "أركان الإسلام الخمسة",
            summary = "The essential foundational acts of worship and declaration upon which the entire practice of Islam is built.",
            sections = listOf(
                ArticleSection(
                    heading = "1. Shahada (Declaration of Faith)",
                    body = "The testimony that 'There is no deity worthy of worship except Allah, and Muhammad is His servant and final Messenger.' Sincere belief and verbal affirmation make a person a Muslim.",
                    arabicQuote = "أَشْهَدُ أَنْ لَا إِلَٰهَ إِلَّا اللَّهُ وَأَشْهَدُ أَنَّ مُحَمَّدًا رَسُولُ اللَّهِ",
                    translationQuote = "I bear witness that there is no god but Allah, and I bear witness that Muhammad is the Messenger of Allah."
                ),
                ArticleSection(
                    heading = "2. Salah (The Five Daily Prayers)",
                    body = "Establishing direct communion with Allah five times daily: Fajr (Dawn), Dhuhr (Noon), Asr (Afternoon), Maghrib (Sunset), and Isha (Night). It anchors the believer's day in spiritual consciousness.",
                    arabicQuote = "إِنَّ الصَّلَاةَ تَنْهَىٰ عَنِ الْفَحْشَاءِ وَالْمُنكَرِ",
                    translationQuote = "Indeed, prayer prohibits immorality and wrongdoing. (Quran 29:45)"
                ),
                ArticleSection(
                    heading = "3. Zakat (Obligatory Almsgiving)",
                    body = "Paying a calculated 2.5% of one's accumulated qualifying wealth and savings annually above the Nisab threshold to support the poor, needy, and vulnerable in society.",
                    arabicQuote = "وَأَقِيمُوا الصَّلَاةَ وَآتُوا الزَّكَاةَ",
                    translationQuote = "And establish prayer and give Zakat. (Quran 2:43)"
                ),
                ArticleSection(
                    heading = "4. Sawm (Fasting during Ramadan)",
                    body = "Abstaining from food, drink, and marital relations from dawn until sunset throughout the 9th lunar month of Ramadan to foster self-restraint (Taqwa), gratitude, and empathy.",
                    arabicQuote = "يَا أَيُّهَا الَّذِينَ آمَنُوا كُتِبَ عَلَيْكُمُ الصِّيَامُ",
                    translationQuote = "O you who have believed, decreed upon you is fasting... that you may become righteous. (Quran 2:183)"
                ),
                ArticleSection(
                    heading = "5. Hajj (Pilgrimage to Makkah)",
                    body = "The sacred pilgrimage to the Holy House of Allah in Makkah performed during Dhu al-Hijjah at least once in a lifetime for every physically and financially able adult Muslim.",
                    arabicQuote = "وَلِلَّهِ عَلَى النَّاسِ حِجُّ الْبَيْتِ مَنِ اسْتَطَاعَ إِلَيْهِ سَبِيلًا",
                    translationQuote = "And [due] to Allah from the people is a pilgrimage to the House - for whoever is able to find thereto a way. (Quran 3:97)"
                )
            ),
            reference = "Sahih al-Bukhari (8), Sahih Muslim (16)"
        ),
        KnowledgeArticle(
            id = "articles_faith",
            category = KnowledgeCategory.FAITH,
            title = "The Six Articles of Faith (أركان الإيمان)",
            titleArabic = "أركان الإيمان الستة",
            summary = "The essential theological convictions that define the Muslim worldview and creed (Aqeedah).",
            sections = listOf(
                ArticleSection(
                    heading = "1. Belief in Allah (Tawhid)",
                    body = "Belief in Allah as the sole Creator, Sustainer, and Ruler of the universe, possessing perfect attributes without partner, child, or equal."
                ),
                ArticleSection(
                    heading = "2. Belief in the Angels (Mala'ikah)",
                    body = "Belief in Allah's noble, unseen creations made of light who execute His divine commands flawlessly (including Jibril, Mika'il, Israfil, and Malak al-Mawt)."
                ),
                ArticleSection(
                    heading = "3. Belief in the Divine Books (Kutub)",
                    body = "Belief in the original scriptures revealed to the Prophets, including the Tawrah (Torah to Musa), Zabur (Psalms to Dawud), Injeel (Gospel to Isa), and the final preserved Quran to Muhammad (PBUH)."
                ),
                ArticleSection(
                    heading = "4. Belief in the Prophets and Messengers (Rusul)",
                    body = "Belief in all chosen Messengers sent to guide humanity with the message of monotheism, starting from Adam (AS) to Nuh, Ibrahim, Musa, Isa, and finalized with Prophet Muhammad (PBUH)."
                ),
                ArticleSection(
                    heading = "5. Belief in the Day of Judgment (Yawm al-Qiyamah)",
                    body = "Belief in bodily resurrection after death, the Day of Reckoning, the Balance of Deeds (Mizan), and the eternal afterlife in Paradise (Jannah) or Hellfire (Jahannam)."
                ),
                ArticleSection(
                    heading = "6. Belief in Divine Decree (Al-Qadr)",
                    body = "Belief that everything occurs with Allah's all-encompassing knowledge, divine will, and wisdom, while recognizing that humans possess moral free will and moral accountability."
                )
            ),
            reference = "Sahih Muslim (8 - Hadith Jibril)"
        ),
        KnowledgeArticle(
            id = "beginner_wudu_salah",
            category = KnowledgeCategory.BEGINNERS,
            title = "Step-by-Step Guide: Wudu (Ablution) & Salah",
            titleArabic = "صفة الوضوء والصلاة للمبتدئين",
            summary = "Clear, illustrated instructions on performing ritual purity (Wudu) and the daily Islamic prayer (Salah).",
            sections = listOf(
                ArticleSection(
                    heading = "How to Perform Wudu (Ablution)",
                    body = "1. Make intention (Niyyah) in the heart and say 'Bismillah'.\n2. Wash both hands up to the wrists three times.\n3. Rinse mouth with water three times.\n4. Sniff water into nostrils and blow out gently three times.\n5. Wash the entire face from hairline to chin and ear to ear three times.\n6. Wash right arm up to and including elbow three times, then left arm three times.\n7. Wipe wet hands over entire head once from front to back and return, then wipe inside and outside of ears.\n8. Wash right foot including ankles three times, then left foot three times."
                ),
                ArticleSection(
                    heading = "Basic Steps of Salah (Prayer)",
                    body = "1. Stand facing Qibla with focus and humility.\n2. Takbirat al-Ihram: Raise hands to ears/shoulders and say 'Allahu Akbar'.\n3. Recite Surah Al-Fatihah followed by another Surah.\n4. Ruku' (Bowing): Bow with flat back, say 'Subhana Rabbiyal Azeem' 3 times.\n5. Stand upright: Say 'Sami' Allahu liman hamidah', then 'Rabbana lakal hamd'.\n6. Sujud (Prostration): Place forehead, nose, palms, knees, and toes on ground, say 'Subhana Rabbiyal A'la' 3 times.\n7. Sit briefly and repeat Sujud.\n8. Complete required Rak'ahs and conclude with Tashahhud, Salawat, and Tasleem ('Assalamu alaykum wa rahmatullah' to right and left)."
                )
            ),
            reference = "Fiqh as-Sunnah & Sahih Hadith descriptions"
        )
    )

    val ASMA_UL_HUSNA: List<com.example.data.model.AllahName> = listOf(
        com.example.data.model.AllahName(1, "الرَّحْمَٰنُ", "Ar-Rahman", "بہت زیادہ رحم کرنے والا", "The Most Gracious", "جو شخص ہر نماز کے بعد ۱۰۰ مرتبہ پڑھے گا اس کے دل سے غفلت اور نسیان دور ہو جائے گا۔"),
        com.example.data.model.AllahName(2, "الرَّحِيمُ", "Ar-Raheem", "نہایت مہربان", "The Most Merciful", "جو شخص روزانہ ۱۰۰ مرتبہ پڑھے گا تمام دنیاوی آفات سے محفوظ رہے گا۔"),
        com.example.data.model.AllahName(3, "الْمَلِكُ", "Al-Malik", "حقیقی بادشاہ", "The King, The Sovereign", "جو شخص زوال کے بعد کثرت سے پڑھے گا اللہ اسے غنی فرما دے گا۔"),
        com.example.data.model.AllahName(4, "الْقُدُّوسُ", "Al-Quddus", "نہایت پاک ذات", "The Most Holy", "جو شخص زوال کے وقت پڑھے گا اس کا دل روحانی بیماریوں سے پاک ہو گا۔"),
        com.example.data.model.AllahName(5, "السَّلَامُ", "As-Salam", "سلامتی دینے والا", "The Giver of Peace", "جو شخص مریض پر ۱۶۰ بار دم کرے اللہ اسے شفا عطا فرمائے گا۔"),
        com.example.data.model.AllahName(6, "الْمُؤْمِنُ", "Al-Mu'min", "امن و امان دینے والا", "The Granter of Security", "جو شخص کسی خوف کے وقت ۶۳۰ بار پڑھے گا ہر خوف سے محفوظ رہے گا۔"),
        com.example.data.model.AllahName(7, "الْمُهَيْمِنُ", "Al-Muhaymin", "نگہبان اور محافظ", "The Guardian", "جو شخص غسل کر کے ۲ رکعت پڑھ کر ۱۰۰ بار پڑھے اس کا باطن روشن ہو گا۔"),
        com.example.data.model.AllahName(8, "الْعَزِيزُ", "Al-Aziz", "سب پر غالب اور زبردست", "The All Mighty", "جو شخص فجر کے بعد ۴۰ دن تک ۴۱ بار پڑھے وہ کبھی کسی کا محتاج نہ ہو گا۔"),
        com.example.data.model.AllahName(9, "الْجَبَّارُ", "Al-Jabbar", "حکم چلانے والا اور بگڑے کام بنانے والا", "The Compeller", "جو شخص صبح و شام ۲۲۶ بار پڑھے گا ظالموں کے ظلم سے محفوظ رہے گا۔"),
        com.example.data.model.AllahName(10, "الْمُتَكَبِّرُ", "Al-Mutakabbir", "بڑائی اور عظمت والا", "The Supreme", "جو شخص ہر کام کے آغاز میں کثرت سے پڑھے گا اس میں برکت اور کامیابی حاصل ہو گی۔"),
        com.example.data.model.AllahName(11, "الْخَالِقُ", "Al-Khaliq", "پیدا کرنے والا", "The Creator", "جو شخص آدھی رات کو ۱۰۰ بار پڑھے اللہ اس کے لیے فرشتہ پیدا کرے گا جو عبادت کرے گا۔"),
        com.example.data.model.AllahName(12, "الْبَارِئُ", "Al-Bari'", "عدم سے وجود میں لانے والا", "The Maker", "بانجھ عورت ۷ دن روزے رکھ کر ۲۱ بار پڑھ کر پانی پر دم کرے تو اولاد عطا ہو۔"),
        com.example.data.model.AllahName(13, "الْمُصَوِّرُ", "Al-Musawwir", "صورت گری کرنے والا", "The Fashioner", "صورت اور سیرت کی بہتری اور اولاد کی نعمت کے لیے نہایت مفید ہے۔"),
        com.example.data.model.AllahName(14, "الْغَفَّارُ", "Al-Ghaffar", "بہت زیادہ بخشنے والا", "The Great Forgiver", "جو شخص جمعہ کے بعد ۱۰۰ بار پڑھے گا اس کے گناہ معاف ہوں گے۔"),
        com.example.data.model.AllahName(15, "الْقَهَّارُ", "Al-Qahhar", "سب کو اپنے قابو میں رکھنے والا", "The Subduer", "جو شخص دنیا کی محبت دل سے نکالنے کے لیے کثرت سے پڑھے گا دل پاک ہو گا۔"),
        com.example.data.model.AllahName(16, "الْوَهَّابُ", "Al-Wahhab", "بے حساب عطا کرنے والا", "The Bestower", "جو شخص چاشت کے سجدے میں ۷ بار پڑھے گا فقر و فاقہ سے نجات پائے گا۔"),
        com.example.data.model.AllahName(17, "الرَّزَّاقُ", "Ar-Razzaq", "رزق دینے والا", "The Provider", "جو شخص صبح نمازِ فجر سے پہلے گھر کے چاروں کونوں میں ۱۰، ۱۰ بار پڑھے رزق میں برکت ہو۔"),
        com.example.data.model.AllahName(18, "الْفَتَّاحُ", "Al-Fattah", "مشکلات کھولنے والا اور فتح دینے والا", "The Opener", "نماز فجر کے بعد سینے پر ہاتھ رکھ کر ۷۰ بار پڑھے دل کا زنگ دور ہو گا۔"),
        com.example.data.model.AllahName(19, "الْعَلِيمُ", "Al-Aleem", "سب کچھ جاننے والا باخبر", "The All-Knowing", "جو شخص کثرت سے پڑھے گا اللہ تعالیٰ اس پر حکمت و معرفت کے دروازے کھولے گا۔"),
        com.example.data.model.AllahName(20, "الْقَابِضُ", "Al-Qabid", "تنگ کرنے والا اور قبض کرنے والا", "The Withholder", "روٹی کے لقمے پر ۴۰ دن تک لکھ کر کھانے سے بھوک و پیاس کے خوف سے امان ملتی ہے۔"),
        com.example.data.model.AllahName(21, "الْبَاسِطُ", "Al-Basit", "فراخی اور کشادگی دینے والا", "The Extender", "چاشت کے وقت ۱۰ بار ہاتھ اٹھا کر منہ پر پھیرے تو کبھی کسی کا محتاج نہ ہو گا۔"),
        com.example.data.model.AllahName(22, "الْخَافِضُ", "Al-Khafid", "پست کرنے والا اور نیچا دکھانے والا", "The Reducer", "دشمن کے شر سے بچنے کے لیے ۷۰ بار پڑھنا مفید ہے۔"),
        com.example.data.model.AllahName(23, "الرَّافِعُ", "Ar-Rafi'", "بلند کرنے والا اور عزت دینے والا", "The Exalter", "جو شخص آدھی رات یا ظہر کے بعد ۱۰۰ بار پڑھے اللہ اسے عزت و رفعت عطا فرمائے گا۔"),
        com.example.data.model.AllahName(24, "الْمُعِزُّ", "Al-Mu'izz", "عزت بخشنے والا", "The Honourer", "پیر یا جمعہ کی شب بعد نماز مغرب ۴۰ بار پڑھے تو لوگوں میں باوقار ہو۔"),
        com.example.data.model.AllahName(25, "الْمُذِلُّ", "Al-Mudhill", "ذلیل و خوار کرنے والا", "The Humiliator", "۷۵ بار پڑھ کر سجدے میں دعا مانگے تو حاسدوں سے امان پائے۔"),
        com.example.data.model.AllahName(26, "السَّمِيعُ", "As-Samee'", "سب کچھ سننے والا", "The All-Hearing", "جمعرات کو چاشت کے بعد ۵۰۰ بار پڑھے تو دعائیں قبول ہوں گی۔"),
        com.example.data.model.AllahName(27, "الْبَصِيرُ", "Al-Baseer", "سب کچھ دیکھنے والا", "The All-Seeing", "جمعہ کی نماز سے پہلے ۱۰۰ بار پڑھے تو اللہ نظر میں نور اور بصیرت عطا فرمائے۔"),
        com.example.data.model.AllahName(28, "الْحَكَمُ", "Al-Hakam", "فیصلہ کرنے والا حاکمِ مطلق", "The Judge", "رات کو باوضو ۹۹ بار پڑھے تو اللہ اس کا باطن پرنور فرمائے۔"),
        com.example.data.model.AllahName(29, "الْعَدْلُ", "Al-Adl", "سراپا عدل و انصاف", "The Utterly Just", "جمعہ کی رات کو روٹی کے ٹکڑے پر لکھ کر کھائے تو دل مسخر ہو۔"),
        com.example.data.model.AllahName(30, "اللَّطِيفُ", "Al-Lateef", "نہایت باریک بین اور مہربان", "The Subtle One", "۱۳۳ بار پڑھنے سے رزق میں کشادگی اور تمام دکھ درد دور ہوتے ہیں۔"),
        com.example.data.model.AllahName(31, "الْخَبِيرُ", "Al-Khabeer", "ہر بات سے باخبر", "The All-Aware", "۷ دن تک کثرت سے پڑھنے سے پوشیدہ راز اور سچائی عیاں ہوتی ہے۔"),
        com.example.data.model.AllahName(32, "الْحَلِيمُ", "Al-Haleem", "بردبار اور تحمل والا", "The Most Forbearing", "کاغذ پر لکھ کر پانی میں گھول کر جس چیز پر چھڑکے وہ برکت پائے۔"),
        com.example.data.model.AllahName(33, "الْعَظِيمُ", "Al-Azeem", "بڑی عظمت اور شان والا", "The Magnificent", "کثرت سے پڑھنے والا ہر خاص و عام کی نظر میں معزز اور محترم ہوتا ہے۔"),
        com.example.data.model.AllahName(34, "الْغَفُورُ", "Al-Ghafoor", "بے انتہا معاف فرمانے والا", "The All-Forgiving", "سجدے میں ۳ بار پڑھنے سے سر درد اور غم و اندوہ سے نجات ملتی ہے۔"),
        com.example.data.model.AllahName(35, "الشَّكُورُ", "Ash-Shakoor", "قدردان اور بدلہ دینے والا", "The Most Appreciative", "مالی تنگی میں ۴۱ بار پانی پر دم کر کے پینے سے کشادگی ہوتی ہے۔"),
        com.example.data.model.AllahName(36, "الْعَلِيُّ", "Al-Aliyy", "سب سے بلند و بالا", "The Most High", "جو شخص کثرت سے ورد رکھے گا اس کا نصیبہ بلند اور اقبال بلند ہو گا۔"),
        com.example.data.model.AllahName(37, "الْكَبِيرُ", "Al-Kabeer", "سب سے بڑا اور کبریائی والا", "The Greatest", "جو شخص نوکری یا مرتبے سے معزول ہو گیا ہو وہ ۷ دن روزے رکھ کر روزانہ ۱۰۰۰ بار پڑھے۔"),
        com.example.data.model.AllahName(38, "الْحَفِيظُ", "Al-Hafeez", "سب کا حقیقی نگہبان", "The Preserver", "روزانہ ۱۶ بار پڑھنے والا تمام خطرات، چوری اور حادثات سے محفوظ رہے گا۔"),
        com.example.data.model.AllahName(39, "الْمُقِيتُ", "Al-Muqeet", "سب کو روزی اور قوت پہنچانے والا", "The Sustainer", "خالی برتن پر ۷ بار پڑھ کر دم کرے اور پانی بھر کر پئے تو سفر آسان ہو۔"),
        com.example.data.model.AllahName(40, "الْحَسِيبُ", "Al-Haseeb", "کفایت کرنے والا اور حساب لینے والا", "The Reckoner", "ہر خطرے کے وقت ۷۰ بار 'حَسْبِيَ اللّٰهُ الْحَسِيبُ' پڑھنا اکسیر ہے۔"),
        com.example.data.model.AllahName(41, "الْجَلِيلُ", "Al-Jaleel", "بزرگی اور جلال والا", "The Majestic", "مشک و زعفران سے لکھ کر پاس رکھے تو لوگوں میں پروقار رہے۔"),
        com.example.data.model.AllahName(42, "الْكَرِيمُ", "Al-Kareem", "بڑا ہی سخی اور کرم کرنے والا", "The Most Generous", "سوتے وقت کثرت سے پڑھے تو علماء اور صالحین میں شمار ہو۔"),
        com.example.data.model.AllahName(43, "الرَّقِيبُ", "Ar-Raqeeb", "نگران اور دیکھ بھال کرنے والا", "The Watchful", "اہل و عیال اور مال پر ۷ بار پڑھ کر دم کرے تو امان رہے۔"),
        com.example.data.model.AllahName(44, "الْمُجِيبُ", "Al-Mujeeb", "دعائیں قبول فرمانے والا", "The Responsive", "کثرت سے پڑھنے والے کی ہر جائز دعا بارگاہِ الٰہی میں قبول ہوتی ہے۔"),
        com.example.data.model.AllahName(45, "الْوَاسِعُ", "Al-Wasi'", "وسیع اور کشادہ رحمت والا", "The All-Encompassing", "کثرت سے پڑھنے سے تنگدستی اور بخل سے نجات ملتی ہے۔"),
        com.example.data.model.AllahName(46, "الْحَكِيمُ", "Al-Hakeem", "حکمت والا اور دانا", "The All-Wise", "کثرت سے ورد رکھنے والے پر علم و حکمت کے دروازے کھلتے ہیں۔"),
        com.example.data.model.AllahName(47, "الْوَدُودُ", "Al-Wadood", "بے پناہ محبت کرنے والا", "The Loving", "۱۰۰۰ بار کھانے پر دم کر کے میاں بیوی کھائیں تو محبت قائم ہو۔"),
        com.example.data.model.AllahName(48, "الْمَجِيدُ", "Al-Majeed", "بڑی بزرگی اور شرف والا", "The Glorious", "ایام بیض کے روزے رکھ کر کثرت سے پڑھے تو امراض سے نجات ملے۔"),
        com.example.data.model.AllahName(49, "الْبَاعِثُ", "Al-Ba'ith", "مردوں کو زندہ کرنے والا", "The Resurrector", "سوتے وقت سینے پر ہاتھ رکھ کر ۱۰۱ بار پڑھے دل زندہ ہو گا۔"),
        com.example.data.model.AllahName(50, "الشَّهِيدُ", "Ash-Shaheed", "ہر جگہ حاضر و ناظر", "The Witness", "نافرمان اولاد یا خادم کی پیشانی پر ہاتھ رکھ کر ۲۱ بار پڑھے تو فرمانبردار ہو۔"),
        com.example.data.model.AllahName(51, "الْحَقُّ", "Al-Haqq", "سچا اور برحق", "The Truth", "چوکور کاغذ پر لکھ کر سحری میں ہاتھ پر رکھ کر دعا کرے تو گمشدہ ملے گی۔"),
        com.example.data.model.AllahName(52, "الْوَكِيلُ", "Al-Wakeel", "بہترین کارساز", "The Trustee", "آفاتِ ارضی و سماوی سے بچنے کے لیے کثرت سے پڑھنا تیر بہدف ہے۔"),
        com.example.data.model.AllahName(53, "الْقَوِيُّ", "Al-Qawiyy", "قوت و طاقت والا", "The Strong", "ظالم دشمن کے مقابلے کے وقت ورد کرنا فتح کا باعث ہے۔"),
        com.example.data.model.AllahName(54, "الْمَتِينُ", "Al-Mateen", "انتہائی مضبوط", "The Firm", "دودھ پلانے والی عورت کے دودھ میں برکت کے لیے دم کرنا مفید ہے۔"),
        com.example.data.model.AllahName(55, "الْوَلِيُّ", "Al-Waliyy", "مددگار اور دوست", "The Protecting Associate", "بیوی کے اچھے اخلاق کے لیے گھر داخل ہوتے وقت پڑھنا فائدہ مند ہے۔"),
        com.example.data.model.AllahName(56, "الْحَمِيدُ", "Al-Hameed", "تعریفوں کے لائق", "The Praiseworthy", "تنہائی میں ۹۳ بار پڑھنے سے زبان اور اخلاق پاکیزہ ہوتے ہیں۔"),
        com.example.data.model.AllahName(57, "الْمُحْصِي", "Al-Muhsee", "ایک ایک چیز کو گننے والا", "The All-Enumerating", "۲۰ روٹی کے ٹکڑوں پر ۲۰ بار پڑھ کر کھائے تو مخلوق مسخر ہو۔"),
        com.example.data.model.AllahName(58, "الْمُبْدِئُ", "Al-Mubdi'", "پہلی بار پیدا کرنے والا", "The Originator", "حاملہ پر پیٹ پر ہاتھ رکھ کر ۹۹ بار پڑھے تو اسقاط سے حفاظت ہو۔"),
        com.example.data.model.AllahName(59, "الْمُعِيدُ", "Al-Mu'eed", "دوبارہ زندہ کرنے والا", "The Restorer", "گھر کے چاروں کونوں میں ۷۰ بار پڑھے تو گمشدہ شخص خیریت سے واپس آئے۔"),
        com.example.data.model.AllahName(60, "الْمُحْيِي", "Al-Muhyee", "زندگی بخشنے والا", "The Giver of Life", "بیمار پر کثرت سے دم کرنے سے شفا حاصل ہوتی ہے۔"),
        com.example.data.model.AllahName(61, "الْمُمِيتُ", "Al-Mumeet", "موت دینے والا", "The Creator of Death", "نفسِ سرکش کو قابو کرنے کے لیے سینے پر ہاتھ رکھ کر سوتے وقت پڑھے۔"),
        com.example.data.model.AllahName(62, "الْحَيُّ", "Al-Hayy", "ہمیشہ زندہ رہنے والا", "The Ever-Living", "کثرت سے 'یَا حَیُّ یَا قَیُّومُ' پڑھنے سے عمر اور رزق میں برکت ہو۔"),
        com.example.data.model.AllahName(63, "الْقَيُّومُ", "Al-Qayyoom", "سب کو سنبھالنے والا", "The Sustainer", "فجر کے بعد پڑھنے سے سستی اور کاہلی دور ہوتی ہے۔"),
        com.example.data.model.AllahName(64, "الْوَاجِدُ", "Al-Wajid", "جو چاہے پانے والا", "The Finder", "کھانا کھاتے وقت ہر لقمے پر پڑھے تو دل کو نور ملے۔"),
        com.example.data.model.AllahName(65, "الْمَاجِدُ", "Al-Majid", "بڑی بزرگی والا", "The Noble", "تنہائی میں اتنی بار پڑھے کہ بے خود ہو جائے تو دل روشن ہو۔"),
        com.example.data.model.AllahName(66, "الْوَاحِدُ", "Al-Wahid", "اکیلا اور یکتا", "The One", "خلوت میں ۱۰۰۰ بار پڑھے تو دل سے خوفِ خلق نکل جائے۔"),
        com.example.data.model.AllahName(67, "الْأَحَدُ", "Al-Ahad", "ایک اور بے ہمتا", "The Unique", "۱۰۰۰ بار پڑھنے پر روحانی راز منکشف ہوتے ہیں۔"),
        com.example.data.model.AllahName(68, "الصَّمَدُ", "As-Samad", "بے نیاز اور سب کا سہارا", "The Eternal Refuge", "سحری کے وقت ۱۲۵ بار پڑھے تو سچائی اور امانت نصیب ہو۔"),
        com.example.data.model.AllahName(69, "الْقَادِرُ", "Al-Qadir", "پوری قدرت والا", "The Capable", "وضو کے بعد ہر عضو دھوتے وقت پڑھے تو دشمن عاجز ہو۔"),
        com.example.data.model.AllahName(70, "الْمُقْتَدِرُ", "Al-Muqtadir", "ہر چیز پر قابو رکھنے والا", "The Omnipotent", "سو کر اٹھتے ہی کثرت سے پڑھے تو کام بن جائیں۔"),
        com.example.data.model.AllahName(71, "الْمُقَدِّمُ", "Al-Muqaddim", "آگے کرنے والا", "The Promoter", "میدانِ جنگ یا امتحان میں کثرت سے پڑھنا فتح دلاتا ہے۔"),
        com.example.data.model.AllahName(72, "الْمُؤَخِّرُ", "Al-Mu'akhkhir", "پیچھے کرنے والا", "The Delayer", "ہر روز ۱۰۰ بار پڑھے تو توبہ کی توفیق نصیب ہو۔"),
        com.example.data.model.AllahName(73, "الْأَوَّلُ", "Al-Awwal", "سب سے پہلے موجود", "The First", "مسافر کی خیریت کے لیے ۴۰ جمعہ تک روزانہ ۱۰۰۰ بار پڑھے۔"),
        com.example.data.model.AllahName(74, "الْآخِرُ", "Al-Akhir", "سب کے بعد باقی رہنے والا", "The Last", "روزانہ ۱۰۰۰ بار پڑھنے والے کے دل سے غیر اللہ کی محبت نکل جاتی ہے۔"),
        com.example.data.model.AllahName(75, "الظَّاهِرُ", "Az-Zahir", "سب پر ظاہر اور عیاں", "The Manifest", "اشراق کے بعد ۵۰۰ بار پڑھے تو آنکھوں میں روشنی آئے۔"),
        com.example.data.model.AllahName(76, "الْبَاطِنُ", "Al-Batin", "سب سے پوشیدہ اور چھپا ہوا", "The Hidden", "روزانہ ۳۳ بار پڑھے تو باطنی انوار نصیب ہوں۔"),
        com.example.data.model.AllahName(77, "الْوَالِي", "Al-Wali", "سب کا حاکم و کارساز", "The Governor", "گھر پر پڑھ کر دم کرنے سے تمام مصائب و آفات دور ہوں۔"),
        com.example.data.model.AllahName(78, "الْمُتَعَالِي", "Al-Muta'ali", "بڑی ہی بلندی والا", "The Most Exalted", "کثرت سے پڑھنے سے تمام مشکلات آسان ہوتی ہیں۔"),
        com.example.data.model.AllahName(79, "الْبَرُّ", "Al-Barr", "احسان اور نیکی کرنے والا", "The Source of Goodness", "شراب نوشی اور برائیوں سے نجات کے لیے ۷ بار پڑھنا مفید ہے۔"),
        com.example.data.model.AllahName(80, "التَّوَّابُ", "At-Tawwab", "توبہ قبول فرمانے والا", "The Acceptor of Repentance", "چاشت کے بعد ۳۶۰ بار پڑھے تو سچی توبہ نصیب ہو۔"),
        com.example.data.model.AllahName(81, "الْمُنْتَقِمُ", "Al-Muntaqim", "بدلہ لینے والا", "The Avenger", "ظالم کے شر سے بچنے کے لیے ۳ جمعہ تک کثرت سے پڑھے۔"),
        com.example.data.model.AllahName(82, "الْعَفُوُّ", "Al-Afuww", "بہت درگزر فرمانے والا", "The Pardoner", "کثرت سے پڑھنے والے کے گناہ معاف فرمائے جاتے ہیں۔"),
        com.example.data.model.AllahName(83, "الرَّءُوفُ", "Ar-Ra'oof", "نہایت شفقت فرمانے والا", "The Most Kind", "۱۰ بار درود شریف اور ۱۰ بار یہ نام پڑھنے سے غصہ زائل ہو۔"),
        com.example.data.model.AllahName(84, "مَالِكُ الْمُلْكِ", "Malik-ul-Mulk", "ساری کائنات کا مالک", "Owner of All Sovereignty", "ہمیشہ با عزت اور خوددار رہنے کے لیے کثرت سے پڑھے۔"),
        com.example.data.model.AllahName(85, "ذُو الْجَلَالِ وَالْإِكْرَامِ", "Dhul-Jalali wal-Ikram", "عظمت اور فضل و کرم والا", "Lord of Glory and Honour", "کثرت سے پڑھنے والے کو عزت، دولت اور استغناء نصیب ہو۔"),
        com.example.data.model.AllahName(86, "الْمُقْسِطُ", "Al-Muqsit", "انصاف قائم کرنے والا", "The Equitable", "وسوسوں سے نجات کے لیے روزانہ ۱۰۰ بار پڑھے۔"),
        com.example.data.model.AllahName(87, "الْجَامِعُ", "Al-Jami'", "سب کو جمع کرنے والا", "The Gatherer", "گمشدہ چیز یا بچھڑے ہوؤں کو ملانے کے لیے اکسیر ہے۔"),
        com.example.data.model.AllahName(88, "الْغَنِيُّ", "Al-Ghaniyy", "بے نیاز اور غنی", "The Self-Sufficient", "روزانہ ۷۰ بار پڑھنے سے غیب سے مال و دولت عطا ہو۔"),
        com.example.data.model.AllahName(89, "الْمُغْنِي", "Al-Mughni", "بے نیاز اور غنی کرنے والا", "The Enricher", "۱۱ بار درود اور ۱۱۱۱ بار پڑھنے سے غنا نصیب ہو۔"),
        com.example.data.model.AllahName(90, "الْمَانِعُ", "Al-Mani'", "روکنے اور حفاظت کرنے والا", "The Preventer", "سوتے وقت ۲۰ بار پڑھے تو گھریلو جھگڑوں سے امن ہو۔"),
        com.example.data.model.AllahName(91, "الضَّارُّ", "Ad-Darr", "نقصان پہنچانے پر قادر", "The Distresser", "شبِ جمعہ میں ۱۰۰ بار پڑھے تو جسمانی اور روحانی آفات سے امان رہے۔"),
        com.example.data.model.AllahName(92, "النَّافِعُ", "An-Nafi'", "نفع اور فائدہ پہنچانے والا", "The Propitious", "ہر کام کے آغاز میں پڑھنے سے نفع اور برکت حاصل ہو۔"),
        com.example.data.model.AllahName(93, "النُّورُ", "An-Noor", "سراپا نور اور روشن کرنے والا", "The Light", "جمعرات کی شب سورۃ النور پڑھ کر ۱۰۰۱ بار پڑھے دل پرنور ہو۔"),
        com.example.data.model.AllahName(94, "الْهَادِي", "Al-Hadi", "سیدھا راستہ دکھانے والا", "The Guide", "آسمان کی طرف منہ کر کے ہاتھ اٹھا کر پڑھے تو ہدایت ملے۔"),
        com.example.data.model.AllahName(95, "الْبَدِيعُ", "Al-Badi'", "بے مثال پیدا کرنے والا", "The Incomparable", "غم اور پریشانی کے وقت ۷۰ بار 'یَا بَدِیعَ الْعَجَائِبِ' پڑھے۔"),
        com.example.data.model.AllahName(96, "الْبَاقِي", "Al-Baqi", "ہمیشہ باقی رہنے والا", "The Everlasting", "جمعہ کی رات ۱۰۰ بار پڑھے تو تمام اعمال قبول ہوں۔"),
        com.example.data.model.AllahName(97, "الْوَارِثُ", "Al-Warith", "سب کے بعد وارث رہنے والا", "The Inheritor", "سورج نکلتے وقت ۱۰۰ بار پڑھے تو پریشانیوں سے نجات ملے۔"),
        com.example.data.model.AllahName(98, "الرَّشِيدُ", "Ar-Rasheed", "صحیح راہ پر چلانے والا", "The Righteous Teacher", "مغرب اور عشاء کے درمیان ۱۰۰۰ بار پڑھے تو کاموں میں بہتری آئے۔"),
        com.example.data.model.AllahName(99, "الصَّبُورُ", "As-Saboor", "نہایت صبر اور برداشت والا", "The Patient", "سورج نکلنے سے پہلے ۱۰۰ بار پڑھے تو ہر مصیبت سے محفوظ رہے۔")
    )
}
