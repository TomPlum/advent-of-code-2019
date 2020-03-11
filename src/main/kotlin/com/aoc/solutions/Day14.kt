package com.aoc.solutions

import com.aoc.fuel.factory.Laboratory
import com.aoc.fuel.factory.NanoFactory
import com.aoc.input.Day
import com.aoc.input.InputReader

fun main() {
    val input = InputReader().readInputString(Day.from(14)).values
    val reactions = NanoFactory(input).produceReactionList()
    val lab = Laboratory(reactions)
    println("Solution Part 1: ${lab.minimumOreToProduceOneFuel()}")
}