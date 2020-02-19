package com.aoc.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class SpaceImageTest {
    @Test
    fun toStringTest() {
        val pixels = listOf(Pixel.WHITE, Pixel.BLACK, Pixel.WHITE, Pixel.BLACK)
        val image = SpaceImage(SpaceImageLayer(pixels, SpaceImageDimensions(2, 2)))
        println(image.toString())
        assertThat(image.toString()).isEqualTo("10\n10")
    }
}