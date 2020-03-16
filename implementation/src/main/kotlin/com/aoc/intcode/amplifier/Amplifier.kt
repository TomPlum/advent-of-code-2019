package com.aoc.intcode.amplifier

interface Amplifier {
    fun inputSignal(inputSignal: Long)
    fun outputsTo(amplifier: Amplifier)
    fun loadAmplifierControllerSoftware(software: String)
}