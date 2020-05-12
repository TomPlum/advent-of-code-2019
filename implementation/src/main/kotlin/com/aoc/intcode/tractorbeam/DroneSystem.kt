package com.aoc.intcode.tractorbeam

import com.aoc.intcode.computer.IntCodeComputer
import log.AdventLogger
import math.Point2D

class DroneSystem(input: String) {
    private val computer = IntCodeComputer(input)

    /**
     * Deploys drone technology in the vicinity of the Tractor Beam and scans the area to better understand
     * the effect of the beams pull around the emitter.
     * @param gridSize A positive (or zero) integer representing the width and height of the area to scan
     * @return A [TractorBeamScan] detailing the effects of the beam in the scanned area
     */
    fun scanAreaSurroundingEmitter(gridSize: Long): TractorBeamScan {
        val scan = TractorBeamScan()

        (0 until gridSize).forEach { y ->
            (0 until gridSize).forEach { x ->
                deployDrone(x, y)
                val state = getDroneState()
                scan.addTile(Point2D(x.toInt(), y.toInt()), state)
                computer.reset()
            }
        }

        AdventLogger.debug(scan)
        return scan
    }

    fun scanAreaForSantasShip(): Long {
        return 0L
    }

    private fun getDroneState(): DroneState {
        computer.run()
        return when(val code = computer.getProgramMemory().output.getLastValue()) {
            0L -> DroneState.stationary()
            1L -> DroneState.propagating()
            else -> throw IllegalArgumentException("Invalid Drone Response Code ($code)")
        }
    }

    private fun deployDrone(x: Long, y: Long) {
        val input = computer.getProgramMemory().input
        input.add(x)
        input.add(y)
    }
}