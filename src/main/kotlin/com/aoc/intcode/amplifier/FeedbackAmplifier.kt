package com.aoc.intcode.amplifier

class FeedbackAmplifier(private val phaseSetting: Int) : BaseAmplifier(phaseSetting) {
    private var lastOutputSignal = 0

    override fun inputSignal(inputSignal: Int) {
        computer.getProgramMemory().systemInput(phaseSetting)
        computer.getProgramMemory().systemInput(inputSignal)
        computer.compute()
        lastOutputSignal = computer.getDiagnosticCode()
    }

    fun getThrusterSignal(): Int = lastOutputSignal
}