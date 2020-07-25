package com.aoc.shuffle

import com.aoc.shuffle.parser.ShuffleInstructionParser

class TestShuffleInstructionParser : ShuffleInstructionParser() {
    fun isIncrement(instruction: String) = instruction.isDealWithIncrement()

    fun isCut(instruction: String) = instruction.isCutTheDeck()

    fun isNewStack(instruction: String) = instruction.isDealIntoNewStack()

    fun getParam(instruction: String) = instruction.getParameter()

    fun handleInvalidInstruction(instruction: String): Nothing = throwInvalidInstruction(instruction)
}