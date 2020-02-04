package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.OpCode
import com.aoc.intcode.ParameterMode
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class AddTest {
    private val strategy = Add()

    @Test
    @DisplayName("Given a single digit Add OpCode, when executing the strategy," +
    "then it should store the value of the sum of the first two parameters in the third, in POSITION mode")
    fun addPositionMode() {
        val opCode = OpCode("1")
        val memorySnapshot = Memory(listOf(1,0,0,0))

        val finalSnapshot = strategy.execute(memorySnapshot, StrategyTestUtility.getParameterModes(opCode))

        assertThat(finalSnapshot).isEqualTo(Memory(listOf(2,0,0,0)))
    }

    @Test
    @DisplayName("Given a four digit Add OpCode (1101), when executing the strategy, then it should store the value of" +
            "the immediate sum of the first two parameters as the address index of the third parameter")
    fun addImmediateMode() {
        val opCode = OpCode("1101")
        val memorySnapshot = Memory(listOf(1101,56,11,3,99))
        val finalSnapshot = strategy.execute(memorySnapshot, StrategyTestUtility.getParameterModes(opCode))
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(1101,56,11,67,99)))
    }

    @Test
    @DisplayName("Given a valid memory snapshot with a non-zero instruction pointer, when executing add," +
            "then it should update the correct addresses in memory")
    fun addWithNonZeroInstructionPointer() {
        val opCode = OpCode("1")
        val memorySnapshot = Memory(listOf(3,4,99,1,3,3,3))
        memorySnapshot.incrementInstructionPointer(3)

        val finalSnapshot = strategy.execute(memorySnapshot, StrategyTestUtility.getParameterModes(opCode))

        assertThat(finalSnapshot).isEqualTo(Memory(listOf(3,4,99,2,3,3,3)))
    }


}
