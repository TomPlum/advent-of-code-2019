package com.aoc.radio

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class SignalTest {
    @Test
    fun toStringTest() {
        assertThat(Signal(listOf(1,2,3,4,5)).toString()).isEqualTo("12345")
    }
}