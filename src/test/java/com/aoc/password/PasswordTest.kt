package com.aoc.password

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class PasswordTest {
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["223450", "123789", "12345", "1234567"])
    @DisplayName("Given an invalid input, when checking if it meets the password criteria, then it should return false")
    fun doesNotMeetCriteria(input: String) {
        val password = Password(input)
        val meetsCriteria = password.isValid()
        assertThat(meetsCriteria).isFalse()
    }

    @ParameterizedTest
    @ValueSource(strings = ["112345", "098862", "123455", "001233", "112233", "111122"])
    @DisplayName("Given the input string has exactly on pair of adjacent digits, when checking for it, then it should return true")
    fun hasAdjacentDigitPairs(input: String) {
        val password = Password(input)
        val result = password.hasAdjacentDigitPair()
        assertThat(result).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["123456", "010101", "123789", "123444"])
    @DisplayName("Given the input string does not have adjacent pairs , when checking for it, then it should return false")
    fun doesNotHaveAdjacentDigitPairs(input: String) {
        val password = Password(input)
        val result = password.hasAdjacentDigitPair()
        assertThat(result).isFalse()
    }

    @ParameterizedTest
    @ValueSource(strings = ["223450", "987654", "123465", "123450"])
    @DisplayName("Given the input string has descending digits, when checking if it doesn't, then it should return false")
    fun hasDescendingDigits(input: String) {
        val password = Password(input)
        val result = password.hasOnlyAscendingCharacters()
        assertThat(result).isFalse()
    }

    @ParameterizedTest
    @ValueSource(strings = ["111123", "135679", "013579", "111111"])
    @DisplayName("Given the input string has descending digits, when checking if it doesn't, then it should return false")
    fun doesNotHaveDescendingDigits(input: String) {
        val password = Password(input)
        val result = password.hasOnlyAscendingCharacters()
        assertThat(result).isTrue()
    }
}