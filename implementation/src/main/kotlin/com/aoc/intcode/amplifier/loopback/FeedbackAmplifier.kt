package com.aoc.intcode.amplifier.loopback

import com.aoc.intcode.amplifier.BaseAmplifier
import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.computer.State

class FeedbackAmplifier(private val phaseSetting: Long) : BaseAmplifier(phaseSetting) {
    private var lastOutputSignal = 0L

    override fun inputSignal(inputSignal: Long) {
        computer.program.memory.input.add(inputSignal)
        computer.run()
        lastOutputSignal = computer.getDiagnosticCode()

        if (computer.state == State.WAITING) nextAmplifier.inputSignal(lastOutputSignal)
    }

    override fun loadAmplifierControllerSoftware(software: String) {
        computer = IntCodeComputer(software)
        computer.program.memory.input.add(phaseSetting)
    }

    fun getThrusterSignal(): Long = lastOutputSignal
}