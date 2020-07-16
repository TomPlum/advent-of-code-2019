package com.aoc.monitoring.eris

import com.aoc.log.AdventLogger

class ErisLayoutMonitor(layout: ErisPlanetLayout) {

    private var minutesElapsed = 0
    private val history = mutableListOf(layout)
    private var layout = getNextLayout()

    init {
        AdventLogger.debug("Initial State:")
        AdventLogger.debug(layout)
    }

    fun watchForMatchingLayout(): ErisPlanetLayout {
        while (!history.contains(layout)) {
            incrementTime()
        }
        return layout
    }

    private fun getNextLayout(): ErisPlanetLayout {
        val layout = history.last().snapshot()
        val dyingBugs = layout.getDyingBugs()
        val infestedTiles = layout.getInfestedTiles()
        layout.kill(dyingBugs)
        layout.infest(infestedTiles)
        return layout
    }

    private fun incrementTime() {
        minutesElapsed++
        AdventLogger.debug("After $minutesElapsed Minute(s):")
        AdventLogger.debug(layout)
        history.add(layout)
        layout = getNextLayout()
    }
}