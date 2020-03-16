package com.aoc.intcode.amplifier.loopback

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.amplifier.BaseAmplifier

class FeedbackAmplifier(private val phaseSetting: Long) : BaseAmplifier(phaseSetting) {
    private var lastOutputSignal = 0L

    override fun inputSignal(inputSignal: Long) {
        computer.getProgramMemory().input.add(inputSignal)
        computer.compute()
        lastOutputSignal = computer.getDiagnosticCode()

        if (!computer.programHalted) nextAmplifier.inputSignal(lastOutputSignal)
    }

    override fun loadAmplifierControllerSoftware(software: String) {
        computer = IntCodeComputer(software)
        computer.getProgramMemory().input.add(phaseSetting)
    }

    fun getThrusterSignal(): Long = lastOutputSignal
}