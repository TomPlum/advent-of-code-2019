package com.aoc.intcode.computer

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotEqualTo
import assertk.assertions.isTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SystemOutputTest {
    @Test
    fun addTest() {
        val systemOutput = SystemOutput()
        systemOutput.add(172)
        assertThat(systemOutput.getLastValue()).isEqualTo(172)
    }

    @Test
    @DisplayName("Given a system out with two output values, when getting the last two output values, then it" +
    "should return a pair with the second to last and last values in the first and second position of a Pair respectively")
    fun getLastTwoOutputValues() {
        val systemOutput = SystemOutput()
        systemOutput.add(5L)
        systemOutput.add(12L)
        assertThat(systemOutput.getLastTwoValues()).isEqualTo(Pair(5L, 12L))
    }

    @Test
    @DisplayName("Given a system out with two output values, when getting the last two output values, then it" +
    "should consume the values and leave the system output empty")
    fun getLastTwoOutputValuesShouldConsumeSystemOutput() {
        val systemOutput = SystemOutput()
        systemOutput.add(2)
        systemOutput.add(6)
        systemOutput.getLastTwoValues()
        assertThat(systemOutput.isEmpty()).isTrue()
    }

    @Test
    @DisplayName("Given an empty System Output, when getting the last two values, then it should throw an IllegalStateException")
    fun getLastTwoValuesWhenEmpty() {
        val e = assertThrows<IllegalStateException> { SystemOutput().getLastTwoValues() }
        assertThat(e.message).isEqualTo("System output must have at least two values")
    }

    @Test
    @DisplayName("Given a system output with three values, when getting the first three output values, then it should" +
    "return a triple first, second and third values in the Triple respectively.")
    fun getFirstThreeOutputValues() {
        val systemOutput = SystemOutput()
        systemOutput.add(5)
        systemOutput.add(12)
        systemOutput.add(56)
        assertThat(systemOutput.getFirstThreeValues()).isEqualTo(Triple(5L, 12L, 56L))
    }

    @Test
    @DisplayName("Given a system output with four values, when getting the first three output values, then it should" +
    "return a triple first, second and third values in the Triple respectively.")
    fun getFirstThreeOutputValuesWhenOutputHasMoreThanThree() {
        val systemOutput = SystemOutput()
        systemOutput.add(5)
        systemOutput.add(12)
        systemOutput.add(56)
        systemOutput.add(75)
        assertThat(systemOutput.getFirstThreeValues()).isEqualTo(Triple(5L, 12L, 56L))
    }

    @Test
    @DisplayName("Given a system out with three output values, when getting the first three output values, then it" +
    "should consume the values and leave the system output empty")
    fun getFirstThreeOutputValuesShouldConsumeSystemOutput() {
        val systemOutput = SystemOutput()
        systemOutput.add(2)
        systemOutput.add(6)
        systemOutput.add(56)
        systemOutput.getFirstThreeValues()
        assertThat(systemOutput.isEmpty()).isTrue()
    }

    @Test
    @DisplayName("Given an empty System Output, when getting the first three values, then it should throw an IllegalStateException")
    fun getFirstThreeValuesWhenEmpty() {
        val e = assertThrows<IllegalStateException> { SystemOutput().getFirstThreeValues() }
        assertThat(e.message).isEqualTo("System output must have at least three values")
    }

    @Test
    @DisplayName("Given a system out with a single value, when getting the last value, it should return the only value")
    fun getLastValueSingleValue() {
        val systemOutput = SystemOutput()
        systemOutput.add(1)
        assertThat(systemOutput.getLastValue()).isEqualTo(1)
    }

    @Test
    @DisplayName("Given a system out with multiple values, when getting the last value, it should return the last value")
    fun getLastValueMultipleValues() {
        val systemOutput = SystemOutput()
        systemOutput.add(1)
        systemOutput.add(7)
        assertThat(systemOutput.getLastValue()).isEqualTo(7)
    }

    @Test
    @DisplayName("Given a system out with no values, when getting the last value, then it should throw an IllegalStateException")
    fun getLastValueWhenSysOutEmpty() {
        val e = assertThrows<IllegalStateException> { SystemOutput().getLastValue() }
        assertThat(e.message).isEqualTo("System output is empty!")
    }

    @Test
    @DisplayName("Given a system out with one output value, when getting the last output value, then it" +
            "should consume the value and leave the system output empty")
    fun getLastValueShouldConsumeSystemOutput() {
        val systemOutput = SystemOutput()
        systemOutput.add(6)
        systemOutput.getLastValue()
        assertThat(systemOutput.isEmpty()).isTrue()
    }

    @Test
    @DisplayName("Given a SystemOutput with values, when getting values, then it should consume and return them all")
    fun getAllValuesWhenSystemOutHasValues() {
        val systemOutput = SystemOutput()
        systemOutput.add(15L)
        systemOutput.add(1L)
        assertThat(systemOutput.getValues()).isEqualTo(listOf<Long>(15,1))
        assertThat(systemOutput.isEmpty())
    }

    @Test
    fun getAllValuesWhenSystemOutputIsEmpty() {
        val e = assertThrows<IllegalStateException> { SystemOutput().getValues() }
        assertThat(e.message).isEqualTo("System Output is empty!")
    }

    @Test
    fun isEmptyPositive() {
        assertThat(SystemOutput().isEmpty()).isTrue()
    }

    @Test
    fun isEmptyNegative() {
        val systemOutput = SystemOutput()
        systemOutput.add(12L)
        assertThat(systemOutput.isEmpty()).isFalse()
    }

    @Test
    fun isNotEmptyPositive() {
        val systemOutput = SystemOutput()
        systemOutput.add(12L)
        assertThat(systemOutput.isNotEmpty()).isTrue()
    }

    @Test
    fun isNotEmptyNegative() {
        assertThat(SystemOutput().isNotEmpty()).isFalse()
    }

    @Test
    fun parseAsciiToString() {
        val systemOutput = SystemOutput()
        systemOutput.add(77)
        systemOutput.add(97)
        systemOutput.add(105)
        systemOutput.add(110)
        systemOutput.add(58)
        systemOutput.add(10)
        assertThat(systemOutput.parseStringFromAscii()).isEqualTo("Main:\n")
    }

    @Test
    @DisplayName("Given two System Output, when they have the same internal values, then they should be equal")
    fun equalityTestPositive() {
        val first = SystemOutput()
        first.add(12)
        val second = SystemOutput()
        second.add(12)
        assertThat(first).isEqualTo(second)
    }

    @Test
    @DisplayName("Given two System Output, when they have different internal values, then they should not be equal")
    fun equalityTestNegative() {
        val first = SystemOutput()
        first.add(12)
        val second = SystemOutput()
        second.add(5)
        assertThat(first).isNotEqualTo(second)
    }

    @Test
    fun toStringTest() {
        val systemOutput = SystemOutput()
        systemOutput.add(7)
        systemOutput.add(4)
        systemOutput.add(1)
        assertThat(systemOutput.toString()).isEqualTo("[7, 4, 1]")
    }
}