package com.aoc.intcode.amplifier

import com.aoc.intcode.IntCodeComputer

abstract class BaseAmplifier(private val phaseSetting: Int) : Amplifier {
    protected lateinit var nextAmplifier: Amplifier
    lateinit var computer: IntCodeComputer

    override fun inputSignal(inputSignal: Int) {
        computer.getProgramMemory().systemInput(phaseSetting)
        computer.getProgramMemory().systemInput(inputSignal)
        computer.compute()
        nextAmplifier.inputSignal(computer.getDiagnosticCode())
    }

    override fun outputsTo(amplifier: Amplifier) {
        this.nextAmplifier = amplifier
    }

    override fun loadAmplifierControllerSoftware(software: String) {
        computer = IntCodeComputer(software)
        nextAmplifier.loadAmplifierControllerSoftware(software)
    }
}