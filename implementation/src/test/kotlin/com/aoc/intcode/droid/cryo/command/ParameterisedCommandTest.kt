package com.aoc.intcode.droid.cryo.command

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.droid.cryo.command.TestParameterisedCommand
import org.junit.jupiter.api.Test

class ParameterisedCommandTest {
    @Test
    fun shouldAddSpaceBetweenInstructionAndItem() {
        assertThat(TestParameterisedCommand("item").encode().map {it.toChar()}.joinToString("")).isEqualTo("test item\n")
    }
}