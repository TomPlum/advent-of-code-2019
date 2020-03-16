package com.aoc.image

data class SpaceImageRow(val pixels: MutableList<Pixel>) {

    override fun toString(): String {
        return pixels.joinToString(separator = " ") {
            if (it == Pixel.WHITE) {
                "#"
            } else {
                " "
            }
        }
    }

    fun toRaw(): String = pixels.joinToString(separator = " ") { "$it" }

}