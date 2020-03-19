package com.aoc.intcode.droid

enum class Direction(val code: Int) {
    NORTH(1),
    SOUTH(2),
    WEST(3),
    EAST(4);

    /**
     * Returns the opposite direction.
     * Allows the [RepairDroid] to reverse when backtracking.
     */
    fun reverse(): Direction = when(this) {
        NORTH -> SOUTH
        EAST -> WEST
        SOUTH -> NORTH
        WEST -> EAST
    }
}