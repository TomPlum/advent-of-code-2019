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
    val shuffler = SpaceCardDeckShuffler(instructions)
    println("Part 1 Solution: ${shuffler.shuffle(deck).getCardWithValue(2019)}")

    val giantDeck = SpaceCardDeckFactory.factoryOrder(119315717514047)
    println("Part 2 Solution: ${shuffler.shuffle(giantDeck, 101741582076661).getCard(2020).value}")
}