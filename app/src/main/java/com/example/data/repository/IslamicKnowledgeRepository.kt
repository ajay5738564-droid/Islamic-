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
}
