package com.aoc.intcode.amplifier.series

import com.aoc.intcode.IntCodeComputer
import com.aoc.intcode.amplifier.Amplifier
import com.aoc.intcode.amplifier.BaseAmplifier

class OutputAmplifier(private val phaseSetting: Long) : BaseAmplifier(phaseSetting) {
    private var outputSignal: Long = 0

    override fun inputSignal(inputSignal: Long) {
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