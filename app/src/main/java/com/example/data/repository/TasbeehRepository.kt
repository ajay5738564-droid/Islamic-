package com.example.data.repository

import com.example.data.model.TasbeehPreset

object TasbeehRepository {

    val PRESET_ZIKRS = listOf(
        TasbeehPreset(
            id = "subhanallah",
            arabicText = "سُبْحَانَ اللَّهِ",
            transliteration = "SubhanAllah",
            englishMeaning = "Glory be to Allah",
            hindiMeaning = "अल्लाह हर ऐब और नुक़्स से पाक है",
            defaultTarget = 33
        ),
        TasbeehPreset(
            id = "alhamdulillah",
            arabicText = "الْحَمْدُ لِلَّهِ",
            transliteration = "Alhamdulillah",
            englishMeaning = "All praise is due to Allah",
            hindiMeaning = "तमाम तारीफें अल्लाह ही के लिए हैं",
            defaultTarget = 33
        ),
        TasbeehPreset(
            id = "allahuakbar",
            arabicText = "اللَّهُ أَكْبَرُ",
            transliteration = "Allahu Akbar",
            englishMeaning = "Allah is the Greatest",
            hindiMeaning = "अल्लाह सबसे बड़ा और अज़ीम है",
            defaultTarget = 34
        ),
        TasbeehPreset(
            id = "astaghfirullah",
            arabicText = "أَسْتَغْفِرُ اللَّهَ",
            transliteration = "Astaghfirullah",
            englishMeaning = "I seek forgiveness from Allah",
            hindiMeaning = "मैं अल्लाह से मग़फ़िरत (माफ़ी) मांगता हूँ",
            defaultTarget = 100
        ),
        TasbeehPreset(
            id = "lailahaillallah",
            arabicText = "لَا إِلَٰهَ إِلَّا اللَّهُ",
            transliteration = "La ilaha illallah",
            englishMeaning = "There is no deity worthy of worship except Allah",
            hindiMeaning = "अल्लाह के सिवा कोई सच्चा माबूद नहीं",
            defaultTarget = 100
        ),
        TasbeehPreset(
            id = "salawat",
            arabicText = "اللَّهُمَّ صَلِّ عَلَىٰ مُحَمَّدٍ وَعَلَىٰ آلِ مُحَمَّدٍ",
            transliteration = "Allahumma Salli 'ala Muhammad wa 'ala Aali Muhammad",
            englishMeaning = "O Allah, send blessings upon Muhammad and the family of Muhammad",
            hindiMeaning = "ऐ अल्लाह! हज़रत मुहम्मद (सल्ल.) और आपकी आल पर दरूद-ओ-रहमत नाज़िल फरमा",
            defaultTarget = 100
        ),
        TasbeehPreset(
            id = "hawqala",
            arabicText = "لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ",
            transliteration = "La hawla wa la quwwata illa billah",
            englishMeaning = "There is no power and no strength except with Allah",
            hindiMeaning = "गुनाह से बचने और नेकी करने की ताक़त सिर्फ अल्लाह की मदद से है",
            defaultTarget = 33
        ),
        TasbeehPreset(
            id = "subhanallahi_wabihamdihi",
            arabicText = "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ ، سُبْحَانَ اللَّهِ الْعَظِيمِ",
            transliteration = "SubhanAllahi wa bihamdihi, SubhanAllahil 'Azeem",
            englishMeaning = "Glory be to Allah and His is the praise; Glory be to Allah, the Supreme",
            hindiMeaning = "अल्लाह अपनी तारीफों समेत पाक है, अज़मत वाला अल्लाह पाक है",
            defaultTarget = 100
        )
    )
}
