package com.aoc.intcode.droid.cryo.command

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TakeCommandTest {
    @Nested
    inner class Equality {
        @Test
        fun equal() {
            assertThat(TakeCommand("item")).isEqualTo(TakeCommand("item"))
        }

        @Test
        fun notEqualDifferentCasing() {
            assertThat(TakeCommand("item")).isNotEqualTo(TakeCommand("Item"))
        }
    }
}