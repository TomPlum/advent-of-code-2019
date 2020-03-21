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
        val outputSignal = flawedFrequencyTransmission(100)
        val messageStartIndex = outputSignal.getFirstNValues(7).sequence.joinToString(separator = "").toInt()
        return Signal(outputSignal.sequence.slice(IntRange(messageStartIndex, messageStartIndex + 8)))
    }

}