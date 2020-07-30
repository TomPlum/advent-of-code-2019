package com.aoc.intcode.droid.cryo

import com.aoc.math.Direction

data class Room(val name: String, val doors: List<Direction>, val items: MutableList<Item>) {
    fun hasItems() = items.isNotEmpty()

    fun takeItem(item: Item): Item? {
        val found = items.find { it == item }
        items.remove(found)
        return found
    }

    companion object {
        fun empty() = Room("N/A", emptyList(), mutableListOf())
    }

    override fun toString(): String {
        return super.toString()
    }
}