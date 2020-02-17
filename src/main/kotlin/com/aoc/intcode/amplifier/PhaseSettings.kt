package com.aoc.intcode.amplifier

import java.util.*
import java.util.concurrent.ArrayBlockingQueue

data class PhaseSettings(val input: Set<Int>) {
    private val settings: Queue<Int> = ArrayBlockingQueue(5)

    init {
        if (input.size != 5) {
            throw IllegalArgumentException("A PhaseSettings must have exactly 5 phase settings")
        }

        input.forEach {
            if (it < 0 || it > 4) throw IllegalArgumentException("Phase settings must be between 0 and 4 (inclusive)")
        }

        settings.addAll(input)
    }

    fun getSetting(): Int {
        if (settings.size > 0) {
            return settings.poll()
        }
        throw IllegalCallerException("Invalid setting request. There are no phase settings remaining")
    }

    companion object {
        fun getAllPossiblePhaseSettingCombinations(phaseSettingValues: MutableList<Int>): List<PhaseSettings> {
            val integerArrayPermutations = IntegerArrayPermutations()
            val permutations = integerArrayPermutations.getPermutationsForIntegerArray(phaseSettingValues)
            return permutations.map { PhaseSettings(it) }
        }
    }

    private class IntegerArrayPermutations {
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

        fun getPermutationsForIntegerArray(values: MutableList<Int>): MutableList<Set<Int>> {
            permute(values, 0)
            return permutations
        }
    }


}