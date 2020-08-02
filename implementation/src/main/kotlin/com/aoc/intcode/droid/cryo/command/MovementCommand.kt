package com.aoc.intcode.droid.cryo.command

import com.aoc.intcode.droid.cryo.command.types.Command
import com.aoc.math.Direction

data class MovementCommand(private val direction: String) : Command(direction) {
    fun getDirection(): Direction = when(direction) {
        "north" -> Direction.UP
        "east" -> Direction.RIGHT
        "south" -> Direction.DOWN
        "west" -> Direction.LEFT
        else -> throw IllegalArgumentException("Invalid Direction: $direction")
    }
}