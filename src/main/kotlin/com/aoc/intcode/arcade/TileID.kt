package com.aoc.intcode.arcade

enum class TileID(val displayIcon: String) {
    EMPTY (" "),
    WALL ("|"),
    BLOCK ("="),
    HORIZONTAL_PADDLE ("_"),
    BALL ("o");

    companion object {
        fun fromValue(value: Int) = when(value) {
            0 -> EMPTY
            1 -> WALL
            2 -> BLOCK
            3 -> HORIZONTAL_PADDLE
            4 -> BALL
            else -> throw IllegalArgumentException("Invalid Tile ID ($value)")
        }
    }

    override fun toString() = displayIcon
}