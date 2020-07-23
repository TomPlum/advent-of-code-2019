package com.aoc.cards

import com.aoc.cards.giant.GiantCuttingStrategy
import com.aoc.cards.giant.GiantIncrementStrategy
import com.aoc.cards.giant.GiantNewStackStrategy
import com.aoc.cards.giant.GiantShufflingStrategy
import java.math.BigInteger

class GiantShuffleInstructionParser(private val deckSize: BigInteger) {
    fun parse(instructions: List<String>): List<GiantShufflingStrategy> = instructions.map { instruction ->
        val n = instruction.split(" ").last()
        when {
            instruction.matches(Regex("deal with increment \\d+")) -> GiantIncrementStrategy(deckSize, n.toBigInteger())
            instruction.matches(Regex("deal into new stack")) -> GiantNewStackStrategy(deckSize)
            instruction.matches(Regex("cut -?\\d+")) -> GiantCuttingStrategy(deckSize, n.toBigInteger())
            else -> throw IllegalArgumentException("Invalid instruction: $instruction")
        }
    }.reversed()
}