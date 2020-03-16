package com.aoc.image.utility

import com.aoc.image.EncodedSpaceImage
import com.aoc.image.Pixel
import com.aoc.image.SpaceImageDimensions
import com.aoc.image.SpaceImageLayer

/**
 * Assembles the given data string (Format 12020102221010...) into an [EncodedSpaceImage]
 */
class ImageDataAssembler {

    fun assemble(imageDataString: String, dimensions: SpaceImageDimensions): EncodedSpaceImage {
        val imageData: List<Pixel> = imageDataString.chunked(1).map { Pixel.fromColourCode(it.toInt()) }
        return EncodedSpaceImage(imageData.chunked(dimensions.width * dimensions.height).map {
            SpaceImageLayer(it, dimensions)
        })
    }

}