package com.aoc.intcode.computer.instructions

enum class InstructionLength(val value: Int) {
    ONE_ADDRESS_INSTRUCTION(1),
    TWO_ADDRESS_INSTRUCTION(2),
    THREE_ADDRESS_INSTRUCTION(3),
    FOUR_ADDRESS_INSTRUCTION(4)
}