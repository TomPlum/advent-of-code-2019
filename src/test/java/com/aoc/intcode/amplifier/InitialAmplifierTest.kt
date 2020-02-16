package com.aoc.intcode.amplifier

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class InitialAmplifierTest {
    @Test
    @DisplayName("Given an InitialAmplifier, when starting it, then it should send it's input signal as 0")
    fun startShouldInputSignalZero() {
        val amplifier = InitialAmplifier(0)
        amplifier.outputsTo(OutputAmplifier(0))
        amplifier.loadAmplifierControllerSoftware("1,0,0,3,4,3,99")
        amplifier.start()
        assertThat(amplifier.computer.getProgramMemory().input[0]).isEqualTo(0)
    }
}