package com.aoc.intcode.arcade

import com.aoc.intcode.IntCodeComputer
import com.aoc.math.Point2D

class ArcadeCabinet(gameSoftware: String) {
    private val computer = IntCodeComputer(gameSoftware)
    private var tiles = setOf<Tile>()

    init {
        computer.compute()
        tiles = computer.getProgramMemory().output.chunked(3).map {
            (Tile(TileID.fromValue(it[2].toInt()), Point2D(it[0].toInt(), it[1].toInt())))
        }.toSet()
    }

    fun startGame() {

    }

    fun getTileQuantity(id: TileID) = tiles.filter { it.id == id}.count()
}