package com.aoc.intcode.droid.cryo

import com.aoc.math.Direction

data class Room(val name: String, val doors: List<Direction>, val items: MutableList<Item>) {
    override fun toString(): String {
        return super.toString()
    }
}