package com.aoc.monitoring.eris

import com.aoc.log.AdventLogger

class ErisLayoutMonitor(private val layout: ErisPlanetLayout) {

    private var minutesElapsed = 0
    private val history = setOf(layout)

    init {
        AdventLogger.debug("Initial State:")
        AdventLogger.debug(layout)
    }

    fun watchForMatchingLayout(): ErisPlanetLayout {
        while (!history.contains(getNextLayout())) {
            incrementTime()
        }
        return history.last()
    }

    private fun getNextLayout(): ErisPlanetLayout {
        val layout = history.last()
        layout.kill(layout.getDyingBugs())
        layout.infest(layout.getInfestedTiles())
        return layout
    }

    private fun incrementTime() {
        minutesElapsed++
        AdventLogger.debug("After $minutesElapsed Minutes:")
        AdventLogger.debug(layout)
    }
}