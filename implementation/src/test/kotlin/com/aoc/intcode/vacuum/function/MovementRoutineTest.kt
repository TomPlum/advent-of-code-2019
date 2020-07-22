package com.aoc.intcode.vacuum.function

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.vacuum.function.FunctionID.*
import com.aoc.intcode.vacuum.function.FunctionParameter.LEFT
import com.aoc.intcode.vacuum.function.FunctionParameter.RIGHT
import org.junit.jupiter.api.Test

class MovementRoutineTest {
    private val a = MovementFunctionA().add(RIGHT, 8).add(RIGHT, 8)
    private val b = MovementFunctionB().add(RIGHT, 4).add(RIGHT, 4).add(RIGHT, 8)
    private val c = MovementFunctionC().add(LEFT, 6).add(LEFT, 2)

    @Test
    fun toStringTest() {
        assertThat(MovementRoutine(a, b, c).create(A, B, C, B, A, C).toString()).isEqualTo("A,B,C,B,A,C")
    }

    @Test
    fun getMovementRoutine() {
        val routine = MovementRoutine(a, b, c).create(A, A, B, C, B, C, B, C)
        assertThat(routine.getRoutine()).isEqualTo(listOf<Long>(65,44,65,44,66,44,67,44,66,44,67,44,66,44,67,10))
    }

    @Test
    fun getBaseFunctions() {
        assertThat(MovementRoutine(a, b, c).getBaseFunctions()).isEqualTo(setOf(a,b,c))
    }
}