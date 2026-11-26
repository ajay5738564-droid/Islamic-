package com.example.data.repository

import com.example.data.model.DuaCategory
import com.example.data.model.DuaItem

object DuaRepository {

    val CATEGORIES = listOf(
        DuaCategory("morning", "Morning Azkar", "सुबह के अज़कार", "صبح کے اذکار", "wb_sunny", 5),
        DuaCategory("evening", "Evening Azkar", "शाम के अज़कार", "شام کے اذکار", "nights_stay", 5),
        DuaCategory("sleep", "Before Sleeping", "सोने से पहले", "سونے سے پہلے کی دعائیں", "bedtime", 4),
        DuaCategory("waking", "After Waking", "जागने के बाद", "جاگنے کے بعد کی دعا", "alarm", 2),
        DuaCategory("food", "Food & Meals", "खाने-पीने की दुआएं", "کھانے پینے کی دعائیں", "restaurant", 3),
        DuaCategory("travel", "Travel & Journey", "सफ़र की दुआएं", "سفر کی دعائیں", "flight", 3),
        DuaCategory("protection", "Protection & Evil Eye", "हिफ़ाज़त और नज़र-ए-बद", "حفاظت اور نظر بد", "security", 4),
        DuaCategory("forgiveness", "Forgiveness & Istighfar", "तौबा और इस्तिग़फ़ार", "توبہ اور استغفار", "favorite", 4),
        DuaCategory("gratitude", "Gratitude & Praise", "शुक्र और हम्द", "شکر اور حمد", "sentiment_satisfied", 3),
        DuaCategory("stress", "Anxiety & Distress", "परेशानी और रंज-ओ-ग़म", "پریشانی اور غم دور کرنے کی دعا", "psychology", 4),
        DuaCategory("parents", "Parents & Family", "वालिदैन और खानदान", "والدین اور اہل خانہ", "family_restroom", 3),
        DuaCategory("health", "Health & Shifa", "शिफ़ा और बीमारी", "صحت اور شفا کی دعائیں", "healing", 3),
        DuaCategory("ramadan", "Ramadan & Fasting", "रमज़ान और रोज़ा", "رمضان اور روزے کی دعائیں", "brightness_3", 4),
        DuaCategory("hajj", "Hajj & Umrah", "हज और उमरा", "حج اور عمرہ کی دعائیں", "mosque", 4),
        DuaCategory("daily", "Daily Essentials", "रोज़मर्रा की दुआएं", "روزمرہ کی اہم دعائیں", "schedule", 5),
        DuaCategory("poetry", "Dua & Munajat (Poetry)", "दुआ और मुनाजात", "دعا اور مناجات", "menu_book", 1)
    )

    val ALL_DUAS = listOf(
        // Morning
        DuaItem(
            id = "morn_1",
            categoryId = "morning",
            titleEnglish = "Morning Affirmation of Faith",
            titleHindi = "सुबह की तौहीद की दुआ",
            titleUrdu = "صبح کی دعا",
            arabicText = "أَصْبَحْنَا وَأَصْبَحَ الْمُلْكُ لِلَّهِ، وَالْحَمْدُ لِلَّهِ، لَا إِلَٰهَ إِلَّا اللَّهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ",
            transliteration = "Asbahnaa wa asbahal-mulku lillaah, walhamdu lillaah, laa ilaaha ill-Allaahu wahdahu laa shareeka lah, lahul-mulku wa lahul-hamdu wa huwa 'alaa kulli shay'in qadeer.",
            englishTranslation = "We have reached the morning and the kingdom belongs to Allah, all praise is for Allah. None has the right to be worshipped except Allah alone, without partner. To Him belongs the dominion and to Him belongs all praise, and He is over all things competent.",
            hindiTranslation = "हम सुबह में दाखिल हुए और सारी कायनात की बादशाही अल्लाह के लिए है, और सब तारीफें अल्लाह ही के लिए हैं। अल्लाह के सिवा कोई माबूद नहीं, वो अकेला है उसका कोई शरीक नहीं, उसी की बादशाही है और उसी के लिए सब तारीफ है, और वो हर चीज़ पर पूरी क़ुदरत रखता है।",
            urduTranslation = "ہم نے صبح کی اور اللہ کی تمام کائنات نے صبح کی، سب تعریفیں اللہ کے لیے ہیں۔ اللہ کے سوا کوئی معبود نہیں، وہ اکیلا ہے اس کا کوئی شریک نہیں، اسی کی بادشاہی ہے اور اسی کی تعریف ہے اور وہ ہر چیز پر قادر ہے۔",
            reference = "Sahih Muslim (Book 48, Hadith 75)",
            benefit = "Recited once each morning for guidance, clarity, and reliance on Allah.",
            recommendedCount = 1
        ),
        DuaItem(
            id = "morn_2",
            categoryId = "morning",
            titleEnglish = "Dua for Well-being & Protection",
            titleHindi = "आफ़ियत और हिफाज़त की दुआ",
            titleUrdu = "عافیت اور حفاظت کی دعا",
            arabicText = "اللَّهُمَّ عَافِنِي فِي بَدَنِي، اللَّهُمَّ عَافِنِي فِي سَمْعِي، اللَّهُمَّ عَافِنِي فِي بَصَرِي، لَا إِلَٰهَ إِلَّا أَنْتَ",
            transliteration = "Allaahumma 'aafinee fee badanee, Allaahumma 'aafinee fee sam'ee, Allaahumma 'aafinee fee basaree, laa ilaaha illaa Ant.",
            englishTranslation = "O Allah, grant me well-being in my body. O Allah, grant me well-being in my hearing. O Allah, grant me well-being in my sight. There is none worthy of worship except You.",
            hindiTranslation = "ऐ अल्लाह! मेरे बदन में मुझे आफ़ियत (सलामती) अता फरमा, ऐ अल्लाह! मेरे कानों में मुझे आफ़ियत अता फरमा, ऐ अल्लाह! मेरी आँखों में मुझे आफ़ियत अता फरमा। तेरे सिवा कोई सच्चा माबूद नहीं।",
            urduTranslation = "اے اللہ! میرے بدن میں عافیت عطا فرما، اے اللہ! میری سماعت میں عافیت عطا فرما، اے اللہ! میری بصارت میں عافیت عطا فرما، تیرے سوا کوئی معبود نہیں۔",
            reference = "Sunan Abi Dawud (5090), Sunan an-Nasa'i",
            benefit = "Sunnah to recite 3 times in the morning and 3 times in the evening.",
            recommendedCount = 3
        ),

        // Evening
        DuaItem(
            id = "eve_1",
            categoryId = "evening",
            titleEnglish = "Evening Reliance on Allah",
            titleHindi = "शाम का इक़रार-ए-तौहीद",
            titleUrdu = "شام کے وقت کی دعا",
            arabicText = "أَمْسَيْنَا وَأَمْسَى الْمُلْكُ لِلَّهِ، وَالْحَمْدُ لِلَّهِ، لَا إِلَٰهَ إِلَّا اللَّهُ وَحْدَهُ لَا شَرِيكَ لَهُ",
            transliteration = "Amsaynaa wa amsal-mulku lillaah, walhamdu lillaah, laa ilaaha ill-Allaahu wahdahu laa shareeka lah.",
            englishTranslation = "We have reached the evening and the kingdom belongs to Allah, all praise is for Allah. None has the right to be worshipped except Allah alone, without partner.",
            hindiTranslation = "हम शाम में दाखिल हुए और सारी बादशाही अल्लाह के लिए है, और सब तारीफें अल्लाह ही के लिए हैं। अल्लाह के सिवा कोई सच्चा माबूद नहीं, वो अकेला है।",
            urduTranslation = "ہم نے شام کی اور اللہ کے ملک نے شام کی، سب تعریف اللہ کے لیے ہے، اللہ کے سوا کوئی معبود نہیں وہ اکیلا ہے۔",
            reference = "Sahih Muslim (2723)",
            recommendedCount = 1
        ),

        // Sayyidul Istighfar
        DuaItem(
            id = "forg_1",
            categoryId = "forgiveness",
            titleEnglish = "Sayyidul Istighfar (The Master Supplication for Forgiveness)",
            titleHindi = "सैय्यदुल इस्तिग़फ़ार (तौबा की सबसे अज़ीम दुआ)",
            titleUrdu = "سید الاستغفار",
            arabicText = "اللَّهُمَّ أَنْتَ رَبِّي لَا إِلَٰهَ إِلَّا أَنْتَ، خَلَقْتَنِي وَأَنَا عَبْدُكَ، وَأَنَا عَلَىٰ عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ لَكَ بِذَنْبِي فَاغْفِرْ لِي فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ",
            transliteration = "Allaahumma Anta Rabbee laa ilaaha illaa Anta, khalaqtanee wa ana 'abduka, wa ana 'alaa 'ahdika wa wa'dika mastata'tu, a'oodhu bika min sharri maa sana'tu, aboo'u laka bini'matika 'alayya, wa aboo'u laka bidhanbee faghfir lee fa-innahu laa yaghfirudh-dhunooba illaa Ant.",
            englishTranslation = "O Allah, You are my Lord, none has the right to be worshipped except You. You created me and I am Your servant, and I abide to Your covenant and promise as best I can. I seek refuge in You from the evil of what I have done. I acknowledge before You Your blessings upon me, and I confess to You my sin. So forgive me, for indeed none can forgive sins except You.",
            hindiTranslation = "ऐ अल्लाह! तू मेरा रब है, तेरे सिवा कोई माबूद नहीं। तूने मुझे पैदा किया और मैं तेरा बन्दा हूँ, और अपनी ताक़त के मुताबिक तेरे अहद और वादे पर कायम हूँ। मैं अपने किए हुए आमाल की बुराई से तेरी पनाह मांगता हूँ। मैं अपने ऊपर तेरी नेमतों का इक़रार करता हूँ और अपने गुनाहों का एतिराफ़ करता हूँ, पस मुझे बख़्श दे, क्योंकि तेरे सिवा कोई गुनाहों को नहीं बख़्श सकता।",
            urduTranslation = "اے اللہ! تو میرا رب ہے، تیرے سوا کوئی معبود نہیں۔ تو نے مجھے پیدا کیا اور میں تیرا بندہ ہوں اور میں اپنی طاقت کے مطابق تیرے عہد اور وعدے پر قائم ہوں۔ میں اپنے اعمال کے شر سے تیری پناہ مانگتا ہوں۔ میں اپنے اوپر تیری نعمتوں کا اقرار کرتا ہوں اور اپنے گناہوں کا اعتراف کرتا ہوں، پس مجھے معاف فرما دے کیونکہ تیرے سوا کوئی گناہوں کو معاف نہیں کر سکتا۔",
            reference = "Sahih al-Bukhari (6306)",
            benefit = "Prophet Muhammad (PBUH) said: 'Whoever recites this during the day with firm faith and dies before evening will be among the people of Paradise.'",
            recommendedCount = 1
        ),

        // Sleep
        DuaItem(
            id = "sleep_1",
            categoryId = "sleep",
            titleEnglish = "Before Sleeping",
            titleHindi = "सोने की सुन्नत दुआ",
            titleUrdu = "سونے کے وقت کی مسنون دعا",
            arabicText = "بِاسْمِكَ اللَّهُمَّ أَمُوتُ وَأَحْيَا",
            transliteration = "Bismika Allaahumma amootu wa ahyaa.",
            englishTranslation = "In Your name, O Allah, I die and I live.",
            hindiTranslation = "ऐ अल्लाह! तेरे ही नाम से मैं मरता (सोता) हूँ और जीता (जागता) हूँ।",
            urduTranslation = "اے اللہ! تیرے نام کے ساتھ میں مرتا اور جیتا ہوں۔",
            reference = "Sahih al-Bukhari (6324)",
            recommendedCount = 1
        ),

        // Waking
        DuaItem(
            id = "wake_1",
            categoryId = "waking",
            titleEnglish = "Upon Waking Up",
            titleHindi = "सो कर उठने की दुआ",
            titleUrdu = "نیند سے بیدار ہونے کی دعا",
            arabicText = "الْحَمْدُ لِلَّهِ الَّذِي أَحْيَانَا بَعْدَ مَا أَمَاتَنَا وَإِلَيْهِ النُّشُورُ",
            transliteration = "Alhamdu lillaahil-ladhee ahyaanaa ba'da maa amaatanaa wa ilayhin-nushoor.",
            englishTranslation = "All praise is for Allah who gave us life after having given us death, and unto Him is the resurrection.",
            hindiTranslation = "तमाम तारीफें अल्लाह के लिए हैं जिसने हमें मौत (नींद) के बाद ज़िन्दगी (बेदारी) बख्शी और उसी की तरफ लौट कर जाना है।",
            urduTranslation = "تمام تعریفیں اللہ کے لیے ہیں جس نے ہمیں مارنے کے بعد زندہ کیا اور اسی کی طرف اٹھ کر جانا ہے۔",
            reference = "Sahih al-Bukhari (6312)",
            recommendedCount = 1
        ),

        // Food
        DuaItem(
            id = "food_1",
            categoryId = "food",
            titleEnglish = "Before Starting a Meal",
            titleHindi = "खाना शुरू करने से पहले",
            titleUrdu = "کھانا شروع کرنے کی دعا",
            arabicText = "بِسْمِ اللَّهِ وَعَلَىٰ بَرَكَةِ اللَّهِ",
            transliteration = "Bismillaahi wa 'alaa barakatillaah.",
            englishTranslation = "In the name of Allah and with the blessings of Allah.",
            hindiTranslation = "अल्लाह के नाम के साथ और अल्लाह की बरकत पर (मैं खाना शुरू करता हूँ)।",
            urduTranslation = "اللہ کے نام سے اور اللہ کی برکت پر۔",
            reference = "Sunan Abi Dawud (3767)",
            recommendedCount = 1
        ),
        DuaItem(
            id = "food_2",
            categoryId = "food",
            titleEnglish = "After Finishing a Meal",
            titleHindi = "खाना खाने के बाद की दुआ",
            titleUrdu = "کھانا کھانے کے بعد کی دعا",
            arabicText = "الْحَمْدُ لِلَّهِ الَّذِي أَطْعَمَنَا وَسَقَانَا وَجَعَلَنَا مُسْلِمِينَ",
            transliteration = "Alhamdu lillaahil-ladhee at'amanaa wa saqaanaa wa ja'alanaa muslimeen.",
            englishTranslation = "All praise is for Allah who has fed us, given us drink, and made us Muslims.",
            hindiTranslation = "सब तारीफें अल्लाह के लिए हैं जिसने हमें खिलाया, पिलाया और हमें मुसलमान बनाया।",
            urduTranslation = "تمام تعریفیں اللہ کے لیے ہیں جس نے ہمیں کھلایا، پلایا اور ہمیں مسلمان بنایا۔",
            reference = "Sunan Abi Dawud (3850), Jami' at-Tirmidhi (3457)",
            recommendedCount = 1
        ),

        // Travel
        DuaItem(
            id = "trav_1",
            categoryId = "travel",
            titleEnglish = "Dua for Traveling & Transport",
            titleHindi = "सफ़र और सवारी की दुआ",
            titleUrdu = "سفر کی مسنون دعا",
            arabicText = "سُبْحَانَ الَّذِي سَخَّرَ لَنَا هَٰذَا وَمَا كُنَّا لَهُ مُقْرِنِينَ، وَإِنَّا إِلَىٰ رَبِّنَا لَمُنقَلِبُونَ",
            transliteration = "Subhaanal-ladhee sakh-khara lanaa haadhaa wa maa kunnaa lahoo muqrineen, wa innaaa ilaa Rabbinaa lamunqaliboon.",
            englishTranslation = "Glory be to Him who has subjected this to us, and we could never have achieved it by ourselves. And indeed, to our Lord we will return.",
            hindiTranslation = "पाक है वो ज़ात जिसने इसे हमारे काबू में कर दिया, वरना हम इसे काबू करने वाले नहीं थे। और यक़ीनन हम अपने रब ही की तरफ लौटने वाले हैं।",
            urduTranslation = "پاک ہے وہ ذات جس نے اس کو ہمارے تابع کر دیا حالانکہ ہم اس پر قابو پانے والے نہ تھے، اور بے شک ہم اپنے رب ہی کی طرف لوٹنے والے ہیں۔",
            reference = "Surah Az-Zukhruf (43:13-14), Sahih Muslim (1342)",
            recommendedCount = 1
        ),

        // Stress & Anxiety
        DuaItem(
            id = "stress_1",
            categoryId = "stress",
            titleEnglish = "Dua in Times of Distress (Dua of Prophet Yunus)",
            titleHindi = "मुसीबत और परेशानी दूर करने की दुआ (आयते करीमा)",
            titleUrdu = "پریشانی کی دعا (آیتِ کریمہ)",
            arabicText = "لَا إِلَٰهَ إِلَّا أَنْتَ سُبْحَانَكَ إِنِّي كُنتُ مِنَ الظَّالِمِينَ",
            transliteration = "Laa ilaaha illaa Anta subhaanaka innee kuntu minadh-dhaalimeen.",
            englishTranslation = "There is no deity except You; exalted are You. Indeed, I have been of the wrongdoers.",
            hindiTranslation = "तेरे सिवा कोई सच्चा माबूद नहीं, तू पाक है, बेशक मैं ही क़ुसूरवारों (नाइंसाफी करने वालों) में से हूँ।",
            urduTranslation = "تیرے سوا کوئی معبود نہیں، تو پاک ہے، بے شک میں ہی قصورواروں میں سے تھا۔",
            reference = "Surah Al-Anbiya (21:87), Jami' at-Tirmidhi (3505)",
            benefit = "Prophet Muhammad (PBUH) stated that no Muslim calls upon Allah with these words in any distress except that Allah relieves their distress.",
            recommendedCount = 33
        ),

        // Parents
        DuaItem(
            id = "par_1",
            categoryId = "parents",
            titleEnglish = "Dua for Parents' Mercy",
            titleHindi = "वालिदैन (माता-पिता) के लिए रहमत की दुआ",
            titleUrdu = "والدین کے لیے دعا",
            arabicText = "رَّبِّ ارْحَمْهُمَا كَمَا رَبَّيَانِي صَغِيرًا",
            transliteration = "Rabbir-hamhumaa kamaa rabbayaanee sagheeraa.",
            englishTranslation = "My Lord, have mercy upon them as they brought me up when I was small.",
            hindiTranslation = "ऐ मेरे रब! उन दोनों (माँ-बाप) पर रहम फरमा जैसा कि उन्होंने बचपन में मेरी परवरिश की थी।",
            urduTranslation = "اے میرے رب! ان دونوں پر رحم فرما جس طرح انہوں نے مجھے بچپن میں پالا۔",
            reference = "Surah Al-Isra (17:24)",
            recommendedCount = 3
        ),

        // Protection
        DuaItem(
            id = "prot_1",
            categoryId = "protection",
            titleEnglish = "Seeking Protection in Allah's Perfect Words",
            titleHindi = "हर बुराई से हिफाज़त के कलिमात",
            titleUrdu = "حفاظت کے کلمات",
            arabicText = "أَعُوذُ بِكَلِمَاتِ اللَّهِ التَّامَّاتِ مِنْ شَرِّ مَا خَلَقَ",
            transliteration = "A'oodhu bikalimaatillaahit-taammaati min sharri maa khalaq.",
            englishTranslation = "I seek refuge in the Perfect Words of Allah from the evil of what He has created.",
            hindiTranslation = "मैं अल्लाह के मुकम्मल कलिमात की पनाह मांगता हूँ हर उस चीज़ की बुराई से जो उसने पैदा की।",
            urduTranslation = "میں اللہ کے کامل کلمات کے ذریعے پناہ مانگتا ہوں ہر اس چیز کے شر سے جو اس نے پیدا کی۔",
            reference = "Sahih Muslim (2708)",
            recommendedCount = 3
        ),

        // Fasting / Ramadan
        DuaItem(
            id = "ram_1",
            categoryId = "ramadan",
            titleEnglish = "Dua for Breaking the Fast (Iftar)",
            titleHindi = "इफ्तार (रोज़ा खोलने) की मसनून दुआ",
            titleUrdu = "افطار کے وقت کی مسنون دعا",
            arabicText = "ذَهَبَ الظَّمَأُ وَابْتَلَّتِ الْعُرُوقُ وَثَبَتَ الأَجْرُ إِنْ شَاءَ اللَّهُ",
            transliteration = "Dhahabadh-dhama'u wabtallatil-'urooqu wa thabatal-ajru in shaa'Allaah.",
            englishTranslation = "The thirst is gone, the veins are moistened, and the reward is confirmed, if Allah wills.",
            hindiTranslation = "प्यास चली गई, नसें तर-ओ-ताज़ा हो गईं और अल्लाह ने चाहा तो सवाब साबित हो गया।",
            urduTranslation = "پیاس ختم ہو گئی، رگیں تر ہو گئیں اور اجر ثابت ہو گیا، اگر اللہ نے چاہا۔",
            reference = "Sunan Abi Dawud (2357)",
            recommendedCount = 1
        ),
        DuaItem(
            id = "ram_2",
            categoryId = "ramadan",
            titleEnglish = "Dua for Laylatul Qadr (Night of Power)",
            titleHindi = "शब-ए-क़द्र की अज़ीम दुआ",
            titleUrdu = "شب قدر کی دعا",
            arabicText = "اللَّهُمَّ إِنَّكَ عَفُوٌّ تُحِبُّ الْعَفْوَ فَاعْفُ عَنِّي",
            transliteration = "Allaahumma innaka 'Afuwwun tuhibbul-'afwa fa'fu 'annee.",
            englishTranslation = "O Allah, You are Most Forgiving, and You love forgiveness, so forgive me.",
            hindiTranslation = "ऐ अल्लाह! तू बहुत माफ़ करने वाला है और माफ़ करने को पसन्द करता है, पस मुझे माफ़ फरमा दे।",
            urduTranslation = "اے اللہ! تو بہت معاف کرنے والا ہے اور معافی کو پسند فرماتا ہے، پس مجھے معاف فرما دے۔",
            reference = "Jami' at-Tirmidhi (3513), Sunan Ibn Majah",
            recommendedCount = 100
        ),
        DuaItem(
            id = "poetry_iqbal_1",
            categoryId = "poetry",
            titleEnglish = "Lab Pe Aati Hai Dua (Allama Iqbal)",
            titleHindi = "लब पे आती है दुआ बन के तमन्ना मेरी",
            titleUrdu = "لب پہ آتی ہے دعا بن کے تمنا میری",
            arabicText = "لب پہ آتی ہے دعا بن کے تمنا میری\nزندگی شمع کی صورت ہو خدایا میری\nدور دنیا کا مرے دم سے اندھیرا ہو جائے\nہر جگہ میرے چمکنے سے اجالا ہو جائے\nہو مرے دم سے یونہی میرے وطن کی زینت\nجس طرح پھول سے ہوتی ہے چمن کی زینت\nزندگی ہو مری پروانے کی صورت یا رب\nعلم کی شمع سے ہو مجھ کو محبت یا رب\nہو مرا کام غریبوں کی حمایت کرنا\nدردمندوں سے، ضعیفوں سے محبت کرنا\nمرے اللہ! برائی سے بچانا مجھ کو\nنیک جو راہ ہو اس رہ پہ چلانا مجھ کو",
            transliteration = "Lab pe aati hai dua ban ke tamanna meri; zindagi shamma ki soorat ho Khudaya meri. Door duniya ka mere dam se andhera ho jaaye; har jagah mere chamakne se ujala ho jaaye. Ho mere dam se yunhi mere watan ki zeenat; jis tarah phool se hoti hai chaman ki zeenat. Zindagi ho meri parwane ki soorat ya Rab; ilm ki shamma se ho mujh ko mohabbat ya Rab. Ho mera kaam ghareebon ki himayat karna; dard-mandon se, zaeefon se mohabbat karna. Mere Allah, burai se bachana mujh ko; nek jo raah ho us rah pe chalana mujh ko.",
            englishTranslation = "A prayer that my wish may rise to my lips: O God, let my life shine like a candle. May darkness be lifted through my efforts and light spread wherever I shine. May I add beauty to my homeland as flowers beautify a garden. Let my life be devoted to goodness and let me love the light of knowledge. Let me support the poor and care for the suffering and the weak. O Allah, protect me from wrongdoing and guide me along the good path.",
            hindiTranslation = "मेरे होंठों पर मेरी तमन्ना बनकर यह दुआ आती है: ऐ खुदा, मेरी ज़िंदगी शमा की तरह रोशन हो। मेरी कोशिशों से दुनिया का अंधेरा दूर हो और हर जगह उजाला फैले। मैं अपने वतन की उसी तरह ज़ीनत बनूँ जैसे फूल चमन की शोभा बढ़ाते हैं। मेरी ज़िंदगी भलाई के लिए समर्पित हो और मुझे इल्म की रोशनी से मोहब्बत हो। मैं गरीबों का सहारा बनूँ और दुखी व कमज़ोर लोगों से मोहब्बत करूँ। ऐ अल्लाह, मुझे बुराई से बचा और नेक रास्ते पर चला।",
            urduTranslation = "لب پہ آتی ہے دعا بن کے تمنا میری، زندگی شمع کی صورت ہو خدایا میری۔ دور دنیا کا مرے دم سے اندھیرا ہو جائے، ہر جگہ میرے چمکنے سے اجالا ہو جائے۔ ہو مرے دم سے یونہی میرے وطن کی زینت، جس طرح پھول سے ہوتی ہے چمن کی زینت۔ زندگی ہو مری پروانے کی صورت یا رب، علم کی شمع سے ہو مجھ کو محبت یا رب۔ ہو مرا کام غریبوں کی حمایت کرنا، درد مندوں سے، ضعیفوں سے محبت کرنا۔ مرے اللہ برائی سے بچانا مجھ کو، نیک جو راہ ہو اس رہ پہ چلانا مجھ کو۔",
            reference = "Poem by Allama Muhammad Iqbal (not a Qur'anic verse or Hadith)",
            benefit = "A children's prayer expressing hope for knowledge, kindness, service, and a life guided by goodness.",
            recommendedCount = 1
        )
    )
}
