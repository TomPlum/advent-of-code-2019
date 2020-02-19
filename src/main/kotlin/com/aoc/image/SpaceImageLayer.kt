package com.aoc.image

class SpaceImageLayer(pixels: List<Pixel>, dimensions: SpaceImageDimensions) {
    val rows: List<SpaceImageRow> = pixels.chunked(dimensions.width).map { SpaceImageRow(it) }

    fun getRow(index: Int): SpaceImageRow = rows[index]

}