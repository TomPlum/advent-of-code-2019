package com.aoc.intcode.droid.spring

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.droid.spring.register.read.DistanceCode
import com.aoc.intcode.droid.spring.register.read.GroundSensorRegister
import com.aoc.intcode.droid.spring.register.write.JumpRegister
import com.aoc.intcode.droid.spring.register.write.TemporaryValueRegister
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
        program.addInstruction(SpringScriptInstruction(LogicGate.NOT, GroundSensorRegister(DistanceCode.A), JumpRegister()))
        program.addInstruction(SpringScriptInstruction(LogicGate.NOT, GroundSensorRegister(DistanceCode.B), TemporaryValueRegister()))
        program.addInstruction(SpringScriptInstruction(LogicGate.AND, TemporaryValueRegister(), JumpRegister()))
        program.addInstruction(SpringScriptInstruction(LogicGate.NOT, GroundSensorRegister(DistanceCode.C), TemporaryValueRegister()))
        program.addInstruction(SpringScriptInstruction(LogicGate.AND, TemporaryValueRegister(), JumpRegister()))
        program.addInstruction(SpringScriptInstruction(LogicGate.AND, GroundSensorRegister(DistanceCode.D), JumpRegister()))
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