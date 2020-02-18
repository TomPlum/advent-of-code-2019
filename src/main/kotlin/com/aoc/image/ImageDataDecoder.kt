package com.aoc.image

class ImageDataDecoder(private val dimensions: SpaceImageDimensions) {
    fun decode(imageData: List<Int>): SpaceImage {
        var layers = mutableListOf<SpaceImageLayer>()

        imageData.chunked(dimensions.width * dimensions.height).forEach { layerData ->
            layerData.map { SpaceImageLayer(layerData) }
        }

        return SpaceImage(listOf())
    }
}