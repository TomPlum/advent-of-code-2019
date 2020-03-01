package com.aoc.hull

class SpaceshipController {

    /**
     * In order to comply with Space Law, the Spaceship must have a clearly visible Registration Identifier.
     * To quickly satisfy the Space Police's request, the ship must deploy an emergency [PaintingRobot].
     * Upon deployment, the robot will follow the Elves' instructions are paint the [Hull] appropriately.
     *
     * @return The freshly painted [Hull] with a clearly visible Registration Identifier.
     */
    fun deployEmergencyHullPaintingRobot(instructions: String): Hull {
        val robot = PaintingRobot(instructions)
        robot.start()
        return robot.getPaintedHull()
    }
}