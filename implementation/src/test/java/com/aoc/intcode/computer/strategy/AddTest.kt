package com.aoc.intcode.computer.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.OpCode
import com.aoc.intcode.computer.instructions.strategies.Add
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AddTest {
    private val strategy = Add()

    @Test
    @DisplayName("Given an Add OpCode (1) in POSITION, when executing the strategy, then it should write the sum of the value" +
    "at the index given by the first parameter and the value at the index given by the second parameter to the index" +
    "given by the third parameter")
    fun addPositionMode() {
        val opCode = OpCode("1")
        val memorySnapshot = Memory(listOf(1, 0, 0, 0))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(2, 0, 0, 0)))
    }

    @Test
    @DisplayName("Given an Add OpCode (1101) in IMMEDIATE, when executing the strategy, then it should store the value of" +
    "the immediate sum of the first two parameters as the address index of the third parameter")
    fun addImmediateMode() {
        val opCode = OpCode("1101")
        val memorySnapshot = Memory(listOf(1101, 56, 11, 3, 99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(1101, 56, 11, 67, 99)))
    }

    @Test
    @DisplayName("Given a five digit Add OpCode (22202), when executing the strategy, then it should store the value of" +
    "the sum of the sum of the relative base plus the first two parameters in the address given by the sum of" +
    "the relative base and the third parameter")
    fun addRelativeMode() {
        val opCode = OpCode("22201")
        val memorySnapshot = Memory(listOf(22201, 4, 5, -1, 99, 12, 34))
        memorySnapshot.relativeBase = 1
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(46, 4, 5, -1, 99, 12, 34)))
    }

}
