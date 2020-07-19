package com.aoc.input

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GenericInputReaderTest {
    private val reader = GenericInputReader()

    @Test
    fun asString() {
        assertThat(reader.read<String>(Day.from(100)).value).isEqualTo(listOf("1", "2", "3", "4", "5"))
    }

    @Test
    fun asInteger() {
        assertThat(reader.read<Int>(Day.from(100)).value).isEqualTo(listOf(1, 2, 3, 4, 5))
    }

    @Test
    fun asSingleString() {
        val input: StringInput = reader.read<String>(Day.from(100)) as StringInput
        assertThat(input.asSingleString()).isEqualTo("12345")
    }

    @Test
    fun asInvalidType() {
        val e = assertThrows<UnsupportedOperationException> { reader.read<Double>(Day.from(100)) }
        assertThat(e.message).isEqualTo("Input Reader does not support type: Double")
    }
}