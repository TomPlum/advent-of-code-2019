package com.aoc.intcode.droid.cryo.command

import com.aoc.intcode.droid.cryo.Item

data class DropCommand(private val itemName: String) : ParameterisedCommand("drop", itemName) {
    fun getItem(): Item = Item(itemName) //TODO: Abstract Drop/Take -> ItemCommand
}