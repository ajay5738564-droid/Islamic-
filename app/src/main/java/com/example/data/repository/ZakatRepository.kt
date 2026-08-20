package com.example.data.repository

import com.example.data.model.ZakatCalculationResult
import com.example.data.model.ZakatInput

object ZakatRepository {

    // Standard Nisab thresholds (Gold: 85 grams, Silver: 595 grams)
    const val NISAB_GOLD_GRAMS = 85.0
    const val NISAB_SILVER_GRAMS = 595.0
    const val ZAKAT_RATE = 0.025 // 2.5%

    fun calculateZakat(input: ZakatInput): ZakatCalculationResult {
        val goldValue = input.goldGrams * input.goldRatePerGram
        val silverValue = input.silverGrams * input.silverRatePerGram

        val totalAssets = goldValue +
                silverValue +
                input.cashInHand +
                input.bankSavings +
                input.businessInventoryValue +
                input.sharesAndInvestments +
                input.moneyOwedToYou

        val totalLiabilities = input.immediateDebts + input.unpaidBillsAndExpenses

        val netEligibleWealth = (totalAssets - totalLiabilities).coerceAtLeast(0.0)

        val nisabValueGold = NISAB_GOLD_GRAMS * input.goldRatePerGram
        val nisabValueSilver = NISAB_SILVER_GRAMS * input.silverRatePerGram

        // Silver standard is traditionally more cautious/beneficial for the poor
        val activeNisabThreshold = if (nisabValueSilver > 0) nisabValueSilver else nisabValueGold
        val meetsNisab = netEligibleWealth >= activeNisabThreshold

        val zakatPayable = if (meetsNisab) netEligibleWealth * ZAKAT_RATE else 0.0

        return ZakatCalculationResult(
            totalWealth = totalAssets,
            totalDeductions = totalLiabilities,
            netZakatEligibleWealth = netEligibleWealth,
            nisabValueGold = nisabValueGold,
            nisabValueSilver = nisabValueSilver,
            meetsNisab = meetsNisab,
            zakatPayable = zakatPayable
        )
    }
}
