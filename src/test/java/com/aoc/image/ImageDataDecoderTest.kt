package com.aoc.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class ImageDataDecoderTest {
    @Test
    fun partOneExample() {
        val imageData = listOf(1,2,3,4,5,6,7,8,9,0,1,2)
        val dimensions = SpaceImageDimensions(3, 2)
        val spaceImage = ImageDataDecoder(dimensions).decode(imageData)
        assertThat(spaceImage.getImageLayer(0).getRow(0)).isEqualTo(listOf(1,2,3))
        assertThat(spaceImage.getImageLayer(0).getRow(1)).isEqualTo(listOf(4,5,6))
        assertThat(spaceImage.getImageLayer(1).getRow(0)).isEqualTo(listOf(7,8,9))
        assertThat(spaceImage.getImageLayer(1).getRow(1)).isEqualTo(listOf(0,1,2))
        println(spaceImage.toString())
    }
}