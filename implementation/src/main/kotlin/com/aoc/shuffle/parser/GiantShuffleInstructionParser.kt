package com.aoc.shuffle.parser

import com.aoc.shuffle.strategy.giant.GiantCuttingStrategy
import com.aoc.shuffle.strategy.giant.GiantIncrementStrategy
import com.aoc.shuffle.strategy.giant.GiantNewStackStrategy
import com.aoc.shuffle.strategy.giant.GiantShufflingStrategy
import java.math.BigInteger

class GiantShuffleInstructionParser(private val deckSize: BigInteger) : ShuffleInstructionParser() {
    fun parse(instructions: List<String>): List<GiantShufflingStrategy> = instructions.map { instruction ->
        val parameter = instruction.getParameter()
        when {
            instruction.isCutTheDeck() -> GiantCuttingStrategy(deckSize, parameter.toBigInteger())
            instruction.isDealIntoNewStack() -> GiantNewStackStrategy(deckSize)
            instruction.isDealWithIncrement() -> GiantIncrementStrategy(deckSize, parameter.toBigInteger())
            else -> throwInvalidInstruction(instruction)
        }
    }.reversed()
}