package com.aoc.panel

class Wire constructor(input: String) {
    val segments: List<WireSegment> = input.split(",").map { WireSegment(it.substring(0, 1), it.substring(1).toInt()) }

    fun getPath(): List<WireCoordinate> {
        val path: MutableList<WireCoordinate> = mutableListOf()
        var coordinate = WireCoordinate()
        segments.forEach { segment ->
            (0 until segment.length).forEach { _ ->
                coordinate = updateOrdinate(segment, coordinate)
                path.add(WireCoordinate(coordinate.x, coordinate.y))
            }
        }
        return path
    }

    private fun updateOrdinate(segment: WireSegment, wireCoordinate: WireCoordinate): WireCoordinate {
        when (segment.direction) {
            "U" -> wireCoordinate.y += 1
            "R" -> wireCoordinate.x += 1
            "D" -> wireCoordinate.y -= 1
            "L" -> wireCoordinate.x -= 1
        }
        return wireCoordinate
    }

}