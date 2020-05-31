package com.aoc.intcode.arcade

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.log.AdventLogger
import com.aoc.math.Point2D

class ArcadeCabinet(gameSoftware: String) {
    private var computer = IntCodeComputer(gameSoftware)
    private val tiles : MutableMap<Point2D, TileID> = mutableMapOf()
    private var score: Long = 0L
    private var frame = 0
    private val startingBlocks: Int

    init {
        playForFree()
        computer.run()
        updateTiles()
        startingBlocks = getTileQuantity(TileID.BLOCK)
    }

    /**
     * Automatically plays the game to win.
     * @return Final scores once all the blocks have been broken by the ball.
     */
    fun startGame(): Long {
        while (!computer.halted) {
            frame++
            if (computer.waiting) computer.program.memory.input.add(getJoystickCommand().toLong())
            computer.run()
            updateTiles()
        }

        if (tiles.filterValues { it == TileID.BLOCK }.count() > 0) {
            AdventLogger.info("GAME OVER!")
        } else {
            AdventLogger.info("Congratulations, You Win! Final Score: $score")
        }

        return score
    }

    /**
     * @return The quantity of tiles that that match the given [TileID] in the games current state.
     */
    fun getTileQuantity(id: TileID) = tiles.filterValues { it == id}.count()

    private fun playForFree() = computer.program.memory.updateInstructionAtAddress(0, 2)

    private fun getJoystickCommand(): Int {
        val ballCurrentPosition = tiles.filterValues { it == TileID.BALL }.keys.first()
        val paddlePosition = tiles.filterValues { it == TileID.HORIZONTAL_PADDLE }.keys.first()

        return when {
            paddlePosition.x < ballCurrentPosition.x -> JoystickInput.RIGHT.direction
            paddlePosition.x > ballCurrentPosition.x -> JoystickInput.LEFT.direction
            else -> JoystickInput.NEUTRAL.direction
        }
    }

    private fun updateTiles() {
        val output = computer.program.memory.output
        while(output.isNotEmpty()) {
            val tileData = output.getFirstThreeValues()

            if (tileData.first.toInt() == -1 && tileData.second.toInt() == 0) {
                score = tileData.third
            } else {
                val position = Point2D(tileData.first.toInt(), tileData.second.toInt())
                val tileID = TileID.fromValue(tileData.third.toInt())
                tiles[position] = tileID
            }
        }

        updateDisplay()
    }

    private fun updateDisplay() {
        var s = ""
        repeat(37) { s += "-" }
        s += "\n"
        s += "| Frame: $frame | B: ${tiles.filterValues { it == TileID.BALL }.keys.first()} | P: ${tiles.filterValues { it == TileID.HORIZONTAL_PADDLE }.keys.first()}\n"
        s += "| Score: $score | Blocks: ${tiles.filterValues { it == TileID.BLOCK }.count()}/$startingBlocks |\n"

        tiles.forEach {
            if (it.key.x == 36) s += "${it.value}\n" else s += it.value
        }
        AdventLogger.info(s)
    }

}