package com.aoc.image

class ImageDataDecoder {

    fun decode(imageDataString: String, dimensions: SpaceImageDimensions): SpaceImage {
        val imageData: List<Pixel> = imageDataString.chunked(1).map { Pixel.fromColourCode(it.toInt()) }
        return SpaceImage(imageData.chunked(dimensions.width * dimensions.height).map {
            SpaceImageLayer(it, dimensions)
        })
    }

}