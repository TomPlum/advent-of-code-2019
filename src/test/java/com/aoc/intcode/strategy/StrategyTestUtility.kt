package com.aoc.intcode.strategy

import com.aoc.intcode.OpCode
import com.aoc.intcode.ParameterMode
import java.util.*

class StrategyTestUtility {
    companion object { //TODO: Add getter to OpCode and remove this? As needed in IntCodeComputer to pass to Strategy
        fun getParameterModes(opCode: OpCode): Stack<ParameterMode> {
            val parameterModes = Stack<ParameterMode>()
            for (i in 1..2) {
                parameterModes.push(opCode.getParameterMode())
            }
            return parameterModes
        }
    }
}