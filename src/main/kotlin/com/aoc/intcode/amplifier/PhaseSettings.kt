package com.aoc.intcode.amplifier

import java.lang.IllegalArgumentException

class PhaseSettings(phaseSettings: Set<Int>) {
    init {
        if (phaseSettings.size != 5) {
            throw IllegalArgumentException("A PhaseSettings must have exactly 5 phase settings")
        }

        phaseSettings.forEach {
            if (it < 0 || it > 4) throw IllegalArgumentException("Phase settings must be between 0 and 4 (inclusive)")
        }
    }

}