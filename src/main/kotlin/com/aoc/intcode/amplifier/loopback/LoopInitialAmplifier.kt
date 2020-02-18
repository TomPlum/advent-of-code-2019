package com.aoc.intcode.amplifier.loopback

import com.aoc.intcode.IntCodeComputer
import com.aoc.intcode.amplifier.BaseAmplifier

class LoopInitialAmplifier(private val phaseSetting: Int) : BaseAmplifier(phaseSetting) {
    fun start() {
        inputSignal(0)
    }

    override fun inputSignal(inputSignal: Int) {
        computer.getProgramMemory().systemInput(inputSignal)
        computer.compute()
        nextAmplifier.inputSignal(computer.getDiagnosticCode())
    }

    override fun loadAmplifierControllerSoftware(software: String) {
        computer = IntCodeComputer(software)
        computer.getProgramMemory().systemInput(phaseSetting) //Set PhaseSetting exactly once
    }
}