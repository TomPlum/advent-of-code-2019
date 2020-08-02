package com.aoc.intcode.droid.cryo.map

import com.aoc.intcode.droid.cryo.droid.Item
import com.aoc.math.Direction

data class Room(val name: String, val description: String, val doors: List<Direction>, val items: MutableList<Item>) {

    companion object {
        fun empty() = Room("N/A", "An un-explored room", listOf(Direction.UP), mutableListOf())
    }

    fun hasItems() = items.isNotEmpty()

    fun takeItem(item: Item): Item? {
        val found = items.find { it == item }
        items.remove(found)
        return found
    }

    fun placeItem(item: Item) = items.add(item)

    fun hasDoorLeading(direction: Direction) = doors.contains(direction)

    fun isSecurityCheckpoint() = name == "Security Checkpoint"

    override fun toString(): String {
        val s = StringBuilder()
        s.append("-$name").append("\n")
        s.append(description).append("\n")
        if (items.isNotEmpty()) {
            s.append("Upon entering the room you find: ${items.joinToString()}")
        }
        return s.toString()
    }
}