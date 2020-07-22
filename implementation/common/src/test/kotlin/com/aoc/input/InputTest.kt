package com.aoc.input

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.types.IntegerInput
import org.junit.jupiter.api.Test

class InputTest {
    @Test
    fun asSingleString() {
        assertThat(IntegerInput(listOf("1", "4", "6")).asSingleString()).isEqualTo("146")
    }
}