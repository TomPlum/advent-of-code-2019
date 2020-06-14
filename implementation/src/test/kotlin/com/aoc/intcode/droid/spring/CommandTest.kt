package com.aoc.intcode.droid.spring

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class CommandTest {
    @Test
    fun toStringTest() {
        assertThat(Command.WALK.toString()).isEqualTo("WALK")
    }

    @Test
    fun encode() {
        assertThat(Command.WALK.encode()).isEqualTo(listOf<Long>(87,65,76,75,10))
    }
}