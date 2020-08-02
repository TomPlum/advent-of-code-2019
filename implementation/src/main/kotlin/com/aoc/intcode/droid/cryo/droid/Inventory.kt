package com.aoc.intcode.droid.cryo.droid

/**
 * Stores the [Item]s collected by the [CryostasisDroid].
 */
class Inventory {
    private val items = mutableListOf<Item>()

    /**
     * Adds the taken [item] to the [Inventory].
     */
    fun add(item: Item) = items.add(item)

    /**
     * Removes the dropped [item] from the [Inventory].
     */
    fun take(item: Item): Item? {
        val found = items.find { it == item }
        items.remove(found)
        return found
    }

    /**
     * Retrieves all the [Item]s from the [Inventory].
     */
    fun getAllItems() = items

    override fun toString(): String {
        val items = if (items.isNotEmpty()) items.joinToString(separator = ", ") else "Empty"
        return "Inventory: $items"
    }
}