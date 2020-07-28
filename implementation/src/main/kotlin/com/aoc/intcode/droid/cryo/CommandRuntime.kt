package com.aoc.intcode.droid.cryo

import com.aoc.intcode.droid.cryo.command.Command
import com.aoc.intcode.droid.cryo.command.CommandParser
import com.aoc.intcode.droid.cryo.command.InvalidCommand
import com.aoc.log.AdventLogger

class CommandRuntime(instructions: String) {
    private val droid = CryostasisDroid(instructions)
    private val parser = CommandParser()

    fun run() {
        AdventLogger.info("Booting Cryostasis Droid runtime environment...\n")
        while(true) {
            try {
                droid.command(readUserInput())
            } catch (e: InvalidCommand) {
                AdventLogger.info("${e.message}\n")
                AdventLogger.info("Use command 'view commands' to view a complete list of commands\n")
            }
        }
    }

    private fun readUserInput(): Command = parser.parse(readLine())
}