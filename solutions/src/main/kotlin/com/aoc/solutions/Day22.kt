package com.aoc.solutions

import com.aoc.Day
import com.aoc.cards.*
import com.aoc.input.InputReader

fun main() {
    val input = InputReader.read<String>(Day(22)).value
    part1(input)
    part2(input)
}

private fun part1(input: List<String>) {
    val instructions = ShuffleInstructionParser.parse(input)
    val deck = SpaceCardDeckFactory.default()
    val shuffler = SpaceCardDeckShuffler(instructions)
    println("Part 1 Solution: ${shuffler.shuffle(deck).getCardWithValue(2019)}")
}

private fun part2(input: List<String>) {
    val deckSize = 119315717514047.toBigInteger()
    val times = 101741582076661.toBigInteger()
    val position = 2020.toBigInteger()
    val shuffler = GiantSpaceCardDeckShuffler(deckSize, input)
    println("Part 2 Solution: ${shuffler.shuffle(times).getCard(position)}")
    //Too High: 113253064822378
}