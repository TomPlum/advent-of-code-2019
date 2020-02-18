package com.aoc.intcode.circuit

import com.aoc.intcode.amplifier.*
import com.aoc.intcode.amplifier.series.InitialAmplifier
import com.aoc.intcode.amplifier.series.OutputAmplifier

class SeriesAmplificationCircuit : AmplificationCircuitStrategy {

    override fun calculateThrusterSignal(software: String, phaseSettings: PhaseSettings): Int {
        val a = InitialAmplifier(phaseSettings.getSetting())
        val b = ThrustAmplifier(phaseSettings.getSetting())
        val c = ThrustAmplifier(phaseSettings.getSetting())
        val d = ThrustAmplifier(phaseSettings.getSetting())
        val e = OutputAmplifier(phaseSettings.getSetting())

        a.outputsTo(b)
        b.outputsTo(c)
        c.outputsTo(d)
        d.outputsTo(e)

        a.loadAmplifierControllerSoftware(software)
        a.start()

        return e.getThrusterSignal()
    }

    override fun getPhaseSettingConfiguration(): List<Int> = listOf(0,1,2,3,4)
}