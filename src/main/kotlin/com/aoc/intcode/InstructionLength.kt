package com.aoc.intcode

enum class InstructionLength(val length: Int) {
    TWO_ADDRESS_INSTRUCTION(2),
    FOUR_ADDRESS_INSTRUCTION(4)
}