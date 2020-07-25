package com.aoc.shuffle.parser

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.aoc.shuffle.TestShuffleInstructionParser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ShuffleInstructionParserTest {
    private val parser = TestShuffleInstructionParser()

    @Test
    fun isDealWithIncrement() {
        assertThat(parser.isIncrement("deal with increment 5")).isTrue()
    }

    @Test
    fun isCut() {
        assertThat(parser.isCut("cut 5")).isTrue()
    }

    @Test
    fun isNewStack() {
        assertThat(parser.isNewStack("deal into new stack")).isTrue()
    }

    @Test
    fun getParameter() {
        assertThat(parser.getParam("cut 26")).isEqualTo("26")
    }

    @Test
    fun handleInvalidInstruction() {
        val e = assertThrows<IllegalArgumentException> { parser.handleInvalidInstruction("blah 5") }
        assertThat(e.message).isEqualTo("Invalid instruction: blah 5")
    }
}