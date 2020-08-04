package com.aoc.intcode.droid.cryo.command.types

import com.aoc.intcode.droid.cryo.droid.Item
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.command.CommandParser
import com.aoc.intcode.droid.cryo.command.item.TakeCommand
import com.aoc.intcode.droid.cryo.command.item.DropCommand

/**
 * A [Command] issued to the [CryostasisDroid] instructing it to interact with an [Item].
 * @see CommandParser
 * @see DropCommand
 * @see TakeCommand
 */
abstract class ItemCommand(instruction: String, private val name: String) : ParameterisedCommand(instruction, name) {
    fun getItem(): Item = Item(name)
}