package com.aoc.intcode.vacuum

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.vacuum.FunctionParameter.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MovementRoutineTest {
    @Test
    fun toStringTest() {
        val a = MovementFunction('A').add(RIGHT, '8').add(RIGHT, '8')
        val b = MovementFunction('B').add(RIGHT, '4').add(RIGHT, '4').add(RIGHT, '8')
        val c = MovementFunction('C').add(LEFT, '6').add(LEFT, '2')
        val routine = MovementRoutine().add(a).add(b).add(c).add(b).add(a).add(c)
        assertThat(routine.toString()).isEqualTo("A,B,C,B,A,C")
    }

    @Test
    @DisplayName("Given a Movement Routine, when getting a function, then it should return them in FIFO order")
    fun getFunction() {
        val a = MovementFunction('A').add(RIGHT, '8').add(RIGHT, '8')
        val b = MovementFunction('B').add(RIGHT, '4').add(RIGHT, '4').add(RIGHT, '8')
        val c = MovementFunction('C').add(LEFT, '6').add(LEFT, '2')

        val routine = MovementRoutine().add(a).add(b).add(c).add(b).add(a).add(c)

        assertThat(routine.getFunction()).isEqualTo(a)
        assertThat(routine.getFunction()).isEqualTo(b)
        assertThat(routine.getFunction()).isEqualTo(c)
        assertThat(routine.getFunction()).isEqualTo(b)
        assertThat(routine.getFunction()).isEqualTo(a)
        assertThat(routine.getFunction()).isEqualTo(c)
    }
}