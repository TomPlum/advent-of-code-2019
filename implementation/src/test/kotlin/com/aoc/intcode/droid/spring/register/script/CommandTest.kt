package com.aoc.intcode.droid.spring.register.script

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.droid.spring.script.Command
import org.junit.jupiter.api.Test

class CommandTest {
    @Test
    fun toStringTest() {
        assertThat(Command.WALK.toString()).isEqualTo("WALK")
    }

    @Test
    fun encodeWalk() {
        assertThat(Command.WALK.encode()).isEqualTo(listOf<Long>(87,65,76,75,10))
    }

    @Test
    fun encodeRun() {
        assertThat(Command.RUN.encode()).isEqualTo(listOf<Long>(82,85,78,10))
    }
}