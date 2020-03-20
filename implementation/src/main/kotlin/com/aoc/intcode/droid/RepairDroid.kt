package com.aoc.intcode.droid

import com.aoc.intcode.computer.IntCodeComputer
import math.Point2D
import java.util.*

class RepairDroid(instructions: String) {
    private val computer = IntCodeComputer(instructions)
    private val shipFloorMap = ShipFloorMap()
    private val visited = Stack<Pair<Point2D, Direction>>()
    private var x = 0
    private var y = 0

    /**
     * Command the [RepairDroid] to explore the ship in order to find the Oxygen System.
     * The search algorithm is a DFS (Depth First Search), meaning it is not the most efficient.
     * @return The coordinates of the destination and the number of movements the droid has to take in order to reach it.
     */
    fun locateShipsOxygenSystem(): Pair<Point2D, Int> {
        //Set Starting Tile & Initial Movement Direction
        visited.push(Pair(Point2D(0, 0), Direction.NORTH))

        var oxygenSystemInfo: Pair<Point2D, Int>? = null

        while (!visited.isEmpty()) {
            val direction = getNextDirection()

            computer.getProgramMemory().input.add(direction.code.toLong())
            computer.compute()

            when (DroidResponse.fromCode(computer.getDiagnosticCode().toInt())) {
                DroidResponse.HIT_WALL_POSITION_NOT_CHANGED -> {
                    //Record Wall Coordinate
                    when (direction) {
                        Direction.NORTH -> shipFloorMap.updateTile(Point2D(x, y + 1), ShipFloorTile.WALL)
                        Direction.EAST -> shipFloorMap.updateTile(Point2D(x + 1, y), ShipFloorTile.WALL)
                        Direction.SOUTH -> shipFloorMap.updateTile(Point2D(x, y - 1), ShipFloorTile.WALL)
                        Direction.WEST -> shipFloorMap.updateTile(Point2D(x - 1, y), ShipFloorTile.WALL)
                    }
                }
                DroidResponse.SUCCESSFULLY_CHANGED_POSITION -> {
                    //Record Current Coordinate As Traversable (Unless Inside Oxygen System)
                    if (shipFloorMap.tileType(Point2D(x, y)) != ShipFloorTile.OXYGEN_SYSTEM) {
                        shipFloorMap.updateTile(Point2D(x, y), ShipFloorTile.TRAVERSABLE)
                    }

                    //Increment Relevant Ordinate
                    when (direction) {
                        Direction.NORTH -> y++
                        Direction.EAST -> x++
                        Direction.SOUTH -> y--
                        Direction.WEST -> x--
                    }

                    //Updated Visited Tiles (Unless Backtracking)
                    if (!shipFloorMap.hasRecorded(Point2D(x, y))) {
                        visited.push(Pair(Point2D(x, y), direction))
                    }

                    //Record New Coordinate As Droid Current Position
                    shipFloorMap.updateTile(Point2D(x, y), ShipFloorTile.DROID)
                }
                DroidResponse.LOCATED_OXYGEN_SYSTEM ->  {
                    //Record Current Coordinate As Traversable
                    shipFloorMap.updateTile(Point2D(x, y), ShipFloorTile.TRAVERSABLE)

                    //Increment Relevant Ordinate
                    when (direction) {
                        Direction.NORTH -> y++
                        Direction.EAST -> x++
                        Direction.SOUTH -> y--
                        Direction.WEST -> x--
                    }

                    //Log Oxygen System Details
                    oxygenSystemInfo = Pair(Point2D(x, y), visited.size)
                    shipFloorMap.updateTile(Point2D(x, y), ShipFloorTile.OXYGEN_SYSTEM)

                    //Log Oxygen System Visit
                    visited.push(Pair(Point2D(x, y), direction))
                }
            }

            println(shipFloorMap)
        }

        println(shipFloorMap)
        println("Oxygen System Location ${oxygenSystemInfo!!.first}")
        return oxygenSystemInfo
    }

    fun downloadShipMappingData() = shipFloorMap

    private fun getNextDirection() = getUnexploredSurroundingCoordinateDirection() ?: visited.pop().second.reverse()

    private fun getUnexploredSurroundingCoordinateDirection(): Direction? {
        if (!shipFloorMap.hasRecorded(Point2D(x, y + 1))) return Direction.NORTH
        if (!shipFloorMap.hasRecorded(Point2D(x + 1, y))) return Direction.EAST
        if (!shipFloorMap.hasRecorded(Point2D(x, y - 1))) return Direction.SOUTH
        if (!shipFloorMap.hasRecorded(Point2D(x - 1, y))) return Direction.WEST
        return null
    }
}