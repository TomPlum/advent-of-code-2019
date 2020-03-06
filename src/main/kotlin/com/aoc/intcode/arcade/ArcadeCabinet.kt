package com.aoc.intcode.arcade

import com.aoc.intcode.IntCodeComputer
import com.aoc.math.Point2D
import kotlin.math.abs

class ArcadeCabinet(gameSoftware: String) {
    private var computer = IntCodeComputer(gameSoftware)
    private val tilesNew : MutableMap<Point2D, TileID> = mutableMapOf()
    private var ballStates: MutableList<Point2D> = mutableListOf()
    private var score = 0
    private var frame = 0
    private val startingBlocks: Int

    init {
        playForFree()
        computer.compute()
        updateTiles()
        startingBlocks = getTileQuantity(TileID.BLOCK)
    }

    fun startGame(): Int {
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

    private fun playForFree() {
        computer.getProgramMemory().updateInstructionAtAddress(0, 2)
    }

    private fun getJoystickCommand(): Int {
        val ballCurrentPosition = tilesNew.filterValues { it == TileID.BALL }.keys.first()
        val paddlePosition = tilesNew.filterValues { it == TileID.HORIZONTAL_PADDLE }.keys.first()
        val yDelta = abs(ballCurrentPosition.y - paddlePosition.y) //Vertical distance between paddle and balls current position
        val ballMovingRight = ballCurrentPosition.x > getLastBallPosition().x

        val paddleTargetPosition = if (ballMovingRight) {
                Point2D(ballCurrentPosition.x + yDelta, ballCurrentPosition.y + yDelta)
            } else {
                Point2D(ballCurrentPosition.x - yDelta, ballCurrentPosition.y - yDelta)
            }

        return if (paddlePosition == paddleTargetPosition) {
            JoystickInput.NEUTRAL.direction
        } else {
            if (paddleTargetPosition.x < paddlePosition.x) {
                JoystickInput.LEFT.direction
            } else {
                JoystickInput.RIGHT.direction
            }
        }
    }

    private fun updateTiles() {

        val output = computer.getProgramMemory().output
        while(output.values.isNotEmpty()) {
            val tileData = output.getLastThreeValues()

            if (tileData.first.toInt() == -1 && tileData.second.toInt() == 0) {
                score += tileData.third.toInt()
            } else {
                val newTile = Tile(TileID.fromValue(tileData.third.toInt()), Point2D(tileData.first.toInt(), tileData.second.toInt()))
                tilesNew[newTile.position] = newTile.id
            }
        }

        ballStates.add(tilesNew.filterValues { it == TileID.BALL }.keys.first())
        //tiles.sortedBy { it.position.x }

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

    private fun getLastBallPosition(): Point2D {
        return if (ballStates.size <= 1)  {
            ballStates[0]
        } else {
            ballStates[ballStates.size - 2]
        }
    }

    fun getTileQuantity(id: TileID) = tilesNew.filterValues { it == id}.count()
}