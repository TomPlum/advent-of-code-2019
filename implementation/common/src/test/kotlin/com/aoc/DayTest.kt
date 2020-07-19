package com.aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DayTest {
    @Test
    fun value() {
        assertThat(Day(14).value).isEqualTo(14)
    }
}