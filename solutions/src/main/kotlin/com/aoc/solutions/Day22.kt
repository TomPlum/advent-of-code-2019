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

    val giantDeck = SpaceCardDeckFactory.factoryOrder(119315717514047)
    val giantShuffler = SpaceCardDeckShuffler(giantDeck)
    println("Part 2 Solution: ${giantShuffler.shuffle(instructions, 101741582076661).getCard(2020).value}")
}