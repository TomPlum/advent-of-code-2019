package com.aoc.intcode.amplifier.loopback

import com.aoc.intcode.IntCodeComputer
import com.aoc.intcode.amplifier.BaseAmplifier

class LoopInitialAmplifier(private val phaseSetting: Long) : BaseAmplifier(phaseSetting) {
    fun start() {
        inputSignal(0)
    }

    override fun inputSignal(inputSignal: Long) {
        computer.getProgramMemory().input.add(inputSignal)
        computer.compute()
        nextAmplifier.inputSignal(computer.getDiagnosticCode())
    }

    override fun loadAmplifierControllerSoftware(software: String) {
        computer = IntCodeComputer(software)
        computer.getProgramMemory().input.add(phaseSetting)
    }
}