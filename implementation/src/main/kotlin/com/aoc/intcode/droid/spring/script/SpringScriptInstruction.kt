package com.aoc.intcode.droid.spring.script

import com.aoc.intcode.droid.spring.register.Register
import com.aoc.intcode.droid.spring.register.write.WriteableRegister

/**
 * A single instruction in a [SpringScriptProgram]
 *
 * There are only three instructions available in Spring Script:
 *
 * [AND X Y] sets Y to true if both X and Y are true; otherwise, it sets Y to false.
 * [OR  X Y] sets Y to true if at least one of X or Y is true; otherwise, it sets Y to false.
 * [NOT X Y] sets Y to true if X is false; otherwise, it sets Y to false.
 */
data class SpringScriptInstruction(
        private val logicGate: LogicGate,
        private val arg1: Register,
        private val arg2: WriteableRegister
) {
    fun encode(): List<Long> = logicGate.encode() + 32L +  arg1.encode() + 32L + arg2.encode()

    override fun toString(): String = "$logicGate $arg1 $arg2"
}