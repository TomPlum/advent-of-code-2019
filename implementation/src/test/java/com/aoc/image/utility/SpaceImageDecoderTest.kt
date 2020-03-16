package com.aoc.image.utility

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.image.Pixel
import com.aoc.image.SpaceImageDimensions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SpaceImageDecoderTest {
    @Test
    @DisplayName("Given valid image data and dimensions, when decoding the Space Image, it should show the correct colours")
    fun partTwoExample() {
        val imageData = "0222112222120000"
        val dimensions = SpaceImageDimensions(2, 2)
        val encodedSpaceImage = ImageDataAssembler().assemble(imageData, dimensions)
        val spaceImage = SpaceImageDecoder().decode(encodedSpaceImage)
        assertThat(spaceImage.layer.rows[0].pixels).isEqualTo(listOf(Pixel.BLACK, Pixel.WHITE))
        assertThat(spaceImage.layer.rows[1].pixels).isEqualTo(listOf(Pixel.WHITE, Pixel.BLACK))
        println(spaceImage.toString())
    }
}