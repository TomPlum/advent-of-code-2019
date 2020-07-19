package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.network.IntCodeComputerNetwork
import com.aoc.intcode.network.NetworkAddress

fun main() {
    val software = InputReader.read<String>(Day(23)).asSingleString()
    part1(software)
    part2(software)
}

private fun part1(software: String) {
    val network = IntCodeComputerNetwork(software)
    network.attachPacketAnalyserAtTargetAddress(NetworkAddress(255))
    val targetPacket = network.boot()
    println("Solution Part 1: ${targetPacket.data.y}")
}

private fun part2(software: String) {
    val network = IntCodeComputerNetwork(software)
    val targetPacket = network.boot()
    println("Solution Part 2: ${targetPacket.data.y}")
}