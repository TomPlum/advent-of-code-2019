package com.aoc.intcode.droid.cryo.command

import com.aoc.intcode.droid.cryo.command.system.HelpCommand
import com.aoc.intcode.droid.cryo.command.system.QuitCommand
import com.aoc.intcode.droid.cryo.command.types.Command
import com.aoc.intcode.droid.cryo.exceptions.InvalidCommand

class CommandParser {
    fun parse(instruction: String?): Command = when {
        instruction == null -> throw InvalidCommand("NULL")
        instruction.isQuit() -> QuitCommand()
        instruction.isHelp() -> HelpCommand()
        instruction.isTakeCommand() -> TakeCommand(instruction.getParameter())
        instruction.isDropCommand() -> DropCommand(instruction.getParameter())
        instruction.isMovementCommand() -> MovementCommand(instruction)
        instruction.isViewInventoryCommand() -> InventoryCommand()
        else -> throw InvalidCommand(instruction)
    }

    private fun String.isQuit() = this.trim() == "quit"
    private fun String.isHelp() = this.trim() == "help"
    private fun String.isTakeCommand() = this.take(5) == "take "
    private fun String.isDropCommand() = this.take(5) == "drop "
    private fun String.isMovementCommand() = listOf("north", "east", "south", "west").contains(this)
    private fun String.isViewInventoryCommand() = this == "inv"
    private fun String.getParameter() = this.drop(4).trim()
}