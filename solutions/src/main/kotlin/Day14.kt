package com.aoc.solutions

import com.aoc.fuel.factory.Laboratory
import com.aoc.fuel.factory.NanoFactory
import input.Day
import input.InputReader

fun main() {
    val input = InputReader().readInputString(Day.from(14)).values
    val reactions = NanoFactory(input).produceReactionList()

    println("Solution Part 1: ${Laboratory(reactions).minimumOreToProduceOneFuel()} Ore")
    println("Solution Part 2: ${Laboratory(reactions).maximumFuelProducedFromOneTrillionOre()} Fuel")
}