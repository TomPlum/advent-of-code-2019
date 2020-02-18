package com.aoc.intcode.amplifier

import com.aoc.intcode.IntCodeComputer

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