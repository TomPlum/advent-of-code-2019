package com.aoc.intcode.computer

class Program(instructionString: String) {
    var memory: Memory = Memory(instructionString.split(",").map { it.toLong() }.toMutableList())

    override fun toString(): String {
        return memory.instructions.joinToString(",", postfix = "") { it.toString() }
    }
}