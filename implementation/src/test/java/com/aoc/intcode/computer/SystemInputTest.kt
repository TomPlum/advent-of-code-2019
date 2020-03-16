package com.aoc.intcode.computer

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.computer.SystemInput
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SystemInputTest {
    @Test
    fun add() {
        val systemInput = SystemInput()
        systemInput.add(92)
        assertThat(systemInput.values[0]).isEqualTo(92)
    }

    @Test
    fun getValueWhenOnlyOneValue() {
        val systemInput = SystemInput()
        systemInput.add(62)
        assertThat(systemInput.getValue()).isEqualTo(62)
    }

    @Test
    @DisplayName("Given a System Input with 2 values, when getting value, then it should return the first value added")
    fun getValueWhenMultipleValues() {
        val systemInput = SystemInput()
        systemInput.add(12)
        systemInput.add(57)
        assertThat(systemInput.getValue()).isEqualTo(12)
    }

    @Test
    @DisplayName("Given a System Input with 2 values, when getting value, then it should remove the first value added")
    fun getValueWhenMultipleValuesShouldRemove() {
        val systemInput = SystemInput()
        systemInput.add(12)
        systemInput.add(57)
        systemInput.getValue()
        assertThat(systemInput.values.size).isEqualTo(1)
    }

    @Test
    @Disabled("Not required anymore as need to return NULL if empty")
    @DisplayName("Given an empty system input, when getting value, then it should thrown an IllegalStateException")
    fun getValueWhenSystemInputEmpty() {
        val e = assertThrows<IllegalStateException> { SystemInput().getValue() }
        assertThat(e.message).isEqualTo("System input is empty!")
    }

    @Test
    fun toStringTest() {
        val systemInput = SystemInput()
        systemInput.add(7)
        systemInput.add(4)
        systemInput.add(1)
        assertThat(systemInput.toString()).isEqualTo("[7, 4, 1]")
    }
}