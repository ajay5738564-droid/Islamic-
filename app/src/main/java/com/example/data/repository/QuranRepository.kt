package com.example.data.repository

import com.example.data.local.DeenovaDatabase
import com.example.data.local.OfflineAyahEntity
import com.example.data.model.Ayah
import com.example.data.model.Surah
import com.example.data.model.SurahDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object QuranRepository {

    val ALL_114_SURAHS = listOf(
        Surah(1, "الفاتحة", "Al-Fatihah", "The Opening", "Meccan", 7, 1),
        Surah(2, "البقرة", "Al-Baqarah", "The Cow", "Medinan", 286, 1),
        Surah(3, "آل عمران", "Ali 'Imran", "Family of Imran", "Medinan", 200, 3),
        Surah(4, "النساء", "An-Nisa", "The Women", "Medinan", 176, 4),
        Surah(5, "المائدة", "Al-Ma'idah", "The Table Spread", "Medinan", 120, 6),
        Surah(6, "الأنعام", "Al-An'am", "The Cattle", "Meccan", 165, 7),
        Surah(7, "الأعراف", "Al-A'raf", "The Heights", "Meccan", 206, 8),
        Surah(8, "الأنفال", "Al-Anfal", "The Spoils of War", "Medinan", 75, 9),
        Surah(9, "التوبة", "At-Tawbah", "The Repentance", "Medinan", 129, 10),
        Surah(10, "يونس", "Yunus", "Jonah", "Meccan", 109, 11),
        Surah(11, "هود", "Hud", "Hud", "Meccan", 123, 11),
        Surah(12, "يوسف", "Yusuf", "Joseph", "Meccan", 111, 12),
        Surah(13, "الرعد", "Ar-Ra'd", "The Thunder", "Medinan", 43, 13),
        Surah(14, "إبراهيم", "Ibrahim", "Abraham", "Meccan", 52, 13),
        Surah(15, "الحجر", "Al-Hijr", "The Rocky Tract", "Meccan", 99, 14),
        Surah(16, "النحل", "An-Nahl", "The Bee", "Meccan", 128, 14),
        Surah(17, "الإسراء", "Al-Isra", "The Night Journey", "Meccan", 111, 15),
        Surah(18, "الكهف", "Al-Kahf", "The Cave", "Meccan", 110, 15),
        Surah(19, "مريم", "Maryam", "Mary", "Meccan", 98, 16),
        Surah(20, "طه", "Taha", "Ta-Ha", "Meccan", 135, 16),
        Surah(21, "الأنبياء", "Al-Anbiya", "The Prophets", "Meccan", 112, 17),
        Surah(22, "الحج", "Al-Hajj", "The Pilgrimage", "Medinan", 78, 17),
        Surah(23, "المؤمنون", "Al-Mu'minun", "The Believers", "Meccan", 118, 18),
        Surah(24, "النور", "An-Nur", "The Light", "Medinan", 64, 18),
        Surah(25, "الفرقان", "Al-Furqan", "The Criterion", "Meccan", 77, 18),
        Surah(26, "الشعراء", "Ash-Shu'ara", "The Poets", "Meccan", 227, 19),
        Surah(27, "النمل", "An-Naml", "The Ant", "Meccan", 93, 19),
        Surah(28, "القصص", "Al-Qasas", "The Stories", "Meccan", 88, 20),
        Surah(29, "العنكبوت", "Al-'Ankabut", "The Spider", "Meccan", 69, 20),
        Surah(30, "الروم", "Ar-Rum", "The Romans", "Meccan", 60, 21),
        Surah(31, "لقمان", "Luqman", "Luqman", "Meccan", 34, 21),
        Surah(32, "السجدة", "As-Sajdah", "The Prostration", "Meccan", 30, 21),
        Surah(33, "الأحزاب", "Al-Ahzab", "The Combined Forces", "Medinan", 73, 21),
        Surah(34, "سبأ", "Saba", "Sheba", "Meccan", 54, 22),
        Surah(35, "فاطر", "Fatir", "Originator", "Meccan", 45, 22),
        Surah(36, "يس", "Yaseen", "Ya-Seen", "Meccan", 83, 22),
        Surah(37, "الصافات", "As-Saffat", "Those Who Set The Ranks", "Meccan", 182, 23),
        Surah(38, "ص", "Sad", "The Letter Sad", "Meccan", 88, 23),
        Surah(39, "الزمر", "Az-Zumar", "The Troops", "Meccan", 75, 23),
        Surah(40, "غافر", "Ghafir", "The Forgiver", "Meccan", 85, 24),
        Surah(41, "فصلت", "Fussilat", "Explained in Detail", "Meccan", 54, 24),
        Surah(42, "الشورى", "Ash-Shura", "The Consultation", "Meccan", 53, 25),
        Surah(43, "الزخرف", "Az-Zukhruf", "The Ornaments of Gold", "Meccan", 89, 25),
        Surah(44, "الدخان", "Ad-Dukhan", "The Smoke", "Meccan", 59, 25),
        Surah(45, "الجاثية", "Al-Jathiyah", "The Crouching", "Meccan", 37, 25),
        Surah(46, "الأحقاف", "Al-Ahqaf", "The Wind-Curved Sandhills", "Meccan", 35, 26),
        Surah(47, "محمد", "Muhammad", "Muhammad", "Medinan", 38, 26),
        Surah(48, "الفتح", "Al-Fath", "The Victory", "Medinan", 29, 26),
        Surah(49, "الحجرات", "Al-Hujurat", "The Rooms", "Medinan", 18, 26),
        Surah(50, "ق", "Qaf", "The Letter Qaf", "Meccan", 45, 26),
        Surah(51, "الذاريات", "Adh-Dhariyat", "The Winnowing Winds", "Meccan", 60, 26),
        Surah(52, "الطور", "At-Tur", "The Mount", "Meccan", 49, 27),
        Surah(53, "النجم", "An-Najm", "The Star", "Meccan", 62, 27),
        Surah(54, "القمر", "Al-Qamar", "The Moon", "Meccan", 55, 27),
        Surah(55, "الرحمن", "Ar-Rahman", "The Beneficent", "Medinan", 78, 27),
        Surah(56, "الواقعة", "Al-Waqi'ah", "The Inevitable", "Meccan", 96, 27),
        Surah(57, "الحديد", "Al-Hadid", "The Iron", "Medinan", 29, 27),
        Surah(58, "المجادلة", "Al-Mujadila", "The Pleading Woman", "Medinan", 22, 28),
        Surah(59, "الحشر", "Al-Hashr", "The Exile", "Medinan", 24, 28),
        Surah(60, "الممتحنة", "Al-Mumtahanah", "She That Is To Be Examined", "Medinan", 13, 28),
        Surah(61, "الصف", "As-Saff", "The Ranks", "Medinan", 14, 28),
        Surah(62, "الجمعة", "Al-Jumu'ah", "Friday", "Medinan", 11, 28),
        Surah(63, "المنافقون", "Al-Munafiqun", "The Hypocrites", "Medinan", 11, 28),
        Surah(64, "التغابن", "At-Taghabun", "Mutual Disillusion", "Medinan", 18, 28),
        Surah(65, "الطلاق", "At-Talaq", "The Divorce", "Medinan", 12, 28),
        Surah(66, "التحريم", "At-Tahrim", "The Prohibition", "Medinan", 12, 28),
        Surah(67, "الملك", "Al-Mulk", "The Sovereignty", "Meccan", 30, 29),
        Surah(68, "القلم", "Al-Qalam", "The Pen", "Meccan", 52, 29),
        Surah(69, "الحاقة", "Al-Haqqah", "The Reality", "Meccan", 52, 29),
        Surah(70, "المعارج", "Al-Ma'arij", "The Ascending Stairways", "Meccan", 44, 29),
        Surah(71, "نوح", "Nuh", "Noah", "Meccan", 28, 29),
        Surah(72, "الجن", "Al-Jinn", "The Jinn", "Meccan", 28, 29),
        Surah(73, "المزمل", "Al-Muzzammil", "The Enshrouded One", "Meccan", 20, 29),
        Surah(74, "المدثر", "Al-Muddaththir", "The Cloaked One", "Meccan", 56, 29),
        Surah(75, "القيامة", "Al-Qiyamah", "The Resurrection", "Meccan", 40, 29),
        Surah(76, "الإنسان", "Al-Insan", "Man", "Medinan", 31, 29),
        Surah(77, "المرسلات", "Al-Mursalat", "The Emissaries", "Meccan", 50, 29),
        Surah(78, "النبأ", "An-Naba", "The Tidings", "Meccan", 40, 30),
        Surah(79, "النازعات", "An-Nazi'at", "Those Who Drag Forth", "Meccan", 46, 30),
        Surah(80, "عبس", "'Abasa", "He Frowned", "Meccan", 42, 30),
        Surah(81, "التكوير", "At-Takwir", "The Overthrowing", "Meccan", 29, 30),
        Surah(82, "الانفطار", "Al-Infitar", "The Cleaving", "Meccan", 19, 30),
        Surah(83, "المطففين", "Al-Mutaffifin", "Defrauding", "Meccan", 36, 30),
        Surah(84, "الانشقاق", "Al-Inshiqaq", "The Splitting Open", "Meccan", 25, 30),
        Surah(85, "البروج", "Al-Buruj", "The Mansions of the Stars", "Meccan", 22, 30),
        Surah(86, "الطارق", "At-Tariq", "The Morning Star", "Meccan", 17, 30),
        Surah(87, "الأعلى", "Al-A'la", "The Most High", "Meccan", 19, 30),
        Surah(88, "الغاشية", "Al-Ghashiyah", "The Overwhelming", "Meccan", 26, 30),
        Surah(89, "الفجر", "Al-Fajr", "The Dawn", "Meccan", 30, 30),
        Surah(90, "البلد", "Al-Balad", "The City", "Meccan", 20, 30),
        Surah(91, "الشمس", "Ash-Shams", "The Sun", "Meccan", 15, 30),
        Surah(92, "الليل", "Al-Layl", "The Night", "Meccan", 21, 30),
        Surah(93, "الضحى", "Ad-Duha", "The Morning Hours", "Meccan", 11, 30),
        Surah(94, "الشرح", "Ash-Sharh", "The Relief", "Meccan", 8, 30),
        Surah(95, "التين", "At-Tin", "The Fig", "Meccan", 8, 30),
        Surah(96, "العلق", "Al-'Alaq", "The Clot", "Meccan", 19, 30),
        Surah(97, "القدر", "Al-Qadr", "The Power", "Meccan", 5, 30),
        Surah(98, "البينة", "Al-Bayyinah", "The Clear Proof", "Medinan", 8, 30),
        Surah(99, "الزلزلة", "Az-Zalzalah", "The Earthquake", "Medinan", 8, 30),
        Surah(100, "العاديات", "Al-'Adiyat", "The Courser", "Meccan", 11, 30),
        Surah(101, "القارعة", "Al-Qari'ah", "The Calamity", "Meccan", 11, 30),
        Surah(102, "التكاثر", "At-Takathur", "Rivalry in World Increase", "Meccan", 8, 30),
        Surah(103, "العصر", "Al-'Asr", "The Declining Day", "Meccan", 3, 30),
        Surah(104, "الهمزة", "Al-Humazah", "The Traducer", "Meccan", 9, 30),
        Surah(105, "الفيل", "Al-Fil", "The Elephant", "Meccan", 5, 30),
        Surah(106, "قريش", "Quraysh", "Quraysh", "Meccan", 4, 30),
        Surah(107, "الماعون", "Al-Ma'un", "Small Kindnesses", "Meccan", 7, 30),
        Surah(108, "الكوثر", "Al-Kawthar", "Abundance", "Meccan", 3, 30),
        Surah(109, "الكافرون", "Al-Kafirun", "The Disbelievers", "Meccan", 6, 30),
        Surah(110, "النصر", "An-Nasr", "The Divine Help", "Medinan", 3, 30),
        Surah(111, "المسد", "Al-Masad", "The Palm Fibre", "Meccan", 5, 30),
        Surah(112, "الإخلاص", "Al-Ikhlas", "The Sincerity", "Meccan", 4, 30),
        Surah(113, "الفلق", "Al-Falaq", "The Daybreak", "Meccan", 5, 30),
        Surah(114, "الناس", "An-Nas", "Mankind", "Meccan", 6, 30)
    )

    // Curated offline essential Surahs (Al-Fatihah, Al-Ikhlas, Al-Falaq, An-Nas, Al-Kawthar, Al-Asr, Ayat al-Kursi, etc.)
    val CURATED_OFFLINE_AYAHS: Map<Int, List<Ayah>> = mapOf(
        1 to listOf(
            Ayah(1, 1, 1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "In the name of Allah, the Entirely Merciful, the Especially Merciful.", "अल्लाह के नाम से जो बहुत मेहरबान, निहायत रहम फरमाने वाला है।", "اللہ کے نام سے جو بڑا مہربان نہایت رحم والا ہے", "Bismillahir Rahmanir Raheem", 1, "https://everyayah.com/data/Alafasy_128kbps/001001.mp3"),
            Ayah(2, 1, 2, "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", "[All] praise is [due] to Allah, Lord of the worlds.", "सब तारीफ़ अल्लाह ही के लिए है जो सारे जहान का रब है।", "سب تعریفیں اللہ ہی کے لیے ہیں جو تمام جہانوں کا رب ہے", "Alhamdu lillahi Rabbil 'aalameen", 1, "https://everyayah.com/data/Alafasy_128kbps/001002.mp3"),
            Ayah(3, 1, 3, "الرَّحْمَٰنِ الرَّحِيمِ", "The Entirely Merciful, the Especially Merciful,", "बहुत मेहरबान, निहायत रहम फरमाने वाला।", "بڑا مہربان نہایت رحم فرمانے والا", "Ar-Rahmanir-Raheem", 1, "https://everyayah.com/data/Alafasy_128kbps/001003.mp3"),
            Ayah(4, 1, 4, "مَالِكِ يَوْمِ الدِّينِ", "Sovereign of the Day of Recompense.", "बदले के दिन (क़यामत) का मालिक।", "روزِ جزا کا مالک", "Maliki yawmid-Deen", 1, "https://everyayah.com/data/Alafasy_128kbps/001004.mp3"),
            Ayah(5, 1, 5, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "It is You we worship and You we ask for help.", "हम तेरी ही इबादत करते हैं और तुझ ही से मदद चाहते हैं।", "ہم تیری ہی عبادت کرتے ہیں اور تجھ ہی سے مدد مانگتے ہیں", "Iyyaka na'budu wa iyyaka nasta'een", 1, "https://everyayah.com/data/Alafasy_128kbps/001005.mp3"),
            Ayah(6, 1, 6, "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ", "Guide us to the straight path -", "हमें सीधे रास्ते की हिदायत अता फरमा।", "ہمیں سیدھا راستہ دکھا", "Ihdinas-Siraatal-Mustaqeem", 1, "https://everyayah.com/data/Alafasy_128kbps/001006.mp3"),
            Ayah(7, 1, 7, "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", "The path of those upon whom You have bestowed favor, not of those who have evoked [Your] anger or of those who are astray.", "उन लोगों का रास्ता जिन पर तूने इनाम फरमाया, न कि उनका जिन पर गज़ब हुआ और न भटके हुओं का।", "ان لوگوں کا راستہ جن پر تو نے انعام کیا، نہ ان کا جن پر غضب ہوا اور نہ گمراہوں کا", "Siraatal-ladheena an'amta 'alayhim ghayril-maghdoobi 'alayhim wa lad-daalleen", 1, "https://everyayah.com/data/Alafasy_128kbps/001007.mp3")
        ),
        103 to listOf(
            Ayah(6177, 103, 1, "وَالْعَصْرِ", "By time,", "ज़माने की क़सम!", "عصر کے وقت کی قسم", "Wal-'asr", 30, "https://everyayah.com/data/Alafasy_128kbps/103001.mp3"),
            Ayah(6178, 103, 2, "إِنَّ الْإِنسَانَ لَفِي خُسْرٍ", "Indeed, mankind is in loss,", "बेशक इंसान बड़े नुकसान और घाटे में है,", "بے شک انسان خسارے میں ہے", "Innal-insaana lafee khusr", 30, "https://everyayah.com/data/Alafasy_128kbps/103002.mp3"),
            Ayah(6179, 103, 3, "إِلَّا الَّذِينَ آمَنُوا وَعَمِلُوا الصَّالِحَاتِ وَتَوَاصَوْا بِالْحَقِّ وَتَوَاصَوْا بِالصَّبْرِ", "Except for those who have believed and done righteous deeds and advised each other to truth and advised each other to patience.", "सिवाए उन लोगों के जो ईमान लाए और उन्होंने नेक अमल किए और एक दूसरे को हक़ की नसीहत की और सब्र की ताकीद की।", "سوائے ان کے جو ایمان لائے اور نیک عمل کیے اور حق کی وصیت کی اور صبر کی تاکید کی", "Illal-ladheena aamanoo wa 'amilus-saalihaati wa tawaasaw bilhaqqi wa tawaasaw bis-sabr", 30, "https://everyayah.com/data/Alafasy_128kbps/103003.mp3")
        ),
        108 to listOf(
            Ayah(6194, 108, 1, "إِنَّا أَعْطَيْنَاكَ الْكَوْثَرَ", "Indeed, We have granted you, [O Muhammad], al-Kawthar.", "बेशक हमने आपको (ऐ नबी) कौसर (बेहिसाब खैर) अता फरमाई।", "بے شک ہم نے آپ کو کوثر عطا فرمائی", "Innaaa a'taynaakal-Kawthar", 30, "https://everyayah.com/data/Alafasy_128kbps/108001.mp3"),
            Ayah(6195, 108, 2, "فَصَلِّ لِرَبِّكَ وَانْحَرْ", "So pray to your Lord and sacrifice [to Him alone].", "तो आप अपने रब के लिए नमाज़ पढ़िए और क़ुरबानी कीजिए।", "پس اپنے رب کے لیے نماز پڑھیے اور قربانی کیجیے", "Fasalli li-Rabbika wanhar", 30, "https://everyayah.com/data/Alafasy_128kbps/108002.mp3"),
            Ayah(6196, 108, 3, "إِنَّ شَانِئَكَ هُوَ الْأَبْتَرُ", "Indeed, your enemy is the one cut off.", "यक़ीनन आपका दुश्मन ही बे-नाम-ओ-निशान रहेगा।", "بے شک آپ کا دشمن ہی بے نام و نشان رہے گا", "Inna shaani'aka huwal-abtar", 30, "https://everyayah.com/data/Alafasy_128kbps/108003.mp3")
        ),
        112 to listOf(
            Ayah(6222, 112, 1, "قُلْ هُوَ اللَّهُ أَحَدٌ", "Say, \"He is Allah, [who is] One,", "कह दीजिए: वो अल्लाह एक है,", "کہہ دیجیے کہ وہ اللہ ایک ہے", "Qul Huwal-Laahu Ahad", 30, "https://everyayah.com/data/Alafasy_128kbps/112001.mp3"),
            Ayah(6223, 112, 2, "اللَّهُ الصَّمَدُ", "Allah, the Eternal Refuge.", "अल्लाह बेनियाज़ और सबका सहारा है,", "اللہ بے نیاز ہے", "Allaahus-Samad", 30, "https://everyayah.com/data/Alafasy_128kbps/112002.mp3"),
            Ayah(6224, 112, 3, "لَمْ يَلِدْ وَلَمْ يُولَدْ", "He neither begets nor is born,", "न उसकी कोई औलाद है और न वो किसी से पैदा हुआ,", "نہ اس کی کوئی اولاد ہے اور نہ وہ کسی سے پیدا ہوا", "Lam yalid wa lam yoolad", 30, "https://everyayah.com/data/Alafasy_128kbps/112003.mp3"),
            Ayah(6225, 112, 4, "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ", "Nor is there to Him any equivalent.\"", "और न कोई उसके बराबर का है।", "اور کوئی اس کا ہمسر نہیں", "Wa lam yakul-lahoo kufuwan ahad", 30, "https://everyayah.com/data/Alafasy_128kbps/112004.mp3")
        ),
        113 to listOf(
            Ayah(6226, 113, 1, "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", "Say, \"I seek refuge in the Lord of daybreak", "कह दीजिए: मैं सुबह के रब की पनाह मांगता हूँ,", "کہہ دیجیے کہ میں صبح کے رب کی پناہ مانگتا ہوں", "Qul a'oodhu bi Rabbil-falaq", 30, "https://everyayah.com/data/Alafasy_128kbps/113001.mp3"),
            Ayah(6227, 113, 2, "مِن شَرِّ مَا خَلَقَ", "From the evil of that which He created", "हर उस चीज़ की बुराई से जो उसने पैदा की,", "ہر اس چیز کی برائی سے جو اس نے پیدا کی", "Min sharri maa khalaq", 30, "https://everyayah.com/data/Alafasy_128kbps/113002.mp3"),
            Ayah(6228, 113, 3, "وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ", "And from the evil of darkness when it settles", "और अँधेरी रात की बुराई से जब वो छा जाए,", "اور اندھیری رات کی برائی سے جب وہ چھا جائے", "Wa min sharri ghaasiqin idhaa waqab", 30, "https://everyayah.com/data/Alafasy_128kbps/113003.mp3"),
            Ayah(6229, 113, 4, "وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ", "And from the evil of the blowers in knots", "और गिरहों में फूंकने वाली औरतों की बुराई से,", "اور گرہوں میں پھونکنے والیوں کے شر سے", "Wa min sharrin-naffaathaati fil 'uqad", 30, "https://everyayah.com/data/Alafasy_128kbps/113004.mp3"),
            Ayah(6230, 113, 5, "وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ", "And from the evil of an envier when he envies.\"", "और हसद करने वाले की बुराई से जब वो हसद करे।", "اور حسد کرنے والے کی برائی سے جب وہ حسد کرے", "Wa min sharri haasidin idhaa hasad", 30, "https://everyayah.com/data/Alafasy_128kbps/113005.mp3")
        ),
        114 to listOf(
            Ayah(6231, 114, 1, "قُلْ أَعُوذُ بِرَبِّ النَّاسِ", "Say, \"I seek refuge in the Lord of mankind,", "कह दीजिए: मैं लोगों के रब की पनाह में आता हूँ,", "کہہ دیجیے کہ میں انسانوں کے پروردگار کی پناہ میں آتا ہوں", "Qul a'oodhu bi Rabbin-naas", 30, "https://everyayah.com/data/Alafasy_128kbps/114001.mp3"),
            Ayah(6232, 114, 2, "مَلِكِ النَّاسِ", "The Sovereign of mankind,", "जो लोगों का बादशाह है,", "جو انسانوں کا بادشاہ ہے", "Malikin-naas", 30, "https://everyayah.com/data/Alafasy_128kbps/114002.mp3"),
            Ayah(6233, 114, 3, "إِلَٰهِ النَّاسِ", "The God of mankind,", "जो लोगों का सच्चा माबूद है,", "جو انسانوں کا معبود ہے", "Ilaahin-naas", 30, "https://everyayah.com/data/Alafasy_128kbps/114003.mp3"),
            Ayah(6234, 114, 4, "مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ", "From the evil of the retreating whisperer -", "पीछे हट जाने वाले वसवसा डालने वाले के शर से,", "وسوسہ ڈالنے والے پیچھے ہٹ جانے والے کے شر سے", "Min sharril-waswaasil-khannaas", 30, "https://everyayah.com/data/Alafasy_128kbps/114004.mp3"),
            Ayah(6235, 114, 5, "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ", "Who whispers into the breasts of mankind -", "जो लोगों के सीनों में वसवसे डालता है,", "جو لوگوں کے دلوں میں وسوسے ڈالتا ہے", "Alladhee yuwaswisu fee sudoorin-naas", 30, "https://everyayah.com/data/Alafasy_128kbps/114005.mp3"),
            Ayah(6236, 114, 6, "مِنَ الْجِنَّةِ وَالنَّاسِ", "From among the jinn and mankind.\"", "जिन्नों में से भी और इंसानों में से भी।", "خواہ وہ جنوں میں سے ہو یا انسانوں میں سے", "Minal-jinnati wan-naas", 30, "https://everyayah.com/data/Alafasy_128kbps/114006.mp3")
        ),
        67 to listOf(
            Ayah(5242, 67, 1, "تَبَارَكَ الَّذِي بِيَدِهِ الْمُلْكُ وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ", "Blessed is He in whose hand is dominion, and He is over all things competent -", "बड़ी बरकत वाला है वो जिसके हाथ में सारी बादशाही है और वो हर चीज़ पर पूरी क़ुदरत रखता है।", "بڑی برکت والا ہے وہ جس کے ہاتھ میں سلطنت ہے اور وہ ہر چیز پر قادر ہے", "Tabaarakal-ladhee biyadihil-mulku wa Huwa 'alaa kulli shay'in Qadeer", 29, "https://everyayah.com/data/Alafasy_128kbps/067001.mp3"),
            Ayah(5243, 67, 2, "الَّذِي خَلَقَ الْمَوْتَ وَالْحَيَاةَ لِيَبْلُوَكُمْ أَيُّكُمْ أَحْسَنُ عَمَلًا ۚ وَهُوَ الْعَزِيزُ الْغَفُورُ", "[He] who created death and life to test you [as to] which of you is best in deed - and He is the Exalted in Might, the Forgiving -", "जिसने मौत और ज़िन्दगी को पैदा किया ताकि तुम्हें आज़माए कि तुम में से कौन बेहतर अमल करता है।", "جس نے موت اور زندگی کو پیدا کیا تاکہ تمہیں آزمائے کہ تم میں سے کون اچھے عمل کرتا ہے", "Alladhee khalaqal-mawta wal-hayaata liyabluwakum ayyukum ahsanu 'amalaa", 29, "https://everyayah.com/data/Alafasy_128kbps/067002.mp3")
        )
    )

    suspend fun getSurahAyahs(database: DeenovaDatabase, surahNumber: Int): List<Ayah> {
        return withContext(Dispatchers.IO) {
            // 1. Check local Room database
            val localList = database.offlineAyahDao().getAyahsForSurah(surahNumber)
            if (localList.isNotEmpty()) {
                return@withContext localList.map {
                    Ayah(
                        number = it.numberGlobal,
                        surahNumber = it.surahNumber,
                        numberInSurah = it.numberInSurah,
                        textArabic = it.textArabic,
                        textEnglish = it.textEnglish,
                        textHindi = it.textHindi,
                        textUrdu = it.textUrdu,
                        transliteration = it.transliteration,
                        juz = it.juz,
                        audioUrl = it.audioUrl
                    )
                }
            }

            // 2. Check bundled curated offline verses
            if (CURATED_OFFLINE_AYAHS.containsKey(surahNumber)) {
                val list = CURATED_OFFLINE_AYAHS[surahNumber]!!
                // Save to Room for future fast access
                database.offlineAyahDao().insertAyahs(list.map {
                    OfflineAyahEntity(
                        surahNumber = it.surahNumber,
                        numberInSurah = it.numberInSurah,
                        numberGlobal = it.number,
                        textArabic = it.textArabic,
                        textEnglish = it.textEnglish,
                        textHindi = it.textHindi,
                        textUrdu = it.textUrdu,
                        transliteration = it.transliteration,
                        juz = it.juz,
                        audioUrl = it.audioUrl
                    )
                })
                return@withContext list
            }

            // 3. Online fetch from verified api.alquran.cloud (Arabic + Sahih International)
            try {
                val fetched = fetchSurahFromApi(surahNumber)
                if (fetched.isNotEmpty()) {
                    database.offlineAyahDao().insertAyahs(fetched.map {
                        OfflineAyahEntity(
                            surahNumber = it.surahNumber,
                            numberInSurah = it.numberInSurah,
                            numberGlobal = it.number,
                            textArabic = it.textArabic,
                            textEnglish = it.textEnglish,
                            textHindi = it.textHindi,
                            textUrdu = it.textUrdu,
                            transliteration = it.transliteration,
                            juz = it.juz,
                            audioUrl = it.audioUrl
                        )
                    })
                    return@withContext fetched
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Fallback generated placeholder with clear data indicator if offline
            val surah = ALL_114_SURAHS.find { it.number == surahNumber }
            return@withContext (1..(surah?.numberOfAyahs ?: 7)).take(10).map { i ->
                val sNumFormatted = String.format("%03d", surahNumber)
                val aNumFormatted = String.format("%03d", i)
                Ayah(
                    number = surahNumber * 100 + i,
                    surahNumber = surahNumber,
                    numberInSurah = i,
                    textArabic = if (i == 1 && surahNumber != 9) "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ" else "آية رقم $i من سورة ${surah?.nameArabic ?: ""}",
                    textEnglish = "Ayah $i of Surah ${surah?.nameEnglish ?: ""}. [Connect to internet to download full text]",
                    textHindi = "सूरह ${surah?.nameEnglish} की आयत $i (ऑनलाइन होने पर पूरा पाठ लोड होगा)",
                    textUrdu = "سورۃ ${surah?.nameArabic} کی آیت $i",
                    transliteration = "Ayah $i",
                    juz = surah?.startingJuz ?: 1,
                    audioUrl = "https://everyayah.com/data/Alafasy_128kbps/$sNumFormatted$aNumFormatted.mp3"
                )
            }
        }
    }

    suspend fun getJuzAyahs(database: DeenovaDatabase, juzNumber: Int): List<Ayah> {
        return withContext(Dispatchers.IO) {
            require(juzNumber in 1..30) { "Juz number must be between 1 and 30" }

            val cached = database.offlineAyahDao().getAyahsForJuz(juzNumber)
            if (cached.isNotEmpty()) {
                return@withContext cached.map {
                    Ayah(it.numberGlobal, it.surahNumber, it.numberInSurah, it.textArabic,
                        it.textEnglish, it.textHindi, it.textUrdu, it.transliteration, it.juz, it.audioUrl)
                }
            }

            // Fetch each edition separately. The combined-editions endpoint can fail on
            // some networks/CDN responses; separate requests provide a reliable fallback.
            fun fetchEdition(edition: String): JSONArray? {
                val url = URL("https://api.alquran.cloud/v1/juz/$juzNumber/$edition")
                val conn = (url.openConnection() as HttpURLConnection).apply {
                    connectTimeout = 20000
                    readTimeout = 25000
                    requestMethod = "GET"
                    setRequestProperty("Accept", "application/json")
                    setRequestProperty("User-Agent", "Deenova-Android")
                    instanceFollowRedirects = true
                }
                return try {
                    val code = conn.responseCode
                    if (code != HttpURLConnection.HTTP_OK) {
                        val error = conn.errorStream?.bufferedReader()?.use { it.readText() }
                        android.util.Log.w("QuranRepository", "Juz API returned HTTP $code: $error")
                        null
                    } else {
                        val root = JSONObject(conn.inputStream.bufferedReader().use { it.readText() })
                        if (root.optInt("code", 200) != 200) null
                        else root.optJSONObject("data")?.optJSONArray("ayahs")
                    }
                } catch (e: Exception) {
                    android.util.Log.w("QuranRepository", "Failed to fetch Juz $juzNumber edition $edition", e)
                    null
                } finally {
                    conn.disconnect()
                }
            }

            try {
                val arabicAyahs = fetchEdition("quran-uthmani")
                val englishAyahs = fetchEdition("en.sahih")
                if (arabicAyahs == null || englishAyahs == null || arabicAyahs.length() == 0 ||
                    arabicAyahs.length() != englishAyahs.length()) {
                    android.util.Log.e("QuranRepository", "Incomplete Juz $juzNumber response")
                    return@withContext emptyList()
                }

                val result = ArrayList<Ayah>(arabicAyahs.length())
                for (i in 0 until arabicAyahs.length()) {
                    val ar = arabicAyahs.getJSONObject(i)
                    val en = englishAyahs.getJSONObject(i)
                    val surahNumber = ar.getJSONObject("surah").getInt("number")
                    val numberInSurah = ar.getInt("numberInSurah")
                    val surahInfo = ALL_114_SURAHS.find { it.number == surahNumber }
                    val s = String.format(Locale.US, "%03d", surahNumber)
                    val a = String.format(Locale.US, "%03d", numberInSurah)
                    result.add(
                        Ayah(
                            number = ar.getInt("number"),
                            surahNumber = surahNumber,
                            numberInSurah = numberInSurah,
                            textArabic = ar.getString("text"),
                            textEnglish = en.getString("text"),
                            textHindi = "",
                            textUrdu = "",
                            transliteration = "",
                            juz = ar.optInt("juz", juzNumber),
                            audioUrl = "https://everyayah.com/data/Alafasy_128kbps/$s$a.mp3"
                        )
                    )
                }
                database.offlineAyahDao().insertAyahs(result.map {
                    OfflineAyahEntity(it.surahNumber, it.numberInSurah, it.number, it.textArabic,
                        it.textEnglish, it.textHindi, it.textUrdu, it.transliteration, it.juz, it.audioUrl)
                })
                result
            } catch (e: Exception) {
                android.util.Log.e("QuranRepository", "Unable to load Juz $juzNumber", e)
                emptyList()
            }
        }
    }

    suspend fun cacheAllJuzForOffline(database: DeenovaDatabase) {
        withContext(Dispatchers.IO) {
            for (juz in 1..30) {
                try {
                    if (database.offlineAyahDao().getAyahsForJuz(juz).isEmpty()) {
                        val downloaded = getJuzAyahs(database, juz)
                        if (downloaded.isEmpty()) break
                    }
                } catch (e: Exception) {
                    android.util.Log.w("QuranRepository", "Offline cache paused at Juz $juz", e)
                    break
                }
            }
        }
    }

    private fun fetchSurahFromApi(surahNumber: Int): List<Ayah> {
        val surahInfo = ALL_114_SURAHS.find { it.number == surahNumber }
        val sNumFormatted = String.format("%03d", surahNumber)

        // API endpoints: quran-uthmani and en.sahih
        val urlStr = "https://api.alquran.cloud/v1/surah/$surahNumber/editions/quran-uthmani,en.sahih"
        val url = URL(urlStr)
        val conn = url.openConnection() as HttpURLConnection
        conn.connectTimeout = 5000
        conn.readTimeout = 5000
        conn.requestMethod = "GET"

        if (conn.responseCode == 200) {
            val responseText = conn.inputStream.bufferedReader().use { it.readText() }
            val root = JSONObject(responseText)
            val dataArray = root.getJSONArray("data")

            val arabicEd = dataArray.getJSONObject(0)
            val englishEd = dataArray.getJSONObject(1)

            val arabicAyahs = arabicEd.getJSONArray("ayahs")
            val englishAyahs = englishEd.getJSONArray("ayahs")

            val result = mutableListOf<Ayah>()
            for (i in 0 until arabicAyahs.length()) {
                val arObj = arabicAyahs.getJSONObject(i)
                val enObj = englishAyahs.getJSONObject(i)

                val numInSurah = arObj.getInt("numberInSurah")
                val aNumFormatted = String.format("%03d", numInSurah)

                result.add(
                    Ayah(
                        number = arObj.getInt("number"),
                        surahNumber = surahNumber,
                        numberInSurah = numInSurah,
                        textArabic = arObj.getString("text"),
                        textEnglish = enObj.getString("text"),
                        textHindi = "",
                        textUrdu = "",
                        transliteration = "",
                        juz = arObj.optInt("juz", surahInfo?.startingJuz ?: 1),
                        audioUrl = "https://everyayah.com/data/Alafasy_128kbps/$sNumFormatted$aNumFormatted.mp3"
                    )
                )
            }
            return result
        }
        return emptyList()
    }
}
