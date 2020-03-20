package com.aoc.radio

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

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
        assertThat(Signal(listOf(0,3,0,8,1,7,7,0,8,8)).getFirstEightValues()).isEqualTo(Signal(listOf(0,3,0,8,1,7,7,0)))
    }
}