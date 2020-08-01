package com.aoc.intcode.droid.cryo.droid

import com.aoc.extensions.capitaliseWords

data class Item(val name: String) {
    override fun toString(): String = name.capitaliseWords()
}