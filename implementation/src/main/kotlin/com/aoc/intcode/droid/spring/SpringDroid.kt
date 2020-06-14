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
        return HullDamageReport(getOutput().getLastValue())
    }

    private fun getManualProgram(): SpringScriptProgram {
        val program = SpringScriptProgram()
        val parser = SpringScriptParser()
        program.addInstruction(parser.parseInstruction("NOT A J")) //If the tile 1 ahead is empty, set jump to true
        program.addInstruction(parser.parseInstruction("NOT B T")) //If the tile 2 ahead is empty, set temp true
        program.addInstruction(parser.parseInstruction("AND D T"))
        program.addInstruction(parser.parseInstruction("OR T J")) //If temp or jump registers are true, set jump to true
        program.addInstruction(parser.parseInstruction("NOT C T")) //If the tile 3 ahead is empty, set temp to true
        program.addInstruction(parser.parseInstruction("OR T J")) //If temp or jump registers are true, set jump to true
        //If the tile 4 ahead is ground and we've not previously set jump to false, set (keep) jump to true
        program.addInstruction(parser.parseInstruction("AND D J"))
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

    private fun logOutput() = AdventLogger.info(getOutput().parseStringFromAscii())

    private fun getOutput() = computer.program.memory.output

}