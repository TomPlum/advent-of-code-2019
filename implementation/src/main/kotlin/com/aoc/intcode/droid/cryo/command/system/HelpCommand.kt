package com.aoc.intcode.droid.cryo.command.system

import com.aoc.intcode.droid.cryo.command.types.SystemCommand
import com.aoc.intcode.droid.cryo.command.runtime.CommandRuntime
import com.aoc.intcode.droid.cryo.command.CommandParser


/**
 * A [SystemCommand] that provides the user with details of the available commands they can run via the [CommandRuntime].
 * @see CommandParser
 */
class HelpCommand : SystemCommand() {
    fun getCommands(): String {
        val s = StringBuilder()
        s.append("You can move via 'north', 'east', 'south', 'west'").append("\n")
        s.append("You can take an item with 'take <item name>'").append("\n")
        s.append("You can drop an item with 'drop <item name>'").append("\n")
        s.append("You can get a list of all the items current in the droid inventory with 'inv'").append("\n")
        return s.toString()
    }
}