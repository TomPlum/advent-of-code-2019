package com.aoc.intcode.droid.cryo.command

import com.aoc.intcode.droid.cryo.command.runtime.CommandRuntime
import com.aoc.intcode.droid.cryo.command.types.Command
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.droid.Inventory
import com.aoc.intcode.droid.cryo.droid.Item

/**
 * A command issued to the [CryostasisDroid] via the [CommandRuntime] that instructs it to report the current
 * [Item]s stored in it's [Inventory].
 * @see CommandParser
 */
class InventoryCommand : Command("inv")