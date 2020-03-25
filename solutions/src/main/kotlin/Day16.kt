package com.aoc.solutions

import com.aoc.radio.Receiver
import com.aoc.radio.Transmitter
import input.Day
import input.InputReader

fun main() {
    val data = InputReader().readInputAsSingleString(Day.from(16))
    val signal = Receiver().listen(data)
    println("Solution Part 1: ${Transmitter(signal).flawedFrequencyTransmission(100).getFirstNValues(8)}")
    println("Solution Part 2: ${Transmitter(signal.convertToRealSignal()).decodeRealSignalMessage()}")
}