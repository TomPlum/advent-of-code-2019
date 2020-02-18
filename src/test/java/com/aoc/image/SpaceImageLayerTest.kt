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
        val layer = SpaceImageLayer(listOf(1,2,3,4,5,6), dimensions)
        val firstRow = layer.getRow(0)
        val secondRow = layer.getRow(1)
        assertThat(firstRow).isEqualTo(SpaceImageRow(listOf(1,2,3)))
        assertThat(secondRow).isEqualTo(SpaceImageRow(listOf(4,5,6)))
    }
}