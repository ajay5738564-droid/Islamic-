package com.example.data.repository

import com.example.data.model.HadithItem

object HadithRepository {

    val ALL_HADITHS = listOf(
        HadithItem(
            id = "hadith_1",
            collectionName = "40 Hadith Nawawi",
            hadithNumber = "Hadith 1",
            topicEnglish = "Actions Depend on Intentions",
            topicHindi = "आमाल का दारोमदार नियत पर है",
            narrator = "Umar ibn al-Khattab (RA)",
            arabicText = "إِنَّمَا الأَعْمَالُ بِالنِّيَّاتِ، وَإِنَّمَا لِكُلِّ امْرِئٍ مَا نَوَى، فَمَنْ كَانَتْ هِجْرَتُهُ إِلَى اللَّهِ وَرَسُولِهِ فَهِجْرَتُهُ إِلَى اللَّهِ وَرَسُولِهِ",
            englishTranslation = "Actions are according to intentions, and everyone will get what was intended. Whoever migrated to Allah and His Messenger, his migration is to Allah and His Messenger.",
            hindiTranslation = "तमाम आमाल (कर्मों) का दारोमदार नियतों पर है, और हर शख्स को वही मिलेगा जिसकी उसने नियत की। पस जिसकी हिजरत अल्लाह और उसके रसूल के लिए हो, उसकी हिजरत अल्लाह और उसके रसूल ही के लिए मानी जाएगी।",
            urduTranslation = "اعمال کا دارومدار نیتوں پر ہے، اور ہر انسان کے لیے وہی ہے جس کی اس نے نیت کی۔ پس جس کی ہجرت اللہ اور اس کے رسول کے لیے ہو، اس کی ہجرت اللہ اور اس کے رسول ہی کی طرف ہے۔",
            grade = "Sahih (Muttafaq 'Alayh)",
            reference = "Sahih al-Bukhari (1), Sahih Muslim (1907)",
            explanation = "This fundamental Hadith serves as the foundation of Islamic jurisprudence and spiritual devotion, emphasizing purity of heart and purpose."
        ),
        HadithItem(
            id = "hadith_2",
            collectionName = "40 Hadith Nawawi",
            hadithNumber = "Hadith 2",
            topicEnglish = "Islam, Iman, and Ihsan (Hadith Jibril)",
            topicHindi = "हदीस-ए-जिबरील: इस्लाम, ईमान और एहसान",
            narrator = "Umar ibn al-Khattab (RA)",
            arabicText = "أَنْ تَعْبُدَ اللَّهَ كَأَنَّكَ تَرَاهُ، فَإِنْ لَمْ تَكُنْ تَرَاهُ فَإِنَّهُ يَرَاكَ",
            englishTranslation = "[Ihsan is] that you worship Allah as if you see Him, for even if you do not see Him, indeed He sees you.",
            hindiTranslation = "[एहसान यह है कि] तुम अल्लाह की इबादत इस तरह करो गोया तुम उसे देख रहे हो, और अगर तुम उसे नहीं देख रहे तो यक़ीनन वो तुम्हें देख रहा है।",
            urduTranslation = "احسان یہ ہے کہ تم اللہ کی عبادت اس طرح کرو گویا تم اسے دیکھ رہے ہو، پس اگر تم اسے نہیں دیکھ رہے تو وہ یقیناً تمہیں دیکھ رہا ہے۔",
            grade = "Sahih",
            reference = "Sahih Muslim (8)",
            explanation = "Defines the highest level of spiritual consciousness (Ihsan), being mindful of Allah's watchful presence at all times."
        ),
        HadithItem(
            id = "hadith_3",
            collectionName = "Sahih al-Bukhari",
            hadithNumber = "Book 2, Hadith 6",
            topicEnglish = "The True Muslim & Safety of Others",
            topicHindi = "सच्चा मुसलमान कौन है",
            narrator = "Abdullah ibn Amr (RA)",
            arabicText = "الْمُسْلِمُ مَنْ سَلِمَ الْمُسْلِمُونَ مِنْ لِسَانِهِ وَيَدِهِ",
            englishTranslation = "A true Muslim is the one from whose tongue and hands the Muslims are safe.",
            hindiTranslation = "मुसलमान वो है जिसकी ज़बान और हाथ (की तकलीफ़) से दूसरे मुसलमान महफ़ूज़ (सुरक्षित) रहें।",
            urduTranslation = "مسلمان وہ ہے جس کی زبان اور ہاتھ سے دوسرے مسلمان محفوظ رہیں۔",
            grade = "Sahih",
            reference = "Sahih al-Bukhari (10), Sahih Muslim (40)",
            explanation = "Commands kindness, ethical character, avoidance of backbiting, and absolute non-harm to others."
        ),
        HadithItem(
            id = "hadith_4",
            collectionName = "40 Hadith Nawawi",
            hadithNumber = "Hadith 13",
            topicEnglish = "Brotherhood and Love for Others",
            topicHindi = "दूसरों के लिए वही चाहना जो अपने लिए चाहते हो",
            narrator = "Anas ibn Malik (RA)",
            arabicText = "لَا يُؤْمِنُ أَحَدُكُمْ حَتَّىٰ يُحِبَّ لِأَخِيهِ مَا يُحِبُّ لِنَفْسِهِ",
            englishTranslation = "None of you truly believes until he loves for his brother what he loves for himself.",
            hindiTranslation = "तुम में से कोई शख्स उस वक्त तक कामिल (पूर्ण) मोमिन नहीं हो सकता जब तक कि वो अपने भाई के लिए वही पसंद न करे जो अपने लिए पसंद करता है।",
            urduTranslation = "تم میں سے کوئی شخص اس وقت تک سچا مومن نہیں ہو سکتا جب تک وہ اپنے بھائی کے لیے وہی پسند نہ کرے جو اپنے لیے پسند کرتا ہے۔",
            grade = "Sahih",
            reference = "Sahih al-Bukhari (13), Sahih Muslim (45)",
            explanation = "Sets the golden rule of universal empathy, altruism, and heartfelt care in human relationships."
        ),
        HadithItem(
            id = "hadith_5",
            collectionName = "Sahih Muslim",
            hadithNumber = "Book 45, Hadith 87",
            topicEnglish = "Kindness & Gentleness in All Things",
            topicHindi = "हर काम में नरमी और भलाई",
            narrator = "Aisha (RA)",
            arabicText = "إِنَّ الرِّفْقَ لَا يَكُونُ فِي شَيْءٍ إِلَّا زَانَهُ، وَلَا يُنْزَعُ مِنْ شَيْءٍ إِلَّا شَانَهُ",
            englishTranslation = "Indeed, gentleness is not in anything except that it beautifies it, and it is not removed from anything except that it disgraces it.",
            hindiTranslation = "बेशक नरमी जिस चीज़ में भी होती है उसे खूबसूरत बना देती है, और जिस चीज़ से भी खींच ली जाती है उसे बदरंग और ऐबदार कर देती है।",
            urduTranslation = "بے شک نرمی جس چیز میں بھی ہوتی ہے اسے خوبصورت بنا دیتی ہے اور جس چیز سے نکال لی جائے اسے عیب دار بنا دیتی ہے۔",
            grade = "Sahih",
            reference = "Sahih Muslim (2594)",
            explanation = "Encourages soft-spoken communication, patience, forgiveness, and gentle interactions."
        ),
        HadithItem(
            id = "hadith_6",
            collectionName = "40 Hadith Nawawi",
            hadithNumber = "Hadith 18",
            topicEnglish = "Good Character & Repelling Evil with Good",
            topicHindi = "तक़्वा (ईश्वर-भय) और अच्छे अख़लाक़",
            narrator = "Abu Dharr al-Ghifari (RA)",
            arabicText = "اتَّقِ اللَّهَ حَيْثُمَا كُنْتَ، وَأَتْبِعِ السَّيِّئَةَ الْحَسَنَةَ تَمْحُهَا، وَخَالِقِ النَّاسَ بِخُلُقٍ حَسَنٍ",
            englishTranslation = "Fear Allah wherever you may be, follow up a bad deed with a good deed which will wipe it out, and behave well towards the people with noble character.",
            hindiTranslation = "तुम जहाँ कहीं भी रहो अल्लाह से डरो (तक़्वा रखो), और गुनाह के बाद कोई नेकी कर लो वो उस गुनाह को मिटा देगी, और लोगों के साथ अच्छे अख़लाक़ से पेश आओ।",
            urduTranslation = "تم جہاں بھی رہو اللہ سے ڈرو، اور برائی کے بعد نیکی کرو جو اسے مٹا دے گی اور لوگوں کے ساتھ اچھے اخلاق سے پیش آؤ۔",
            grade = "Hasan",
            reference = "Jami' at-Tirmidhi (1987)",
            explanation = "A comprehensive roadmap for conscious living: accountability before God, self-rehabilitation after mistakes, and noble ethics with society."
        )
    )
}
