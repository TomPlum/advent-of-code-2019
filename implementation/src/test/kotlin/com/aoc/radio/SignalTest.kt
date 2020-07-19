package com.aoc.radio

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SignalTest {
    @Test
    fun toStringTest() {
        assertThat(Signal(listOf(1,2,3,4,5)).toString()).isEqualTo("12345")
    }

    @Test
    fun getPatternBaseIndex() {
        assertThat(Signal(listOf(1,2,3)).getPattern(0)).isEqualTo(SignalPattern(listOf(0,1,0,-1)))
    }

    @Test
    fun getPatternSecondIndex() {
        assertThat(Signal(listOf(1,2,3)).getPattern(1)).isEqualTo(SignalPattern(listOf(0,0,1,1,0,0,-1,-1)))
    }

    @Test
    fun getPatternThirdIndex() {
        assertThat(Signal(listOf(1,2,3)).getPattern(2)).isEqualTo(SignalPattern(listOf(0,0,0,1,1,1,0,0,0,-1,-1,-1)))
    }

    @Test
    fun getPatternIndexOutOfBounds() {
        val e = assertThrows<IllegalArgumentException> { Signal(listOf(1, 2, 3)).getPattern(4) }
        assertThat(e.message).isEqualTo("Invalid Sequence Index (4) for Signal Length 3")
    }

    @Test
    fun getFirstEightValues() {
        assertThat(Signal(listOf(0,3,0,8,1,7,7,0,8,8)).getFirstNValues(8)).isEqualTo(Signal(listOf(0,3,0,8,1,7,7,0)))
    }

    @Test
    fun getFirstSevenValues() {
        assertThat(Signal(listOf(0,3,0,8,1,7,7,0,8,8)).getFirstNValues(7)).isEqualTo(Signal(listOf(0,3,0,8,1,7,7)))
    }

    @Test
    fun convertToRealSignal() {
        assertThat(Signal(listOf(1)).convertToRealSignal().sequence.size).isEqualTo(10000)
    }

    @Test
    fun length() {
        assertThat(Signal(listOf(1,2,3,4,5,6)).length()).isEqualTo(6)
    }

    @Test
    fun getSecondHalf() {
        assertThat(Signal(listOf(1,2,3,4,5,6,7,8)).getSecondHalf()).isEqualTo(listOf(5,6,7,8))
    }

    @Test
    fun getMessageOffset() {
        assertThat(Signal(listOf(0,3,0,3,6,7,3,2,5,7,7,2,1,2,9,4,4,0,6,3,4,9)).getMessageOffset()).isEqualTo(303673)
    }

    @Test
    fun getMessage() {
        assertThat(Signal(listOf(0,0,0,0,0,0,9,5,6,1,7,4,2,3,5,4,1)).getMessage()).isEqualTo(Signal(listOf(1,7,4,2,3,5,4,1)))
    }
}