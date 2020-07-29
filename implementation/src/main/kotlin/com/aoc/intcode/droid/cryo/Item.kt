package com.aoc.intcode.droid.cryo

import com.aoc.extensions.capitaliseWords

data class Item(val name: String) {
    override fun toString(): String = name.capitaliseWords()
}