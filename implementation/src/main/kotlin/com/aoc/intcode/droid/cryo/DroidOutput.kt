package com.aoc.intcode.droid.cryo

import com.aoc.math.Direction

class DroidOutput(private val value: String) {
    fun parse(): Room {
        val name = value.getRoomName()
        val doors = value.getDoors()
        val items = value.getItems()
        return Room(name, doors, items)
    }

    private fun String.getRoomName() = this.substringAfter("==").substringBefore("==").trim()

    private fun String.getItems() = this
            .substringAfter("Items here:\\n").substringBefore("\\n\\n")
            .replace("- ", "").split("\\n")
            .map { Item(it) }.toMutableList()

    private fun String.getDoors() = this
            .substringAfter("Doors here lead:\\n").substringBefore("\\n\\n")
            .replace("- ", "").split("\\n")
            .map {
                when (it) {
                    "north" -> Direction.UP
                    "east" -> Direction.RIGHT
                    "south" -> Direction.DOWN
                    "west" -> Direction.LEFT
                    else -> throw IllegalArgumentException("Invalid Direction: $it")
                }
            }
}