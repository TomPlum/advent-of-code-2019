package com.aoc.intcode.vacuum.function

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.vacuum.function.FunctionID.*
import com.aoc.intcode.vacuum.function.FunctionParameter.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MovementRoutineTest {
    @Test
    fun toStringTest() {
        val a = MovementFunctionA().add(RIGHT, 8).add(RIGHT, 8)
        val b = MovementFunctionB().add(RIGHT, 4).add(RIGHT, 4).add(RIGHT, 8)
        val c = MovementFunctionC().add(LEFT, 6).add(LEFT, 2)
        val routine = MovementRoutine(a, b, c).create(A, B, C, B, A, C)
        assertThat(routine.toString()).isEqualTo("A,B,C,B,A,C")
    }

    @Test
    @DisplayName("Given a Movement Routine, when getting a function, then it should return them in FIFO order")
    fun getFunction() {
        val a = MovementFunctionA().add(RIGHT, 8).add(RIGHT, 8)
        val b = MovementFunctionB().add(RIGHT, 4).add(RIGHT, 4).add(RIGHT, 8)
        val c = MovementFunctionC().add(LEFT, 6).add(LEFT, 2)
        val routine = MovementRoutine(a, b, c).create(A, B, C, B, A, C)

        assertThat(routine.getFunction()).isEqualTo(a)
        assertThat(routine.getFunction()).isEqualTo(b)
        assertThat(routine.getFunction()).isEqualTo(c)
        assertThat(routine.getFunction()).isEqualTo(b)
        assertThat(routine.getFunction()).isEqualTo(a)
        assertThat(routine.getFunction()).isEqualTo(c)
    }

    @Test
    fun getMovementRoutine() {
        val a = MovementFunctionA().add(RIGHT, 8).add(RIGHT, 8)
        val b = MovementFunctionB().add(RIGHT, 4).add(RIGHT, 4).add(RIGHT, 8)
        val c = MovementFunctionC().add(LEFT, 6).add(LEFT, 2)
        val routine = MovementRoutine(a, b, c).create(A, A, B, C, B, C, B, C)
        assertThat(routine.getRoutine()).isEqualTo(listOf<Long>(65,44,65,44,66,44,67,44,66,44,67,44,66,44,67,10))
    }
}