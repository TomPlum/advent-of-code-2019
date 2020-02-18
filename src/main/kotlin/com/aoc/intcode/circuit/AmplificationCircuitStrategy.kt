package com.aoc.intcode.circuit

import com.aoc.intcode.amplifier.PhaseSettings

interface AmplificationCircuitStrategy {
    fun calculateThrusterSignal(software: String, phaseSettings: PhaseSettings): Int
    fun getPhaseSettingConfiguration(): List<Int>
}