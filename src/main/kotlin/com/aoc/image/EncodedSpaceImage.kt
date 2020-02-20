package com.aoc.image

/**
 * An [EncodedSpaceImage] preserves all of the original layers of pixels before they are flattened
 * into a single [SpaceImageLayer] that forms a [SpaceImage].
 */
class EncodedSpaceImage(val layers: List<SpaceImageLayer>) {
    fun getImageDimensions() = layers.first().dimensions
}