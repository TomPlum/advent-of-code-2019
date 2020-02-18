package com.aoc.image

data class SpaceImageRow(val pixels: List<Int>) {
    override fun toString(): String {
        return pixels.joinToString(separator = "") { it.toString() }
    }
}