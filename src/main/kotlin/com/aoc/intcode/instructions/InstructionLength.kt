package com.aoc.intcode.instructions

enum class InstructionLength(val length: Int) {
    ONE_ADDRESS_INSTRUCTION(1),
    TWO_ADDRESS_INSTRUCTION(2),
    THREE_ADDRESS_INSTRUCTION(3),
    FOUR_ADDRESS_INSTRUCTION(4)
}