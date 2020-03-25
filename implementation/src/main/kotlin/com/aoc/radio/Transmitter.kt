package com.aoc.radio

import kotlin.math.abs

class Transmitter(private val inputSignal: Signal) {

    fun flawedFrequencyTransmission(phases: Int): Signal {
        var outputSignal = inputSignal
        (1..phases).forEach { _ ->
            outputSignal = Signal((inputSignal.sequence.indices).map { sequenceIndex ->
                val pattern = outputSignal.getPattern(sequenceIndex)
                val sum = outputSignal.sequence.sumBy {
                    it * pattern.getValue()
                }
                abs(sum % 10)
            })
        }
        return outputSignal
    }

    fun decodeRealSignalMessage(): Signal {
        val sig = inputSignal.sequence.chunked(inputSignal.length() / 2)[1].toMutableList()
        (1..100).forEach { _ ->
            (0 until sig.size - 1).forEach { i ->
                sig[(sig.size - 1) - (i + 1)] = (sig[(sig.size - 1) - i] + sig[(sig.size - 1) - (i + 1)]) % 10
            }
        }
        val messageStartIndex = inputSignal.getFirstNValues(7).sequence.joinToString(separator = "").toInt() - sig.size
        return Signal(sig.slice(IntRange(messageStartIndex, messageStartIndex + 7)))
    }

}