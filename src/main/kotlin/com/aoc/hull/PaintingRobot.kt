package com.aoc.hull

import com.aoc.intcode.IntCodeComputer

class PaintingRobot(instructions: String) {
    private val computer = IntCodeComputer(instructions)
    var direction = Direction.UP

    fun start(): Int {
        //The robot starts facing UP
        //Input into IntCodeComputer subject to current panel colour (0 if Black, 1 if White)
        //Compute
        //First output is the colour in which to paint the current panel
        //Second output is the direction the robot should turn (0 = Left 90deg, 1 = Right 90deg)
        //After robot turns it should move forward exactly 1 panel

        return 0
    }

    private fun paint(paint: HullPaint) {

    }

    private fun getCurrentPanelColour(): HullPaint {
        return HullPaint.BLACK
    }

    private fun moveForward() {

    }

    private fun turn(directionalCode: Int) {

    }

}