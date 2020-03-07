package com.aoc.intcode.arcade

import com.aoc.intcode.IntCodeComputer
import com.aoc.math.Point2D

class ArcadeCabinet(gameSoftware: String) {
    private var computer = IntCodeComputer(gameSoftware)
    private val tilesNew : MutableMap<Point2D, TileID> = mutableMapOf()
    private var score: Long = 0L
    private var frame = 0
    private val startingBlocks: Int

    init {
        playForFree()
        computer.compute()
        updateTiles()
        startingBlocks = getTileQuantity(TileID.BLOCK)
    }

    fun startGame(): Long {
        while (!computer.programHalted) {
            frame++

            if (computer.waiting) {
                computer.getProgramMemory().input.add(getJoystickCommand().toLong())
            }

            computer.compute()

            updateTiles()
        }

        if (tilesNew.filterValues { it == TileID.BLOCK }.count() > 0) {
            println("GAME OVER!")
        } else {
            println("You Win! Final Score: $score")
        }

        return score
    }

    private fun playForFree() = computer.getProgramMemory().updateInstructionAtAddress(0, 2)


    private fun getJoystickCommand(): Int {
        val ballCurrentPosition = tilesNew.filterValues { it == TileID.BALL }.keys.first()
        val paddlePosition = tilesNew.filterValues { it == TileID.HORIZONTAL_PADDLE }.keys.first()

        return when {
            paddlePosition.x < ballCurrentPosition.x -> JoystickInput.RIGHT.direction
            paddlePosition.x > ballCurrentPosition.x -> JoystickInput.LEFT.direction
            else -> JoystickInput.NEUTRAL.direction
        }
    }

    private fun updateTiles() {
        val output = computer.getProgramMemory().output
        while(output.values.isNotEmpty()) {
            val tileData = output.getLastThreeValues()

            if (tileData.first.toInt() == -1 && tileData.second.toInt() == 0) {
                score = tileData.third
            } else {
                val newTile = Tile(TileID.fromValue(tileData.third.toInt()), Point2D(tileData.first.toInt(), tileData.second.toInt()))
                tilesNew[newTile.position] = newTile.id
            }
        }

        updateDisplay()
    }

    private fun updateDisplay() {
        repeat(37) { print("-") }
        print("\n")
        println("| Frame: $frame | B: ${tilesNew.filterValues { it == TileID.BALL }.keys.first()} | P: ${tilesNew.filterValues { it == TileID.HORIZONTAL_PADDLE }.keys.first()}")
        println("| Score: $score | Blocks: ${tilesNew.filterValues { it == TileID.BLOCK }.count()}/$startingBlocks |")

        tilesNew.forEach {
            if (it.key.x == 36) print("${it.value}\n") else print(it.value)
        }
    }

    fun getTileQuantity(id: TileID) = tilesNew.filterValues { it == id}.count()
}