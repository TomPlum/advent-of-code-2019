package com.aoc.shuffle.parser

abstract class ShuffleInstructionParser {
    protected open fun String.isDealWithIncrement() = this.matches(Regex("deal with increment \\d+"))
    protected open fun String.isDealIntoNewStack() = this.matches(Regex("deal into new stack"))
    protected open fun String.isCutTheDeck() = this.matches(Regex("cut -?\\d+"))

    protected open fun String.getParameter() = this.split(" ").last()

    protected open fun throwInvalidInstruction(instruction: String): Nothing {
        throw IllegalArgumentException("Invalid instruction: $instruction")
    }
}