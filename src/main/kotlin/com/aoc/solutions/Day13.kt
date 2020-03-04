package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.arcade.ArcadeCabinet
import com.aoc.intcode.arcade.TileID

fun main() {
    val input = InputReader().readInputAsSingleString(Day.from(13))
    val arcadeCabinet = ArcadeCabinet(input)
    println("Solution Part 1: ${arcadeCabinet.getTileQuantity(TileID.BLOCK)}")
}