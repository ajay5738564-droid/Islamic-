package com.example.data.repository

import com.example.data.model.ArticleSection
import com.example.data.model.KnowledgeArticle
import com.example.data.model.KnowledgeCategory

object IslamicKnowledgeRepository {

    val ALL_ARTICLES = listOf(
        // 1. 6 Kalimas of Islam
        KnowledgeArticle(
            id = "six_kalimas",
            category = KnowledgeCategory.SIX_KALIMAS,
            title = "The Six Kalimas of Islam (چھ کلمے)",
            titleUrdu = "اسلام کے چھ کلمے مع ترجمہ و فضائل",
            titleHindi = "इस्लाम के ६ कलमे (अनुवाद व फज़ीलत)",
            titleArabic = "الكلمات الست في الإسلام",
            summary = "The essential 6 Kalimas covering faith declaration, praise, monotheism, repentance, and rejection of disbelief.",
            summaryUrdu = "اسلام کے بنیادی چھ کلمے، جن میں کلمہ طیبہ، شہادت، تمجید، توحید، استغفار اور ردِ کفر شامل ہیں۔",
            summaryHindi = "इस्लाम के छह बुनियादी कलमे, जो ईमान, गवाही, तौहीद, तौबा और कुफ्र से बचने की तालीम देते हैं।",
            sections = listOf(
                ArticleSection(
                    heading = "1. First Kalima - Tayyibah (کلمہ طیبہ)",
                    headingUrdu = "پہلا کلمہ: طیب (پاکیزگی کا کلمہ)",
                    headingHindi = "पहला कलमा: तय्यब (पवित्रता का कलमा)",
                    body = "The core creed of Islam. Declares absolute monotheism and the final prophethood of Muhammad (PBUH).",
                    bodyUrdu = "ترجمہ: اللہ کے سوا کوئی معبود نہیں، محمد (ﷺ) اللہ کے رسول ہیں۔\nفضیلت: جو شخص صدقِ دل سے یہ کلمہ کہے گا وہ جنت میں داخل ہو گا۔",
                    bodyHindi = "अनुवाद: अल्लाह के सिवा कोई इबादत के लायक नहीं, हज़रत मुहम्मद (ﷺ) अल्लाह के रसूल हैं।\nफज़ीलत: जो सच्चे दिल से यह पढ़ेगा वह जन्नत का हकदार होगा।",
                    arabicQuote = "لَا إِلٰهَ إِلَّا اللهُ مُحَمَّدٌ رَسُولُ اللهِ",
                    translationQuote = "There is no deity worthy of worship except Allah, and Muhammad is the Messenger of Allah."
                ),
                ArticleSection(
                    heading = "2. Second Kalima - Shahadah (کلمہ شہادت)",
                    headingUrdu = "دوسرا کلمہ: شہادت (گواہی کا کلمہ)",
                    headingHindi = "दूसरा कलमा: शहादत (गवाही का कलमा)",
                    body = "Bearing witness to the oneness of Allah and the servanthood and messenger-ship of Muhammad (PBUH).",
                    bodyUrdu = "ترجمہ: میں گواہی دیتا ہوں کہ اللہ کے سوا کوئی معبود نہیں، وہ اکیلا ہے، اس کا کوئی شریک نہیں، اور میں گواہی دیتا ہوں کہ محمد (ﷺ) اس کے بندے اور رسول ہیں۔",
                    bodyHindi = "अनुवाद: मैं गवाही देता हूँ कि अल्लाह के सिवा कोई माबूद नहीं, वह अकेला है, उसका कोई साझीदार नहीं, और मुहम्मद (ﷺ) उसके बंदे और रसूल हैं।",
                    arabicQuote = "أَشْهَدُ أَنْ لَا إِلٰهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ وَأَشْهَدُ أَنَّ مُحَمَّدًا عَبْدُهُ وَرَسُولُهُ",
                    translationQuote = "I bear witness that there is no god but Allah, alone without partner, and I bear witness that Muhammad is His servant and Messenger."
                ),
                ArticleSection(
                    heading = "3. Third Kalima - Tamjeed (کلمہ تمجید)",
                    headingUrdu = "تیسرا کلمہ: تمجید (بزرگی و عظمت کا کلمہ)",
                    headingHindi = "तीसरा कलमा: तमजीद (अल्लाह की पाकी व बुजुर्गी)",
                    body = "Glorifying, praising, and affirming the supreme majesty of Allah.",
                    bodyUrdu = "ترجمہ: اللہ پاک ہے، اور سب تعریفیں اللہ کے لیے ہیں، اور اللہ کے سوا کوئی معبود نہیں، اور اللہ سب سے بڑا ہے، اور گناہوں سے بچنے کی طاقت اور نیکی کرنے کی قوت صرف اللہ ہی کی طرف سے ہے جو بلند و برتر اور عظمت والا ہے۔",
                    bodyHindi = "अनुवाद: अल्लाह पाक है, सारी तारीफें अल्लाह के लिए हैं, अल्लाह के सिवा कोई माबूद नहीं और अल्लाह सबसे बड़ा है। गुनाहों से बचने और नेकी करने की ताकत केवल अल्लाह की मदद से है।",
                    arabicQuote = "سُبْحَانَ اللهِ وَالْحَمْدُ لِلّٰهِ وَلَا إِلٰهَ إِلَّا اللهُ وَاللهُ أَكْبَرُ وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ الْعَلِيِّ الْعَظِيمِ",
                    translationQuote = "Glory be to Allah, and all praise is due to Allah, and there is no god but Allah, and Allah is the Greatest. And there is no power nor strength except with Allah, the Most High, the Supreme."
                ),
                ArticleSection(
                    heading = "4. Fourth Kalima - Tawheed (کلمہ توحید)",
                    headingUrdu = "چوتھا کلمہ: توحید (یکتائی کا کلمہ)",
                    headingHindi = "चौथा कलमा: तौहीद (अल्लाह की एकता का कलमा)",
                    body = "Affirmation of the absolute sovereignty, eternal life, and omnipotence of Allah.",
                    bodyUrdu = "ترجمہ: اللہ کے سوا کوئی معبود نہیں، وہ اکیلا ہے، اس کا کوئی شریک نہیں، اسی کی بادشاہی ہے اور اسی کے لیے تمام تعریفیں ہیں، وہی زندہ کرتا ہے اور وہی مارتا ہے، اور وہ ہمیشہ زندہ رہنے والا ہے جسے کبھی موت نہیں آئے گی، عظمت اور بزرگی والا ہے، اسی کے ہاتھ میں تمام بھلائی ہے اور وہ ہر چیز پر قادر ہے۔",
                    bodyHindi = "अनुवाद: अल्लाह के सिवा कोई माबूद नहीं, वह एक है, कोई उसका साझीदार नहीं, उसी की सल्तनत है और उसी के लिए सारी तारीफें हैं, वही जिलाता है और वही मारता है, वह हमेशा जिंदा रहने वाला है, सारी भलाई उसी के हाथ में है और वह हर चीज़ पर कादिर है।",
                    arabicQuote = "لَا إِلٰهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ يُحْيِي وَيُمِيتُ وَهُوَ حَيٌّ لَا يَمُوتُ أَبَدًا أَبَدًا، ذُو الْجَلَالِ وَالْإِكْرَامِ، بِيَدِهِ الْخَيْرُ وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ",
                    translationQuote = "There is no god but Allah alone, without partner. His is the sovereignty and His is the praise. He gives life and causes death, and He is living and will never die. Possessor of majesty and honor. In His hand is all good, and He has power over all things."
                ),
                ArticleSection(
                    heading = "5. Fifth Kalima - Astaghfar (کلمہ استغفار)",
                    headingUrdu = "پانچواں کلمہ: استغفار (بخشش طلب کرنے کا کلمہ)",
                    headingHindi = "पांचवां कलमा: इस्तिगफार (तौबा व माफी का कलमा)",
                    body = "Seeking forgiveness from Allah for all sins, known and unknown, intentional or unintentional.",
                    bodyUrdu = "ترجمہ: میں اللہ سے اپنے تمام گناہوں کی بخشش مانگتا ہوں جو میں نے جان بوجھ کر کیے یا بھول چوک سے کیے، چھپ کر کیے یا علانیہ کیے، اور میں توبہ کرتا ہوں اس کے حضور اس گناہ سے جسے میں جانتا ہوں اور اس گناہ سے بھی جسے میں نہیں جانتا، بیشک تو ہی غیبوں کو خوب جاننے والا اور عیبوں کو چھپانے والا اور گناہوں کو بخشنے والا ہے، اور گناہوں سے بچنے اور نیکی کرنے کی طاقت اللہ کے بغیر ممکن نہیں۔",
                    bodyHindi = "अनुवाद: मैं अपने रब अल्लाह से अपने हर गुनाह की माफी मांगता हूँ जो मैंने जानबूझकर या अनजाने में किया, छिपकर किया या खुलकर किया। मैं हर उस गुनाह से तौबा करता हूँ जिसे मैं जानता हूँ या नहीं जानता। बेशक तू ग़ैब का जानने वाला, ऐबों को छुपाने वाला और गुनाहों को बख्शने वाला है।",
                    arabicQuote = "أَسْتَغْفِرُ اللهَ رَبِّي مِنْ كُلِّ ذَنْبٍ أَذْنَبْتُهُ عَمَدًا أَوْ خَطَأً سِرًّا أَوْ عَلَانِيَةً وَأَتُوبُ إِلَيْهِ مِنَ الذَّنْبِ الَّذِي أَعْلَمُ وَمِنَ الذَّنْبِ الَّذِي لَا أَعْلَمُ، إِنَّكَ أَنْتَ عَلَّامُ الْغُيُوبِ وَسَتَّارُ الْعُيُوبِ وَغَفَّارُ الذُّنُوبِ وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ الْعَلِيِّ الْعَظِيمِ",
                    translationQuote = "I seek forgiveness from Allah, my Lord, for every sin I committed knowingly or unknowingly, secretly or openly, and I turn to Him in repentance."
                ),
                ArticleSection(
                    heading = "6. Sixth Kalima - Rad-de-Kufr (کلمہ ردِ کفر)",
                    headingUrdu = "چھٹا کلمہ: ردِ کفر (کفر و شرک سے بیزاری کا کلمہ)",
                    headingHindi = "छठा कलमा: रद्दे कुफ्र (कुफ्र व शिर्क से नफरत का कलमा)",
                    body = "Explicit rejection of polytheism, disbelief, falsehood, backbiting, and all sinful innovations.",
                    bodyUrdu = "ترجمہ: اے اللہ! میں تیری پناہ مانگتا ہوں اس بات سے کہ تیرے ساتھ کسی چیز کو شریک ٹھہراؤں جس کا مجھے علم ہو، اور میں تجھ سے بخشش مانگتا ہوں اس کی جس کا مجھے علم نہیں۔ میں نے اس سے توبہ کی اور میں کفر، شرک، جھوٹ، غیبت، بدعت، چغلی، بے حیائی، تہمت اور تمام نافرمانیوں سے بیزار ہوا، اور میں اسلام لایا اور میں کہتا ہوں کہ اللہ کے سوا کوئی معبود نہیں، محمد (ﷺ) اللہ کے رسول ہیں۔",
                    bodyHindi = "अनुवाद: ऐ अल्लाह! मैं तेरी पनाह मांगता हूँ इस बात से कि किसी को तेरा साझीदार बनाऊँ जिसे मैं जानता हूँ, और माफी मांगता हूँ उससे जिसे मैं नहीं जानता। मैंने तौबा की और कुफ्र, शिर्क, झूठ, ग़ीबत, चुगली और तमाम बुराइयों से दूर हुआ। मैंने इस्लाम कबूल किया और कहा: अल्लाह के सिवा कोई माबूद नहीं, मुहम्मद (ﷺ) अल्लाह के रसूल हैं।",
                    arabicQuote = "اللّٰهُمَّ إِنِّي أَعُوذُ بِكَ مِنْ أَنْ أُشْرِكَ بِكَ شَيْئًا وَأَنَا أَعْلَمُ بِهِ، وَأَسْتَغْفِرُكَ لِمَا لَا أَعْلَمُ بِهِ، تُبْتُ عَنْهُ وَتَبَرَّأْتُ مِنَ الْكُفْرِ وَالشِّرْكِ وَالْكِذْبِ وَالْغِيبَةِ وَالْبِدْعَةِ وَالنَّمِيمَةِ وَالْفَوَاحِشِ وَالْبُهْتَانِ وَالْمَعَاصِي كُلِّهَا، وَأَسْلَمْتُ وَأَقُولُ: لَا إِلٰهَ إِلَّا اللهُ مُحَمَّدٌ رَسُولُ اللهِ",
                    translationQuote = "O Allah, I seek refuge in You from associating anything with You knowingly, and I ask Your forgiveness for what I do not know. I repent from it and disassociate from disbelief, polytheism, and all sins."
                )
            ),
            reference = "Sunni Islamic Tradition & Hadith Collections"
        ),

        // 2. The Five Pillars of Islam
        KnowledgeArticle(
            id = "five_pillars",
            category = KnowledgeCategory.PILLARS,
            title = "The Five Pillars of Islam (أركان الإسلام)",
            titleUrdu = "اسلام کے ۵ بنیادی ارکان (مکمل تفصیل)",
            titleHindi = "इस्लाम के ५ बुनियादी अरकान (खंभे)",
            titleArabic = "أركان الإسلام الخمسة",
            summary = "The essential foundational acts of worship and declaration upon which the entire practice of Islam is built.",
            summaryUrdu = "اسلام کے پانچ بنیادی ارکان: توحید و رسالت، نماز، زکوٰۃ، روزہ اور حج بیت اللہ کی شرعی اور عملی تفصیل۔",
            summaryHindi = "इस्लाम के पाँच बुनियादी स्तंभ: कलमा शहादत, नमाज़, ज़कात, रोज़ा और हज का विस्तृत विवरण।",
            sections = listOf(
                ArticleSection(
                    heading = "1. Shahada (Declaration of Faith)",
                    headingUrdu = "۱. کلمہ شہادت (ایمان و توحید کا اقرار)",
                    headingHindi = "१. कलमा-ए-शहादत (ईमान की गवाही)",
                    body = "The verbal and sincere testimony that 'There is no deity worthy of worship except Allah, and Muhammad is His servant and final Messenger.'",
                    bodyUrdu = "دل سے تصدیق اور زبان سے اقرار کہ اللہ کے سوا کوئی معبود نہیں اور حضرت محمد ﷺ اللہ کے آخری رسول اور بندے ہیں۔ یہ اسلام کا پہلا اور بنیادی رکن ہے۔",
                    bodyHindi = "सच्चे दिल से मानना और ज़बान से इकरार करना कि अल्लाह के सिवा कोई माबूद नहीं और मुहम्मद (ﷺ) अल्लाह के बंदे और आखिरी रसूल हैं।",
                    arabicQuote = "أَشْهَدُ أَنْ لَا إِلَٰهَ إِلَّا اللَّهُ وَأَشْهَدُ أَنَّ مُحَمَّدًا رَسُولُ اللَّهِ",
                    translationQuote = "I bear witness that there is no god but Allah, and I bear witness that Muhammad is the Messenger of Allah."
                ),
                ArticleSection(
                    heading = "2. Salah (The Five Daily Prayers)",
                    headingUrdu = "۲. نماز (پانچ وقت کی فرض نمازیں)",
                    headingHindi = "२. नमाज़ (दैनिक पाँच वक्त की नमाज़)",
                    body = "Establishing direct communion with Allah five times daily: Fajr, Dhuhr, Asr, Maghrib, and Isha.",
                    bodyUrdu = "روزانہ ۵ وقت فجر، ظہر، عصر، مغرب اور عشاء کی نمازیں مقررہ اوقات میں ادا کرنا۔ نماز دین کا ستون اور مومن کی معراج ہے۔",
                    bodyHindi = "दिन में पांच बार (फ़ज्र, ज़ुहर, असर, मग़रिब, इशा) वक़्त पर नमाज़ अदा करना। नमाज़ दीन का सुतून और रूहानी पाकी का ज़रिया है।",
                    arabicQuote = "إِنَّ الصَّلَاةَ كَانَتْ عَلَى الْمُؤْمِنِينَ كِتَابًا مَّوْقُوتًا",
                    translationQuote = "Indeed, prayer has been decreed upon the believers at specified times. (Quran 4:103)"
                ),
                ArticleSection(
                    heading = "3. Zakat (Obligatory Almsgiving)",
                    headingUrdu = "۳. زکوٰۃ (مال کی سالانہ پاکیزگی)",
                    headingHindi = "३. ज़कात (अनिवार्य दान/खैरात)",
                    body = "Paying 2.5% of one's accumulated qualifying wealth and savings annually above the Nisab threshold to support the poor and needy.",
                    bodyUrdu = "صاحبِ نصاب مسلمان پر اپنے سونے، چاندی، نقدی اور مالِ تجارت پر سال گزرنے کے بعد ۲.۵ فیصد غرباء اور مساکین کو دینا فرض ہے۔",
                    bodyHindi = "साहिबे-निसाब मुसलमान पर अपनी बचत और माल पर साल में एक बार २.५% गरीबों और जरूरतमंदों को देना फर्ज़ है।",
                    arabicQuote = "وَأَقِيمُوا الصَّلَاةَ وَآتُوا الزَّكَاةَ",
                    translationQuote = "And establish prayer and give Zakat. (Quran 2:43)"
                ),
                ArticleSection(
                    heading = "4. Sawm (Fasting in Ramadan)",
                    headingUrdu = "۴. صوم (رمضان المبارک کے روزے)",
                    headingHindi = "४. सौम (रमज़ान के रोज़े)",
                    body = "Abstaining from food, drink, and desires from dawn until sunset throughout Ramadan to foster Taqwa.",
                    bodyUrdu = "ماہِ رمضان کے تمام دنوں میں صبحِ صادق سے لے کر غروبِ آفتاب تک کھانے، پینے اور نفسانی خواہشات سے رکنا تاکہ تقویٰ اور پرہیزگاری حاصل ہو۔",
                    bodyHindi = "माहे रमज़ान में सुबह सादिक़ से सूरज डूबने तक खाने, पीने और नफ़्सानी ख्वाहिशों से रुकना ताकि तक़वा और अल्लाह का डर हासिल हो।",
                    arabicQuote = "يَا أَيُّهَا الَّذِينَ آمَنُوا كُتِبَ عَلَيْكُمُ الصِّيَامُ",
                    translationQuote = "O you who have believed, decreed upon you is fasting... (Quran 2:183)"
                ),
                ArticleSection(
                    heading = "5. Hajj (Pilgrimage to Makkah)",
                    headingUrdu = "۵. حج بیت اللہ (مکہ مکرمہ کی زیارت)",
                    headingHindi = "५. हज (मक्का शरीफ की तीर्थयात्रा)",
                    body = "The sacred pilgrimage to the Holy Kaaba in Makkah during Dhu al-Hijjah at least once in a lifetime for those who can afford it.",
                    bodyUrdu = "استطاعت رکھنے والے ہر صاحبِ مال و صحت مسلمان پر زندگی میں ایک بار ذوالحجہ کے ایام میں مکہ مکرمہ جا کر مناسکِ حج ادا کرنا فرض ہے۔",
                    bodyHindi = "जो सेहत और माली हैसियत रखता हो, उसके लिए ज़िंदगी में एक बार हज के दिनों में मक्का जाकर हज अदा करना फ़र्ज़ है।",
                    arabicQuote = "وَلِلَّهِ عَلَى النَّاسِ حِجُّ الْبَيْتِ مَنِ اسْتَطَاعَ إِلَيْهِ سَبِيلًا",
                    translationQuote = "And [due] to Allah from the people is a pilgrimage to the House - for whoever is able to find thereto a way. (Quran 3:97)"
                )
            ),
            reference = "Sahih al-Bukhari (8), Sahih Muslim (16)"
        ),

        // 3. The Six Articles of Faith
        KnowledgeArticle(
            id = "articles_faith",
            category = KnowledgeCategory.FAITH,
            title = "The Six Articles of Faith (أركان الإيمان)",
            titleUrdu = "ایمان کے ۶ بنیادی ارکان (عقائدِ اسلام)",
            titleHindi = "ईमान के ६ बुनियादी अरकान (अक़ीदा)",
            titleArabic = "أركان الإيمان الستة",
            summary = "The essential theological convictions that define the Muslim worldview and creed (Aqeedah).",
            summaryUrdu = "مسلمان کے بنیادی عقائد: اللہ، فرشتوں، آسمانی کتابوں، انبیاء و رسل، قیامت اور تقدیر پر کامل ایمان۔",
            summaryHindi = "सच्चे मुसलमान के छह बुनियादी अक़ीदे: अल्लाह, फ़रिश्तों, आसमानी किताबों, नबियों, क़यामत और तक़दीर पर ईमान।",
            sections = listOf(
                ArticleSection(
                    heading = "1. Belief in Allah (Tawhid)",
                    headingUrdu = "۱. اللہ تعالیٰ کی ذات و صفات پر ایمان (توحید)",
                    headingHindi = "१. अल्लाह तआला की तौहीद पर ईमान",
                    body = "Belief in Allah as the sole Creator, Sustainer, and Ruler of the universe without partner, child, or equal.",
                    bodyUrdu = "اللہ تعالیٰ کو اکیلا، واحد، لاشریک، اور تمام کائنات کا خالق و مالک ماننا۔ نہ اس کی کوئی اولاد ہے نہ وہ کسی سے پیدا ہوا۔",
                    bodyHindi = "अल्लाह को अकेला, बे-साझीदार और सारी कायनात का खालिक़ व मालिक मानना। न उसकी कोई औलाद है और न वह किसी से पैदा हुआ।",
                    arabicQuote = "قُلْ هُوَ اللَّهُ أَحَدٌ • اللَّهُ الصَّمَدُ",
                    translationQuote = "Say, 'He is Allah, [who is] One, Allah, the Eternal Refuge.' (Surah Al-Ikhlas)"
                ),
                ArticleSection(
                    heading = "2. Belief in Angels (Mala'ikah)",
                    headingUrdu = "۲. اللہ کے فرشتوں پر ایمان (ملائکہ)",
                    headingHindi = "२. अल्लाह के फरिश्तों पर ईमान",
                    body = "Belief in Allah's noble, unseen creations created from light (Jibril, Mika'il, Israfil, Azra'il).",
                    bodyUrdu = "فرشتے اللہ کی نوری مخلوق ہیں جو کبھی نافرمانی نہیں کرتے۔ حضرت جبرائیلؑ وحی لانے والے، حضرت میکائیلؑ بارش و رزق، حضرت اسرافیلؑ صور پھونکنے والے اور حضرت عزرائیلؑ روح قبض کرنے والے ہیں۔",
                    bodyHindi = "फ़रिश्ते अल्लाह की नूरी मख़लूक़ हैं जो हर हुक्म मानते हैं। हज़रत जिब्रील (अ.स.) वह्य लाते थे, हज़रत मीकाइल (अ.स.) रिज़्क़ के ज़िम्मेदार हैं।",
                    arabicQuote = "وَالْمَلَائِكَةُ يُسَبِّحُونَ بِحَمْدِ رَبِّهِمْ",
                    translationQuote = "And the angels exalt [Allah] with praise of their Lord. (Quran 42:5)"
                ),
                ArticleSection(
                    heading = "3. Belief in Divine Books (Kutub)",
                    headingUrdu = "۳. آسمانی کتابوں پر ایمان (کتبِ سماویہ)",
                    headingHindi = "३. आसमानी किताबों पर ईमान",
                    body = "Belief in original scriptures: Tawrah (Musa), Zabur (Dawud), Injeel (Isa), and the final preserved Quran to Muhammad (PBUH).",
                    bodyUrdu = "اللہ تعالیٰ نے انسانوں کی رہنمائی کے لیے کتابیں نازل فرمائیں: تورات (حضرت موسیٰؑ)، زبور (حضرت داؤدؑ)، انجیل (حضرت عیسیٰؑ) اور قرآنِ مجید جو حضرت محمد ﷺ پر نازل ہوا اور ہمیشہ کے لیے محفوظ ہے۔",
                    bodyHindi = "अल्लाह की आसमानी किताबों पर ईमान: तौरात (हज़रत मूसा), ज़बूर (हज़रत दाऊद), इंजील (हज़रत ईसा) और पाक क़ुरआन जो हज़रत मुहम्मद (ﷺ) पर नाज़िल हुआ।",
                    arabicQuote = "نَزَّلَ عَلَيْكَ الْكِتَابَ بِالْحَقِّ",
                    translationQuote = "He has sent down upon you, [O Muhammad], the Book in truth... (Quran 3:3)"
                ),
                ArticleSection(
                    heading = "4. Belief in Prophets and Messengers (Rusul)",
                    headingUrdu = "۴. تمام انبیاء و رسل پر ایمان (رسالت)",
                    headingHindi = "४. तमाम नबियों और रसूलों पर ईमान",
                    body = "Belief in all chosen Messengers from Adam (AS) to Nuh, Ibrahim, Musa, Isa, and finalized with Prophet Muhammad (PBUH) as the Seal of Prophets.",
                    bodyUrdu = "حضرت آدمؑ سے لے کر حضرت محمد مصطفیٰ ﷺ تک تمام انبیاء پر ایمان لانا۔ حضرت محمد ﷺ اللہ کے آخری نبی (خاتم النبیین) ہیں اور آپ کے بعد کوئی نیا نبی نہیں آئے گا۔",
                    bodyHindi = "हज़रत आदम (अ.स.) से लेकर हज़रत मुहम्मद (ﷺ) तक भेजे गए तमाम नबियों पर ईमान। मुहम्मद (ﷺ) आखिरी नबी (ख़ातमुन नबिय्यीन) हैं।",
                    arabicQuote = "مَّا كَانَ مُحَمَّدٌ أَبَا أَحَدٍ مِّن رِّجَالِكُمْ وَلَٰكِن رَّسُولَ اللَّهِ وَخَاتَمَ النَّبِيِّينَ",
                    translationQuote = "Muhammad is the Messenger of Allah and last of the prophets. (Quran 33:40)"
                ),
                ArticleSection(
                    heading = "5. Belief in Day of Judgment (Yawm al-Qiyamah)",
                    headingUrdu = "۵. قیامت اور یومِ جزا پر ایمان",
                    headingHindi = "५. क़यामत और आख़िरत के दिन पर ईमान",
                    body = "Belief in resurrection, the final accounting of deeds, the bridge of Sirat, Paradise (Jannah), and Hell (Jahannam).",
                    bodyUrdu = "مرنے کے بعد دوبارہ زندہ کیے جانے، اعمال کے حساب کتاب، میزان، پل صراط، اور نیکوکاروں کے لیے جنت اور گناہ گاروں کے لیے دوزخ پر کامل یقین۔",
                    bodyHindi = "मरने के बाद दोबारा ज़िंदा होने, आमाल के हिसाब-किताब, मीज़ान (तराज़ू), जन्नत और जहन्नम पर पक्का यक़ीन रखना।",
                    arabicQuote = "فَمَن يَعْمَلْ مِثْقَالَ ذَرَّةٍ خَيْرًا يَرَهُ",
                    translationQuote = "So whoever does an atom's weight of good will see it. (Quran 99:7)"
                ),
                ArticleSection(
                    heading = "6. Belief in Divine Decree (Al-Qadr)",
                    headingUrdu = "۶. تقدیر کے اچھے اور برے ہونے پر ایمان",
                    headingHindi = "६. तक़दीर पर ईमान (अल्लाह का फैसला)",
                    body = "Belief that everything occurs within Allah's knowledge, supreme wisdom, and will, while humans hold moral accountability.",
                    bodyUrdu = "کائنات میں جو کچھ ہوا اور جو ہو گا وہ سب اللہ کے علم اور قدرت میں ہے، اور بندے کو نیکی اور بدی کا اختیار دیا گیا ہے۔",
                    bodyHindi = "जो कुछ भी दुनिया में होता है वह सब अल्लाह के इल्म और हिकमत से तय है, और इंसान अपने कर्मों के लिए ज़िम्मेदार है।",
                    arabicQuote = "إِنَّا كُلَّ شَيْءٍ خَلَقْنَاهُ بِقَدَرٍ",
                    translationQuote = "Indeed, all things We created with predestination. (Quran 54:49)"
                )
            ),
            reference = "Sahih Muslim (Hadith Jibril)"
        ),

        // 4. Seerat-un-Nabi ﷺ (Life of the Prophet Muhammad ﷺ)
        KnowledgeArticle(
            id = "seerah_prophet",
            category = KnowledgeCategory.SEERAH,
            title = "Seerat-un-Nabi ﷺ: Life of Prophet Muhammad",
            titleUrdu = "سیرت النبی ﷺ (حیاتِ طیبہ کا مکمل خلاصہ)",
            titleHindi = "सीरत-उन-नबी ﷺ (हज़रत मुहम्मद की पाकीज़ा ज़िंदगी)",
            titleArabic = "السيرة النبوية الشريفة",
            summary = "Comprehensive historical timeline of Prophet Muhammad (PBUH) from birth in Makkah to Hijrah, treaties, and the Farewell Sermon.",
            summaryUrdu = "حضور نبی کریم ﷺ کی ولادتِ باسعادت، مکی زندگی، اعلانِ نبوت، معراج، ہجرتِ مدینہ، غزوات، فتحِ مکہ اور خطبہ حجتہ الوداع۔",
            summaryHindi = "हज़रत मुहम्मद (ﷺ) की मक्का में पैदाइश, नबुव्वत का ऐलान, मेराज, हिजरत, मदीना का दस्तूर, फ़तह-ए-मक्का और आख़िरी ख़ुत्बा।",
            sections = listOf(
                ArticleSection(
                    heading = "1. Birth and Upbringing in Makkah",
                    headingUrdu = "۱. ولادتِ با سعادت اور بچپن",
                    headingHindi = "१. पैदाइश और बचपन",
                    body = "Born in the Year of the Elephant (570 CE) in Makkah as an orphan to Aminah and Abdullah (Quraysh tribe). Known from youth as 'As-Sadiq' (The Truthful) and 'Al-Amin' (The Trustworthy).",
                    bodyUrdu = "آپ ﷺ ۱۲ ربیع الاول عام الفیل کو مکہ مکرمہ میں پیدا ہوئے۔ دادا عبدالمطلب اور چچا ابوطالب نے پرورش کی۔ آپ اپنی امانت اور سچائی کی وجہ سے 'صادق' اور 'امین' کے لقب سے مشہور تھے۔",
                    bodyHindi = "आप (ﷺ) १२ रबी-उल-अव्वल को मक्का में पैदा हुए। बचपन से ही आपकी सच्चाई और ईमानदारी की वजह से लोग आपको 'सादिक' और 'अमीन' कहते थे।"
                ),
                ArticleSection(
                    heading = "2. First Revelation & Call to Monotheism",
                    headingUrdu = "۲. غارِ حرا میں پہلی وحی اور اعلانِ نبوت",
                    headingHindi = "२. पहली वह्य और नबुव्वत का ऐलान",
                    body = "At age 40, in Cave Hira during Ramadan, Angel Jibril descended with the first verses: 'Iqra bismi Rabbikallazi khalaq'. The Prophet called people to worship One God, facing boycott and persecution.",
                    bodyUrdu = "۴۰ سال کی عمر میں غارِ حرا میں جبرائیلؑ کے ذریعے سورۃ العلق کی پہلی ۵ آیات نازل ہوئیں۔ آپ نے مکہ والوں کو توحید کی دعوت دی جس پر قریش نے شدید مظالم ڈھائے۔",
                    bodyHindi = "४० साल की उम्र में ग़ारे हिरा में जिब्रील (अ.स.) 'इक़रा' (पढ़ो अपने रब के नाम से) की आयतें लेकर आए। आपने तौहीद की दावत दी।"
                ),
                ArticleSection(
                    heading = "3. Isra and Mi'raj (The Miraculous Night Journey)",
                    headingUrdu = "۳. واقعہ معراج النبی ﷺ اور ۵ وقت کی نماز",
                    headingHindi = "३. वाक़िया-ए-मेराज और ५ वक़्त की नमाज़",
                    body = "The miraculous night journey from Makkah to Al-Aqsa Mosque in Jerusalem, ascending through the 7 heavens. The 5 daily prayers were gifted directly by Allah to the Ummah.",
                    bodyUrdu = "مسجدِ حرام سے مسجدِ اقصیٰ اور وہاں سے ساتوں آسمانوں کی سیر۔ سدرۃ المنہیٰ پر اللہ تعالیٰ سے شرفِ ہمکلامی اور امت کے لیے تحفہ کے طور پر ۵ وقت کی نمازیں فرض ہوئیں۔",
                    bodyHindi = "मक्का से बैतुल मुक़द्दस और वहाँ से सातों आसमानों का सफ़र। इसी रात उम्मत के लिए ५ वक़्त की नमाज़ों का तोहफ़ा मिला।"
                ),
                ArticleSection(
                    heading = "4. Hijrah to Madinah (622 CE)",
                    headingUrdu = "۴. ہجرتِ مدینہ منورہ اور میثاقِ مدینہ",
                    headingHindi = "४. हिजरत-ए-मदीना और मदीना का संविधान",
                    body = "Migrated to Yathrib (Madinah) with Abu Bakr (RA). Established the first Islamic society, Masjid an-Nabawi, brotherhood (Muwakhat), and the Charter of Madinah granting universal human rights.",
                    bodyUrdu = "اللہ کے حکم سے مکہ سے مدینہ ہجرت فرمائی۔ مہاجرین و انصار میں بھائی چارہ قائم کیا، مسجدِ نبوی تعمیر کی اور تاریخ کا پہلا تحریری دستور 'میثاقِ مدینہ' مرتب فرمایا۔",
                    bodyHindi = "मक्का के ज़ुल्मों के बाद मदीना हिजरत की। मस्जिद-ए-नबवी बनाई, अंसार व मुहाजिरीन में भाईचारा कायम किया और मदीना का दस्तूर बनाया।"
                ),
                ArticleSection(
                    heading = "5. Conquest of Makkah & General Amnesty",
                    headingUrdu = "۵. فتحِ مکہ مکرمہ اور عام معافی کا اعلان",
                    headingHindi = "५. फ़तह-ए-मक्का और आम माफ़ी",
                    body = "In 8 AH (630 CE), the Prophet entered Makkah with 10,000 companions peacefully. He cleansed the Kaaba of idols and declared universal forgiveness: 'No blame on you today; go, you are free.'",
                    bodyUrdu = "۸ ہجری میں مکہ بغیر خون خرابے کے فتح ہوا۔ کعبہ کو ۳۶۰ بتوں سے پاک کیا۔ جانی دشمنوں کو معاف کرتے ہوئے فرمایا: 'آج تم پر کوئی گرفت نہیں، جاؤ تم سب آزاد ہو۔'",
                    bodyHindi = "८ हिजरी में मक्का फ़तह हुआ। काबा से ३६० बुतों को हटाया। पुराने दुश्मनों को माफ़ करते हुए फ़रमाया: 'आज तुमसे कोई बदला नहीं, तुम सब आज़ाद हो।'"
                ),
                ArticleSection(
                    heading = "6. The Farewell Pilgrimage & Universal Rights",
                    headingUrdu = "۶. خطبہ حجتہ الوداع (انسانی حقوق کا منشور)",
                    headingHindi = "६. आख़िरी हज का ख़ुत्बा (इंसानी हुक़ूक़)",
                    body = "In 10 AH, delivered his final sermon at Mount Arafat: 'An Arab has no superiority over a non-Arab, nor a white over a black, except by piety.' He passed away in Madinah in 11 AH.",
                    bodyUrdu = "میدانِ عرفات میں تاریخی خطبہ: 'کسی عربی کو عجمی پر اور کسی گورے کو کالے پر کوئی فضیلت نہیں مگر تقویٰ کی بنیاد پر۔ عورتوں کے حقوق کا خیال رکھو۔' ۱۱ ہجری کو آپ کا وصال ہوا۔",
                    bodyHindi = "अराफ़ात के मैदान में फ़रमाया: 'किसी अरबी को अजमी पर और किसी गोरे को काले पर कोई बड़प्पन नहीं सिवाय तक़वे के।' ११ हिजरी में आपका विसाल हुआ।"
                )
            ),
            reference = "Ar-Raheeq Al-Makhtum (The Sealed Nectar)"
        ),

        // 5. Stories of the Great Prophets (قصص الانبیاء)
        KnowledgeArticle(
            id = "stories_prophets",
            category = KnowledgeCategory.PROPHETS,
            title = "Stories of the Great Prophets (قصص الأنبياء)",
            titleUrdu = "قصص الانبیاء علیہم السلام (اہم انبیاء کے ایمان افروز واقعات)",
            titleHindi = "क़िससुल अंबिया (महान पैग़ंबरों के वाक़ियात)",
            titleArabic = "قصص الأنبياء والرسل عليهم السلام",
            summary = "Key lessons and historical accounts from Prophet Adam, Nuh, Ibrahim, Yusuf, Musa, Yunus, and Isa (peace be upon them).",
            summaryUrdu = "حضرت آدمؑ، نوحؑ، ابراہیمؑ، یوسفؑ، موسیٰؑ، یونسؑ اور عیسیٰؑ کے قرآن میں بیان کردہ واقعات اور اسباق۔",
            summaryHindi = "हज़रत आदम, नूह, इब्राहीम, यूसुफ़, मूसा, यूनुस और ईसा (अ.स.) की ज़िंदगियों से जुड़े सबक़आमोज़ वाक़ियात।",
            sections = listOf(
                ArticleSection(
                    heading = "Prophet Adam (AS) - The First Human and Prophet",
                    headingUrdu = "حضرت آدم علیہ السلام (پہلے انسان اور پیغمبر)",
                    headingHindi = "हज़रत आदम (अ.स.) - पहले इंसान और नबी",
                    body = "Created by Allah from clay, breathed His spirit into him, and taught him the names of all things. Iblis refused to prostrate out of arrogance and was expelled.",
                    bodyUrdu = "اللہ تعالیٰ نے مٹی سے پیدا فرمایا، تمام اشیاء کے نام سکھائے، اور فرشتوں کو سجدہ تعظیمی کا حکم دیا۔ ابلیس نے تکبر کیا اور مردود ٹھہرا۔ آدمؑ و حواؑ کو زمین پر اتارا گیا۔",
                    bodyHindi = "अल्लाह ने मिट्टी से पैदा किया और सारी चीज़ों के नाम सिखाए। इबलीस ने घमंड किया और सजदा न करने पर धुतकारा गया।"
                ),
                ArticleSection(
                    heading = "Prophet Nuh (AS) - The Great Ark and Deluge",
                    headingUrdu = "حضرت نوح علیہ السلام (۹۵۰ سال دعوت اور کشتی کا طوفان)",
                    headingHindi = "हज़रत नूह (अ.स.) - बड़ी कश्ती और तूफ़ान",
                    body = "Preached monotheism for 950 years with boundless patience. Allah commanded him to build the Ark to save believers and pairs of animals from the global flood.",
                    bodyUrdu = "۹۵۰ سال تک اپنی قوم کو اللہ کی طرف بلایا۔ سرکش قوم پر طوفان کا عذاب آیا اور حضرت نوحؑ نے اللہ کے حکم سے کشتی بنا کر اہل ایمان کو بچایا۔",
                    bodyHindi = "९५० साल तक तौहीद की दावत दी। ज़ालिमों पर तूफ़ान का अज़ाब आया और ईमान वालों को कश्ती के ज़रिये बचाया गया।"
                ),
                ArticleSection(
                    heading = "Prophet Ibrahim (AS) - Friend of Allah (Khalilullah)",
                    headingUrdu = "حضرت ابراہیم علیہ السلام (خلیل اللہ اور تعمیرِ کعبہ)",
                    headingHindi = "हज़रत इब्राहीम (अ.स.) - खलीलुल्लाह व काबा की तामीर",
                    body = "Challenged idol worship, cast into Nimrod's fire which turned cool and safe by Allah's command. Rebuilt the Holy Kaaba with his son Ismail (AS).",
                    bodyUrdu = "بت پرستی کے خلاف کھڑے ہوئے۔ نمرود نے آگ میں ڈالا تو اللہ کے حکم سے آگ گلزار بن گئی۔ بیٹے اسماعیلؑ کے ساتھ مل کر خانہ کعبہ کی تعمیر فرمائی۔",
                    bodyHindi = "नमरूद ने आग में डाला तो अल्लाह के हुक्म से आग ठंडी और सलामती वाली बन गई। बेटे इस्माइल (अ.स.) के साथ मिलकर काबा बनाया।"
                ),
                ArticleSection(
                    heading = "Prophet Musa (AS) - The Speech of Allah (Kalimullah)",
                    headingUrdu = "حضرت موسیٰ علیہ السلام (کوہِ طور، فرعون اور عصا کے معجزات)",
                    headingHindi = "हज़रत मूसा (अ.स.) - कलीमुल्लाह व फ़िरऔन का ग़र्क़ होना",
                    body = "Spoke directly with Allah at Mount Sinai. Confronted Pharaoh with signs including his staff turning to a serpent, and parted the Red Sea to deliver the Israelites.",
                    bodyUrdu = "کوہِ طور پر اللہ سے ہمکلام ہوئے۔ فرعون کے دربار میں عصا اور یدِ بیضاء کے معجزات دکھائے۔ بحیرہ قلزم کو پھاڑ کر بنی اسرائیل کو نجات دلائی اور فرعون غرق ہوا۔",
                    bodyHindi = "कोह-ए-तूर पर अल्लाह से बात की। फ़िरऔन के सामने असामेअजिज़ा दिखाया। दरिया-ए-नील फटा और फ़िरऔन अपने लश्कर समेत डूब गया।"
                ),
                ArticleSection(
                    heading = "Prophet Yunus (AS) - In the Belly of the Whale",
                    headingUrdu = "حضرت یونس علیہ السلام (مچھلی کے پیٹ میں تسبیح و دعا)",
                    headingHindi = "हज़रत यूनुस (अ.स.) - मछली के पेट में दुआ",
                    body = "Swallowed by a giant fish in the dark ocean depths. He called upon Allah with sincere repentance: 'La ilaha illa Anta, Subhanaka, inni kuntu minaz-zalimeen' and was delivered.",
                    bodyUrdu = "سمندر کی تاریکیوں میں مچھلی کے پیٹ میں دعا مانگی: 'لَّا إِلَٰهَ إِلَّا أَنتَ سُبْحَانَكَ إِنِّي كُنتُ مِنَ الظَّالِمِينَ'۔ اللہ نے دعا قبول فرمائی اور نجات عطا کی۔",
                    bodyHindi = "मछली के पेट के अंधेरे में तौबा की: 'ला इलाहा इल्ला अंता सुब्हानका इन्नी कुन्तु मिनज़-ज़ालिमीन'। अल्लाह ने पुकार सुनकर नजात बख़्शी।"
                ),
                ArticleSection(
                    heading = "Prophet Isa (AS) - Miracles and Ascension",
                    headingUrdu = "حضرت عیسیٰ علیہ السلام (معجزات اور رفعِ سماوی)",
                    headingHindi = "हज़रत ईसा (अ.स.) - मोजिज़ात और आसमान पर उठाया जाना",
                    body = "Born miraculously without father to the Virgin Mary (Maryam). He healed the blind, cured lepers, and raised the dead by Allah's permission, and was raised to heaven.",
                    bodyUrdu = "حضرت مریمؑ کے بطن سے بغیر باپ کے معجزانہ ولادت۔ اللہ کے حکم سے اندھوں کو بینائی دی، کوڑھ کا علاج کیا، مردوں کو زندہ کیا۔ اللہ نے آپ کو آسمان پر اٹھا لیا۔",
                    bodyHindi = "हज़रत मरियम (अ.स.) के यहाँ बिना बाप के पैदा हुए। अल्लाह के हुक्म से बीमारों को शिफा दी और मुर्दों को ज़िंदा किया। अल्लाह ने आसमान पर उठा लिया।"
                )
            ),
            reference = "Qisas al-Anbiya (Ibn Kathir)"
        ),

        // 6. Taharat, Wudu, Ghusl & Tayammum
        KnowledgeArticle(
            id = "taharat_ghusl_guide",
            category = KnowledgeCategory.TAHARAT,
            title = "Purity, Wudu, Ghusl & Tayammum Guide",
            titleUrdu = "طہارت، وضو، غسل اور تیمم کے احکام و فرائض",
            titleHindi = "पाकीज़गी: वुज़ू, ग़ुस्ल और तयम्मुम का मुकम्मल तरीक़ा",
            titleArabic = "فقه الطهارة والوضوء والغسل والتيمم",
            summary = "Complete Islamic jurisprudence on ritual purity, the mandatory steps of Wudu, 3 obligations of Ghusl, and dry ablution.",
            summaryUrdu = "وضو کے ۴ فرائض، سنن، نواقضِ وضو، غسلِ جنابت کے ۳ فرائض اور مسنون طریقہ، اور تیمم کی شرائط۔",
            summaryHindi = "वुज़ू के ४ फ़र्ज़ व सुन्नतें, वुज़ू तोड़ने वाली चीज़ें, ग़ुस्ल के ३ फ़र्ज़ और तयम्मुम का आसान तरीक़ा।",
            sections = listOf(
                ArticleSection(
                    heading = "The 4 Obligations (Fard) of Wudu",
                    headingUrdu = "وضو کے ۴ فرائض",
                    headingHindi = "वुज़ू के ४ फ़र्ज़",
                    body = "1. Washing face from forehead to chin and ear to ear once.\n2. Washing both arms including elbows once.\n3. Wiping one-fourth of the head (Masah).\n4. Washing both feet up to and including ankles once.",
                    bodyUrdu = "۱. پیشانی کے بالوں سے ٹھوڑی کے نیچے تک اور ایک کان کی لو سے دوسرے کان تک پورا چہرہ دھونا۔\n۲. کہنیوں سمیت دونوں ہاتھ دھونا۔\n۳. چوتھائی سر کا مسح کرنا۔\n۴. ٹخنوں سمیت دونوں پاؤں دھونا۔",
                    bodyHindi = "१. पेशानी के बालों से ठोड़ी तक और एक कान से दूसरे कान तक पूरा चेहरा धोना।\n२. कोहनियों समेत दोनों हाथ धोना।\n३. चौथाई सर का मसह करना।\n४. टखनों समेत दोनों पैर धोना।"
                ),
                ArticleSection(
                    heading = "Nullifiers of Wudu (Nawaqid)",
                    headingUrdu = "وضو توڑنے والی چیزیں (نواقضِ وضو)",
                    headingHindi = "वुज़ू तोड़ने वाली चीज़ें",
                    body = "Urine, stool, passing gas, deep sleep lying down, bleeding or pus flowing from the body, vomiting a mouthful, and loss of consciousness.",
                    bodyUrdu = "پیشاب یا پاخانہ خارج ہونا، ہوا خارج ہونا، ٹیک لگا کر سو جانا، جسم سے خون یا پیپ نکل کر بہنا، منہ بھر کر قے آنا، یا بے ہوش ہونا۔",
                    bodyHindi = "पेशाब या पाखाना होना, हवा खारिज होना, टेक लगाकर सो जाना, जिस्म से खून बहना, मुंह भर उल्टी आना या बेहोश होना।"
                ),
                ArticleSection(
                    heading = "The 3 Obligations (Fard) of Ghusl (Full Bath)",
                    headingUrdu = "غسل کے ۳ فرائض اور مسنون طریقہ",
                    headingHindi = "ग़ुस्ल के ३ फ़र्ज़ और तरीक़ा",
                    body = "1. Gargling and rinsing the entire mouth thoroughly.\n2. Inhaling water into the nose up to the soft bone.\n3. Pouring water over the entire body so not a single hair remains dry.",
                    bodyUrdu = "۱. منہ بھر کر اچھی طرح کلی کرنا کہ حلق تک پانی پہنچے۔\n۲. ناک کی نرم ہڈی تک پانی چڑھانا۔\n۳. پورے جسم پر اس طرح پانی بہانا کہ بال برابر جگہ بھی خشک نہ رہے۔",
                    bodyHindi = "१. अच्छी तरह कुल्ली करना।\n२. नाक की नरम हड्डी तक पानी चढ़ाना।\n३. पूरे बदन पर इस तरह पानी बहाना कि एक बाल भी सूखा न रहे।"
                ),
                ArticleSection(
                    heading = "How to Perform Tayammum (Dry Ablution)",
                    headingUrdu = "تیمم کا طریقہ (پانی نہ ملنے یا بیماری کی صورت میں)",
                    headingHindi = "तयम्मुम का तरीक़ा (पानी न मिलने पर)",
                    body = "1. Make intention (Niyyah) for purification.\n2. Strike pure earth/dust with both hands, blow off excess, wipe entire face.\n3. Strike hands on earth again, wipe right arm including elbow with left hand, and left arm with right hand.",
                    bodyUrdu = "۱. پاکی کی نیت کرنا۔\n۲. پاک مٹی پر دونوں ہاتھ مار کر جھاڑیں اور پورے چہرے پر ملیں۔\n۳. دوبارہ ہاتھ مٹی پر ماریں اور دائیں ہاتھ سے بائیں بازو اور بائیں ہاتھ سے دائیں بازو کہنیوں سمیت مسح کریں۔",
                    bodyHindi = "१. नीयत करना।\n२. पाक मिट्टी पर हाथ मारकर चेहरे पर मलना।\n३. दोबारा हाथ मारकर दोनों हाथों को कोहनियों तक मलना।"
                )
            ),
            reference = "Nur al-Idah & Al-Fiqh Al-Islami"
        ),

        // 7. Janazah & Eid Prayers
        KnowledgeArticle(
            id = "janazah_eid_guide",
            category = KnowledgeCategory.JANAZAH_EID,
            title = "Funeral (Janazah) & Eid Prayers Guide",
            titleUrdu = "نمازِ جنازہ اور عیدین کا طریقہ و دعائیں",
            titleHindi = "जनाज़ा और ईदैन की नमाज़ का मुकम्मल तरीक़ा",
            titleArabic = "صفة صلاة الجنازة وصلاة العيدين",
            summary = "Step-by-step method for Namaz-e-Janazah with 4 Takbeers and authentic funeral supplications, alongside Eid prayer rules.",
            summaryUrdu = "نمازِ جنازہ کے فرائض، ۴ تکبیریں، ثناء، درود اور بالغ و نابالغ کی دعائیں، اور عیدین کی ۶ زائد تکبیروں کا طریقہ۔",
            summaryHindi = "नमाज़-ए-जनाज़ा की ४ तकबीरें, दुआएं और ईद-उल-फ़ित्र व ईद-उल-अज़हा की ६ ज़ायद तकबीरों का तरीक़ा।",
            sections = listOf(
                ArticleSection(
                    heading = "Method of Janazah Prayer (4 Takbeers)",
                    headingUrdu = "نمازِ جنازہ کا طریقہ (۴ تکبیریں)",
                    headingHindi = "नमाज़-ए-जनाज़ा का तरीक़ा (४ तकबीरें)",
                    body = "Stand facing Qibla with the deceased in front. No Ruku or Sujud.\n1st Takbeer: Say Allahu Akbar, fold hands, recite Subhanakallahumma with 'wa jalla thana'uk'.\n2nd Takbeer: Recite Durood-e-Ibrahim.\n3rd Takbeer: Recite the Janazah Dua.\n4th Takbeer: Turn face right and left with Salaam.",
                    bodyUrdu = "جنازہ سامنے رکھ کر قبلہ رخ کھڑے ہوں۔ اس میں رکوع اور سجدہ نہیں ہوتا۔\nپہلی تکبیر: ہاتھ باندھیں، ثناء پڑھیں مع 'وَجَلَّ ثَنَاؤُكَ'۔\nدوسری تکبیر: درودِ ابراہیم پڑھیں۔\nتیسری تکبیر: جنازہ کی مخصوص دعا پڑھیں۔\nچوتھی تکبیر: ہاتھ چھوڑ کر دائیں اور بائیں سلام پھیریں۔",
                    bodyHindi = "जनाज़ा सामने रखकर खड़े हों। इसमें रुकूअ और सजदा नहीं होता।\n१ली तकबीर: हाथ बांधें और सना पढ़ें।\n२री तकबीर: दुरूदे इब्राहीम पढ़ें।\n३री तकबीर: जनाज़े की मखसूस दुआ पढ़ें।\n४थी तकबीर: हाथ छोड़कर दोनों तरफ सलाम फेरें।"
                ),
                ArticleSection(
                    heading = "Dua for Adult Deceased (بالغ میت کی دعا)",
                    headingUrdu = "بالغ مرد و عورت کی جنازہ دعا",
                    headingHindi = "बालीग़ मय्यत की जनाज़ा दुआ",
                    body = "O Allah, forgive our living and our dead, those present and absent, our young and our old, our males and our females.",
                    bodyUrdu = "ترجمہ: اے اللہ! بخش دے ہمارے زندوں کو اور ہمارے مُردوں کو، ہمارے حاضرین کو اور غائبین کو، ہمارے چھوٹوں کو اور بڑوں کو، اور ہمارے مردوں کو اور عورتوں کو۔ اے اللہ! ہم میں سے جس کو تو زندہ رکھے اسے اسلام پر زندہ رکھ اور جس کو موت دے اسے ایمان پر موت دے۔",
                    bodyHindi = "अनुवाद: ऐ अल्लाह! हमारे ज़िंदों और मुर्दों को, हाज़िर और ग़ायब को, छोटों और बड़ों को, मर्दों और औरतों को बख्श दे। जिसे ज़िंदा रखे इस्लाम पर रख और जिसे मौत दे ईमान पर दे।",
                    arabicQuote = "اللَّهُمَّ اغْفِرْ لِحَيِّنَا وَمَيِّتِنَا وَشَاهِدِنَا وَغَائِبِنَا وَصَغِيرِنَا وَكَبِيرِنَا وَذَكَرِنَا وَأُنْثَانَا، اللَّهُمَّ مَنْ أَحْيَيْتَهُ مِنَّا فَأَحْيِهِ عَلَى الْإِسْلَامِ، وَمَنْ تَوَفَّيْتَهُ مِنَّا فَتَوَفَّهُ عَلَى الْإِيمَانِ",
                    translationQuote = "Abu Dawud (3201), Tirmidhi (1024)"
                ),
                ArticleSection(
                    heading = "Method of Eid Prayer (6 Extra Takbeers)",
                    headingUrdu = "عید الفطر اور عید الاضحیٰ کی نماز کا طریقہ (۶ زائد تکبیریں)",
                    headingHindi = "ईद की नमाज़ का तरीक़ा (६ ज़ायद तकबीरें)",
                    body = "1st Rak'ah: Say Takbirat al-Ihram, recite Sana, then say 3 extra Takbeers raising hands each time (drop hands on 1st & 2nd, fold on 3rd). Recite Surah Fatihah and Surah, complete Ruku & Sujud.\n2nd Rak'ah: Recite Fatihah and Surah, then say 3 extra Takbeers raising hands and dropping them, 4th Takbeer go to Ruku.",
                    bodyUrdu = "پہلی رکعت: تکبیرِ تحریمہ کے بعد ثناء پڑھیں، پھر امام کے ساتھ ۳ زائد تکبیریں کہیں (پہلی ۲ پر ہاتھ چھوڑیں، تیسری پر باندھیں)۔ پھر سورۃ الفاتحہ اور سورت پڑھ کر رکوع و سجدہ کریں۔\nدوسری رکعت: سورۃ الفاتحہ اور سورت کے بعد رکوع سے پہلے ۳ زائد تکبیریں ہاتھ اٹھا کر چھوڑیں، چوتھی تکبیر پر رکوع میں جائیں۔",
                    bodyHindi = "पहली रकात: सना के बाद ३ ज़ायद तकबीरें (२ बार हाथ छोड़ें, तीसरी पर बांधें)। क़ुरआन पढ़कर रुकूअ व सजदा करें।\nदूसरी रकात: क़ुरआन पढ़ने के बाद रुकूअ से पहले ३ ज़ायद तकबीरें कहें और हाथ छोड़ें, चौथी पर रुकूअ में जाएं।"
                )
            ),
            reference = "Sahih Fiqh as-Sunnah"
        ),

        // 8. Ramadan & Fasting Rules
        KnowledgeArticle(
            id = "ramadan_fasting_rules",
            category = KnowledgeCategory.RAMADAN_FASTING,
            title = "Ramadan & Fasting Fiqh & Kaffarah",
            titleUrdu = "روزے کے احکام، مسائل، مکروہات اور کفارہ",
            titleHindi = "रोज़े के अहकाम, मसाइल, मकरूहात व कफ़्फ़ारा",
            titleArabic = "أحكام الصيام وفدية رمضان وكفارته",
            summary = "Detailed guide on fasting intentions, invalidating actions, makeup fasts (Qadha), and mandatory expiation (Kaffarah).",
            summaryUrdu = "روزے کی نیت، سحر و افطار، کن چیزوں سے روزہ ٹوٹتا ہے اور کن سے نہیں، مکروہات اور کفارہ کے مسائل۔",
            summaryHindi = "रोज़े की नीयत, सहरी व इफ़्तार, किन चीज़ों से रोज़ा टूटता है और किनसे नहीं, और कफ़्फ़ारे के नियम।",
            sections = listOf(
                ArticleSection(
                    heading = "Intentions & Duas for Sehri and Iftar",
                    headingUrdu = "سحری اور افطار کی مسنون دعائیں",
                    headingHindi = "सहरी और इफ़्तार की दुआएं",
                    body = "Sehri Niyyah: 'Wa bisawmi ghadin nawaytu min shahri Ramadan'.\nIftar Dua: 'Allahumma inni laka sumtu wa bika aamantu wa 'alayka tawakkaltu wa 'ala rizqika aftartu'.",
                    bodyUrdu = "سحری کی نیت: وَبِصَوْمِ غَدٍ نَّوَيْتُ مِنْ شَهْرِ رَمَضَانَ (میں نے کل کے ماہِ رمضان کے روزے کی نیت کی)۔\nافطار کی دعا: اللَّهُمَّ إِنِّي لَكَ صُمْتُ وَبِكَ آمَنْتُ وَعَلَيْكَ تَوَكَّلْتُ وَعَلَىٰ رِزْقِكَ أَفْطَرْتُ (اے اللہ! میں نے تیرے لیے روزہ رکھا، تجھ پر ایمان لایا اور تیرے ہی رزق سے افطار کیا)۔",
                    bodyHindi = "सहरी की नीयत: व बिसौमि ग़दिन नवैतु मिन शहरि रमज़ान।\nइफ़्तार की दुआ: अल्लाहुम्मा इन्नी लका सुमतु व बिका आमंतु व अलैका तवक्कलतु व अला रिज़्किका अफ़्तरतु।"
                ),
                ArticleSection(
                    heading = "Things that Break the Fast (Invalidators)",
                    headingUrdu = "کن چیزوں سے روزہ ٹوٹ جاتا ہے؟",
                    headingHindi = "किन चीज़ों से रोज़ा टूट जाता है?",
                    body = "Deliberate eating or drinking, smoking, marital relations, deliberate mouthful vomiting, intake of nutritional injections or IV drips.",
                    bodyUrdu = "جان بوجھ کر کھانا یا پینا، سگریٹ یا حقہ پینا، جان بوجھ کر منہ بھر کر قے کرنا، غذائیت بخش ڈرپ لگوانا، اور ہمبستری کرنا۔",
                    bodyHindi = "जानबूझकर खाना या पीना, सिगरेट पीना, जानबूझकर मुंह भर उल्टी करना, ड्रिप लगवाना और हमबिस्तरी करना।"
                ),
                ArticleSection(
                    heading = "Things that Do NOT Break the Fast",
                    headingUrdu = "کن چیزوں سے روزہ نہیں ٹوٹتا؟",
                    headingHindi = "किन चीज़ों से रोज़ा नहीं टूटता?",
                    body = "Eating or drinking forgetfully, using Miswak or dry toothbrush, applying Surma/kohl or eye drops, applying oil or perfume, taking blood tests or non-nutritive injections, swallowing saliva.",
                    bodyUrdu = "بھول کر کھانا یا پینا، مسواک کرنا، آنکھوں میں سرمہ یا ڈراپ ڈالنا، خوشبو یا تیل لگانا، خون کا ٹیسٹ کروانا، یا غیر غذائی انجکشن لگوانا۔",
                    bodyHindi = "भूलकर खा या पी लेना, मिस्वाक करना, सुरमा लगाना, खुशबू लगाना, खून टेस्ट कराना या नसों में इंजेक्शन लगवाना।"
                ),
                ArticleSection(
                    heading = "Kaffarah (Expiation for Deliberate Breaking)",
                    headingUrdu = "روزے کا کفارہ (بغیر عذر جان بوجھ کر توڑنے پر)",
                    headingHindi = "रोज़े का कफ़्फ़ारा",
                    body = "If an obligatory Ramadan fast is broken deliberately without valid excuse, one must fast 60 consecutive days, or feed 60 needy persons two meals.",
                    bodyUrdu = "اگر بغیر شرعی عذر کے جان بوجھ کر رمضان کا روزہ توڑا جائے تو ۶۰ مسلسل روزے رکھنا لازم ہے، یا ۶۰ مسکینوں کو دونوں وقت پیٹ بھر کر کھانا کھلانا ہے۔",
                    bodyHindi = "बिना किसी जायज़ मजबूरी के जानबूझकर रमज़ान का रोज़ा तोड़ने पर लगातार ६० रोज़े रखना या ६० गरीबों को खाना खिलाना लाज़िम है।"
                )
            ),
            reference = "Sahih al-Bukhari & Muslim"
        ),

        // 9. Daily Masnoon Azkar
        KnowledgeArticle(
            id = "daily_masnoon_azkar",
            category = KnowledgeCategory.DAILY_AZKAR,
            title = "Morning & Evening Masnoon Azkar",
            titleUrdu = "صبح و شام کے مسنون اذکار اور حفاظتی دعائیں",
            titleHindi = "सुबह व शाम के मसनून अज़कार व हिफ़ाज़ती दुआएं",
            titleArabic = "أذكار الصباح والمساء وحصن المسلم",
            summary = "Essential daily authentic supplications, Sayyidul Istighfar, Ayat al-Kursi, and protection against evil, harm, and anxiety.",
            summaryUrdu = "سید الاستغفار، آیت الکرسی، چاروں قل، اور صبح و شام کی مسنون دعائیں جو تمام آفات و بلایات سے محفوظ رکھتی ہیں۔",
            summaryHindi = "सैयद-उल-इस्तिगफ़ार, आयतुल कुर्सी, और सुबह-शाम के मसनून अज़कार जो हर बला और नज़र-ए-बद से बचाते हैं।",
            sections = listOf(
                ArticleSection(
                    heading = "Sayyid al-Istighfar (The Master of Forgiveness)",
                    headingUrdu = "سید الاستغفار (بخشش کی عظیم ترین دعا)",
                    headingHindi = "सैयद-उल-इस्तिगफ़ार (माफ़ी की अज़ीम दुआ)",
                    body = "Whoever recites this with firm faith in the morning and dies before evening will be among the people of Paradise.",
                    bodyUrdu = "ترجمہ: اے اللہ! تو ہی میرا رب ہے، تیرے سوا کوئی معبود نہیں، تو نے ہی مجھے پیدا کیا اور میں تیرا بندہ ہوں، اور میں اپنی طاقت کے مطابق تیرے عہد اور وعدے پر قائم ہوں۔ میں اپنے کیے کے برے انجام سے تیری پناہ مانگتا ہوں، میں اپنے اوپر تیری نعمتوں کا اقرار کرتا ہوں اور اپنے گناہوں کا اعتراف کرتا ہوں، پس مجھے بخش دے کیونکہ تیرے سوا کوئی گناہوں کو بخشنے والا نہیں۔",
                    bodyHindi = "अनुवाद: ऐ अल्लाह! तू ही मेरा रब है, तेरे सिवा कोई माबूद नहीं, तूने मुझे पैदा किया और मैं तेरा बंदा हूँ। मैं अपनी ताक़त भर तेरे वादे पर कायम हूँ। मैं अपने गुनाहों का इकरार करता हूँ, मुझे माफ़ कर दे क्योंकि तेरे सिवा कोई गुनाह नहीं बख्श सकता।",
                    arabicQuote = "اللَّهُمَّ أَنْتَ رَبِّي لَا إِلٰهَ إِلَّا أَنْتَ، خَلَقْتَنِي وَأَنَا عَبْدُكَ، وَأَنَا عَلَىٰ عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ بِذَنْبِي فَاغْفِرْ لِي فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ",
                    translationQuote = "Sahih al-Bukhari (6306)"
                ),
                ArticleSection(
                    heading = "Protection from All Harm (3 Times Morning & Evening)",
                    headingUrdu = "ہر قسم کے نقصان و برائی سے حفاظت کی دعا",
                    headingHindi = "हर बुराई व नुकसान से हिफाज़त की दुआ",
                    body = "Recited 3 times every morning and evening. Nothing on earth or in heaven can harm the reciter.",
                    bodyUrdu = "ترجمہ: اللہ کے نام کے ساتھ جس کے نام کی برکت سے زمین اور آسمان کی کوئی چیز نقصان نہیں پہنچا سکتی، اور وہی سب کچھ سننے والا اور خوب جاننے والا ہے۔",
                    bodyHindi = "अनुवाद: अल्लाह के नाम से जिसके नाम की बरकत से ज़मीन और आसमान की कोई चीज़ नुकसान नहीं पहुंचा सकती, और वह सब सुनने वाला और जानने वाला है।",
                    arabicQuote = "بِسْمِ اللَّهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ وَهُوَ السَّمِيعُ الْعَلِيمُ",
                    translationQuote = "Sunan Abi Dawud (5088), Tirmidhi (3388)"
                )
            ),
            reference = "Hisn al-Muslim (Fortress of the Muslim)"
        )
    )

    val ASMA_UL_HUSNA: List<com.example.data.model.AllahName> = listOf(
        com.example.data.model.AllahName(1, "الرَّحْمَٰنُ", "Ar-Rahman", "بہت زیادہ رحم کرنے والا", "अत्यंत दयालु", "The Most Gracious", "جو شخص ہر نماز کے بعد ۱۰۰ مرتبہ پڑھے گا اس کے دل سے غفلت دور ہو گی۔", "जो हर नमाज़ के बाद १०० बार पढ़ेगा उसके दिल से ग़फ़लत दूर होगी।"),
        com.example.data.model.AllahName(2, "الرَّحِيمُ", "Ar-Raheem", "نہایت مہربان", "अति कृपालु", "The Most Merciful", "جو شخص روزانہ ۱۰۰ مرتبہ پڑھے گا تمام دنیاوی آفات سے محفوظ رہے گا۔", "जो रोज़ाना १०० बार पढ़ेगा दुनिया की आफ़तों से महफ़ूज़ रहेगा।"),
        com.example.data.model.AllahName(3, "الْمَلِكُ", "Al-Malik", "حقیقی بادشاہ", "असली बादशाह", "The King, The Sovereign", "جو شخص زوال کے بعد کثرت سے پڑھے گا اللہ اسے غنی فرمائے گا۔", "जो ज़वाल के बाद पढ़ेगा अल्लाह उसे बेनियाज़ करेगा।"),
        com.example.data.model.AllahName(4, "الْقُدُّوسُ", "Al-Quddus", "نہایت پاک ذات", "अत्यंत पवित्र", "The Most Holy", "جو شخص زوال کے وقت پڑھے گا اس کا دل روحانی بیماریوں سے پاک ہو گا۔", "जो पढ़ेगा उसका दिल रूहानी बीमारियों से पाक होगा।"),
        com.example.data.model.AllahName(5, "السَّلَامُ", "As-Salam", "سلامتی دینے والا", "सलामती देने वाला", "The Giver of Peace", "جو شخص مریض پر ۱۶۰ بار دم کرے اللہ اسے شفا عطا فرمائے گا۔", "मरीज़ पर १६० बार दम करने से अल्लाह शिफा देगा।"),
        com.example.data.model.AllahName(6, "الْمُؤْمِنُ", "Al-Mu'min", "امن و امان دینے والا", "अमन व ईमान देने वाला", "The Granter of Security", "خوف کے وقت ۶۳۰ بار پڑھنے والا ہر ڈر سے محفوظ رہے گا۔", "खौफ़ के वक़्त पढ़ने से अमन हासिल होता है।"),
        com.example.data.model.AllahName(7, "الْمُهَيْمِنُ", "Al-Muhaymin", "نگہبان اور محافظ", "निगहबान व रक्षक", "The Guardian", "غسل کر کے ۱۰۰ بار پڑھنے والے کا باطن روشن ہوتا ہے۔", "१०० बार पढ़ने से दिल का नूर बढ़ता है।"),
        com.example.data.model.AllahName(8, "الْعَزِيزُ", "Al-Aziz", "سب پر غالب اور زبردست", "सब पर ग़ालिब व अज़ीम", "The All Mighty", "فجر کے بعد ۴۰ دن تک ۴۱ بار پڑھنے والا کسی کا محتاج نہ ہو گا۔", "फ़ज्र के बाद पढ़ने से इज़्ज़त हासिल होती है।"),
        com.example.data.model.AllahName(9, "الْجَبَّارُ", "Al-Jabbar", "حکم چلانے والا اور بگڑے کام بنانے والا", "बिगड़े काम बनाने वाला", "The Compeller", "صبح و شام ۲۲۶ بار پڑھنے والا ظالموں سے محفوظ رہے گا۔", "ज़ुल्म से हिफ़ाज़त के लिए बेहतरीन है।"),
        com.example.data.model.AllahName(10, "الْمُتَكَبِّرُ", "Al-Mutakabbir", "بڑائی اور عظمت والا", "बड़ाई व बुजुर्गी वाला", "The Supreme", "ہر کام کے آغاز میں کثرت سے پڑھنے سے کامیابی ملتی ہے۔", "हर काम के शुरू में पढ़ने से बरकत होती है।"),
        com.example.data.model.AllahName(11, "الْخَالِقُ", "Al-Khaliq", "پیدا کرنے والا", "पैदा करने वाला", "The Creator", "آدھی رات کو ۱۰۰ بار پڑھنے سے دل روشن ہوتا ہے۔", "आधा रात को पढ़ने से रूहानी ताकत मिलती है।"),
        com.example.data.model.AllahName(12, "الْبَارِئُ", "Al-Bari'", "عدم سے وجود میں لانے والا", "वजूद बख्शने वाला", "The Maker", "بانجھ عورت روزے رکھ کر ۲۱ بار پڑھے تو اولاد عطا ہو۔", "औलाद की नेमत के लिए बहुत मुफ़ीद है।"),
        com.example.data.model.AllahName(13, "الْمُصَوِّرُ", "Al-Musawwir", "صورت گری کرنے والا", "सूरत देने वाला", "The Fashioner", "صورت و سیرت کی خوبصورتی کے لیے پڑھنا باعثِ برکت ہے۔", "सूरत और सीरत की अच्छाई के लिए पढ़ें।"),
        com.example.data.model.AllahName(14, "الْغَفَّارُ", "Al-Ghaffar", "بہت زیادہ بخشنے والا", "बहुत बख्शने वाला", "The Great Forgiver", "جمعہ کے بعد ۱۰۰ بار پڑھنے سے گناہ معاف ہوتے ہیں۔", "जुमे के बाद पढ़ने से गुनाह माफ़ होते हैं।"),
        com.example.data.model.AllahName(15, "الْقَهَّارُ", "Al-Qahhar", "سب کو قابو میں رکھنے والا", "सब पर हावी", "The Subduer", "دل سے دنیا کی محبت نکالنے کے لیے کثرت سے پڑھیں۔", "नफ़्स पर काबू पाने के लिए पढ़ें।"),
        com.example.data.model.AllahName(16, "الْوَهَّابُ", "Al-Wahhab", "بے حساب عطا کرنے والا", "बेहिसाब अता करने वाला", "The Bestower", "چاشت کے سجدے میں ۷ بار پڑھنے سے فقر دور ہوتا ہے۔", "चाश्त में पढ़ने से तंगदस्ती दूर होती है।"),
        com.example.data.model.AllahName(17, "الرَّزَّاقُ", "Ar-Razzaq", "روزی دینے والا", "रोज़ी देने वाला", "The Provider", "صبح کی نماز سے پہلے ۱۰ بار پڑھنے سے رزق میں کشادگی ہوتی ہے۔", "सुबह पढ़ने से रिज़्क़ में बरकत होती है।"),
        com.example.data.model.AllahName(18, "الْفَتَّاحُ", "Al-Fattah", "راستے اور دروازے کھولنے والا", "मुश्किलें आसान करने वाला", "The Opener", "فجر کے بعد سینے پر ہاتھ رکھ کر ۷۰ بار پڑھنے سے دل کھلتا ہے۔", "फ़ज्र के बाद ७० बार पढ़ने से रुकावटें दूर होती हैं।"),
        com.example.data.model.AllahName(19, "الْعَلِيمُ", "Al-Aleem", "سب کچھ جاننے والا", "सब कुछ जानने वाला", "The All Knowing", "کثرت سے پڑھنے سے علم اور حکمت میں اضافہ ہوتا ہے۔", "इल्म और समझ बढ़ाने के लिए पढ़ें।"),
        com.example.data.model.AllahName(20, "الْقَابِضُ", "Al-Qabid", "تنگ کرنے والا اور روزی روکنے والا", "रोज़ी समेटने वाला", "The Withholder", "روٹی کے ٹکڑے پر ۴ دن لکھ کر کھانے سے بھوک و پیاس سے حفاظت ہو۔", "खौफ़ से बचने के लिए पढ़ें।"),
        com.example.data.model.AllahName(21, "الْبَاسِطُ", "Al-Basit", "روزی اور دل کو کشادہ کرنے والا", "रोज़ी फ़ैलाने वाला", "The Expander", "چاشت کے وقت ۱۰ بار ہاتھ اٹھا کر پڑھنے سے رزق بڑھے گا۔", "हाथ उठाकर १० बार पढ़ने से रिज़्क़ बढ़ता है।"),
        com.example.data.model.AllahName(22, "الْخَافِضُ", "Al-Khafid", "پست اور نیچا کرنے والا", "ज़ालिमों को नीचा दिखाने वाला", "The Abaser", "ظالم کے شر سے بچنے کے لیے ۵۰۰ بار پڑھیں۔", "दुश्मन के शर से बचने के लिए पढ़ें।"),
        com.example.data.model.AllahName(23, "الرَّافِعُ", "Ar-Rafi'", "بلند اور سرفراز کرنے والا", "बुलंदी देने वाला", "The Exalter", "نصف شب ۱۰۰ بار پڑھنے والے کو عزت اور رفعت ملتی ہے۔", "इज़्ज़त और मर्तबा पाने के लिए पढ़ें।"),
        com.example.data.model.AllahName(24, "الْمُعِزُّ", "Al-Mu'izz", "عزت دینے والا", "इज़्ज़त देने वाला", "The Giver of Honour", "پیر اور جمعہ کو مغرب کے بعد ۴۰ بار پڑھیں عزت بڑھے گی۔", "लोगों में इज़्ज़त पाने के लिए पढ़ें।"),
        com.example.data.model.AllahName(25, "الْمُذِلُّ", "Al-Muzill", "ذلیل و خوار کرنے والا", "ज़लील करने वाला", "The Dishonourer", "سجدے میں ۷۵ بار پڑھنے والا حاسدوں سے محفوظ رہتا ہے۔", "हसद करने वालों से बचाव के लिए पढ़ें।"),
        com.example.data.model.AllahName(26, "السَّمِيعُ", "As-Sami'", "سب کچھ سننے والا", "सब सुनने वाला", "The All Hearing", "جمعرات کو چاشت کے بعد ۵۰۰ بار پڑھیں دعائیں قبول ہوں گی۔", "दुआओं की क़बूलियत के लिए पढ़ें।"),
        com.example.data.model.AllahName(27, "الْبَصِيرُ", "Al-Baseer", "سب کچھ دیکھنے والا", "सब देखने वाला", "The All Seeing", "جمعہ کے بعد ۱۰۰ بار پڑھنے سے آنکھوں کی بینائی اور دل تیز ہوتا ہے۔", "आँखों और दिल की रोशनी के लिए पढ़ें।"),
        com.example.data.model.AllahName(28, "الْحَكَمُ", "Al-Hakam", "حقیقی فیصلہ کرنے والا", "इंसाफ़ करने वाला", "The Judge", "آدھی رات کو باوضو ۹۹ بار پڑھنے سے باطن کھلتا ہے۔", "हिकमत पाने के लिए पढ़ें।"),
        com.example.data.model.AllahName(29, "الْعَدْلُ", "Al-Adl", "مکمل انصاف کرنے والا", "इंसाफ़ करने वाला", "The Utterly Just", "جمعہ کی رات ۲۰ ٹکڑوں پر لکھ کر کھانے سے تسخیر ہو۔", "इंसाफ़ व सच्चाई के लिए पढ़ें।"),
        com.example.data.model.AllahName(30, "اللَّطِيفُ", "Al-Lateef", "نہایت باریک بین اور مہربان", "मेहरबान व बारीकबीन", "The Subtly Kind", "۱۳۳ بار پڑھنے سے رزق اور پریشانیوں میں فوری مدد ملتی ہے۔", "मुश्किलों से निकलने के लिए १३३ बार पढ़ें।"),
        com.example.data.model.AllahName(31, "الْخَبِيرُ", "Al-Khabeer", "ہر بات سے باخبر", "सबकी ख़बर रखने वाला", "The All Aware", "۷ دن تک پڑھنے سے مخفی راز کھلتے ہیں۔", "बुरी आदतों से बचने के लिए पढ़ें।"),
        com.example.data.model.AllahName(32, "الْحَلِيمُ", "Al-Haleem", "بردبار اور نرم خو", "सब्र व नरमी वाला", "The Most Forbearing", "غصہ پر قابو پانے اور برکت کے لیے پانی پر دم کریں۔", "गुस्से को शांत करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(33, "الْعَظِيمُ", "Al-Azeem", "بڑی عظمت اور شان والا", "महान व अज़ीम", "The Magnificent", "کثرت سے پڑھنے سے بیماریاں دور اور عزت بڑھتی ہے۔", "इज़्ज़त व तंदुरुस्ती के लिए पढ़ें।"),
        com.example.data.model.AllahName(34, "الْغَفُورُ", "Al-Ghafoor", "بہت بخشنے والا", "बड़ा बख्शने वाला", "The Forgiving", "سر درد اور غم کے لیے کثرت سے پڑھنا مفید ہے۔", "ग़म और दर्द दूर करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(35, "الشَّكُورُ", "Ash-Shakoor", "قدردان اور بدلہ دینے والا", "क़द्रदान", "The Grateful", "۴۱ بار پانی پر دم کر کے پینے سے دل کا بوجھ ہلکا ہوتا ہے۔", "नेमतों की बढ़ोतरी के लिए पढ़ें।"),
        com.example.data.model.AllahName(36, "الْعَلِيُّ", "Al-Ali", "سب سے بلند اور برتر", "सब पर बुलन्द", "The Most High", "کثرت سے پڑھنے والا ہر میدان میں سرخرو ہوتا ہے۔", "बुलंदी व कामियाबी के लिए पढ़ें।"),
        com.example.data.model.AllahName(37, "الْكَبِيرُ", "Al-Kabeer", "بہت بڑا اور عالی مرتبہ", "सब से बड़ा", "The Greatest", "روزانہ ۱۰۰ بار پڑھنے والے کی عزت لوگوں کے دلوں میں ہوتی ہے۔", "इज़्ज़त बढ़ाने के लिए पढ़ें।"),
        com.example.data.model.AllahName(38, "الْحَفِيظُ", "Al-Hafeez", "سب کا نگہبان اور محافظ", "सबका निगहबान", "The Preserver", "۹۹ بار پڑھنے والا ہر آفت، چوری اور بیماری سے محفوظ رہتا ہے۔", "हिफ़ाज़त के लिए ९९ बार पढ़ें।"),
        com.example.data.model.AllahName(39, "الْمُقِيتُ", "Al-Muqeet", "سب کو روزی اور طاقت دینے والا", "रोज़ी देने वाला", "The Sustainer", "پانی پر ۷ بار دم کر کے پینے سے کمزوری دور ہوتی ہے۔", "ताक़त व सेहत के लिए पढ़ें।"),
        com.example.data.model.AllahName(40, "الْحَسِيبُ", "Al-Haseeb", "سب کا حساب لینے اور کافی ہونے والا", "काफ़ी होने वाला", "The Reckoner", "صبح و شام ۷۷ بار 'حَسْبِيَ اللَّهُ الْحَسِيبُ' پڑھیں، کافی ہو گا۔", "मुसीबतों से बचने के लिए पढ़ें।"),
        com.example.data.model.AllahName(41, "الْجَلِيلُ", "Al-Jaleel", "بزرگ اور جلال والا", "जलाल वाला", "The Sublime", "مشک و زعفران سے لکھ کر پاس رکھنے سے عزت بڑھتی ہے۔", "रौब व इज़्ज़त के लिए पढ़ें।"),
        com.example.data.model.AllahName(42, "الْكَرِيمُ", "Al-Kareem", "نہایت سخی اور کرم کرنے والا", "करम करने वाला", "The Generous", "سوتے وقت پڑھنے سے فرشتے دعا مانگتے ہیں۔", "सोते वक़्त पढ़ने से बरकत होती है।"),
        com.example.data.model.AllahName(43, "الرَّقِيبُ", "Ar-Raqeeb", "ہر چیز پر نگاہ رکھنے والا", "सब देखने वाला", "The Watchful", "۷ بار اولاد اور مال پر دم کرنے سے حفاظت رہتی ہے۔", "औलाद की हिफ़ाज़त के लिए पढ़ें।"),
        com.example.data.model.AllahName(44, "الْمُجِيبُ", "Al-Mujeeb", "دعائیں قبول فرمانے والا", "दुआएं क़बूल करने वाला", "The Responsive", "کثرت سے پڑھنے والے کی تمام دعائیں قبول ہوتی ہیں۔", "दुआओं की फ़ौरी क़बूलियत के लिए पढ़ें।"),
        com.example.data.model.AllahName(45, "الْوَاسِعُ", "Al-Wasi'", "وسعت اور کشادگی والا", "कुशादगी वाला", "The All-Encompassing", "کثرت سے پڑھنے سے رزق اور علم میں بے پناہ وسعت ہوتی ہے۔", "रिज़्क़ में फ़राख़ी के लिए पढ़ें।"),
        com.example.data.model.AllahName(46, "الْحَكِيمُ", "Al-Hakeem", "حکمت اور دانائی والا", "हिकमत वाला", "The Wise", "کثرت سے پڑھنے والے کے دل میں حکمت کے چشمے پھوٹتے ہیں۔", "हिकमत व समझ के लिए पढ़ें।"),
        com.example.data.model.AllahName(47, "الْوَدُودُ", "Al-Wadood", "بہت محبت فرمانے والا", "मुहब्बत करने वाला", "The Loving", "۱۰۰۰ بار کھانے پر دم کر کے میاں بیوی کھائیں تو محبت بڑھے۔", "आपसी मुहब्बत के लिए पढ़ें।"),
        com.example.data.model.AllahName(48, "الْمَجِيدُ", "Al-Majeed", "بزرگی اور شان والا", "बुजुर्गी वाला", "The Glorious", "ایامِ بیض میں روزہ رکھ کر پڑھنے سے بیماری دور ہو۔", "शफ़ा पाने के लिए पढ़ें।"),
        com.example.data.model.AllahName(49, "الْبَاعِثُ", "Al-Ba'ith", "مردوں کو زندہ کرنے والا", "ज़िंदा करने वाला", "The Resurrector", "سوتے وقت سینے پر ۱۰۰ بار ہاتھ رکھ کر پڑھیں دل زندہ ہو گا۔", "दिल की ज़िंदगी के लिए पढ़ें।"),
        com.example.data.model.AllahName(50, "الشَّهِيدُ", "Ash-Shaheed", "ہر جگہ حاضر اور دیکھنے والا", "सबका गवाह", "The Witness", "نافرمان اولاد کے ماتھے پر ہاتھ رکھ کر ۲۱ بار پڑھیں۔", "औलाद की इस्लाह के लिए पढ़ें।"),
        com.example.data.model.AllahName(51, "الْحَقُّ", "Al-Haqq", "حقیقی اور برحق", "हक़ व सच्चा", "The Truth", "لاپتہ شخص یا گمشدہ چیز کے لیے چوکور کاغذ پر لکھ کر دعا کریں۔", "सच्चाई पर टिके रहने के लिए पढ़ें।"),
        com.example.data.model.AllahName(52, "الْوَكِيلُ", "Al-Wakeel", "بہترین کارساز", "बेहतरीन कारसाज़", "The Trustee", "آگ، طوفان یا خوف کے وقت 'حَسْبُنَا اللَّهُ وَنِعْمَ الْوَكِيلُ' پڑھیں۔", "हर काम अल्लाह के सुपुर्द करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(53, "الْقَوِيُّ", "Al-Qawiyy", "بہت طاقتور", "बहुत ताक़तवर", "The Strong", "کمزوری اور دشمن کے خوف سے نجات کے لیے پڑھیں۔", "ताक़त पाने के लिए पढ़ें।"),
        com.example.data.model.AllahName(54, "الْمَتِينُ", "Al-Mateen", "انتہائی مضبوط", "मज़बूत कुव्वत वाला", "The Firm", "دودھ پلانے والی عورت کے لیے پڑھنا باعثِ برکت ہے۔", "नेकनीयती पर मज़बूती के लिए पढ़ें।"),
        com.example.data.model.AllahName(55, "الْوَلِيُّ", "Al-Waliyy", "مددگار اور دوست", "मददगार दोस्त", "The Protecting Friend", "جمعہ کی رات ۱۰۰۰ بار پڑھنے والا اللہ کا ولی بنتا ہے۔", "अल्लाह की दोस्ती के लिए पढ़ें।"),
        com.example.data.model.AllahName(56, "الْحَمِيدُ", "Al-Hameed", "تعریف کے لائق", "तारीफ़ के लायक़", "The Praiseworthy", "تنہائی میں کثرت سے پڑھنے والے کی زبان سے خیر نکلتی ہے۔", "अच्छे अख़लाक़ के लिए पढ़ें।"),
        com.example.data.model.AllahName(57, "الْمُحْصِي", "Al-Muhsee", "ہر چیز کو گننے اور احاطہ کرنے والا", "सबका हिसाब रखने वाला", "The Accounter", "۲۰ بار روٹی پر دم کر کے کھانے سے ذہن تیز ہوتا ہے۔", "याद्दाश्त तेज़ करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(58, "الْمُبْدِئُ", "Al-Mubdi'", "پہلی بار پیدا فرمانے والا", "शुरू करने वाला", "The Originator", "حاملہ عورت پر دم کرنے سے حمل محفوظ رہتا ہے۔", "काम की शुरुआत में पढ़ें।"),
        com.example.data.model.AllahName(59, "الْمُعِيدُ", "Al-Mu'eed", "دوبارہ لوٹانے والا", "दोबारा ज़िंदा करने वाला", "The Restorer", "گمشدہ شخص کے گھر کے چاروں کونوں میں ۷۰ بار پڑھیں۔", "गुमशुदा चीज़ मिलने के लिए पढ़ें।"),
        com.example.data.model.AllahName(60, "الْمُحْيِي", "Al-Muhyee", "زندگی عطا کرنے والا", "ज़िंदगी देने वाला", "The Giver of Life", "بیمار پر ۷ بار دم کرنے سے شفا ملتی ہے۔", "सेहत व तंदुरुस्ती के लिए पढ़ें।"),
        com.example.data.model.AllahName(61, "الْمُمِيتُ", "Al-Mumeet", "موت دینے والا", "मौत देने वाला", "The Creator of Death", "نفسِ امارہ کو قابو میں لانے کے لیے سینے پر ہاتھ رکھ کر پڑھیں۔", "नफ़्स को काबू करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(62, "الْحَيُّ", "Al-Hayy", "ہمیشہ زندہ رہنے والا", "सदा ज़िंदा रहने वाला", "The Ever Living", "روزانہ ۳۰۰۰ بار پڑھنے والا کبھی بیمار نہ ہو گا۔", "लंबी उम्र व सेहत के लिए पढ़ें।"),
        com.example.data.model.AllahName(63, "الْقَيُّومُ", "Al-Qayyoom", "سب کو قائم رکھنے والا", "सबको थामने वाला", "The Self-Subsisting", "فجر کی سنتوں کے بعد ۴۱ بار 'يَا حَيُّ يَا قَيُّومُ' پڑھیں دل زندہ ہو گا۔", "सुस्ती दूर करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(64, "الْوَاجِدُ", "Al-Wajid", "ہر چیز کو پانے والا", "सब कुछ पाने वाला", "The Finder", "کھاتے وقت ہر لقمے پر پڑھنے سے دل کو نور ملتا ہے۔", "रूहानी कुव्वत के लिए पढ़ें।"),
        com.example.data.model.AllahName(65, "الْمَاجِدُ", "Al-Majid", "عزت اور بزرگی والا", "इज़्ज़त वाला", "The Noble", "تنہائی میں پڑھنے سے دل میں نور پیدا ہوتا ہے۔", "इज़्ज़त व पाकीज़गी के लिए पढ़ें।"),
        com.example.data.model.AllahName(66, "الْوَاحِدُ", "Al-Wahid", "اکیلا اور یکتا", "एक व अकेला", "The Unique", "۱۰۰۰ بار پڑھنے والے کے دل سے خوف دور ہوتا ہے۔", "दिल से खौफ़ निकालने के लिए पढ़ें।"),
        com.example.data.model.AllahName(67, "الْأَحَدُ", "Al-Ahad", "ایک جس کا کوئی ثانی نہیں", "ला-सानी", "The One", "۱۰۰۰ بار پڑھنے سے فرشتوں کی صحبت ملتی ہے۔", "तौहीद की मज़बूती के लिए पढ़ें।"),
        com.example.data.model.AllahName(68, "الصَّمَدُ", "As-Samad", "بے نیاز جس کا سب محتاج ہیں", "बेनियाज़", "The Eternal Refuge", "سحری کے وقت سجدے میں ۱۱۵ بار پڑھیں، محتاجی نہ ہو گی۔", "तंगदस्ती से नजात के लिए पढ़ें।"),
        com.example.data.model.AllahName(69, "الْقَادِرُ", "Al-Qadir", "بڑی قدرت اور طاقت والا", "क़ुदरत वाला", "The Capable", "وضو کے بعد دونوں پیروں پر دم کرنے سے طاقت ملے گی۔", "मुश्किल आसान करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(70, "الْمُقْتَدِرُ", "Al-Muqtadir", "مکمل اقتدار والا", "ताक़तवर हाकिम", "The Omnipotent", "سو کر اٹھتے ہی کثرت سے پڑھیں کام آسان ہوں گے۔", "सोकर उठते ही पढ़ें।"),
        com.example.data.model.AllahName(71, "الْمُقَدِّمُ", "Al-Muqaddim", "آگے بڑھانے والا", "आगे बढ़ाने वाला", "The Promoter", "میدانِ جنگ یا امتحان کے وقت کثرت سے پڑھیں۔", "इम्तिहान में कामियाबी के लिए पढ़ें।"),
        com.example.data.model.AllahName(72, "الْمُؤَخِّرُ", "Al-Mu'akhkhir", "پیچھے ہٹانے والا", "पीछे करने वाला", "The Delayer", "روزانہ ۱۰۰ بار پڑھنے والے کے دل میں اللہ کی محبت بڑھے گی۔", "बुरी आदतों से पीछे हटने के लिए पढ़ें।"),
        com.example.data.model.AllahName(73, "الْأَوَّلُ", "Al-Awwal", "سب سے پہلا", "सबसे पहला", "The First", "۴۰ دن تک ۱۰۰۰ بار پڑھنے سے مسافر بخیریت لوٹے گا۔", "सफ़र में हिफ़ाज़त के लिए पढ़ें।"),
        com.example.data.model.AllahName(74, "الْآخِرُ", "Al-Akhir", "سب کے بعد باقی رہنے والا", "हमेशा बाक़ी रहने वाला", "The Last", "روزانہ ۱۰۰۰ بار پڑھنے والے کا خاتمہ بالخیر ہو گا۔", "अच्छे ख़ात्मे के लिए पढ़ें।"),
        com.example.data.model.AllahName(75, "الظَّاهِرُ", "Az-Zahir", "سب پر ظاہر اور عیاں", "ज़ाहिर व नुमायाँ", "The Manifest", "اشراق کے بعد ۵۰۰ بار پڑھنے سے آنکھوں میں نور آتا ہے۔", "आँखों की रोशनी के लिए पढ़ें।"),
        com.example.data.model.AllahName(76, "الْبَاطِنُ", "Al-Batin", "سب سے پوشیدہ اور مخفی", "छिपा हुआ", "The Hidden", "روزانہ ۳۳ بار پڑھنے والے کے باطن میں راز کھلتے ہیں۔", "रूहानी इल्म के लिए पढ़ें।"),
        com.example.data.model.AllahName(77, "الْوَالِي", "Al-Wali", "سب کا مالک اور منتظم", "सबका मालिक", "The Governor", "گھر پر دم کرنے سے آفات سے محفوظ رہتا ہے۔", "घर की हिफ़ाज़त के लिए पढ़ें।"),
        com.example.data.model.AllahName(78, "الْمُتَعَالِي", "Al-Muta'ali", "سب سے بلند اور برتر", "सब से ऊँचा", "The Most Exalted", "کثرت سے پڑھنے والی عورتوں کو عزت ملتی ہے۔", "बुलंद मर्तबे के लिए पढ़ें।"),
        com.example.data.model.AllahName(79, "الْبَرُّ", "Al-Barr", "احسان اور بھلائی فرمانے والا", "एहसान करने वाला", "The Source of Goodness", "بچے کی پیدائش پر ۷ بار پڑھیں، نیک خصلت ہو گا۔", "नेकी व भलाई के लिए पढ़ें।"),
        com.example.data.model.AllahName(80, "التَّوَّابُ", "At-Tawwab", "توبہ قبول فرمانے والا", "तौबा क़बूल करने वाला", "The Acceptor of Repentance", "چاشت کے وقت ۳۶۰ بار پڑھنے والے کی توبہ قبول ہوتی ہے۔", "सच्ची तौबा के लिए पढ़ें।"),
        com.example.data.model.AllahName(81, "الْمُنْتَقِمُ", "Al-Muntaqim", "ظالموں سے بدلہ لینے والا", "बदला लेने वाला", "The Avenger", "ظالم کے شر سے بچنے کے لیے ۳ جمعہ پڑھیں۔", "ज़ुल्म से हिफ़ाज़त के लिए पढ़ें।"),
        com.example.data.model.AllahName(82, "الْعَفُوُّ", "Al-Afuww", "معاف فرمانے والا", "माफ़ करने वाला", "The Pardoner", "کثرت سے پڑھنے والے کے تمام گناہ معاف ہوتے ہیں۔", "गुनाहों की माफ़ी के लिए पढ़ें।"),
        com.example.data.model.AllahName(83, "الرَّءُوفُ", "Ar-Ra'oof", "انتہائی شفقت فرمانے والا", "अत्यंत कृपालु", "The Compassionate", "۱۰ بار پڑھنے سے غصہ اور نفرت دور ہوتی ہے۔", "नफ़रत दूर करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(84, "مَالِكُ الْمُلْكِ", "Malik-ul-Mulk", "تمام جہانوں کا حقیقی مالک", "सल्तनत का मालिक", "Master of the Kingdom", "کثرت سے پڑھنے والا غنی اور خوددار ہوتا ہے۔", "माली ख़ुशहाली के लिए पढ़ें।"),
        com.example.data.model.AllahName(85, "ذُو الْجَلَالِ وَالْإِكْرَامِ", "Zul-Jalali-wal-Ikram", "عظمت، جلال اور کرم والا", "जलाल व करम वाला", "Lord of Majesty and Generosity", "کثرت سے پڑھنے والے کی ہر حاجت پوری ہوتی ہے۔", "हर हाजत पूरी होने के लिए पढ़ें।"),
        com.example.data.model.AllahName(86, "الْمُقْسِطُ", "Al-Muqsit", "انصاف قائم فرمانے والا", "इंसाफ़ क़ायम करने वाला", "The Equitable", "وسوسوں سے نجات کے لیے روزانہ ۱۰۰ بار پڑھیں۔", "बुरे ख्यालों से बचने के लिए पढ़ें।"),
        com.example.data.model.AllahName(87, "الْجَامِعُ", "Al-Jami'", "سب کو جمع فرمانے والا", "सबको इकट्ठा करने वाला", "The Gatherer", "گمشدہ چیز یا بچھڑے ہوؤں کو ملانے کے لیے پڑھیں۔", "गुमशुदा मिलने के लिए पढ़ें।"),
        com.example.data.model.AllahName(88, "الْغَنِيُّ", "Al-Ghaniyy", "بے نیاز اور غنی", "बेनियाज़ व अमीर", "The Self-Sufficient", "۷۰ بار پڑھنے سے برکت اور خودداری حاصل ہوتی ہے۔", "तंगदस्ती दूर करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(89, "الْمُغْنِي", "Al-Mughni", "غنی اور مالدار بنانے والا", "ग़नी बनाने वाला", "The Enricher", "۱۰۰۰ بار پڑھنے والے کے رزق کے دروازے کھلتے ہیں۔", "रोज़ी में बरकत के लिए पढ़ें।"),
        com.example.data.model.AllahName(90, "الْمَانِعُ", "Al-Mani'", "روکنے اور بچانے والا", "नुक़सान रोकने वाला", "The Preventer", "گھریلو جھگڑے ختم کرنے کے لیے سوتے وقت ۲۰ بار پڑھیں۔", "झगड़े खत्म करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(91, "الضَّارُّ", "Ad-Darr", "نقصان پہنچانے پر قادر", "तकलीफ़ पर कादिर", "The Distressor", "جمعہ کی رات ۱۰۰ بار پڑھنے والا آفات سے بچتا ہے۔", "आफ़तों से बचाव के लिए पढ़ें।"),
        com.example.data.model.AllahName(92, "النَّافِعُ", "An-Nafi'", "نفع اور بھلائی دینے والا", "फ़ायदा पहुँचाने वाला", "The Benefactor", "کسی کام کے آغاز میں پڑھیں تو بے پناہ نفع ہو گا۔", "कारोबार में बरकत के लिए पढ़ें।"),
        com.example.data.model.AllahName(93, "النُّورُ", "An-Noor", "تمام کائنات کو روشن فرمانے والا", "रोशन करने वाला", "The Light", "سورۃ النور کے ساتھ پڑھنے سے دل روشن ہوتا ہے۔", "दिल की रोशनी के लिए पढ़ें।"),
        com.example.data.model.AllahName(94, "الْهَادِي", "Al-Hadi", "سیدھا راستہ دکھانے والا", "हिदायत देने वाला", "The Guide", "آسمان کی طرف دیکھ کر پڑھیں ہدایت حاصل ہو گی۔", "हिदायत व रहनुमाई के लिए पढ़ें।"),
        com.example.data.model.AllahName(95, "الْبَدِيعُ", "Al-Badi'", "بغیر مثال کے پیدا کرنے والا", "अनोखी मख़लूक़ बनाने वाला", "The Incomparable", "غم اور پریشانی میں ۷۰ بار 'يَا بَدِيعَ الْعَجَائِبِ' پڑھیں۔", "मुश्किल हल करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(96, "الْبَاقِي", "Al-Baqi", "ہمیشہ باقی رہنے والا", "सदा रहने वाला", "The Everlasting", "جمعہ کی رات ۱۰۰ بار پڑھنے سے اعمال قبول ہوتے ہیں۔", "नेक आमाल की क़बूलियत के लिए पढ़ें।"),
        com.example.data.model.AllahName(97, "الْوَارِثُ", "Al-Warith", "سب کے بعد وارث رہنے والا", "असली वारिस", "The Ultimate Inheritor", "سورج نکلتے وقت ۱۰۰ بار پڑھنے سے لمبی عمر اور ایمان ملے۔", "लंबी उम्र व रिज़्क़ के लिए पढ़ें।"),
        com.example.data.model.AllahName(98, "الرَّشِيدُ", "Ar-Rasheed", "سیدھی راہ پر چلانے والا", "सीधा रास्ता दिखाने वाला", "The Guide to Right Path", "مغرب و عشاء کے درمیان ۱۰۰۰ بار پڑھنے سے الجھنیں ختم ہوں۔", "उलझनें दूर करने के लिए पढ़ें।"),
        com.example.data.model.AllahName(99, "الصَّبُورُ", "As-Saboor", "نہایت صبر فرمانے والا", "बड़ा सब्र करने वाला", "The Patient", "سورج نکلنے سے پہلے ۱۰۰ بار پڑھنے والا مصیبتوں سے محفوظ رہتا ہے۔", "सब्र व सुकून पाने के लिए पढ़ें।")
    )
}
