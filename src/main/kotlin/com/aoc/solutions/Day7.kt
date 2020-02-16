package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.amplifier.AmplificationCircuit
import com.aoc.intcode.amplifier.PhaseSettings
import java.util.*

fun main() {
    val inputReader = InputReader()
    val software = inputReader.readInputAsSingleString(Day.from(7))
    val perm = PhaseSettingPermutations()
    val inputPermutations = perm.getPhaseSettingInputPermutations(mutableListOf(0, 1, 2, 3, 4), 0)

    val thrusterSignals = mutableListOf<Int>()

    for (input: Set<Int> in inputPermutations) {
        val phaseSettings = PhaseSettings(input)
        val amplificationCircuit = AmplificationCircuit(phaseSettings, software)
        val thrusterSignal = amplificationCircuit.calculateThrusterSignal()
        thrusterSignals.add(thrusterSignal)
    }

    val maximumThrusterSignal = thrusterSignals.max()
    println("Part 1 Solution: $maximumThrusterSignal")
}

class PhaseSettingPermutations {
    private val permutations = mutableListOf<Set<Int>>()

    private fun permute(input: MutableList<Int>, k: Int) {
        for (i in k until input.size) {
            Collections.swap(input, i, k)
            permute(input, k + 1)
            Collections.swap(input, k, i)
        }

        if (k == input.size - 1) {
            permutations.add(input.toSet())
        }
    }

    fun getPhaseSettingInputPermutations(input: MutableList<Int>, k: Int): MutableList<Set<Int>> {
        permute(input, k)
        return permutations
    }
}

