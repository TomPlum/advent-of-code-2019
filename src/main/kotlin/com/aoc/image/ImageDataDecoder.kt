package com.aoc.image

class ImageDataDecoder {

    fun decode(imageDataString: String, dimensions: SpaceImageDimensions): SpaceImage {
        val imageData: List<Int> = imageDataString.chunked(1).map { it.toInt() }
        return SpaceImage(imageData.chunked(dimensions.width * dimensions.height).map {
            SpaceImageLayer(it, dimensions)
        })
    }

}