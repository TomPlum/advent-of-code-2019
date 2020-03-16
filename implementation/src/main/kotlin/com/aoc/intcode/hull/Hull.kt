package com.aoc.intcode.hull

class Hull(var panels: MutableMap<Panel, HullPaint> = mutableMapOf()) {

    fun paintPanel(x: Int, y: Int, paint: HullPaint) = panels.put(Panel(x, y), paint)

    /**
     * Returns the [HullPaint] on the [Panel] at the given co-ordinates ([x],[y]).
     * Returns [HullPaint.BLACK] if the [Panel] has not previously been painted on.
     */
    fun getPanelColour(x: Int, y: Int): HullPaint = panels[Panel(x, y)] ?: HullPaint.BLACK

    /**
     * Returns the numbers of panels that were painted by the [PaintingRobot] at least once.
     * This means that if a [Panel] is painted more than once, it is only counted as one towards this value.
     *
     * @return The number of panels painted at least once.
     */
    fun getTimesPanelsPainted() = panels.size

    fun getRegistrationIdentifier(): RegistrationIdentifier = RegistrationIdentifier(panels)

}