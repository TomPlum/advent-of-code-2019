package com.aoc.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class SpaceImageTest {
    @Test
    fun toRawString() {
        val pixels = listOf(Pixel.WHITE, Pixel.BLACK, Pixel.WHITE, Pixel.BLACK)
        val image = SpaceImage(SpaceImageLayer(pixels, SpaceImageDimensions(2, 2)))
        assertThat(image.toRaw()).isEqualTo("1 0\n1 0")
    }

    @Test
    fun toStringTest() {
        val pixels = listOf(Pixel.WHITE, Pixel.BLACK, Pixel.WHITE, Pixel.BLACK)
        val image = SpaceImage(SpaceImageLayer(pixels, SpaceImageDimensions(2, 2)))
        assertThat(image.toString()).isEqualTo("#  \n#  ")
    }
}