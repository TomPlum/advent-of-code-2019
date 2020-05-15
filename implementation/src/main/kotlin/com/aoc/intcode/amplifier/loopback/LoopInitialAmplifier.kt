package com.aoc.intcode.amplifier.loopback

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.amplifier.BaseAmplifier

class LoopInitialAmplifier(private val phaseSetting: Long) : BaseAmplifier(phaseSetting) {
    fun start() {
        inputSignal(0)
    }

    override fun inputSignal(inputSignal: Long) {
        computer.program.memory.input.add(inputSignal)
        computer.run()
        nextAmplifier.inputSignal(computer.getDiagnosticCode())
    }

    override fun loadAmplifierControllerSoftware(software: String) {
        computer = IntCodeComputer(software)
        computer.program.memory.input.add(phaseSetting)
    }
}