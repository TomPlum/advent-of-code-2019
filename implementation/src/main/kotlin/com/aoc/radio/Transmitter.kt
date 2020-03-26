package com.aoc.radio

import kotlin.math.abs

class Transmitter(private val inputSignal: Signal) {

    //TODO Improve Runtime Performance
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

    /**
     * Finds the eight digit message embedded in the real signal at the index given by the message offset after 100
     * phases of Flawed Frequency Transmission.
     *
     * - Real Signal: Created by repeating the [inputSignal] internal [Signal.sequence] 10,000 times.
     * - Message Offset: Defined by the first seven digits of the initial [inputSignal].
     *
     * In order to decode the real signal as efficiently as possible, the algorithm take advantage of the scaling
     * nature of the [SignalPattern]. We know that the final pattern value is always 1 and therefore the final output
     * value after every phase of Flawed Frequency Transmission remains the same.
     *
     * We also know that by iterating over the [inputSignal] in reverse, we can calculate the next output value by
     * simply adding the last value from the current phase to the value at the current index from the last phase. This
     * value is then ran through a modulo 10 operation to retrieve the right-most digit.
     *
     * We therefore need only iterate over the signal starting from the final value and stopping and the index defined
     * by the message offset. This is the smallest range of values you can iterate over for maximum efficiency.
     *
     * - E.g. Your input is 345678 and we want to run a single phase off FFT. We work backwards over the signal.
     * - We'll define our input signal (s) with length (n) where a modulo operation is defined as (mod).
     * 1. The final value (i = 5) in every phase is 8 as the [SignalPattern] will always be [0,0,1] -> 6*0 + 7*0 + 8*1 = 8
     * 2. The next value (i = 4) is calculated by s[ i ] = mod( s[ i + 1 ] + s[ i ] , 10 ) -> s[4] = mod(7+8, 10) = 5
     * 3. The next value (i = 3) is now mod(6+5, 10) = 1. This is the last digit in the latter-half of the sequence.
     * 4. Our output signal for the latter half of the signal is 851.
     */
    fun decodeRealSignalMessage(): Signal {
        val seq = inputSignal.sequence.toMutableList()
        val messageOffset = inputSignal.getMessageOffset()
        (1..100).forEach { _ ->
            (seq.size - 2 downTo messageOffset).forEach { i -> seq[i] = (seq[i + 1] + seq[i]) % 10 }
        }
        return Signal(seq).getMessage()
    }

}