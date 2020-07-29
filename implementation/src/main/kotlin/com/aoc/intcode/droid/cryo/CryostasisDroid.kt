package com.aoc.intcode.droid.cryo

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.droid.cryo.command.Command
import com.aoc.log.AdventLogger

class CryostasisDroid(instructions: String) {
    private val cpu = IntCodeComputer(instructions)
    private val inventory = Inventory()

    fun boot() {
        cpu.run()
        logOutput()
    }

    fun command(command: Command) {
        cpu.program.memory.input.add(command.encode())
        cpu.run()
        val output = cpu.program.memory.output.parseStringFromAscii()
        logOutput()
    }

    private fun logOutput() {
        val output = cpu.program.memory.output
        AdventLogger.info(output.parseStringFromAscii())
        output.clear()
    }

}