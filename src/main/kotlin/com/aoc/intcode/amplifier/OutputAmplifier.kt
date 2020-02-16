package com.aoc.intcode.amplifier

import com.aoc.intcode.IntCodeComputer

class OutputAmplifier(private val phaseSetting: Int) : BaseAmplifier(phaseSetting) {
    private var outputSignal: Int = 0

    override fun inputSignal(inputSignal: Int) {
        computer.getProgramMemory().systemInput(phaseSetting)
        computer.getProgramMemory().systemInput(inputSignal)
        computer.compute()
        outputSignal = computer.getDiagnosticCode()
    }

    override fun loadAmplifierControllerSoftware(software: String) {
        computer = IntCodeComputer(software)
    }

    override fun outputsTo(amplifier: Amplifier) {
        throw UnsupportedOperationException("An Output Amplifier does not send it's signal to another Amplifier")
    }

    fun getThrusterSignal() = outputSignal
}