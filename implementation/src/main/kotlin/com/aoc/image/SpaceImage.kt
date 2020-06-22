package com.aoc.image

class SpaceImage(val layer: SpaceImageLayer) {

    fun toRaw(): String = layer.rows.joinToString(separator = "\n") { it.toRaw() }

    override fun toString(): String = layer.rows.joinToString(separator = "\n") { "$it" }

}