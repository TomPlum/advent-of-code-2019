package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.radio.Receiver
import com.aoc.radio.Transmitter

fun main() {
    val data = InputReader.read<String>(Day(16)).asSingleString()
    val signal = Receiver().listen(data)
    println("Solution Part 1: ${Transmitter(signal).flawedFrequencyTransmission(100).getFirstNValues(8)}")
    println("Solution Part 2: ${Transmitter(signal.convertToRealSignal()).decodeRealSignalMessage()}")
}