package com.aoc.intcode.droid.cryo.command.parameterised

import com.aoc.intcode.droid.cryo.command.runtime.CommandRuntime
import com.aoc.intcode.droid.cryo.command.types.Command
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.map.Room
import com.aoc.math.Direction
import com.aoc.intcode.droid.cryo.command.CommandParser

/**
 * A command issued to the [CryostasisDroid] via the [CommandRuntime] instructing it to move into the [Room] in
 * the given [direction].
 * @see CommandParser
 */
data class MovementCommand(private val direction: String) : Command(direction) {
    fun getDirection(): Direction = when(direction) {
        "north" -> Direction.UP
        "east" -> Direction.RIGHT
        "south" -> Direction.DOWN
        "west" -> Direction.LEFT
        else -> throw IllegalArgumentException("Invalid Direction: $direction")
    }
}