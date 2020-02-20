package com.aoc.image

class ImageDataDecoder {

    fun assembleImageData(imageDataString: String, dimensions: SpaceImageDimensions): EncodedSpaceImage {
        val imageData: List<Pixel> = imageDataString.chunked(1).map { Pixel.fromColourCode(it.toInt()) }
        return EncodedSpaceImage(imageData.chunked(dimensions.width * dimensions.height).map {
            SpaceImageLayer(it, dimensions)
        })
    }

    fun decode(encodedSpaceImage: EncodedSpaceImage): SpaceImage {
        val flattenedLayers = flattenLayers(encodedSpaceImage.layers)
        return SpaceImage(flattenedLayers)
    }

    fun render(spaceImage: SpaceImage) {

    }

    private fun flattenLayers(layers: List<SpaceImageLayer>): SpaceImageLayer {
        val flattenedImage = layers.first()

        for (i in layers.indices) {
            for (j in layers[i].rows.indices) {
                for (k in layers[i].rows[j].pixels.indices) {
                    val pixel = layers[i].rows[j].pixels[k]
                    if (flattenedImage.rows[j].pixels[k] == Pixel.TRANSPARENT) {
                        flattenedImage.rows[j].pixels[k] = pixel
                    }
                }
            }
        }

        return flattenedImage
    }

}