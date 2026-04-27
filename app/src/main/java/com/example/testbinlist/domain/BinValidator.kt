package com.example.testbinlist.domain

object BinValidator {

    fun validate(bin: String): ValidationResult {
        val cleanBin = bin.filter { it.isDigit() }

        if (cleanBin.length < 6 || cleanBin.length > 8) {
            return ValidationResult.Error(
                "BIN должен содержать от 6 до 8 цифр"
            )
        }

        if (!isValidLuhn(cleanBin)) {
            return ValidationResult.Error(
                "Невалидный номер карты (проверка по алгоритму Луна)"
            )
        }

        val paymentSystem = detectPaymentSystem(cleanBin)

        return ValidationResult.Success(
            bin = cleanBin,
            paymentSystem = paymentSystem
        )
    }

    /**
     * Алгоритм Луна (Luhn algorithm)
     * Проверяет контрольную сумму номера карты
     */
    private fun isValidLuhn(number: String): Boolean {
        var sum = 0
        var alternate = false

        for (i in number.length - 1 downTo 0) {
            var n = number[i].digitToInt()
            if (alternate) {
                n *= 2
                if (n > 9) n -= 9
            }
            sum += n
            alternate = !alternate
        }

        return sum % 10 == 0
    }

    /**
     * Определяет платёжную систему по первым цифрам BIN
     */
    private fun detectPaymentSystem(bin: String): String {
        return when {
            bin.startsWith("4") -> "Visa"
            bin.startsWith("5") -> "Mastercard"
            bin.startsWith("34") || bin.startsWith("37") -> "American Express"
            bin.startsWith("62") || bin.startsWith("88") -> "UnionPay"
            bin.startsWith("35") -> "JCB"
            bin.startsWith("60") -> "RuPay"
            bin.startsWith("2200") || bin.startsWith("2201") || bin.startsWith("2202") || bin.startsWith("2203") || bin.startsWith("2204") -> "MIR"
            else -> "Unknown"
        }
    }

    sealed class ValidationResult {
        data class Success(
            val bin: String,
            val paymentSystem: String
        ) : ValidationResult()

        data class Error(
            val message: String
        ) : ValidationResult()
    }
}
