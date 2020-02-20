package com.aoc.image

class SpaceImage(val layer: SpaceImageLayer) {

    override fun toString(): String = layer.rows.joinToString(separator = "\n") { "$it" }

    fun toRaw(): String = layer.rows.joinToString(separator = "\n") { it.toRaw() }

}