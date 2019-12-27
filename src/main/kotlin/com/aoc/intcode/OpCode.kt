package com.aoc.intcode

class OpCode private constructor(val value: Int) {
    companion object {
        fun from(value: Int): OpCode {
            return OpCode(value)
        }
    }

    fun operation(): Operation {
        return when(value) {
            1 -> Operation.ADD
            2 -> Operation.MULTIPLY
            3 -> Operation.INPUT
            4 -> Operation.OUTPUT
            99 -> Operation.HALT
            else -> Operation.UNKNOWN
        }
    }

    fun isValid() = arrayOf(1, 2, 3, 4, 99).contains(value)

}