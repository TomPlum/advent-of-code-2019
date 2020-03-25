package com.aoc.radio

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.Day
import input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TransmitterTest {
    @Test
    fun exampleOne() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-1.txt")
        val inputSignal = Receiver().listen(data)
        val outputSignal = Transmitter(inputSignal).flawedFrequencyTransmission(4)
        assertThat(outputSignal.getFirstNValues(8).toString()).isEqualTo("01029498")
    }

    @Test
    fun exampleTwo() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-2.txt")
        val inputSignal = Receiver().listen(data)
        val outputSignal = Transmitter(inputSignal).flawedFrequencyTransmission(100)
        assertThat(outputSignal.getFirstNValues(8).toString()).isEqualTo("24176176")
    }

    @Test
    fun exampleThree() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-3.txt")
        val inputSignal = Receiver().listen(data)
        val outputSignal = Transmitter(inputSignal).flawedFrequencyTransmission(100)
        assertThat(outputSignal.getFirstNValues(8).toString()).isEqualTo("73745418")
    }

    @Test
    fun exampleFour() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-4.txt")
        val inputSignal = Receiver().listen(data)
        val outputSignal = Transmitter(inputSignal).flawedFrequencyTransmission(100)
        assertThat(outputSignal.getFirstNValues(8).toString()).isEqualTo("52432133")
    }

    @Test
    @DisplayName("Given Day 16 Puzzle Input, after 100 phases of FFT, then the first eight digits of the output signal" +
    "should be 77038830")
    fun solutionPartOne() {
        val data = InputReader().readInputAsSingleString(Day.from(16))
        val inputSignal = Receiver().listen(data)
        val outputSignal = Transmitter(inputSignal).flawedFrequencyTransmission(100)
        assertThat(outputSignal.getFirstNValues(8).toString()).isEqualTo("77038830")
    }

    @Test
    fun partTwoExampleOne() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-5.txt")
        val inputSignal = Receiver().listen(data).convertToRealSignal()
        val transmitter = Transmitter(inputSignal)
        val decodedSignalMessage = transmitter.decodeRealSignalMessage()
        assertThat(decodedSignalMessage.toString()).isEqualTo("84462026")
    }

    @Test
    fun partTwoExampleTwo() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-6.txt")
        val inputSignal = Receiver().listen(data).convertToRealSignal()
        val transmitter = Transmitter(inputSignal)
        val decodedSignalMessage = transmitter.decodeRealSignalMessage()
        assertThat(decodedSignalMessage.toString()).isEqualTo("78725270")
    }

    @Test
    fun partTwoExampleThree() {
        val data = InputReader().readInputAsSingleString("/radio/example-input-7.txt")
        val inputSignal = Receiver().listen(data).convertToRealSignal()
        val transmitter = Transmitter(inputSignal)
        val decodedSignalMessage = transmitter.decodeRealSignalMessage()
        assertThat(decodedSignalMessage.toString()).isEqualTo("53553731")
    }

    @Test
    fun solutionPartTwo() {
        val data = InputReader().readInputAsSingleString(Day.from(16))
        val inputSignal = Receiver().listen(data).convertToRealSignal()
        val outputSignal = Transmitter(inputSignal).decodeRealSignalMessage()
        assertThat(outputSignal.toString()).isEqualTo("28135104")
    }
}