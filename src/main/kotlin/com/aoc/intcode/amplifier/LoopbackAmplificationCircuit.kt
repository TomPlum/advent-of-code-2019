package com.aoc.intcode.amplifier

class LoopbackAmplificationCircuit : AmplificationCircuitStrategy {

    override fun calculateThrusterSignal(software: String, phaseSettings: PhaseSettings): Int {
        val a = InitialAmplifier(phaseSettings.getSetting())
        val b = ThrustAmplifier(phaseSettings.getSetting())
        val c = ThrustAmplifier(phaseSettings.getSetting())
        val d = ThrustAmplifier(phaseSettings.getSetting())
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