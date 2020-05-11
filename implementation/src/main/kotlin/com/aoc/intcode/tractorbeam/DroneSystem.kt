package com.aoc.intcode.tractorbeam

import com.aoc.intcode.computer.IntCodeComputer
import log.AdventLogger
import math.Point2D
import java.lang.IllegalArgumentException

class DroneSystem(input: String) {
    private val computer = IntCodeComputer(input)

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

    private fun getDroneState(): DroneState {
        computer.compute()
        return when(val code = computer.getProgramMemory().output.getLastValue()) {
            0L -> DroneState.STATIONARY
            1L -> DroneState.PROPAGATING
            else -> throw IllegalArgumentException("Invalid Drone Response Code ($code)")
        }
    }

    private fun deployDrone(x: Long, y: Long) {
        val input = computer.getProgramMemory().input
        input.add(x)
        input.add(y)
    }
}