package com.example.data.model

data class ZakatInput(
    val goldGrams: Double = 0.0,
    val goldRatePerGram: Double = 75.0, // in selected currency (USD / INR / EUR)
    val silverGrams: Double = 0.0,
    val silverRatePerGram: Double = 0.95,
    val cashInHand: Double = 0.0,
    val bankSavings: Double = 0.0,
    val businessInventoryValue: Double = 0.0,
    val sharesAndInvestments: Double = 0.0,
    val moneyOwedToYou: Double = 0.0,
    val immediateDebts: Double = 0.0,
    val unpaidBillsAndExpenses: Double = 0.0,
    val currencySymbol: String = "$"
)

data class ZakatCalculationResult(
    val totalWealth: Double,
    val totalDeductions: Double,
    val netZakatEligibleWealth: Double,
    val nisabValueGold: Double, // 85 grams of gold
    val nisabValueSilver: Double, // 595 grams of silver
    val meetsNisab: Boolean,
    val zakatPayable: Double
)
