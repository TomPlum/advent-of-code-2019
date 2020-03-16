package com.aoc.intcode.amplifier.series

import com.aoc.intcode.amplifier.BaseAmplifier

class InitialAmplifier(phaseSetting: Long) : BaseAmplifier(phaseSetting) {
    fun start() {
        super.inputSignal(0)
    }
}