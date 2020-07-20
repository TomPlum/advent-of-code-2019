package com.aoc.input

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class TestInputReaderTest {
    @Test
    fun readAsString() {
        assertThat(TestInputReader().readInputAsString("/inputs/input.txt").value).isEqualTo(listOf("1", "2", "3", "4", "5"))
    }

    @Test
    fun readAsSingleStringFromFilePath() {
        assertThat(TestInputReader().readInputAsString("/inputs/input.txt").asSingleString()).isEqualTo("12345")
    }
}