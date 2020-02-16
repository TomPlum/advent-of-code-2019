package com.aoc.intcode.amplifier

class InitialAmplifier(phaseSetting: Int) : BaseAmplifier(phaseSetting) {
    fun start() {
        super.inputSignal(0)
    }
}