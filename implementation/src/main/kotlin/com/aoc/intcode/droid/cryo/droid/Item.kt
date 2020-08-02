package com.aoc.intcode.droid.cryo.droid

import com.aoc.extensions.capitaliseWords

/**
 * An item found by the [CryostasisDroid] while exploring Santa's Starship.
 * @see Inventory
 */
data class Item(val name: String) {
    override fun toString(): String = name.capitaliseWords()
}