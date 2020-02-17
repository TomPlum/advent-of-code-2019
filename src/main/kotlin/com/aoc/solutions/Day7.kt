package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.amplifier.AmplificationCircuit
import com.aoc.intcode.amplifier.PhaseSettings

fun main() {
    val inputReader = InputReader()
    val software = inputReader.readInputAsSingleString(Day.from(7))
    val perm = PhaseSettings(setOf(0,1,2,3,4)) //TODO: Make static so ignores constructor
    val inputPermutations = perm.getAllPossiblePhaseSettingCombinations(mutableListOf(0, 1, 2, 3, 4))

    val thrusterSignals = mutableListOf<Int>()

    for (input: Set<Int> in inputPermutations) {
        val phaseSettings = PhaseSettings(input)
        val amplificationCircuit = AmplificationCircuit(phaseSettings, software)
        val thrusterSignal = amplificationCircuit.calculateThrusterSignal()
        thrusterSignals.add(thrusterSignal)
    }

    val maximumThrusterSignal = thrusterSignals.max()
    println("Part 1 Solution: $maximumThrusterSignal") //TODO: Answers 21860. Move into other class and test
}

