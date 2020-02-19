package com.aoc.image

class SpaceImageVerifier(val image: EncodedSpaceImage) {

    /**
     * The [SpaceImageVerifier] will return the result of the number of [paramTwo] pixels multiplied by the number
     * of [paramThree] pixels on the layer that contains the fewest [paramOne] pixels for the given [image].
     */
    fun verify(paramOne: Pixel, paramTwo: Pixel, paramThree: Pixel): Int {
        val layer = image.layers.minBy { layer ->
            layer.rows.flatMap { row -> row.pixels }.count { pixels -> pixels == paramOne }
        }

        val pixels = layer!!.rows.flatMap { it.pixels }
        return pixels.count { it == paramTwo } * pixels.count { it == paramThree }
    }

}