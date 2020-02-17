package com.aoc.intcode.amplifier

class CircuitController(private val software: String) {
    fun calculateMaximumThrusterSignal(): Int {
        val phaseSettingCombinations = PhaseSettings.getAllPossiblePhaseSettingCombinations(mutableListOf(0, 1, 2, 3, 4))

        val thrusterSignals = mutableListOf<Int>()

        for (phaseSettings in phaseSettingCombinations) {
            val amplificationCircuit = AmplificationCircuit(phaseSettings, software)
            val thrusterSignal = amplificationCircuit.calculateThrusterSignal()
            thrusterSignals.add(thrusterSignal)
        }

        return thrusterSignals.max()!!
    }
}