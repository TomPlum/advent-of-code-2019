package com.aoc.solutions

import com.aoc.fuel.factory.Laboratory
import com.aoc.fuel.factory.NanoFactory
import com.aoc.input.Day
import com.aoc.input.InputReader

fun main() {
    val input = InputReader.read<String>(Day(14)).value
    val reactions = NanoFactory(input).produceReactionList()

    println("Solution Part 1: ${Laboratory(reactions).minimumOreToProduceOneFuel()} Ore")
    println("Solution Part 2: ${Laboratory(reactions).maximumFuelProducedFromOneTrillionOre()} Fuel")
}