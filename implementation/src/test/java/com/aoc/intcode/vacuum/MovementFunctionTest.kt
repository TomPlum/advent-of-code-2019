package com.aoc.intcode.vacuum

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.vacuum.FunctionParameter.*
import org.junit.jupiter.api.Test

class MovementFunctionTest {
    @Test
    fun exampleFunctionA() {
        val movementFunction = MovementFunction('A').add(RIGHT, 8).add(RIGHT, 8)
        val sequence = movementFunction.getSequence()
        assertThat(sequence).isEqualTo(listOf(82,44,56,44,82,44,56,10))
    }

    @Test
    fun exampleFunctionB() {
        val movementFunction = MovementFunction('B').add(RIGHT, 4).add(RIGHT, 4).add(RIGHT, 8)
        val sequence = movementFunction.getSequence()
        assertThat(sequence).isEqualTo(listOf(82,44,52,44,82,44,52,44,82,44,56,10))
    }

    @Test
    fun exampleFunctionC() {
        val movementFunction = MovementFunction('C').add(LEFT, 6).add(LEFT, 2)
        val sequence = movementFunction.getSequence()
        assertThat(sequence).isEqualTo(listOf(76,44,54,44,76,44,50,10))
    }

    @Test
    fun functionHasUnitValueGreaterThan9() {
        assertThat(MovementFunction('A').add(LEFT, 12).getSequence()).isEqualTo(listOf(76,44,49,50,10))
    }
}