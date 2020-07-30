package com.aoc.intcode.droid.cryo.command

import com.aoc.intcode.droid.cryo.Item

data class TakeCommand(private val itemName: String) : ParameterisedCommand("take", itemName) {
    fun getItem(): Item = Item(itemName)
}