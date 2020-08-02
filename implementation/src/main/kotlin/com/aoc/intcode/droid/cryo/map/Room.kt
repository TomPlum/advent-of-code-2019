package com.aoc.intcode.droid.cryo.map

import com.aoc.intcode.droid.cryo.droid.Item
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.math.Direction

/**
 * A single room on Santa's Starship.
 * They are recorded in the [StarShipMap] after being scanned by the [CryostasisDroid].
 */
data class Room(val name: String, val description: String, val doors: List<Direction>, val items: MutableList<Item>) {

    companion object {
        /**
         * Instantiates an empty [Room].
         */
        fun empty() = Room("N/A", "An un-explored room", listOf(Direction.UP), mutableListOf())
    }

    /**
     * @return true if the [Room] has at-least one item, else false.
     */
    fun hasItems() = items.isNotEmpty()

    /**
     * Removes an [Item] from the [Room].
     * @return The item that was removed.
     */
    fun takeItem(item: Item): Item? {
        val found = items.find { it == item }
        items.remove(found)
        return found
    }

    /**
     * Adds an [Item] to the [Room].
     */
    fun placeItem(item: Item) = items.add(item)

    /**
     * @return true if the [Room] has a door in the given [direction], else false.
     */
    fun hasDoorLeading(direction: Direction) = doors.contains(direction)

    /**
     * @return true if the [Room] is the Security Checkpoint, else false.
     */
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