package com.aoc.intcode.droid

import com.aoc.intcode.computer.IntCodeComputer
import math.Point2D
import java.lang.IllegalStateException
import java.util.*

class RepairDroid(instructions: String) {
    private val computer = IntCodeComputer(instructions)
    private val knownCoordinates = mutableMapOf(Pair(Point2D(0, 0), "D"))
    private var currentDirection = Direction.NORTH
    private var history = Stack<Point2D>()
    private var x = 0
    private var y = 0

    fun locateOxygenSystem(): Point2D {
        while (!computer.programHalted) {
            computer.getProgramMemory().input.add(currentDirection.code.toLong())

            computer.compute()

            when(DroidResponse.fromCode(computer.getDiagnosticCode().toInt())) {
                DroidResponse.HIT_WALL_POSITION_NOT_CHANGED -> updateDirection()
                DroidResponse.SUCCESSFULLY_CHANGED_POSITION -> updatePosition()
                DroidResponse.LOCATED_OXYGEN_SYSTEM -> return Point2D(x, y)
            }

            printShipFloor()

        }

        throw IllegalStateException("Program Halted Unexpectedly!")
    }

    private fun updatePosition() {
        //Record Current Coordinate As Traversable
        knownCoordinates[Point2D(x, y)] = "."

        when(currentDirection) {
            Direction.NORTH -> y++
            Direction.EAST -> x++
            Direction.SOUTH -> y--
            Direction.WEST -> x--
        }

        //Record New Coordinate As Droid Current Position
        knownCoordinates[Point2D(x, y)] = "D"

        //Update History
        history.push(Point2D(x, y))

        if (getUnexploredSurroundingCoordinateDirection() != null) {
            currentDirection = getUnexploredSurroundingCoordinateDirection()!!
        }
    }

    private fun updateDirection() {
        //Record Wall Coordinate
        when(currentDirection) {
            Direction.NORTH -> knownCoordinates[Point2D(x, y + 1)] = "#"
            Direction.EAST -> knownCoordinates[Point2D(x + 1, y)] = "#"
            Direction.SOUTH -> knownCoordinates[Point2D(x, y - 1)] = "#"
            Direction.WEST -> knownCoordinates[Point2D(x - 1, y)] = "#"
        }

        //Dead End (3 Surrounding Tiles Are Walls)
        if (scanSurroundings().count { it == "#" } == 3 && scanSurroundings().count { it == "." } == 1) backtrack()

        currentDirection = getUnexploredSurroundingCoordinateDirection() ?: currentDirection.randomise()
    }

    private fun backtrack() {
        var foundUntraversedLocation = false

        while(!foundUntraversedLocation) {
            if (scanSurroundings().contains(" ")) {
                foundUntraversedLocation = true
                knownCoordinates[Point2D(x, y)] = "D"
            } else {
                val lastPosition = history.pop()
                x = lastPosition.x
                y = lastPosition.y
                knownCoordinates[lastPosition] = "."
            }
            printShipFloor()
        }
    }

    private fun getUnexploredSurroundingCoordinateDirection(): Direction? {
        knownCoordinates.getOrElse(Point2D(x, y + 1)) { return Direction.NORTH }
        knownCoordinates.getOrElse(Point2D(x + 1, y)) { return Direction.EAST }
        knownCoordinates.getOrElse(Point2D(x, y - 1)) { return Direction.SOUTH }
        knownCoordinates.getOrElse(Point2D(x - 1, y)) { return Direction.WEST }

        return null
    }

    private fun scanSurroundings(): List<String> {
        return listOf(getCoordinateDirectly(Direction.NORTH), getCoordinateDirectly(Direction.EAST),
                     getCoordinateDirectly(Direction.WEST), getCoordinateDirectly(Direction.SOUTH))
    }

    private fun getCoordinateDirectly(direction: Direction) = when(direction) {
        Direction.NORTH -> knownCoordinates.getOrDefault(Point2D(x, y + 1), " ")
        Direction.EAST -> knownCoordinates.getOrDefault(Point2D(x + 1, y), " ")
        Direction.SOUTH -> knownCoordinates.getOrDefault(Point2D(x, y - 1), " ")
        Direction.WEST -> knownCoordinates.getOrDefault(Point2D(x - 1, y), " ")
    }

    private fun printShipFloor() {
        repeat(10) { print("\n") }
        val xMin = knownCoordinates.keys.minBy { it.x }!!.x
        val yMax = knownCoordinates.keys.maxBy { it.y }!!.y

        val xMax = knownCoordinates.keys.maxBy { it.x }!!.x
        val yMin = knownCoordinates.keys.minBy { it.y }!!.y

        (yMin..yMax).forEach { y ->
            (xMin..xMax).forEach { x ->
                print(knownCoordinates.getOrDefault(Point2D(x, y), " "))
            }
            print("\n")
        }
    }
}