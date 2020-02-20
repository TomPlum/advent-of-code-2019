package com.aoc.solutions

import com.aoc.image.ImageDataDecoder
import com.aoc.image.Pixel
import com.aoc.image.SpaceImageDimensions
import com.aoc.image.SpaceImageVerifier
import com.aoc.input.Day
import com.aoc.input.InputReader

fun main() {
    val inputReader = InputReader()
    val imageData = inputReader.readInputAsSingleString(Day.from(8))
    val dimensions = SpaceImageDimensions(25, 6)
    val encodedSpaceImage = ImageDataDecoder().assembleImageData(imageData, dimensions)
    val solution = SpaceImageVerifier(encodedSpaceImage).verify(Pixel.BLACK, Pixel.WHITE, Pixel.TRANSPARENT)
    println("Part 1 Solution: $solution\n")

    val spaceImage = ImageDataDecoder().decode(encodedSpaceImage)
    println("Part 2 Raw:\n${spaceImage.toRaw()}\n")
    println("Part 2 Solution:\n$spaceImage")

}