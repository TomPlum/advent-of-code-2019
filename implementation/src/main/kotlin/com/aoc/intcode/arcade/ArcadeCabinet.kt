package com.aoc.intcode.arcade

import com.aoc.intcode.computer.IntCodeComputer
import math.Point2D

class ArcadeCabinet(gameSoftware: String) {
    private var computer = IntCodeComputer(gameSoftware)
    private val tiles : MutableMap<Point2D, TileID> = mutableMapOf()
    private var score: Long = 0L
    private var frame = 0
    private val startingBlocks: Int

    init {
        playForFree()
        computer.compute()
        updateTiles(true)
        startingBlocks = getTileQuantity(TileID.BLOCK)
    }

    /**
     * Automatically plays the game to win.
     * @param debug if true it will print the game display to the console. Increases frame-delay significantly.
     * @return Final scores once all the blocks have been broken by the ball.
     */
    fun startGame(debug: Boolean): Long {
        while (!computer.programHalted) {
            frame++
            if (computer.waiting) computer.getProgramMemory().input.add(getJoystickCommand().toLong())
            computer.compute()
            updateTiles(debug)
        }

        if (tiles.filterValues { it == TileID.BLOCK }.count() > 0) {
            println("GAME OVER!")
        } else {
            println("You Win! Final Score: $score")
        }

        return score
    }

    /**
     * @return The quantity of tiles that that match the given [TileID] in the games current state.
     */
    fun getTileQuantity(id: TileID) = tiles.filterValues { it == id}.count()

    private fun playForFree() = computer.getProgramMemory().updateInstructionAtAddress(0, 2)

    private fun getJoystickCommand(): Int {
        val ballCurrentPosition = tiles.filterValues { it == TileID.BALL }.keys.first()
        val paddlePosition = tiles.filterValues { it == TileID.HORIZONTAL_PADDLE }.keys.first()

        return when {
            paddlePosition.x < ballCurrentPosition.x -> JoystickInput.RIGHT.direction
            paddlePosition.x > ballCurrentPosition.x -> JoystickInput.LEFT.direction
            else -> JoystickInput.NEUTRAL.direction
        }
    }

    private fun updateTiles(debug: Boolean) {
        val output = computer.getProgramMemory().output
        while(output.values.isNotEmpty()) {
            val tileData = output.getFirstThreeValues()

            if (tileData.first.toInt() == -1 && tileData.second.toInt() == 0) {
                score = tileData.third
            } else {
                val position = Point2D(tileData.first.toInt(), tileData.second.toInt())
                val tileID = TileID.fromValue(tileData.third.toInt())
                tiles[position] = tileID
            }
        }

        if (debug) updateDisplay()
    }

    private fun updateDisplay() {
        repeat(37) { print("-") }
        print("\n")
        println("| Frame: $frame | B: ${tiles.filterValues { it == TileID.BALL }.keys.first()} | P: ${tiles.filterValues { it == TileID.HORIZONTAL_PADDLE }.keys.first()}")
        println("| Score: $score | Blocks: ${tiles.filterValues { it == TileID.BLOCK }.count()}/$startingBlocks |")

        tiles.forEach {
            if (it.key.x == 36) print("${it.value}\n") else print(it.value)
        }
    }

}