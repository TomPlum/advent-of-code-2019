package com.aoc.intcode.amplifier

interface AmplificationCircuitStrategy {
    fun calculateThrusterSignal(software: String, phaseSettings: PhaseSettings): Int
}