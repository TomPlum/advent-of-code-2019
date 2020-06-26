package com.aoc.solutions

import com.aoc.cards.ShuffleInstructionParser
import com.aoc.cards.SpaceCardDeckFactory
import com.aoc.cards.SpaceCardDeckShuffler
import com.aoc.input.Day
import com.aoc.input.InputReader

fun main() {
    val input = InputReader().readInputString(Day.from(22))
    val instructions = ShuffleInstructionParser.parse(input.values)
    val deck = SpaceCardDeckFactory.default()
    val shuffler = SpaceCardDeckShuffler(deck)
    println("Part 1 Solution: ${shuffler.shuffle(instructions).getCardWithValue(2019)}")
}