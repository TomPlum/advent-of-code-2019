package com.aoc.image

class SpaceImage(private val layers: List<SpaceImageLayer>) {

    fun getImageLayer(index: Int) = layers[index]

    override fun toString(): String {
        return layers.forEach { "$it/n" }.toString()
    }
}