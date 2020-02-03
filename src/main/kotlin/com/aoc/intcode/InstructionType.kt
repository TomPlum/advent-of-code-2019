package com.aoc.intcode

enum class InstructionType {
    ADD, MULTIPLY, HALT, INPUT, OUTPUT, JUMP_IF_TRUE, JUMP_IF_FALSE, LESS_THAN, EQUALS, UNKNOWN
}