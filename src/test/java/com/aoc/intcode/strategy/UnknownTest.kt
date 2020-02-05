package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.OpCode
import com.aoc.intcode.instructions.strategies.Unknown
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class UnknownTest {
    private val strategy = Unknown()

    @Test
    @DisplayName("Given an OpCode with an unknown value, when executing the strategy, then it should throw an IllegalArgumentException")
    fun unknownOpCode() {
        val opCode = OpCode("89230")
        val memorySnapshot = Memory(listOf(89320))
        val e = assertThrows<IllegalArgumentException> { strategy.execute(memorySnapshot, opCode.parameterModes) }
        assertThat(e.message).isEqualTo("Operation unknown for instruction 89320")
    }
}