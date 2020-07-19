package com.aoc.intcode.droid.spring.register.script

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.droid.spring.register.read.DistanceCode
import com.aoc.intcode.droid.spring.register.read.GroundSensorRegister
import com.aoc.intcode.droid.spring.register.write.JumpRegister
import com.aoc.intcode.droid.spring.register.write.TemporaryValueRegister
import com.aoc.intcode.droid.spring.script.Command
import com.aoc.intcode.droid.spring.script.LogicGate
import com.aoc.intcode.droid.spring.script.SpringScriptInstruction
import com.aoc.intcode.droid.spring.script.SpringScriptProgram
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SpringScriptProgramTest {
    @Test
    fun toStringTest() {
        val program = SpringScriptProgram(Command.WALK)
        program.addInstruction(SpringScriptInstruction(LogicGate.NOT, TemporaryValueRegister(), JumpRegister()))
        program.addInstruction(SpringScriptInstruction(LogicGate.AND, GroundSensorRegister(DistanceCode.B), TemporaryValueRegister()))
        assertThat(program.toString()).isEqualTo("NOT T J\nAND B T\nWALK")
    }

    @Test
    fun encode() {
        val program = SpringScriptProgram(Command.WALK)
        program.addInstruction(SpringScriptInstruction(LogicGate.NOT, TemporaryValueRegister(), JumpRegister()))
        program.addInstruction(SpringScriptInstruction(LogicGate.AND, GroundSensorRegister(DistanceCode.B), TemporaryValueRegister()))
        assertThat(program.encode()).isEqualTo(listOf<Long>(78,79,84,32,84,32,74,10,65,78,68,32,66,32,84,10,87,65,76,75,10))
    }

    @Test
    fun instructionLimit() {
        val program = SpringScriptProgram(Command.WALK)
        val instruction = SpringScriptInstruction(LogicGate.NOT, TemporaryValueRegister(), JumpRegister())
        repeat(15) {
            program.addInstruction(instruction)
        }
        val e = assertThrows<IllegalStateException> { program.addInstruction(instruction) }
        assertThat(e.message).isEqualTo("A Spring Script program cannot have more than 15 instructions")
    }
}