package com.aoc.intcode.vacuum

class MovementFunction(val name: Char) {
    private val sequence = mutableListOf<Int>()

    fun add(parameter: FunctionParameter, units: Char): MovementFunction {
        if (sequence.isNotEmpty()) addDelimiter()
        sequence.add(parameter.code.toInt())
        addDelimiter()
        sequence.add(units.toInt())
        return this
    }

    fun getSequence(): MutableList<Int> {
        sequence.add(10)
        return sequence
    }

    private fun addDelimiter() = sequence.add(44)

}