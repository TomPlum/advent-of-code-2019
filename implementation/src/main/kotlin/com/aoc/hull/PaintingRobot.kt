package com.aoc.hull

import com.aoc.intcode.computer.IntCodeComputer
import java.lang.IllegalArgumentException

class PaintingRobot(instructions: String) {
    private val computer = IntCodeComputer(instructions)
    private val hull = Hull()
    var direction = Direction.UP
    private var x = 0
    private var y = 0

    fun start(startingPanelColour: HullPaint) {
        computer.getProgramMemory().input.add(startingPanelColour.colourCode.toLong())

        while (!computer.programHalted) {
            computer.compute()

            val systemOutput = computer.getProgramMemory().output.getLastTwoValues()

            paint(systemOutput.first.toInt())

            turn(systemOutput.second.toInt())

            moveForward()

            computer.getProgramMemory().input.add(getCurrentPanelColour().colourCode.toLong())
        }

    }

    fun getPaintedHull() = hull

    private fun paint(paintColourCode: Int) = when(paintColourCode) {
        0 -> hull.paintPanel(x, y, HullPaint.BLACK)
        1 -> hull.paintPanel(x, y, HullPaint.WHITE)
        else -> throw IllegalArgumentException("Invalid Hull Paint Colour Code $paintColourCode")
    }

    private fun getCurrentPanelColour(): HullPaint {
        return hull.getPanelColour(x, y)
    }

    private fun moveForward() = when(direction) {
        Direction.UP -> y--
        Direction.RIGHT -> x++
        Direction.DOWN -> y++
        Direction.LEFT -> x--
    }

    private fun turn(directionalCode: Int) {
        direction = when (directionalCode) {
            0 -> direction.rotateAntiClockwise()
            1 -> direction.rotateClockwise()
            else -> throw IllegalArgumentException("Invalid Directional Code ($directionalCode)")
        }
    }

}