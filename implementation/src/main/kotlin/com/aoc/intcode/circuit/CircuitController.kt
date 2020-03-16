package com.aoc.intcode.circuit

import com.aoc.intcode.amplifier.PhaseSettings

class CircuitController(private val software: String) {
    fun calculateMaximumThrusterSignal(circuitStrategy: AmplificationCircuitStrategy): Int {
        val phaseSettingValues = circuitStrategy.getPhaseSettingConfiguration()
        val phaseSettingCombinations = PhaseSettings.getAllPossiblePhaseSettingCombinations(phaseSettingValues)

        val thrusterSignals = mutableListOf<Int>()

        for (phaseSettings in phaseSettingCombinations) {
            thrusterSignals.add(circuitStrategy.calculateThrusterSignal(software, phaseSettings))
        }

        return thrusterSignals.max()!!
    }
}