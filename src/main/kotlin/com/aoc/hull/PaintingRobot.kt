package com.aoc.hull

import com.aoc.intcode.IntCodeComputer
import java.lang.IllegalArgumentException

class PaintingRobot(instructions: String) {
    private val computer = IntCodeComputer(instructions)
    private val hull = Hull()
    var direction = Direction.UP
    private var x = 0
    private var y = 0

    fun start(startingPanelColour: HullPaint) {
        //Start Black Panel
        computer.getProgramMemory().systemInput(startingPanelColour.colourCode.toLong())

        while (!computer.programHalted) {
            //Compute based on current input to produce the two output codes
            computer.compute()

            val systemOutput = computer.getProgramMemory().getLastTwoOutputValues()

            //First output is the colour in which to paint the current panel
            paint(systemOutput.first.toInt())

            //Second output is the direction the robot should turn (0 = Left 90deg, 1 = Right 90deg)
            turn(systemOutput.second.toInt())

            //After robot turns it should move forward exactly 1 panel
            moveForward()

            //Input into IntCodeComputer subject to current panel colour (0 if Black, 1 if White)
            computer.getProgramMemory().systemInput(getCurrentPanelColour().colourCode.toLong())
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