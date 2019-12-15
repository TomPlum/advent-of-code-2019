package com.aoc.intcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ProgramTest {
    @Test
    @DisplayName("Given a valid set of program action, when invoking toString(), then it should concatenate actions into CSV")
    internal fun toStringTest() {
        val program = Program.from("1,2,3,4,5")
        val programString = program.toString()
        assertThat(programString).isEqualTo("1,2,3,4,5")
    }

    @Test
    @DisplayName("Given a valid index, when updating the value at that index, then it should set the new value correctly")
    internal fun updateValueAtPosition() {
        val program = Program.from("1,2,3,4,5")
        program.updateAction(0, 12)
        assertThat(program.getAction(0)).isEqualTo(12)
    }

    @Test
    @DisplayName("Given a valid set of actions, when getting an action via index, it should return the correct action")
    internal fun getAction() {
        val program = Program.from("1,2,3,4,5")
        val action = program.getAction(3)
        assertThat(action).isEqualTo(4)
    }
}