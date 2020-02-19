package com.aoc.image

class SpaceImage(val layer: SpaceImageLayer) {
    override fun toString(): String {
        return layer.rows.joinToString(separator = "\n") { "$it" }
    }
}