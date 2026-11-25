package com.example.data.repository

data class BabyName(val name: String, val meaningHindi: String, val gender: BabyNameGender)
enum class BabyNameGender { BOY, GIRL }

object BabyNamesRepository {
    // 25 distinct standalone boys' names with commonly used meanings.
    val boys = listOf(
        BabyName("Ayaan", "ईश्वर का उपहार", BabyNameGender.BOY),
        BabyName("Rayyan", "जन्नत के एक दरवाज़े का नाम", BabyNameGender.BOY),
        BabyName("Zaid", "वृद्धि, बढ़ोतरी", BabyNameGender.BOY),
        BabyName("Hamza", "साहसी, दृढ़", BabyNameGender.BOY),
        BabyName("Bilal", "प्रसिद्ध सहाबी का नाम", BabyNameGender.BOY),
        BabyName("Yusuf", "पैगंबर का नाम", BabyNameGender.BOY),
        BabyName("Ibrahim", "पैगंबर का नाम", BabyNameGender.BOY),
        BabyName("Ismail", "पैगंबर का नाम", BabyNameGender.BOY),
        BabyName("Musa", "पैगंबर का नाम", BabyNameGender.BOY),
        BabyName("Idris", "पैगंबर का नाम", BabyNameGender.BOY),
        BabyName("Ilyas", "पैगंबर का नाम", BabyNameGender.BOY),
        BabyName("Adam", "पहले पैगंबर का नाम", BabyNameGender.BOY),
        BabyName("Nuh", "पैगंबर का नाम", BabyNameGender.BOY),
        BabyName("Dawud", "पैगंबर का नाम", BabyNameGender.BOY),
        BabyName("Sulaiman", "पैगंबर का नाम", BabyNameGender.BOY),
        BabyName("Amin", "विश्वसनीय, अमानतदार", BabyNameGender.BOY),
        BabyName("Anas", "मित्रता, अपनापन", BabyNameGender.BOY),
        BabyName("Saad", "खुशी, सौभाग्य", BabyNameGender.BOY),
        BabyName("Hasan", "अच्छा, सुंदर", BabyNameGender.BOY),
        BabyName("Husain", "सुंदर, अच्छा", BabyNameGender.BOY),
        BabyName("Omar", "लंबी आयु से जुड़ा नाम", BabyNameGender.BOY),
        BabyName("Ali", "ऊँचा, श्रेष्ठ", BabyNameGender.BOY),
        BabyName("Adeel", "न्यायप्रिय", BabyNameGender.BOY),
        BabyName("Faizan", "कृपा, उदारता", BabyNameGender.BOY),
        BabyName("Danish", "ज्ञान, बुद्धिमत्ता", BabyNameGender.BOY)
    )

    // 25 distinct standalone girls' names with commonly used meanings.
    val girls = listOf(
        BabyName("Ayesha", "जीवंत, खुशहाल", BabyNameGender.GIRL),
        BabyName("Fatima", "प्रसिद्ध इस्लामी ऐतिहासिक नाम", BabyNameGender.GIRL),
        BabyName("Maryam", "पवित्रता से जुड़ा नाम", BabyNameGender.GIRL),
        BabyName("Amina", "विश्वसनीय, अमानतदार", BabyNameGender.GIRL),
        BabyName("Khadija", "प्रसिद्ध इस्लामी ऐतिहासिक नाम", BabyNameGender.GIRL),
        BabyName("Zainab", "खुशबूदार पेड़ का नाम", BabyNameGender.GIRL),
        BabyName("Hafsa", "प्रसिद्ध सहाबिया का नाम", BabyNameGender.GIRL),
        BabyName("Safiya", "शुद्ध, सच्ची मित्र", BabyNameGender.GIRL),
        BabyName("Ruqayya", "उन्नति, बुलंदी", BabyNameGender.GIRL),
        BabyName("Sumayya", "ऊँचे दर्जे वाली", BabyNameGender.GIRL),
        BabyName("Noor", "रोशनी, प्रकाश", BabyNameGender.GIRL),
        BabyName("Hiba", "उपहार, भेंट", BabyNameGender.GIRL),
        BabyName("Inaya", "देखभाल, कृपा", BabyNameGender.GIRL),
        BabyName("Iman", "आस्था, विश्वास", BabyNameGender.GIRL),
        BabyName("Dua", "प्रार्थना, दुआ", BabyNameGender.GIRL),
        BabyName("Sana", "प्रशंसा", BabyNameGender.GIRL),
        BabyName("Yasmin", "चमेली का फूल", BabyNameGender.GIRL),
        BabyName("Rida", "संतोष, प्रसन्नता", BabyNameGender.GIRL),
        BabyName("Fariha", "खुश, प्रसन्न", BabyNameGender.GIRL),
        BabyName("Areeba", "समझदार, बुद्धिमान", BabyNameGender.GIRL),
        BabyName("Mahira", "कुशल, निपुण", BabyNameGender.GIRL),
        BabyName("Amira", "नेतृत्व करने वाली, राजकुमारी", BabyNameGender.GIRL),
        BabyName("Saira", "यात्रा करने वाली", BabyNameGender.GIRL),
        BabyName("Muneera", "प्रकाशमान", BabyNameGender.GIRL),
        BabyName("Nawal", "उपहार, नेमत", BabyNameGender.GIRL)
    )
}