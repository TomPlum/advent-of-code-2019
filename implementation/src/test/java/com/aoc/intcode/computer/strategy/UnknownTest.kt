package com.aoc.intcode.computer.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.OpCode
import com.aoc.intcode.computer.instructions.strategies.Unknown
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class UnknownTest {
    private val strategy = Unknown()

    @Test
    @DisplayName("Given an OpCode with an unknown value, when executing the strategy, then it should throw an IllegalArgumentException")
    fun unknownOpCode() {
        val opCode = OpCode("12130")
        val memorySnapshot = Memory(listOf(12130))
        val e = assertThrows<IllegalArgumentException> { strategy.execute(memorySnapshot, opCode.parameterModes) }
        assertThat(e.message).isEqualTo("Operation unknown for instruction 12130")
    }
}