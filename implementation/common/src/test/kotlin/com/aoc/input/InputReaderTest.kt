package com.aoc.input

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputReaderTest {

    @Test
    fun asString() {
        assertThat(InputReader.read<String>(Day(100)).value).isEqualTo(listOf("1", "2", "3", "4", "5"))
    }

    @Test
    fun asInteger() {
        assertThat(InputReader.read<Int>(Day(100)).value).isEqualTo(listOf(1, 2, 3, 4, 5))
    }

    @Test
    fun asSingleString() {
        val input: StringInput = InputReader.read<String>(Day(100)) as StringInput
        assertThat(input.asSingleString()).isEqualTo("12345")
    }

    @Test
    fun asInvalidType() {
        val e = assertThrows<UnsupportedOperationException> { InputReader.read<Double>(Day(100)) }
        assertThat(e.message).isEqualTo("Input Reader does not support type: Double")
    }
}