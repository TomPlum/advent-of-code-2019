package com.aoc.intcode.droid.cryo

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Test

class ItemTest {
    @Test
    fun name() {
        assertThat(Item("electromagnet").name).isEqualTo("electromagnet")
    }

    @Test
    fun toStringTest() {
        assertThat(Item("wreath").toString()).isEqualTo("wreath")
    }

    @Test
    fun equalityTrue() {
        assertThat(Item("green ball")).isEqualTo(Item("green ball"))
    }

    @Test
    fun equalityFalse() {
        assertThat(Item("green ball")).isNotEqualTo(Item("blue ball"))
    }
}