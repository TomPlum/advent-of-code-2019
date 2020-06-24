package com.aoc.cards

import com.aoc.cards.strategy.CuttingStrategy
import com.aoc.cards.strategy.IncrementStrategy
import com.aoc.cards.strategy.NewStackStrategy
import com.aoc.cards.strategy.ShufflingStrategy

class ShuffleInstructionParser {
    companion object {
        fun parse(instructions: List<String>): List<ShufflingStrategy> = instructions.map { instruction ->
            val last = instruction.split(" ").last()
            when {
                instruction.matches(Regex("deal with increment \\d+")) -> IncrementStrategy(last.toInt())
                instruction.matches(Regex("deal into new stack")) -> NewStackStrategy()
                instruction.matches(Regex("cut -?\\d+")) -> CuttingStrategy(last.toInt())
                else -> throw IllegalArgumentException("Invalid instruction: $instruction")
            }
        }
    }

}