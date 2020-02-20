package com.aoc.intcode

import com.aoc.intcode.instructions.InstructionStrategy
import com.aoc.intcode.instructions.strategies.*
import java.util.*

data class OpCode(val instructionValue: String) {
    private var value: Long = 0
    val parameterModes: Stack<ParameterMode> = Stack()

    init {
        val paddedValue = instructionValue.padStart(4, '0')
        if (paddedValue.takeLast(2).toInt() == 99) {
            this.value = 99
        } else {
            this.value = instructionValue.takeLast(1).toLong()
        }
        paddedValue.take(paddedValue.length - 2).forEach {
            parameterModes.push(when (it) {
                '1' -> ParameterMode.IMMEDIATE
                '2' -> ParameterMode.RELATIVE
                else -> ParameterMode.POSITION
            })
        }
    }

    fun getInstructionStrategy(): InstructionStrategy {
        return when(value) {
            1L -> Add()
            2L -> Multiply()
            3L -> Input()
            4L -> Output()
            5L -> JumpIfTrue()
            6L -> JumpIfFalse()
            7L -> LessThan()
            8L -> Equals()
            9L -> OffsetRelativeBase()
            99L -> Halt()
            else -> Unknown()
        }
    }

    fun getParameterMode(): ParameterMode {
        return parameterModes.pop()
    }

    fun isValid() = arrayOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 99L).contains(value)

    fun getValue() = instructionValue

}