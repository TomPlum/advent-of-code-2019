package com.aoc.monitoring.eris

import com.aoc.log.AdventLogger

class ErisLayoutMonitor(private val layout: ErisPlanetLayout) {

    private var minutesElapsed = 0
    private val history = mutableSetOf(layout)

    init {
        AdventLogger.debug("Initial State:")
        AdventLogger.debug(layout)
    }

    fun watchForMatchingLayout(): ErisPlanetLayout {
        val nextLayout = getNextLayout()
        while (!history.contains(nextLayout)) {
            incrementTime(nextLayout)
        }
        return history.last()
    }

    private fun getNextLayout(): ErisPlanetLayout {
        val layout = history.last().copy()
        layout.kill(layout.getDyingBugs())
        layout.infest(layout.getInfestedTiles())
        return layout
    }

    private fun incrementTime(nextLayout: ErisPlanetLayout) {
        AdventLogger.debug("After $minutesElapsed Minutes:")
        AdventLogger.debug(nextLayout)
        history.add(layout)
        minutesElapsed++
    }
}