package com.aoc.monitoring.eris

import com.aoc.log.AdventLogger

class PlanarErisLayoutMonitor(layout: PlanarErisPlanetLayout) {

    private var minutesElapsed = 0
    private val history = mutableListOf(layout)
    private var layout = getNextLayout()

    init {
        AdventLogger.info("Initial State:")
        AdventLogger.info(layout)
    }

    fun watchForRepeatingLayout(): PlanarErisPlanetLayout {
        while (!history.contains(layout)) {
            incrementTime()
        }
        AdventLogger.info("Found Repeating Layout After $minutesElapsed Minutes")
        return layout
    }

    private fun getNextLayout(): PlanarErisPlanetLayout {
        val layout = history.last().snapshot()
        val dyingBugs = layout.getDyingBugs()
        val infestedTiles = layout.getInfestedTiles()
        layout.kill(dyingBugs)
        layout.infest(infestedTiles)
        return layout
    }

    private fun incrementTime() {
        minutesElapsed++
        AdventLogger.info("After $minutesElapsed Minute(s):")
        AdventLogger.info(layout)
        history.add(layout)
        layout = getNextLayout()
    }
}