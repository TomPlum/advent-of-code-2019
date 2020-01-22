package com.aoc.intcode

import java.util.*

class OpCode {
    private var value: Int
    private val parameterModes: Stack<ParameterMode> = Stack()

    constructor(value: String) {
        val paddedValue = value.padStart(4, '0')

        if (paddedValue.takeLast(2).toInt() == 99) {
            this.value = 99
        } else {
            this.value = value.takeLast(1).toInt()
        }

        paddedValue.take(paddedValue.length - 1).forEach {
            if (it == '1') {
                parameterModes.push(ParameterMode.IMMEDIATE)
            } else {
                parameterModes.push(ParameterMode.POSITION)
            }
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

    fun getParameterMode(): ParameterMode {
        return parameterModes.pop()
    }

    fun isValid() = arrayOf(1, 2, 3, 4, 99).contains(value)

    fun getValue() = value

}