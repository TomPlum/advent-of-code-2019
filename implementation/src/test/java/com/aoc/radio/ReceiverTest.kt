package com.aoc.radio

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.InputReader
import org.junit.jupiter.api.Test

class ReceiverTest {
    @Test
    fun receiverShouldParseDataInputWhenListening() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-1.txt")
        val signal = Receiver().listen(data)
        assertThat(signal).isEqualTo(Signal(listOf(1,2,3,4,5,6,7,8)))
    }
}