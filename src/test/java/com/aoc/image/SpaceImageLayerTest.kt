package com.aoc.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SpaceImageLayerTest {
    @Test
    @DisplayName("Given a SpaceImageLayer with Dimensions (w=3, h=2), when getting the rows, then it should return the correct rows of pixels")
    fun spaceImageLayerShouldSplitPixelsIntoRowsUponConstruction() {
        val dimensions = SpaceImageDimensions(3, 2)
        val layer = SpaceImageLayer(listOf(Pixel.WHITE, Pixel.BLACK, Pixel.WHITE, Pixel.WHITE, Pixel.BLACK, Pixel.BLACK), dimensions)
        val firstRow = layer.getRow(0)
        val secondRow = layer.getRow(1)
        assertThat(firstRow).isEqualTo(SpaceImageRow(mutableListOf(Pixel.WHITE, Pixel.BLACK, Pixel.WHITE)))
        assertThat(secondRow).isEqualTo(SpaceImageRow(mutableListOf(Pixel.WHITE, Pixel.BLACK, Pixel.BLACK)))
    }
}