package com.aoc.intcode.hull

import map.Map
import math.Point2D

class Hull : Map<HullPaint>() {

    fun paintPanel(x: Int, y: Int, paint: HullPaint) = addTile(Point2D(x, y), paint)

    /**
     * Returns the [HullPaint] on the panel at the given co-ordinates ([x],[y]).
     * Returns [HullPaint.BLACK] if the panel has not previously been painted on.
     */
    fun getPanelColour(x: Int, y: Int): HullPaint = getTile(Point2D(x, y), HullPaint.BLACK)

    /**
     * Returns the numbers of panels that were painted by the [PaintingRobot] at least once.
     * This means that if a panel is painted more than once, it is only counted as one towards this value.
     *
     * @return The number of panels painted at least once.
     */
    fun getTimesPanelsPainted() = tileQuantity()

}