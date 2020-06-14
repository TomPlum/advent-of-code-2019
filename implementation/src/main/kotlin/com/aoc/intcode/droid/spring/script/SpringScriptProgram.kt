package com.aoc.intcode.droid.spring.script

import com.aoc.intcode.droid.spring.register.Encodable
import java.lang.IllegalStateException
import java.util.*

class SpringScriptProgram : Encodable {
    val instructions = LinkedList<SpringScriptInstruction>()

    fun addInstruction(instruction: SpringScriptInstruction) {
        if (instructions.size < 15) {
            instructions.add(instruction)
        } else {
            throw IllegalStateException("A Spring Script program cannot have more than 15 instructions")
        }
    }

    override fun encode(): List<Long> = instructions.map { it.encode() + listOf('\n'.toLong()) }.flatten().toMutableList() + Command.WALK.encode()

    override fun toString() = "${instructions.joinToString(separator = "\n")}\n${Command.WALK}"

}