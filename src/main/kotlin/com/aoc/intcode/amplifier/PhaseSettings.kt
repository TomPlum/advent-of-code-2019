package com.aoc.intcode.amplifier

import java.lang.IllegalArgumentException
import java.util.*
import java.util.concurrent.ArrayBlockingQueue

class PhaseSettings(input: Set<Int>) {
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

    fun getAllPossiblePhaseSettingCombinations(phaseSettingValues: MutableList<Int>): MutableList<Set<Int>> {
        val integerArrayPermutations = IntegerArrayPermutations()
        return integerArrayPermutations.getPermutationsForIntegerArray(phaseSettingValues)
    }

    class IntegerArrayPermutations {
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

        fun getPermutationsForIntegerArray(input: MutableList<Int>): MutableList<Set<Int>> {
            permute(input, 0)
            return permutations
        }
    }


}