package com.aoc.shuffle.parser

import com.aoc.shuffle.strategy.small.CuttingStrategy
import com.aoc.shuffle.strategy.small.IncrementStrategy
import com.aoc.shuffle.strategy.small.NewStackStrategy
import com.aoc.shuffle.strategy.small.ShufflingStrategy

/**
 * Parses literal string representations of [ShufflingStrategy].
 */
class SmallShuffleInstructionParser : ShuffleInstructionParser() {
    fun parse(instructions: List<String>): List<ShufflingStrategy> = instructions.map { instruction ->
        val parameter = instruction.getParameter()
        when {
            instruction.isDealWithIncrement() -> IncrementStrategy(parameter.toInt())
            instruction.isDealIntoNewStack() -> NewStackStrategy()
            instruction.isCutTheDeck() -> CuttingStrategy(parameter.toInt())
            else -> throwInvalidInstruction(instruction)
        }
    }
}