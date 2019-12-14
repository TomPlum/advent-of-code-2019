package com.aoc.common

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.value.Day
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class InputReaderTest {
    private val inputReader = InputReader()

    @Test
    @DisplayName("Given a valid input text file, when reading input as string, then it should return a list of strings")
    internal fun readAsString() {
        val input = inputReader.readInputString(Day.from(1))
        assertThat(input.values).isEqualTo(arrayListOf("1", "2", "3", "4", "5"))
    }

    @Test
    @DisplayName("Given a valid input text file, when reading input as int, then it should return a list of integers")
    internal fun readAsInt() {
        val input = inputReader.readInputInteger(Day.from(1))
        assertThat(input.values).isEqualTo(arrayListOf(1, 2, 3, 4, 5))
    }

    @Test
    @DisplayName("Given a valid input text file, when reading input as int, then it should return a list of integers")
    internal fun readAsDouble() {
        val input = inputReader.readInputDouble(Day.from(1))
        assertThat(input.values).isEqualTo(arrayListOf(1.0, 2.0, 3.0, 4.0, 5.0))
    }

    @Test
    @DisplayName("Given a valid input text file, when reading input as a single string, then it should return a string of all values")
    internal fun readAsSingleString() {
        val input = inputReader.readInputAsSingleString(Day.from(1))
        assertThat(input).isEqualTo("1")
    }
}