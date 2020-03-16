package com.aoc.hull

data class RegistrationIdentifier(private val panels: Map<Panel, HullPaint>) {
    override fun toString(): String {
        panels.keys.map { it.x }.min()
        val xMin = panels.keys.minBy { it.x }!!.x
        val xMax = panels.keys.maxBy { it.x }!!.x
        val yMin = panels.keys.minBy { it.y }!!.y
        val yMax = panels.keys.maxBy { it.y }!!.y

        return (yMin..yMax).joinToString("\n") { y: Int ->
            (xMin..xMax).joinToString("") { x: Int ->
                when (panels.getOrDefault(Panel(x, y), HullPaint.BLACK)) {
                    HullPaint.BLACK -> " "
                    HullPaint.WHITE -> "#"
                }
            }
        }
    }
}