package com.aoc.intcode.droid.spring.register.script

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.intcode.droid.spring.register.read.DistanceCode
import com.aoc.intcode.droid.spring.register.read.GroundSensorRegister
import com.aoc.intcode.droid.spring.register.write.JumpRegister
import com.aoc.intcode.droid.spring.register.write.TemporaryValueRegister
import com.aoc.intcode.droid.spring.script.LogicGate
import com.aoc.intcode.droid.spring.script.SpringScriptInstruction
import org.junit.jupiter.api.Test

class SpringScriptInstructionTest {
    @Test
    fun toStringTest() {
        val instruction = SpringScriptInstruction(LogicGate.NOT, GroundSensorRegister(DistanceCode.A), JumpRegister())
        assertThat(instruction.toString()).isEqualTo("NOT A J")
    }

    @Test
    fun encode() {
        val instruction = SpringScriptInstruction(LogicGate.NOT, GroundSensorRegister(DistanceCode.A), JumpRegister())
        assertThat(instruction.encode()).isEqualTo(listOf<Long>(78,79,84,32,65,32,74))
    }

    @Test
    fun equalityPositive() {
        val instruction = SpringScriptInstruction(LogicGate.NOT, GroundSensorRegister(DistanceCode.A), JumpRegister())
        val other = SpringScriptInstruction(LogicGate.NOT, GroundSensorRegister(DistanceCode.A), JumpRegister())
        assertThat(instruction).isEqualTo(other)
    }

    @Test
    fun equalityNegative() {
        val instruction = SpringScriptInstruction(LogicGate.NOT, GroundSensorRegister(DistanceCode.A), JumpRegister())
        val other = SpringScriptInstruction(LogicGate.AND, GroundSensorRegister(DistanceCode.B), TemporaryValueRegister())
        assertThat(instruction).isNotEqualTo(other)
    }
}