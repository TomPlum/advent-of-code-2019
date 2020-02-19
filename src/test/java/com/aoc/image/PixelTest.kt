package com.aoc.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalStateException

class PixelTest {
    @Test
    fun fromColourCodeBlack() {
        val pixel = Pixel.fromColourCode(0)
        assertThat(pixel).isEqualTo(Pixel.BLACK)
    }

    @Test
    fun fromColourCodeWhite() {
        val pixel = Pixel.fromColourCode(1)
        assertThat(pixel).isEqualTo(Pixel.WHITE)
    }

    @Test
    fun fromColourCodeTransparent() {
        val pixel = Pixel.fromColourCode(2)
        assertThat(pixel).isEqualTo(Pixel.TRANSPARENT)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 3, 4, 5])
    fun fromInvalidColourCode(code: Int) {
        val e = assertThrows<IllegalStateException> { Pixel.fromColourCode(code) }
        assertThat(e.message).isEqualTo("Invalid Colour Code")
    }

    @Test
    fun toStringTest() {
        val pixel = Pixel.TRANSPARENT
        val toString = pixel.toString()
        assertThat(toString).isEqualTo("2")
    }
}