package com.aoc.intcode.droid.cryo.command.item

import com.aoc.intcode.droid.cryo.command.types.ItemCommand
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.droid.Item
import com.aoc.intcode.droid.cryo.map.Room
import com.aoc.intcode.droid.cryo.command.runtime.CommandRuntime
import com.aoc.intcode.droid.cryo.command.CommandParser
import com.aoc.intcode.droid.cryo.droid.Inventory

/**
 * An [ItemCommand] issued to the [CryostasisDroid] via the [CommandRuntime] instructing it to take an [Item]
 * from it's [Inventory] and drop it on the floor in the current [Room].
 * @see CommandParser
 */
data class DropCommand(private val itemName: String) : ItemCommand("drop", itemName)