package com.aoc.radio

import java.lang.IllegalArgumentException

data class Signal(val sequence: List<Int>) {
    private val basePattern = listOf(0, 1, 0, -1)

    fun getPattern(sequenceIndex: Int): SignalPattern {
        if (sequenceIndex > sequence.size - 1) {
            throw IllegalArgumentException("Invalid Sequence Index ($sequenceIndex) for Signal Length ${sequence.size}")
        }
        return SignalPattern(basePattern.flatMap { datum -> (0..sequenceIndex).map { datum } })
    }

    fun getFirstNValues(n: Int) = Signal(sequence.dropLast(sequence.size - n))

    fun convertToRealSignal(): Signal {
        val realSignalSequence = mutableListOf<Int>()
        (1..10000).forEach { _ -> realSignalSequence.addAll(sequence) }
        return Signal(realSignalSequence)
    }

    override fun toString() = sequence.joinToString(separator = "")
}