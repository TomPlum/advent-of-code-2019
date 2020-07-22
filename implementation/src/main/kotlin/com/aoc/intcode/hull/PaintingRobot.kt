package com.aoc.intcode.hull

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.computer.State
import com.aoc.math.Direction

class PaintingRobot(instructions: String) {
    private val computer = IntCodeComputer(instructions)
    private val hull = Hull()
    var direction = Direction.UP
    private var x = 0
    private var y = 0

    fun start(startingPanelColour: HullPaint) {
        computer.program.memory.input.add(startingPanelColour.colourCode.toLong())

        while (computer.state != State.HALTED) {
            computer.run()

            val systemOutput = computer.program.memory.output.getLastTwoValues()

            paint(systemOutput.first.toInt())

            turn(systemOutput.second.toInt())

            moveForward()

            computer.program.memory.input.add(getCurrentPanelColour().colourCode.toLong())
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