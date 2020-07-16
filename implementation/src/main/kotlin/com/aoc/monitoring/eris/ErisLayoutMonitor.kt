package com.aoc.monitoring.eris

import com.aoc.log.AdventLogger

class ErisLayoutMonitor(initialState: ErisPlanetLayout) {

    private var minutesElapsed = 0
    private val history = mutableListOf(initialState)
    private var layout = getNextLayout()

    init {
        AdventLogger.debug("Initial State:")
        AdventLogger.debug(initialState)
    }

    fun watchForMatchingLayout(): ErisPlanetLayout {
        while (!history.contains(layout)) {
            incrementTime()
        }
        return layout
    }

    private fun getNextLayout(): ErisPlanetLayout {
        val layout = history.last().copy()
        val dyingBugs = layout.getDyingBugs()
        val infestedTiles = layout.getInfestedTiles()
        layout.kill(dyingBugs)
        layout.infest(infestedTiles)
        return layout
    }

    private fun incrementTime() {
        minutesElapsed++
        history.add(layout)
        layout = getNextLayout()
        AdventLogger.debug("After $minutesElapsed Minute(s):")
        AdventLogger.debug(layout)
    }
}