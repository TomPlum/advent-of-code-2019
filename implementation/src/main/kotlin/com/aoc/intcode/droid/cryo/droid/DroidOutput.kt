package com.aoc.intcode.droid.cryo.droid

import com.aoc.intcode.droid.cryo.map.Room
import com.aoc.intcode.droid.cryo.security.AirlockPassword
import com.aoc.intcode.droid.cryo.security.SecurityAnalysis
import com.aoc.math.Direction

/**
 * Parses the stringified output of the [CryostasisDroid].
 */
class DroidOutput(private var value: String) {

    private val br: String = "\n"

    /**
     * Parses [Room] details scanned by the droid.
     */
    fun parse(): Room {
        val name = value.getRoomName()
        val desc = value.getRoomDescription()
        val doors = value.getDoors()
        val items = value.getItems()
        return Room(name, desc, doors, items)
    }

    /**
     * Parses the recorded voice of the Pressure-Sensitive Floor after passing through the Security Checkpoint.
     * @return The response for the security analysis, indicating whether the [CryostasisDroid] is the correct weight.
     */
    fun parsePressureSensitiveFloor(): SecurityAnalysis {
        if (value.contains("A loud, robotic voice says")) {
            return when {
                value.contains("lighter") -> SecurityAnalysis.TOO_HEAVY
                value.contains("heavier") -> SecurityAnalysis.TOO_LIGHT
                value.contains("Analysis complete! You may proceed.") -> SecurityAnalysis.VALID
                else -> throw IllegalStateException("Invalid Security Analysis:\n$value")
            }
        }
        throw IllegalStateException("Droid is not on the Pressure-Sensitive Floor")
    }

    /**
     * Parses the recorded voice of Santa after telling the droid the [AirlockPassword].
     * This will only be available after invoking [parsePressureSensitiveFloor] with a [SecurityAnalysis.VALID] response.
     * @return The password to the airlock of Santa's Starship.
     */
    fun parsePassword(): AirlockPassword {
        if (value.contains("Analysis complete! You may proceed."))  {
            return AirlockPassword(value.substringAfter("typing ").substringBefore(" on the keypad"))
        }
        throw IllegalStateException("Droid output does not contain a password!:\n$value")
    }

    private fun String.getRoomName() = this.substringAfter("==").substringBefore("==").trim()

    private fun String.getRoomDescription() = this.substringAfter("==$br").substringBefore("$br$br").trim()

    private fun String.getItems(): MutableList<Item> {
        if (this.contains("Items here:")) {
            return this.substringAfter("Items here:$br").substringBefore("$br$br")
                    .replace("- ", "").split("\n")
                    .map { Item(it) }.toMutableList()
        }
        return mutableListOf()
    }

    private fun String.getDoors() = this
            .substringAfter("Doors here lead:$br").substringBefore("$br$br")
            .replace("- ", "").split(br)
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