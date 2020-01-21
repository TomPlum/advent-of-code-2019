package com.aoc.intcode

class OpCode private constructor(val value: Int) {
    companion object {
        fun from(value: Int): OpCode {
            return OpCode(value)
        }
    }

    fun operation(): Operation {
        return when(value) {
            1 -> Operation.ADD //4 Address Instruction
            2 -> Operation.MULTIPLY //4 Address Instruction
            3 -> Operation.INPUT //2 Address Instruction
            4 -> Operation.OUTPUT //2 Address Instruction
            99 -> Operation.HALT
            else -> Operation.UNKNOWN
        }
    }

    fun instructionLength(): Int {
        return when(value) {
            1 -> 4
            2 -> 4
            3 -> 2
            4 -> 2
            99 -> 0
            else -> throw IllegalArgumentException("Invalid OpCode Value: $value")
        }
    }

    fun isValid() = arrayOf(1, 2, 3, 4, 99).contains(value)

}