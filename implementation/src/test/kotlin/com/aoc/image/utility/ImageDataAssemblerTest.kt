package com.aoc.image.utility

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.image.Pixel
import com.aoc.image.SpaceImageDimensions
import com.aoc.image.SpaceImageRow
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ImageDataAssemblerTest {

    @Test
    @DisplayName("Given valid image data and dimensions, when assembling the Space Image data, it should split the layers correctly")
    fun partOneExampleButWithValuesChangedToValidPixelColours() {
        val imageData = "021120201221"
        val dimensions = SpaceImageDimensions(3, 2)
        val spaceImage = ImageDataAssembler().assemble(imageData, dimensions)
        assertThat(spaceImage.layers[0].getRow(0)).isEqualTo(SpaceImageRow(mutableListOf(Pixel.BLACK, Pixel.TRANSPARENT, Pixel.WHITE)))
        assertThat(spaceImage.layers[0].getRow(1)).isEqualTo(SpaceImageRow(mutableListOf(Pixel.WHITE, Pixel.TRANSPARENT, Pixel.BLACK)))
        assertThat(spaceImage.layers[1].getRow(0)).isEqualTo(SpaceImageRow(mutableListOf(Pixel.TRANSPARENT, Pixel.BLACK, Pixel.WHITE)))
        assertThat(spaceImage.layers[1].getRow(1)).isEqualTo(SpaceImageRow(mutableListOf(Pixel.TRANSPARENT, Pixel.TRANSPARENT, Pixel.WHITE)))
    }

}