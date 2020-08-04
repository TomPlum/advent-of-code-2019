package com.aoc.intcode.droid.cryo.command.runtime

import com.aoc.intcode.droid.cryo.command.CommandParser
import com.aoc.intcode.droid.cryo.command.system.HelpCommand
import com.aoc.intcode.droid.cryo.command.system.QuitCommand
import com.aoc.intcode.droid.cryo.exceptions.InvalidCommand
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.command.types.SystemCommand
import com.aoc.intcode.droid.cryo.command.types.Command
import com.aoc.intcode.droid.cryo.droid.DroidLogger

/**
 * The [CryostasisDroid] runtime environment.
 * Accepts text commands via a [CommandReader] and issues the parsed commands to the droid.
 *
 * The [CommandRuntime] handles [SystemCommand]s, where as any regular [Command]s are sent to the droid.
 * An invalid command that is un-recognised by the [CommandParser] will throw an [InvalidCommand] and is handled
 * gracefully.
 */
class CommandRuntime(instructions: String) {
    private val droid = CryostasisDroid(instructions)
    private val parser = CommandParser()
    var reader: CommandReader = DroidCommandReader()
    var logger = DroidLogger()

    fun start() {
        logger.log("Booting Cryostasis Droid runtime environment...\n")

        droid.boot()

        while(true) {
            try {
                when(val command = parser.parse(reader.read())) {
                    is HelpCommand -> logger.log(command.getCommands())
                    is QuitCommand -> return
                    else -> droid.command(command)
                }
            } catch (e: InvalidCommand) {
                logger.log("${e.message}")
                logger.log("Use command 'help' to view a complete list of commands\n")
            }
        }
    }

}