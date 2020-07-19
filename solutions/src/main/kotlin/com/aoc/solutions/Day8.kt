package com.aoc.solutions

import com.aoc.image.utility.ImageDataAssembler
import com.aoc.image.Pixel
import com.aoc.image.SpaceImageDimensions
import com.aoc.image.utility.SpaceImageDecoder
import com.aoc.image.utility.SpaceImageVerifier
import com.aoc.input.Day
import com.aoc.input.InputReader

fun main() {
    val imageData = InputReader.read<String>(Day(8)).asSingleString()
    val dimensions = SpaceImageDimensions(25, 6)
    val encodedSpaceImage = ImageDataAssembler().assemble(imageData, dimensions)
    val solution = SpaceImageVerifier(encodedSpaceImage).verify(Pixel.BLACK, Pixel.WHITE, Pixel.TRANSPARENT)
    println("Part 1 Solution: $solution\n")

    val spaceImage = SpaceImageDecoder().decode(encodedSpaceImage)
    println("Part 2 Raw:\n${spaceImage.toRaw()}\n")
    println("Part 2 Solution:\n$spaceImage")

}