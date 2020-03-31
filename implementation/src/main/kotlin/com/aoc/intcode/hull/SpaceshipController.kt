package com.aoc.intcode.hull

import com.aoc.intcode.computer.IntCodeComputer

class SpaceshipController {

    /**
     * In order to comply with Space Law, the Spaceship must have a clearly visible Registration Identifier.
     * To quickly satisfy the Space Police's request, the ship must deploy an emergency [PaintingRobot].
     * Upon deployment, the robot will follow the Elves' instructions are paint the [Hull] appropriately.
     *
     * @param instructions The instruction String to be passed to the internal [IntCodeComputer] of the [PaintingRobot]
     * @param startingPanelColour The colour of the panel in which to deploy the [PaintingRobot]
     * @return The freshly painted [Hull] with a clearly visible Registration Identifier.
     */
    fun deployEmergencyHullPaintingRobot(instructions: String, startingPanelColour: HullPaint): Hull {
        val robot = PaintingRobot(instructions)
        robot.start(startingPanelColour)
        return robot.getPaintedHull()
    }
}