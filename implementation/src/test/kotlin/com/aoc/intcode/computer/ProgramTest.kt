package com.aoc.intcode.computer

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProgramTest {
    @Test
    @DisplayName("Given a valid set of program action, when invoking toString(), then it should concatenate actions into CSV")
    fun toStringTest() {
        val program = Program("1,2,3,4,5")
        val programString = program.toString()
        assertThat(programString).isEqualTo("1,2,3,4,5")
    }
}