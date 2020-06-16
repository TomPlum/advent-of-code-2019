package com.aoc.intcode.droid.spring.script

import com.aoc.intcode.droid.spring.register.Register
import com.aoc.intcode.droid.spring.register.read.DistanceCode
import com.aoc.intcode.droid.spring.register.read.GroundSensorRegister
import com.aoc.intcode.droid.spring.register.write.JumpRegister
import com.aoc.intcode.droid.spring.register.write.TemporaryValueRegister
import com.aoc.intcode.droid.spring.register.write.WriteableRegister
import java.lang.IllegalArgumentException

/**
 * Parses literal string representations of [SpringScriptInstruction].
 * Makes it easier to quickly modify a manually written [SpringScriptProgram].
 */
class SpringScriptParser {

    /**
     * Parses the given input [instruction] string as a [SpringScriptInstruction]
     * @throws IllegalArgumentException if the instruction string is malformed
     * @see SpringScriptInstruction
     */
    fun parseInstruction(instruction: String): SpringScriptInstruction {
        val args = instruction.split(" ").map { it.trim() }
        if (args.size != 3) {
            throw IllegalArgumentException("Invalid Instruction String ($instruction). Requires 3 arguments but received ${args.size}")
        }

        val logicGate = LogicGate.valueOf(args[0])
        val firstArg = parseRegister(args[1])
        val secondArg = parseWritableRegister(args[2])

        return SpringScriptInstruction(logicGate, firstArg, secondArg)
    }

    private fun parseRegister(value: String): Register = when(value) {
        "T" -> TemporaryValueRegister()
        "J" -> JumpRegister()
        "A" -> GroundSensorRegister(DistanceCode.A)
        "B" -> GroundSensorRegister(DistanceCode.B)
        "C" -> GroundSensorRegister(DistanceCode.C)
        "D" -> GroundSensorRegister(DistanceCode.D)
        "E" -> GroundSensorRegister(DistanceCode.E)
        "F" -> GroundSensorRegister(DistanceCode.F)
        "G" -> GroundSensorRegister(DistanceCode.G)
        "H" -> GroundSensorRegister(DistanceCode.H)
        "I" -> GroundSensorRegister(DistanceCode.I)
        else -> throw IllegalArgumentException("Invalid 1st Argument ($value)")
    }

    private fun parseWritableRegister(value: String): WriteableRegister = when(value) {
        "T" -> TemporaryValueRegister()
        "J" -> JumpRegister()
        else -> throw IllegalArgumentException("Invalid 2nd (Writable) Argument ($value)")
    }
}