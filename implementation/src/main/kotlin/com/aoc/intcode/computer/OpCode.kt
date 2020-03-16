package com.aoc.intcode.computer

import com.aoc.intcode.computer.instructions.InstructionStrategy
import com.aoc.intcode.computer.instructions.strategies.*
import java.lang.IllegalStateException
import java.util.*

data class OpCode(val instructionValue: String) {
    private var value: Long = 0
    val parameterModes: Stack<ParameterMode> = Stack()

    init {
        val paddedValue = instructionValue.padStart(5, '0')
        if (paddedValue.takeLast(2).toInt() == 99) {
            this.value = 99
        } else {
            this.value = instructionValue.takeLast(1).toLong()
        }
        paddedValue.take(paddedValue.length - 2).forEach {
            parameterModes.push(when (it) {
                '0' -> ParameterMode.POSITION
                '1' -> ParameterMode.IMMEDIATE
                '2' -> ParameterMode.RELATIVE
                else -> throw IllegalStateException("OpCodes cannot have parameter modes with the value $it")
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

    fun getParameterMode(): ParameterMode = parameterModes.pop()

    fun getValue() = instructionValue

}