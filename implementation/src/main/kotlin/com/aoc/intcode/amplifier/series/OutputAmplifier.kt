package com.aoc.intcode.amplifier.series

import com.aoc.intcode.amplifier.Amplifier
import com.aoc.intcode.amplifier.BaseAmplifier
import com.aoc.intcode.computer.IntCodeComputer

class OutputAmplifier(private val phaseSetting: Long) : BaseAmplifier(phaseSetting) {
    private var outputSignal: Long = 0

    override fun inputSignal(inputSignal: Long) {
        computer.program.memory.input.add(phaseSetting)
        computer.program.memory.input.add(inputSignal)
        computer.run()
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