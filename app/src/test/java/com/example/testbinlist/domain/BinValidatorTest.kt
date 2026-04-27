package com.example.testbinlist.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BinValidatorTest {

    @Test
    fun `valid Visa BIN returns Success`() {
        // Visa starts with 4, valid Luhn: 4532015112830366
        val result = BinValidator.validate("45320151")

        assertTrue(result is BinValidator.ValidationResult.Success)
        val success = result as BinValidator.ValidationResult.Success
        assertEquals("Visa", success.paymentSystem)
    }

    @Test
    fun `valid Mastercard BIN returns Success`() {
        // Mastercard starts with 5, valid Luhn: 5425233430109903
        val result = BinValidator.validate("54252334")

        assertTrue(result is BinValidator.ValidationResult.Success)
        val success = result as BinValidator.ValidationResult.Success
        assertEquals("Mastercard", success.paymentSystem)
    }

    @Test
    fun `too short BIN returns Error`() {
        val result = BinValidator.validate("12345")

        assertTrue(result is BinValidator.ValidationResult.Error)
        val error = result as BinValidator.ValidationResult.Error
        assertTrue(error.message.contains("6 до 8"))
    }

    @Test
    fun `too long BIN returns Error`() {
        val result = BinValidator.validate("123456789")

        assertTrue(result is BinValidator.ValidationResult.Error)
    }

    @Test
    fun `invalid Luhn BIN returns Error`() {
        // Starts with 4 (Visa prefix) but invalid Luhn
        val result = BinValidator.validate("41111112")

        assertTrue(result is BinValidator.ValidationResult.Error)
        val error = result as BinValidator.ValidationResult.Error
        assertTrue(error.message.contains("Луна"))
    }

    @Test
    fun `MIR card detected correctly`() {
        // MIR starts with 2200-2204
        val result = BinValidator.validate("22000001")

        assertTrue(result is BinValidator.ValidationResult.Success)
        val success = result as BinValidator.ValidationResult.Success
        assertEquals("MIR", success.paymentSystem)
    }

    @Test
    fun `unknown payment system detected`() {
        // Starts with 99 (not in any known system), but valid Luhn
        val result = BinValidator.validate("99999999")

        assertTrue(result is BinValidator.ValidationResult.Success)
        val success = result as BinValidator.ValidationResult.Success
        assertEquals("Unknown", success.paymentSystem)
    }

    @Test
    fun `non-digit characters are filtered`() {
        val result = BinValidator.validate("4532-0151")

        assertTrue(result is BinValidator.ValidationResult.Success)
        val success = result as BinValidator.ValidationResult.Success
        assertEquals("45320151", success.bin)
    }
}
