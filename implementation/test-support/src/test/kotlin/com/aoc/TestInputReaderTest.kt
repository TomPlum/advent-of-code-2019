package com.aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class TestInputReaderTest {
    @Test
    fun readAsSingleStringFromFilePath() {
        assertThat(TestInputReader().readInputAsSingleString("/inputs/input.txt")).isEqualTo("1")
    }
}