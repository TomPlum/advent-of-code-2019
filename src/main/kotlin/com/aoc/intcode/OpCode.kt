package com.aoc.intcode

import com.aoc.intcode.InstructionLength.*
import java.util.*

class OpCode(value: String) {
    private var value: Int
    private val parameterModes: Stack<ParameterMode> = Stack()

    init {
        val paddedValue = value.padStart(4, '0')
        if (paddedValue.takeLast(2).toInt() == 99) {
            this.value = 99
        } else {
            this.value = value.takeLast(1).toInt()
        }
        paddedValue.take(paddedValue.length - 2).forEach {
            if (it == '1') {
                parameterModes.push(ParameterMode.IMMEDIATE)
            } else {
                parameterModes.push(ParameterMode.POSITION)
            }
        }
    }

    fun operation(): Operation {
        return when(value) {
            1 -> Operation.ADD
            2 -> Operation.MULTIPLY
            3 -> Operation.INPUT
            4 -> Operation.OUTPUT
            5 -> Operation.JUMP_IF_TRUE
            6 -> Operation.JUMP_IF_FALSE
            7 -> Operation.LESS_THAN
            8 -> Operation.EQUALS
            99 -> Operation.HALT
            else -> Operation.UNKNOWN
        }
    }

    fun instructionLength(): Int {
        return when(value) {
            1 -> FOUR_ADDRESS_INSTRUCTION.length
            2 -> FOUR_ADDRESS_INSTRUCTION.length
            3 -> TWO_ADDRESS_INSTRUCTION.length
            4 -> TWO_ADDRESS_INSTRUCTION.length
            99 -> 0
            else -> throw IllegalArgumentException("Invalid OpCode Value: $value")
        }
    }

    fun getParameterMode(): ParameterMode {
        return parameterModes.pop()
    }

    fun isValid() = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 99).contains(value)

    fun getValue() = value

}