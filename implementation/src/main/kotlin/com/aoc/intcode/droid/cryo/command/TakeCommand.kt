package com.aoc.intcode.droid.cryo.command

import com.aoc.intcode.droid.cryo.command.runtime.CommandRuntime
import com.aoc.intcode.droid.cryo.command.types.ItemCommand
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.droid.Item
import com.aoc.intcode.droid.cryo.droid.Inventory
import com.aoc.intcode.droid.cryo.map.Room

/**
 * An [ItemCommand] issued to the [CryostasisDroid] via the [CommandRuntime] instructing it to take an [Item]
 * from the current [Room] and store it in it's [Inventory].
 * @see CommandParser
 */
data class TakeCommand(private val itemName: String) : ItemCommand("take", itemName)