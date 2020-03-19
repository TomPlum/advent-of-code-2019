package com.aoc.intcode.droid

enum class Direction(val code: Int) {
    NORTH(1),
    SOUTH(2),
    WEST(3),
    EAST(4);

    /**
     * Rotates the current [Direction] by 90deg.
     */
    fun rotateClockwise(): Direction = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    fun reverse(): Direction = when(this) {
        NORTH -> SOUTH
        EAST -> WEST
        SOUTH -> NORTH
        WEST -> EAST
    }

    fun randomise() = when ((1..4).shuffled().first()) {
        1 -> NORTH
        2 -> SOUTH
        3 -> WEST
        4 -> EAST
        else -> throw IllegalArgumentException("Invalid Directional Code")
    }

}