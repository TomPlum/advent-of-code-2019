package com.aoc.intcode.droid.spring

import com.aoc.intcode.droid.spring.register.read.DistanceCode
import com.aoc.intcode.droid.spring.register.read.GroundSensorRegister
import com.aoc.intcode.droid.spring.register.write.JumpRegister
import com.aoc.intcode.droid.spring.register.write.TemporaryValueRegister
import java.lang.IllegalArgumentException

class SpringScriptParser {
    fun parseInstruction(instruction: String): SpringScriptInstruction {
        val args = instruction.split(" ")
        if (args.size != 3) {
            throw IllegalArgumentException("Invalid Instruction String ($instruction). Requires 3 arguments but received ${args.size}")
        }

        val logicGate = LogicGate.valueOf(args[0])
        val firstArg = parseRegister(args[1])
        val secondArg = parseWritableRegister(args[2])

        return SpringScriptInstruction(logicGate, firstArg, secondArg)
    }

    private fun parseRegister(value: String) = when(value) {
        "T" -> TemporaryValueRegister()
        "J" -> JumpRegister()
        "A" -> GroundSensorRegister(DistanceCode.A)
        "B" -> GroundSensorRegister(DistanceCode.B)
        "C" -> GroundSensorRegister(DistanceCode.C)
        "D" -> GroundSensorRegister(DistanceCode.D)
        else -> throw IllegalArgumentException("Invalid 1st Argument ($value)")
    }

    private fun parseWritableRegister(value: String) = when(value) {
        "T" -> TemporaryValueRegister()
        "J" -> JumpRegister()
        else -> throw IllegalArgumentException("Invalid 2nd (Writable) Argument ($value)")
    }
}