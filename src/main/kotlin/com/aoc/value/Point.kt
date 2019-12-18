package com.aoc.value

class Point constructor(var x: Int = 0, var y: Int = 0) {
    override fun equals(other: Any?): Boolean {
        if (other == null) return false

        if (other !is Point) return false

        return (x == other.x && y == other.y) || (x == other.y && y == other.x)
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}