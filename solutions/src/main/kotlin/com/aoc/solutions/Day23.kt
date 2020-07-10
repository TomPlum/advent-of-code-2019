package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.network.IntCodeComputerNetwork
import com.aoc.intcode.network.NetworkAddress

fun main() {
    val software = InputReader().readInputAsSingleString(Day.from(23))
    val network = IntCodeComputerNetwork(software)
    network.attachPacketAnalyserAtTargetAddress(NetworkAddress(255))
    val targetPacket = network.boot()
    println("Solution Part 1: ${targetPacket.data.y}")
}