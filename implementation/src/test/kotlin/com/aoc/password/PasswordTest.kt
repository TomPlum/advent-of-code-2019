package com.aoc.password

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.password.strategy.RevisedValidation
import org.junit.jupiter.api.DisplayName
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
        val meetsCriteria = password.isValid(RevisedValidation())
        assertThat(meetsCriteria).isFalse()
    }
}