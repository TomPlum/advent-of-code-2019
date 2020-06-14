package com.aoc.intcode.droid.spring

import com.aoc.intcode.droid.spring.register.Register
import com.aoc.intcode.droid.spring.register.write.WriteableRegister

class SpringScriptInstruction(private val logicGate: LogicGate, private val arg1: Register, private val arg2: WriteableRegister) {

    fun encode(): List<Long> = logicGate.encode() + 32L +  arg1.encode() + 32L + arg2.encode()

    override fun toString(): String = "$logicGate $arg1 $arg2"
}