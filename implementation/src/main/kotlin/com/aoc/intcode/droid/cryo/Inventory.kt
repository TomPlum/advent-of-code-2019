package com.aoc.intcode.droid.cryo

class Inventory {
    private val items = mutableListOf<Item>()

    fun add(item: Item) = items.add(item)

    fun take(item: Item): Item? {
        val found = items.find { it == item }
        items.remove(found)
        return found
    }
}