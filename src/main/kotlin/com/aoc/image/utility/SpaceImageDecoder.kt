package com.aoc.image.utility

import com.aoc.image.EncodedSpaceImage
import com.aoc.image.Pixel
import com.aoc.image.SpaceImage
import com.aoc.image.SpaceImageLayer

/**
 * Takes an [EncodedSpaceImage] and decodes it by flattening the layers and exposing the uppermost
 * non-transparent pixels.
 */
class SpaceImageDecoder {
    fun decode(encodedSpaceImage: EncodedSpaceImage): SpaceImage {
        val flattenedLayers = flattenLayers(encodedSpaceImage.layers)
        return SpaceImage(flattenedLayers)
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