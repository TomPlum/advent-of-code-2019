package com.aoc.intcode.droid.spring

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.droid.spring.script.SpringScriptParser
import com.aoc.intcode.droid.spring.script.SpringScriptProgram
import com.aoc.log.AdventLogger
import com.aoc.intcode.droid.spring.register.read.GroundSensorRegister

/**
 * The [SpringDroid] is deployed on the ships hull in order to survey it for damage. This droids ability to jump
 * makes it the best choice for such as task as damage to the hull results in giant holes in the ship.
 *
 * The droid is controlled via an [IntCodeComputer] that is running a simplified assembly language called 'SpringScript'
 * The [SpringScriptProgram] is executed continuously until the droid either finishes surveying the hull, or falls down
 * a hole into empty space.
 *
 * The droid is also equipped with sensors that can detect whether there is ground at various distances in the direction
 * that it is facing; these values are stored in a [GroundSensorRegister].
 */
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

        //If the tile 1 ahead is empty, set jump to true
        program.addInstruction(parser.parseInstruction("NOT A J"))

        //If the tile 2 ahead is empty, set temp true
        program.addInstruction(parser.parseInstruction("NOT B T"))

        //If temp or jump registers are true, set jump to true
        program.addInstruction(parser.parseInstruction("OR T J"))

        //If the tile 3 ahead is empty, set temp to true
        program.addInstruction(parser.parseInstruction("NOT C T"))

        //If temp or jump registers are true, set jump to true
        program.addInstruction(parser.parseInstruction("OR T J"))

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