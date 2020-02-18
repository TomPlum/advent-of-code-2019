package com.aoc.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ImageDataDecoderTest {
    @Test
    @DisplayName("Given valid image data and dimensions from the example, when decoding the Space Image, it should split the layers correctly")
    fun partOneExample() {
        val imageData = "123456789012"
        val dimensions = SpaceImageDimensions(3, 2)
        val spaceImage = ImageDataDecoder().decode(imageData, dimensions)
        assertThat(spaceImage.getImageLayer(0).getRow(0)).isEqualTo(SpaceImageRow(listOf(1,2,3)))
        assertThat(spaceImage.getImageLayer(0).getRow(1)).isEqualTo(SpaceImageRow(listOf(4,5,6)))
        assertThat(spaceImage.getImageLayer(1).getRow(0)).isEqualTo(SpaceImageRow(listOf(7,8,9)))
        assertThat(spaceImage.getImageLayer(1).getRow(1)).isEqualTo(SpaceImageRow(listOf(0,1,2)))
        println(spaceImage.toString())
    }
}