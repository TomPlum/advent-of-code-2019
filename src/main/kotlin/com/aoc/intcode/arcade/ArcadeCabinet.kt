package com.aoc.intcode.arcade

import com.aoc.intcode.IntCodeComputer
import com.aoc.math.Point2D

class ArcadeCabinet(private val gameSoftware: String) {
    private var computer = IntCodeComputer(gameSoftware)
    private var tiles = setOf<Tile>()
    private var score = 0

    init {
        computer.compute()
        updateTiles()
    }

    fun startGame() {
        computer = IntCodeComputer(gameSoftware) //Reset
        computer.getProgramMemory().updateInstructionAtAddress(0, 2) //Play For Free
        while (!computer.programHalted) {
            computer.compute()
            updateDisplay()

            if (computer.waiting) {
                computer.getProgramMemory().systemInput(getJoystickCommand().toLong())
            }
        }
    }

    private fun getJoystickCommand(): Int {
        val ball = tiles.find { it.id == TileID.BALL }!!.position
        val paddle = tiles.find { it.id == TileID.HORIZONTAL_PADDLE }!!.position
        val xDelta = ball.x - paddle.x
        return when {
            xDelta < 0 -> 1
            xDelta > 0 -> -1
            else -> 0
        }
    }

    private fun updateTiles() {
        tiles = computer.getProgramMemory().output.values.chunked(3).map {
            (Tile(TileID.fromValue(it[2].toInt()), Point2D(it[0].toInt(), it[1].toInt())))
        }.toSet()

        tiles.sortedBy { it.position.x }
    }

    private fun updateDisplay() {
        // repeat(tiles.maxBy { it.position.y }!!.position.y) { print("\b") } //Clear Screen
        //repeat(tiles.count()) { print("\b") }
        updateTiles()
        println("| Score: $score | Blocks Remaining: ${tiles.filter { it.id == TileID.BLOCK }.count()} |")
        tiles.forEach {
            if (it.position.x == 36) print("$it\n") else print(it)
        }
    }

    fun getTileQuantity(id: TileID) = tiles.filter { it.id == id}.count()
}