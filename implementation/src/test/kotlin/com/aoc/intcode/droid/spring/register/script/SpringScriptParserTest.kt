package com.aoc.intcode.droid.spring.register.script

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.droid.spring.register.read.DistanceCode
import com.aoc.intcode.droid.spring.register.read.GroundSensorRegister
import com.aoc.intcode.droid.spring.register.write.JumpRegister
import com.aoc.intcode.droid.spring.script.LogicGate
import com.aoc.intcode.droid.spring.script.SpringScriptInstruction
import com.aoc.intcode.droid.spring.script.SpringScriptParser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class SpringScriptParserTest {
    @Test
    fun parseInstructionValid() {
        val instruction = SpringScriptParser().parseInstruction("NOT A J")
        val expected = SpringScriptInstruction(LogicGate.NOT, GroundSensorRegister(DistanceCode.A), JumpRegister())
        assertThat(instruction).isEqualTo(expected)
    }

    @Test
    fun parseInstructionTooFewArguments() {
        val e = assertThrows<IllegalArgumentException> { SpringScriptParser().parseInstruction("AND B") }
        assertThat(e.message).isEqualTo("Invalid Instruction String (AND B). Requires 3 arguments but received 2")
    }

    @Test
    fun parseInstructionTooManyArguments() {
        val e = assertThrows<IllegalArgumentException> { SpringScriptParser().parseInstruction("OR B T A") }
        assertThat(e.message).isEqualTo("Invalid Instruction String (OR B T A). Requires 3 arguments but received 4")
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["K", "AB", "AND", "NOT", "OR"])
    fun parseInstructionInvalidFirstArgument(arg: String) {
        val e = assertThrows<IllegalArgumentException> { SpringScriptParser().parseInstruction("AND $arg J") }
        assertThat(e.message).isEqualTo("Invalid 1st Argument ($arg)")
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["A", "B", "C", "D", "K", "AND", "NOT", "OR"])
    fun parseInstructionInvalidSecondArgument(arg: String) {
        val e = assertThrows<IllegalArgumentException> { SpringScriptParser().parseInstruction("AND A $arg") }
        assertThat(e.message).isEqualTo("Invalid 2nd (Writable) Argument ($arg)")
    }
}