package com.aoc.intcode.droid.cryo.droid

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.intcode.droid.cryo.droid.Item
import org.junit.jupiter.api.Test

class ItemTest {
    @Test
    fun name() {
        assertThat(Item("electromagnet").name).isEqualTo("electromagnet")
    }

    @Test
    fun toStringTest() {
        assertThat(Item("wreath").toString()).isEqualTo("Wreath")
    }

    @Test
    fun equalityTrue() {
        assertThat(Item("Green Ball")).isEqualTo(Item("Green Ball"))
    }

    @Test
    fun equalityFalse() {
        assertThat(Item("green ball")).isNotEqualTo(Item("Blue Ball"))
    }
}