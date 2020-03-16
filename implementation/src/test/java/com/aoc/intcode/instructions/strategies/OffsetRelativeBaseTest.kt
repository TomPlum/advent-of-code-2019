package com.aoc.intcode.instructions.strategies

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.OpCode
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OffsetRelativeBaseTest {
    val strategy = OffsetRelativeBase()

    @Test
    @DisplayName("Given an OpCode (9), when executing the strategy, then it should offset the relative base by the value" +
            "at the index given by the first parameter")
    fun positionMode() {
        val opCode = OpCode("9")
        val memorySnapshot = Memory(listOf(9,0))
        strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(memorySnapshot.relativeBase).isEqualTo(9)
    }

    @Test
    @DisplayName("Given a relative base of 2000, after executing the instruction 109,19, then it should set the relative base to 2019")
    fun immediateMode() {
        val opCode = OpCode("109")
        val memorySnapshot = Memory(listOf(109,19))
        memorySnapshot.relativeBase = 2000
        strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(memorySnapshot.relativeBase).isEqualTo(2019)
    }

    @Test
    @DisplayName("Given an OpCode (209), when executing the strategy, then it should offset the relative base by the" +
            "value at the index of the sum of the current relative base and the first parameter")
    fun relativeMode() {
        val opCode = OpCode("209")
        val memorySnapshot = Memory(listOf(209,-1856))
        memorySnapshot.relativeBase = 1857
        strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(memorySnapshot.relativeBase).isEqualTo(1)
    }
}