package com.aoc.intcode

class OpCode private constructor(val value: Int) {
    companion object {
        fun from(value: Int): OpCode {
            return OpCode(value)
        }
    }

    fun operation(): Operation {
        if (value == 1) return Operation.ADD
        if (value == 2) return Operation.MULTIPLY
        if (value == 99) return Operation.HALT
        return Operation.UNKNOWN
    }

    fun isValid() = arrayOf(1, 2, 99).contains(value)

}