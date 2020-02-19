package com.aoc.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ImageDataDecoderTest {
    @Test
    @DisplayName("Given valid image data and dimensions, when decoding the Space Image, it should split the layers correctly")
    fun partOneExampleButWithValuesChangedToValidPixelColours() {
        val imageData = "021120201221"
        val dimensions = SpaceImageDimensions(3, 2)
        val spaceImage = ImageDataDecoder().decode(imageData, dimensions)
        assertThat(spaceImage.getImageLayer(0).getRow(0)).isEqualTo(SpaceImageRow(listOf(Pixel.BLACK, Pixel.TRANSPARENT, Pixel.WHITE)))
        assertThat(spaceImage.getImageLayer(0).getRow(1)).isEqualTo(SpaceImageRow(listOf(Pixel.WHITE, Pixel.TRANSPARENT, Pixel.BLACK)))
        assertThat(spaceImage.getImageLayer(1).getRow(0)).isEqualTo(SpaceImageRow(listOf(Pixel.TRANSPARENT, Pixel.BLACK, Pixel.WHITE)))
        assertThat(spaceImage.getImageLayer(1).getRow(1)).isEqualTo(SpaceImageRow(listOf(Pixel.TRANSPARENT, Pixel.TRANSPARENT, Pixel.WHITE)))
        println(spaceImage.toString())
    }
}