package com.aoc.intcode

import com.aoc.intcode.instructions.InstructionStrategy
import com.aoc.intcode.instructions.strategies.*
import java.util.*

data class OpCode(val instructionValue: String) {
    private var value: Int = 0
    val parameterModes: Stack<ParameterMode> = Stack()

    init {
        val paddedValue = instructionValue.padStart(4, '0')
        if (paddedValue.takeLast(2).toInt() == 99) {
            this.value = 99
        } else {
            this.value = instructionValue.takeLast(1).toInt()
        }
        paddedValue.take(paddedValue.length - 2).forEach {
            if (it == '1') {
                parameterModes.push(ParameterMode.IMMEDIATE)
            } else {
                parameterModes.push(ParameterMode.POSITION)
            }
        }
    }

    fun getInstructionStrategy(): InstructionStrategy {
        return when(value) {
            1 -> Add()
            2 -> Multiply()
            3 -> Input()
            4 -> Output()
            5 -> JumpIfTrue()
            6 -> JumpIfFalse()
            7 -> LessThan()
            8 -> Equals()
            9 -> OffsetRelativeBase()
            99 -> Halt()
            else -> Unknown()
        }
    }

    fun getParameterMode(): ParameterMode {
        return parameterModes.pop()
    }

    fun isValid() = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 99).contains(value)

    fun getValue() = instructionValue

}