package com.aoc.image

class SpaceImageVerifier(val image: SpaceImage) {

    /**
     * The [SpaceImageVerifier] will return the result of the number of [paramTwo] digits multiplied by the number
     * of [paramThree] digits on the layer that contains the fewest [paramOne] digits for the given [image].
     */
    fun verify(paramOne: Int, paramTwo: Int, paramThree: Int): Int {
        val layer = image.layers.minBy { layer ->
            layer.getRows().flatMap { row ->
                row.pixels
            }.count { pixels ->
                pixels == paramOne
            }
        }

        val pixels = layer!!.getRows().flatMap { it.pixels }
        return pixels.count { it == paramTwo } * pixels.count { it == paramThree}
    }
}