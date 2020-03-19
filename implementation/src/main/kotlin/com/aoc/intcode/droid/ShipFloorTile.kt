package com.aoc.intcode.droid

enum class ShipFloorTile(private val icon: String) {
    TRAVERSABLE("."), //\u2592
    WALL("#"), //\u2589
    DROID("D"),
    OXYGEN_SYSTEM("O"),
    UNKNOWN(" ");

    override fun toString() = icon
}