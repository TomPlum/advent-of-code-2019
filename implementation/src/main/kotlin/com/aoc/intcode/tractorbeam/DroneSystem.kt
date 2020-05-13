package com.aoc.intcode.tractorbeam

import com.aoc.intcode.computer.IntCodeComputer
import log.AdventLogger
import math.Point2D

class DroneSystem(input: String) {
    private val computer = IntCodeComputer(input)
    private val lazyScan = TractorBeamScan()
    private var x = 0
    private var y = 0
    private var lastState = DroneState.stationary()
    private var lastBeamStartX = 0

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
        scanNextBlock(10)
        scanNextBlock(10)
        return 0L
    }

    private fun scanNextBlock(area: Long) {
        var foundEndOfBeam = false
        (y until y + area).forEach { y ->
            foundEndOfBeam = false
            while(!foundEndOfBeam) {
                if (x < lastBeamStartX) {
                    deployDrone(lastBeamStartX.toLong(), y)
                    x = lastBeamStartX
                } else {
                    deployDrone(x.toLong(), y)
                }

                val state = getDroneState()

                //Empty Row (Happens Near Start)
                if (x.toLong() > area) {
                    x = 0
                    foundEndOfBeam = true
                    computer.reset()
                    break
                }

                //Found Beam Start
                if (lastState.isStationary() && state.isPropagating()) {
                    lastBeamStartX = x
                }

                //Found Beam End
                if (lastState.isPropagating() && state.isStationary()) {
                    (x .. area).forEach { lazyScan.addTile(Point2D(it.toInt(), y.toInt()), DroneState.stationary()) }
                    foundEndOfBeam = true
                    x = 0
                } else {
                    lazyScan.addTile(Point2D(x, y.toInt()), state)
                    x++
                }
                computer.reset()
                lastState = state
            }
        }
        this.y += area.toInt()
        AdventLogger.debug(lazyScan)
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