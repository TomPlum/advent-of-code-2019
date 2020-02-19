package com.aoc.image

class ImageDataDecoder {

    fun assembleImageData(imageDataString: String, dimensions: SpaceImageDimensions): EncodedSpaceImage {
        val imageData: List<Pixel> = imageDataString.chunked(1).map { Pixel.fromColourCode(it.toInt()) }
        return EncodedSpaceImage(imageData.chunked(dimensions.width * dimensions.height).map {
            SpaceImageLayer(it, dimensions)
        })
    }

    fun decode(encodedSpaceImage: EncodedSpaceImage): SpaceImage {
        val flattenedLayers = flattenLayers(encodedSpaceImage.layers, encodedSpaceImage.getImageDimensions())
        return SpaceImage(flattenedLayers)
    }

    private fun flattenLayers(layers: List<SpaceImageLayer>, dimensions: SpaceImageDimensions): SpaceImageLayer {
        return SpaceImageLayer(listOf(), dimensions)
    }

}