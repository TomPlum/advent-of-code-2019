package com.aoc.shuffle.parser

import com.aoc.shuffle.strategy.small.CuttingStrategy
import com.aoc.shuffle.strategy.small.IncrementStrategy
import com.aoc.shuffle.strategy.small.NewStackStrategy
import com.aoc.shuffle.strategy.small.ShufflingStrategy

/**
 * Parses literal string representations of [ShufflingStrategy].
 */
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