package com.aoc.intcode.droid.spring

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.droid.spring.register.read.DistanceCode
import com.aoc.intcode.droid.spring.register.read.GroundSensorRegister
import com.aoc.intcode.droid.spring.register.write.JumpRegister
import com.aoc.log.AdventLogger

class SpringDroid(instructions: String) {
    private val computer = IntCodeComputer(instructions)

    fun surveyHull() : HullDamageReport {
        computer.run()
        AdventLogger.info(getOutput())
        return HullDamageReport(0)
    }

    private fun getManualProgram(): SpringScriptProgram {
        val program = SpringScriptProgram()
        program.addInstruction(SpringScriptInstruction(LogicGate.NOT, GroundSensorRegister(DistanceCode.A), JumpRegister()))
        return program
    }

    private fun getOutput() = computer.program.memory.output.parseStringFromAscii()

}