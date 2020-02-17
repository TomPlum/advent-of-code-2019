package com.aoc.intcode.circuit

import com.aoc.intcode.amplifier.FeedbackAmplifier
import com.aoc.intcode.amplifier.InitialAmplifier
import com.aoc.intcode.amplifier.PhaseSettings
import com.aoc.intcode.amplifier.ThrustAmplifier

class LoopbackAmplificationCircuit : AmplificationCircuitStrategy {

    override fun calculateThrusterSignal(software: String, phaseSettings: PhaseSettings): Int {
        val a = InitialAmplifier(phaseSettings.getSetting())
        val b = FeedbackAmplifier(phaseSettings.getSetting())
        val c = FeedbackAmplifier(phaseSettings.getSetting())
        val d = FeedbackAmplifier(phaseSettings.getSetting())
        val e = FeedbackAmplifier(phaseSettings.getSetting())

        a.outputsTo(b)
        b.outputsTo(c)
        c.outputsTo(d)
        d.outputsTo(e)
        e.outputsTo(a)

        a.loadAmplifierControllerSoftware(software)
        a.start()

        return e.getThrusterSignal()
    }
}