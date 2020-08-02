package com.aoc.intcode.droid.cryo.command.types

import com.aoc.intcode.droid.cryo.droid.Item

abstract class ItemCommand(instruction: String, private val name: String) : ParameterisedCommand(instruction, name) {
    fun getItem(): Item = Item(name)
}