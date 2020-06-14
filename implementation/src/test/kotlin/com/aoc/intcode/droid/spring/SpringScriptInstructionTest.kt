package com.aoc.intcode.droid.spring

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.droid.spring.register.read.DistanceCode
import com.aoc.intcode.droid.spring.register.read.GroundSensorRegister
import com.aoc.intcode.droid.spring.register.write.JumpRegister
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
        assertThat(instruction.encode()).isEqualTo(listOf<Long>(78,79,84,65,74))
    }
}