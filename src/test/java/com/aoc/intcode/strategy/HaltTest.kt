package com.aoc.intcode.strategy

import com.aoc.intcode.exceptions.HaltProgram
import com.aoc.intcode.Memory
import com.aoc.intcode.OpCode
import com.aoc.intcode.instructions.strategies.Halt
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HaltTest {
    private val strategy = Halt()

    @Test
    @DisplayName("Given a valid memory snapshot, when executing the Halt strategy, then it should throw a HaltProgram exception")
    fun shouldThrowException() {
        val opCode = OpCode("99")
        val memorySnapshot = Memory(listOf(99))
        assertThrows<HaltProgram> { strategy.execute(memorySnapshot, opCode.parameterModes) }
    }
}