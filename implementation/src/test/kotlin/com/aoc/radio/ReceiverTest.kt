package com.aoc.radio

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class ReceiverTest {
    @Test
    fun receiverShouldParseDataInputWhenListening() {
        val data = TestInputReader().readInputAsString("/radio/example-input-1.txt").asSingleString()
        val signal = Receiver().listen(data)
        assertThat(signal).isEqualTo(Signal(listOf(1,2,3,4,5,6,7,8)))
    }
}