package com.aoc.intcode.droid

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DroidResponseTest {
    @Test
    fun fromCode0() {
        assertThat(DroidResponse.fromCode(0)).isEqualTo(DroidResponse.HIT_WALL_POSITION_NOT_CHANGED)
    }

    @Test
    fun fromCode1() {
        assertThat(DroidResponse.fromCode(1)).isEqualTo(DroidResponse.SUCCESSFULLY_CHANGED_POSITION)
    }

    @Test
    fun fromCode2() {
        assertThat(DroidResponse.fromCode(2)).isEqualTo(DroidResponse.LOCATED_OXYGEN_SYSTEM)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 3, 4, 5, 10])
    @DisplayName("Given an invalid DroidResponseCode, when creating one from an invalid code, then it should throw an IllegalArgumentException")
    fun fromCodeInvalid(code: Int) {
        val e = assertThrows<IllegalArgumentException> { DroidResponse.fromCode(code) }
        assertThat(e.message).isEqualTo("Invalid Droid Response Code ($code)")
    }
}