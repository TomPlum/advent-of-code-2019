package com.aoc.intcode.droid.cryo

import com.aoc.math.Direction

class DroidOutput(private val value: String) {
    fun parse(): Room {
        val name = value.getRoomName()
        val desc = value.getRoomDescription()
        val doors = value.getDoors()
        val items = value.getItems()
        return Room(name, desc, doors, items)
    }

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

    fun parsePassword(): AirlockKey {
        if (value.contains("Analysis complete! You may proceed."))  {
            return AirlockKey(value.substringAfter("typing ").substringBefore(" on the keypad"))
        }
        throw IllegalStateException("Droid output does not contain a password!:\n$value")
    }

    private fun String.getRoomName() = this.substringAfter("==").substringBefore("==").trim()

    private fun String.getRoomDescription() = this.substringAfter("==\n").substringBefore("\n\n").trim()

    private fun String.getItems() = this
            .substringAfter("Items here:\n").substringBefore("\n\n")
            .replace("- ", "").split("\n")
            .map { Item(it) }.toMutableList()

    private fun String.getDoors() = this
            .substringAfter("Doors here lead:\n").substringBefore("\n\n")
            .replace("- ", "").split("\n")
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