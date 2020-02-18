package com.aoc.intcode.circuit

import com.aoc.intcode.amplifier.*
import com.aoc.intcode.amplifier.loopback.FeedbackAmplifier
import com.aoc.intcode.amplifier.loopback.LoopInitialAmplifier

class LoopbackAmplificationCircuit : AmplificationCircuitStrategy {

    override fun calculateThrusterSignal(software: String, phaseSettings: PhaseSettings): Int {
        val a = LoopInitialAmplifier(phaseSettings.getSetting())
        val b = ThrustAmplifier(phaseSettings.getSetting())
        val c = ThrustAmplifier(phaseSettings.getSetting())
        val d = ThrustAmplifier(phaseSettings.getSetting())
        val e = FeedbackAmplifier(phaseSettings.getSetting())

        a.outputsTo(b)
        b.outputsTo(c)
        c.outputsTo(d)
        d.outputsTo(e)
        e.outputsTo(a)

        initialiseSoftware(software, a, b, c, d, e)
        a.start()

        return e.getThrusterSignal()
    }

    override fun getPhaseSettingConfiguration(): List<Int> = listOf(5,6,7,8,9)

    private fun initialiseSoftware(software: String, vararg amplifiers: Amplifier) {
        amplifiers.forEach { it.loadAmplifierControllerSoftware(software) }
    }
}