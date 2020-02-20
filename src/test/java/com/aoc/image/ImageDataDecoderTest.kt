package com.aoc.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ImageDataDecoderTest {

    @Test
    @DisplayName("Given valid image data and dimensions, when assembling the Space Image data, it should split the layers correctly")
    fun partOneExampleButWithValuesChangedToValidPixelColours() {
        val imageData = "021120201221"
        val dimensions = SpaceImageDimensions(3, 2)
        val spaceImage = ImageDataDecoder().assembleImageData(imageData, dimensions)
        assertThat(spaceImage.layers[0].getRow(0)).isEqualTo(SpaceImageRow(mutableListOf(Pixel.BLACK, Pixel.TRANSPARENT, Pixel.WHITE)))
        assertThat(spaceImage.layers[0].getRow(1)).isEqualTo(SpaceImageRow(mutableListOf(Pixel.WHITE, Pixel.TRANSPARENT, Pixel.BLACK)))
        assertThat(spaceImage.layers[1].getRow(0)).isEqualTo(SpaceImageRow(mutableListOf(Pixel.TRANSPARENT, Pixel.BLACK, Pixel.WHITE)))
        assertThat(spaceImage.layers[1].getRow(1)).isEqualTo(SpaceImageRow(mutableListOf(Pixel.TRANSPARENT, Pixel.TRANSPARENT, Pixel.WHITE)))
        println(spaceImage.toString())
    }

    @Test
    @DisplayName("Given valid image data and dimensions, when decoding the Space Image, it should show the correct colours")
    fun partTwoExample() {
        val imageData = "0222112222120000"
        val dimensions = SpaceImageDimensions(2, 2)
        val encodedSpaceImage = ImageDataDecoder().assembleImageData(imageData, dimensions)
        val spaceImage = ImageDataDecoder().decode(encodedSpaceImage)
        assertThat(spaceImage.layer.rows[0].pixels).isEqualTo(listOf(Pixel.BLACK, Pixel.WHITE))
        assertThat(spaceImage.layer.rows[1].pixels).isEqualTo(listOf(Pixel.WHITE, Pixel.BLACK))
        println(spaceImage.toString())
    }
}