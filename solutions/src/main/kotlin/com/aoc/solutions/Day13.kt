package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.intcode.arcade.ArcadeCabinet
import com.aoc.intcode.arcade.TileID

fun main() {
    val input = InputReader.read<String>(Day(13)).asSingleString()
    val arcadeCabinet = ArcadeCabinet(input)
    println("Solution Part 1: ${arcadeCabinet.getTileQuantity(TileID.BLOCK)} Blocks")
    println("Solution Part 2: ${arcadeCabinet.startGame()} Score")
}