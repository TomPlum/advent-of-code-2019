package com.aoc.intcode.droid.spring

import com.aoc.intcode.droid.spring.register.Register
import com.aoc.intcode.droid.spring.register.write.WriteableRegister

class SpringScriptInstruction(private val logicGate: LogicGate, private val arg1: Register, private val arg2: WriteableRegister) {

    fun encode(): List<Long> = logicGate.encode() + arg1.encode() + arg2.encode()

    override fun toString(): String = "$logicGate $arg1 $arg2"
}