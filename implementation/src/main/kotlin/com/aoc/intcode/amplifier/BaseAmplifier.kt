package com.aoc.intcode.amplifier

import com.aoc.intcode.computer.IntCodeComputer

abstract class BaseAmplifier(private val phaseSetting: Long) : Amplifier {
    protected lateinit var nextAmplifier: Amplifier
    lateinit var computer: IntCodeComputer

    override fun inputSignal(inputSignal: Long) {
        computer.program.memory.input.add(inputSignal)
        computer.run()
        nextAmplifier.inputSignal(computer.getDiagnosticCode())
    }

    override fun outputsTo(amplifier: Amplifier) {
        this.nextAmplifier = amplifier
    }

    override fun loadAmplifierControllerSoftware(software: String) {
        computer = IntCodeComputer(software)
        computer.program.memory.input.add(phaseSetting)
        nextAmplifier.loadAmplifierControllerSoftware(software)
    }
}