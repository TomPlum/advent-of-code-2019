package com.aoc.image

class SpaceImageLayer(pixels: List<Int>, dimensions: SpaceImageDimensions) {
    private val rows: List<SpaceImageRow> = pixels.chunked(dimensions.width).map { SpaceImageRow(it) }

    fun getRow(index: Int): SpaceImageRow = rows[index]

    fun getRows() = rows

}