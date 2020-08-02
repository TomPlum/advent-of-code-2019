package com.aoc.intcode.droid.cryo

import com.aoc.intcode.droid.cryo.command.HelpCommand
import com.aoc.intcode.droid.cryo.exceptions.InvalidCommand
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.log.AdventLogger

class CommandRuntime(instructions: String) {
    private val droid = CryostasisDroid(instructions)
    private val parser = CommandParser()

    init {
        AdventLogger.info("Booting Cryostasis Droid runtime environment...\n")
    }

    fun start() {
        droid.boot()

        while(true) {
            try {
                val command = parser.parse(readLine())
                if (command is HelpCommand) {
                    AdventLogger.info(command.getCommands())
                } else {
                    droid.command(command)
                }
            } catch (e: InvalidCommand) {
                AdventLogger.info("${e.message}")
                AdventLogger.info("Use command 'help' to view a complete list of commands\n")
            }
        }
    }

}