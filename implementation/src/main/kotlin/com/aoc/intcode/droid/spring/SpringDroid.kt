package com.aoc.intcode.droid.spring

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.droid.spring.script.SpringScriptParser
import com.aoc.intcode.droid.spring.script.SpringScriptProgram
import com.aoc.log.AdventLogger

class SpringDroid(instructions: String) {
    private val computer = IntCodeComputer(instructions)

    fun surveyHull() : HullDamageReport {
        boot()
        inputProgram(getManualProgram())
        return HullDamageReport(0)
    }

    private fun getManualProgram(): SpringScriptProgram {
        val program = SpringScriptProgram()
        val parser = SpringScriptParser()
        program.addInstruction(parser.parseInstruction("NOT A J"))
        return program
    }

    private fun inputProgram(program: SpringScriptProgram) {
        AdventLogger.info("Inputting Program:\n$program")
        program.encode().forEach { computer.program.memory.input.add(it) }
        computer.run()
        logOutput()
    }

    private fun boot() {
        computer.run()
        logOutput()
    }

    private fun logOutput() = AdventLogger.info(getOutput())

    private fun getOutput() = computer.program.memory.output.parseStringFromAscii()

}