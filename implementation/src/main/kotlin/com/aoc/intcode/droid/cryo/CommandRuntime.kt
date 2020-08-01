package com.aoc.intcode.droid.cryo

import com.aoc.intcode.droid.cryo.command.CommandParser
import com.aoc.intcode.droid.cryo.command.InvalidCommand
import com.aoc.log.AdventLogger

class CommandRuntime(instructions: String) {
    private val droid = CryostasisDroid(instructions)
    private val parser = CommandParser()

    init {
        AdventLogger.info("Booting Cryostasis Droid runtime environment...")
    }

    fun start() {
        droid.boot()

        while(true) {
            try {
                droid.command(parser.parse(readLine()))
            } catch (e: InvalidCommand) {
                AdventLogger.info("${e.message}")
                AdventLogger.info("Use command 'view commands' to view a complete list of commands\n")
            }
        }
    }

}