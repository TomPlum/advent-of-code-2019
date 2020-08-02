package com.aoc.intcode.droid.cryo.command.types

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.droid.cryo.command.TestCommand
import org.junit.jupiter.api.Test

class CommandTest {
    @Test
    fun encode() {
        assertThat(TestCommand().encode()).isEqualTo(listOf<Long>(116, 101, 115, 116, 10))
    }
}
