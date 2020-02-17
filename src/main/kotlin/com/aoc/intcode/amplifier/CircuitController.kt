package com.aoc.intcode.amplifier

class CircuitController(private val software: String) {
    fun calculateMaximumThrusterSignal(circuitStrategy: AmplificationCircuitStrategy): Int {
        val phaseSettingCombinations = PhaseSettings.getAllPossiblePhaseSettingCombinations(mutableListOf(0, 1, 2, 3, 4))

        val thrusterSignals = mutableListOf<Int>()

        for (phaseSettings in phaseSettingCombinations) {
            thrusterSignals.add(circuitStrategy.calculateThrusterSignal(software, phaseSettings))
        }

        return thrusterSignals.max()!!
    }
}