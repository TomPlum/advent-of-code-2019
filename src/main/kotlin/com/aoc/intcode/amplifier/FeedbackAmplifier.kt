package com.aoc.intcode.amplifier

import com.aoc.intcode.IntCodeComputer

class FeedbackAmplifier(private val phaseSetting: Int) : BaseAmplifier(phaseSetting) {
    private var lastOutputSignal = 0

    override fun inputSignal(inputSignal: Int) {
        computer.getProgramMemory().systemInput(phaseSetting)
        computer.getProgramMemory().systemInput(inputSignal)
        val latestSoftwareVersion = computer.compute()
        nextAmplifier.loadAmplifierControllerSoftware(latestSoftwareVersion)
        lastOutputSignal = computer.getDiagnosticCode()
    }

    override fun loadAmplifierControllerSoftware(software: String) {
        computer = IntCodeComputer(software)
    }

    fun getThrusterSignal(): Int = lastOutputSignal
}