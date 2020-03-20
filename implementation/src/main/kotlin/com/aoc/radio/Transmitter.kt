package com.aoc.radio

import kotlin.math.abs

class Transmitter(private val inputSignal: Signal) {

    fun flawedFrequencyTransmission(phases: Int): Signal {
        println("Input signal: $inputSignal")
        var outputSignal = inputSignal
        (1..phases).forEach { phase ->
            outputSignal = Signal((inputSignal.sequence.indices).map { sequenceIndex ->
                val pattern = outputSignal.getPattern(sequenceIndex)
                val sum = outputSignal.sequence.sumBy {
                    it * pattern.getValue()
                }
                abs(sum.toString().last().toString().toInt())
            })
        }
        return outputSignal
    }

}