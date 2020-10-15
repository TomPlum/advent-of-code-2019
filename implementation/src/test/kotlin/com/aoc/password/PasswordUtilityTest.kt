package com.aoc.password

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.password.strategy.InitialValidation
import com.aoc.password.strategy.RevisedValidation
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PasswordUtilityTest {
    private val input = InputReader.read<String>(Day(4)).asSingleString()
    private val utility = PasswordUtility()

    @Nested
    inner class Solutions {
        @Test
        @DisplayName("Given the input from Day 4, when calculating the number of valid password combinations, then it should return 466")
        fun partOne() {
            assertThat(utility.calculatePossiblePasswordCombinations(input, InitialValidation())).isEqualTo(466)
        }

        @Test
        @DisplayName("Given the input from Day 4, when calculating the number of valid password combinations, then it should return 292")
        fun partTwo() {
            assertThat(utility.calculatePossiblePasswordCombinations(input, RevisedValidation())).isEqualTo(292)
        }
    }

    @Nested
    inner class HasAdjacentDigits {
        @ParameterizedTest
        @ValueSource(strings = ["112345", "098862", "123455", "111111", "001233"])
        @DisplayName("Given the input string has at least one pair of adjacent digits, when checking for it, then it should return true")
        fun hasAdjacentDigits(input: String) {
            val password = Password(input)
            val result = utility.hasAdjacentDigits(password.value)
            assertThat(result).isTrue()
        }

        @ParameterizedTest
        @ValueSource(strings = ["123456", "010101", "123789"])
        @DisplayName("Given the input string does not have a pair of adjacent digits, when checking for it, then it should return false")
        fun doesNotHaveAdjacentDigits(input: String) {
            val password = Password(input)
            val result = utility.hasAdjacentDigits(password.value)
            assertThat(result).isFalse()
        }
    }

    @Nested
    inner class HasAdjacentDigitPairs {
        @ParameterizedTest
        @ValueSource(strings = ["112345", "098862", "123455", "001233", "112233", "111122"])
        @DisplayName("Given the input string has exactly on pair of adjacent digits, when checking for it, then it should return true")
        fun hasAdjacentDigitPairs(input: String) {
            val password = Password(input)
            val result = utility.hasAdjacentDigitPair(password.value)
            assertThat(result).isTrue()
        }

        @ParameterizedTest
        @ValueSource(strings = ["123456", "010101", "123789", "123444"])
        @DisplayName("Given the input string does not have adjacent pairs , when checking for it, then it should return false")
        fun doesNotHaveAdjacentDigitPairs(input: String) {
            val password = Password(input)
            val result = utility.hasAdjacentDigitPair(password.value)
            assertThat(result).isFalse()
        }
    }

   @Nested
   inner class HasDescendingDigits {
       @ParameterizedTest
       @ValueSource(strings = ["223450", "987654", "123465", "123450"])
       @DisplayName("Given the input string has descending digits, when checking if it doesn't, then it should return false")
       fun hasDescendingDigits(input: String) {
           val password = Password(input)
           val result = utility.hasOnlyAscendingCharacters(password.value)
           assertThat(result).isFalse()
       }

       @ParameterizedTest
       @ValueSource(strings = ["111123", "135679", "013579", "111111"])
       @DisplayName("Given the input string has descending digits, when checking if it doesn't, then it should return false")
       fun doesNotHaveDescendingDigits(input: String) {
           val password = Password(input)
           val result = utility.hasOnlyAscendingCharacters(password.value)
           assertThat(result).isTrue()
       }
   }

}