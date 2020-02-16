package com.aoc.intcode.amplifier

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.UnsupportedOperationException

class OutputAmplifierTest {
    @Test
    @DisplayName("Given an OutputAmplifier, when setting the output Amplifier, then it should throw an UOE")
    fun outputsToShouldThrow() {
        val e = assertThrows<UnsupportedOperationException> { OutputAmplifier(0).outputsTo(ThrustAmplifier(0)) }
        assertThat(e.message).isEqualTo("An Output Amplifier does not send it's signal to another Amplifier")
    }
}