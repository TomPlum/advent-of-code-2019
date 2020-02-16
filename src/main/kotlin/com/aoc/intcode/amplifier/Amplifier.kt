package com.aoc.intcode.amplifier

interface Amplifier {
    fun inputSignal(inputSignal: Int)
    fun outputsTo(amplifier: Amplifier)
    fun loadAmplifierControllerSoftware(software: String)
}