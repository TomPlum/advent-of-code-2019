package com.aoc.intcode.droid.cryo.command.runtime

import com.aoc.intcode.droid.cryo.CommandParser
import com.aoc.intcode.droid.cryo.command.system.HelpCommand
import com.aoc.intcode.droid.cryo.command.system.QuitCommand
import com.aoc.intcode.droid.cryo.exceptions.InvalidCommand
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.droid.DroidLogger

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