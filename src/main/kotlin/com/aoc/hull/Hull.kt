package com.aoc.hull

class Hull(var panels: MutableMap<Panel, HullPaint> = mutableMapOf()) {

    fun paintPanel(x: Int, y: Int, paint: HullPaint) = panels.put(Panel(x, y), paint)

    fun getPanelColour(x: Int, y: Int): HullPaint = panels[Panel(x, y)] ?: HullPaint.BLACK

    /**
     * Returns the numbers of panels that were painted by the [PaintingRobot] at least once.
     * This means that if a [Panel] is painted more than once, it is only counted as one towards this value.
     *
     * @return The number of panels painted at least once.
     */
    fun getTimesPanelsPainted() = panels.size

}