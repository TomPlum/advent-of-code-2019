package com.aoc.radio

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.InputReader
import org.junit.jupiter.api.Test

class TransmitterTest {
    @Test
    fun exampleOne() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-1.txt")
        val inputSignal = Receiver().listen(data)
        val outputSignal = Transmitter(inputSignal).flawedFrequencyTransmission(4)
        assertThat(outputSignal.toString()).isEqualTo("01029498")
    }

    @Test
    fun exampleTwo() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-2.txt")
        val inputSignal = Receiver().listen(data)
        val outputSignal = Transmitter(inputSignal).flawedFrequencyTransmission(100)
        assertThat(outputSignal.toString()).isEqualTo("24176176")
    }

    @Test
    fun exampleThree() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-3.txt")
        val inputSignal = Receiver().listen(data)
        val outputSignal = Transmitter(inputSignal).flawedFrequencyTransmission(100)
        assertThat(outputSignal.toString()).isEqualTo("73745418")
    }

    @Test
    fun exampleFour() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-4.txt")
        val inputSignal = Receiver().listen(data)
        val outputSignal = Transmitter(inputSignal).flawedFrequencyTransmission(100)
        assertThat(outputSignal.toString()).isEqualTo("52432133")
    }
}