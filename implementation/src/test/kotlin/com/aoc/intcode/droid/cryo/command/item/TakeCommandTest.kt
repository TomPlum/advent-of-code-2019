package com.aoc.intcode.droid.cryo.command.item

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.intcode.droid.cryo.command.item.TakeCommand
import com.aoc.intcode.droid.cryo.droid.Item
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TakeCommandTest {

    @Test
    fun getItem() {
        assertThat(TakeCommand("wreath").getItem()).isEqualTo(Item("wreath"))
    }

    @Test
    fun encode() {
        assertThat(TakeCommand("coin").encode()).isEqualTo(listOf<Long>(116, 97, 107, 101, 32, 99, 111, 105, 110, 10))
    }

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