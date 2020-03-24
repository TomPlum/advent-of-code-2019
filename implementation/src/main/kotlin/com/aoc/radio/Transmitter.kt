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
        var sig = inputSignal.convertToRealSignal().sequence.chunked(inputSignal.length() * 10000 / 2)[1].toMutableList()
        //println("Latter Half: $sig")
        (1..100).forEach { _ ->
            (0 until sig.size - 1).forEach { i ->
                sig[(sig.size - 1) - (i + 1)] = sig[(sig.size - 1) - i] + sig[(sig.size - 1) - (i + 1)]
            }
            sig = sig.map { abs(it % 10) }.toMutableList()
            //println(sig)
        }
        val messageStartIndex = inputSignal.getFirstNValues(7).sequence.joinToString(separator = "").toInt()
        return Signal(sig.slice(IntRange(messageStartIndex, messageStartIndex + 8)))
    }

}