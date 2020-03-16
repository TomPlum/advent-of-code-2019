package com.aoc.intcode.hull

class SpaceshipController {

    /**
     * In order to comply with Space Law, the Spaceship must have a clearly visible [RegistrationIdentifier].
     * To quickly satisfy the Space Police's request, the ship must deploy an emergency [PaintingRobot].
     * Upon deployment, the robot will follow the Elves' instructions are paint the [Hull] appropriately.
     *
     * @param instructions The instruction String to be passed to the internal [com.aoc.intcode.IntCodeComputer] of the [PaintingRobot]
     * @param startingPanelColour The colour of the [Panel] in which to deploy the [PaintingRobot]
     * @return The freshly painted [Hull] with a clearly visible [RegistrationIdentifier]
     */
    fun deployEmergencyHullPaintingRobot(instructions: String, startingPanelColour: HullPaint): Hull {
        val robot = PaintingRobot(instructions)
        robot.start(startingPanelColour)
        return robot.getPaintedHull()
    }
}