package com.aoc.image.utility

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.image.Pixel
import com.aoc.image.SpaceImageDimensions
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class SpaceImageVerifierTest {
    @Test
    @DisplayName("Given the SpaceImage generated from Day 8's Puzzle Input, when verifying the image is not corrupt," +
    "then it should return 2904")
    fun partOneSolution() {
        val imageData = InputReader.read<String>(Day(8)).asSingleString()
        val dimensions = SpaceImageDimensions(25, 6)
        val image = ImageDataAssembler().assemble(imageData, dimensions)
        val solution = SpaceImageVerifier(image).verify(Pixel.BLACK, Pixel.WHITE, Pixel.TRANSPARENT)
        assertThat(solution).isEqualTo(2904)
    }
}