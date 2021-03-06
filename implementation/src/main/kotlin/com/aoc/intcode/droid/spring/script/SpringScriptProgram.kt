package com.aoc.intcode.droid.spring.script

import com.aoc.intcode.computer.Program
import com.aoc.intcode.droid.spring.SpringDroid
import com.aoc.intcode.droid.spring.register.Encodable
import com.aoc.intcode.droid.spring.register.Register
import com.aoc.intcode.droid.spring.register.write.JumpRegister
import com.aoc.intcode.droid.spring.register.write.TemporaryValueRegister
import java.util.*

/**
 * A program written in 'Spring Script'. A simplified assembly language designed to run on a [SpringDroid].
 * Spring Script programs only use [Boolean] values, not [Number] or [String] like a traditional [Program].
 *
 * Due to onboard memory limitations, the [SpringScriptProgram] can only remember 15 [SpringScriptInstruction].
 *
 * There are two [Register] that ultimately decide what the [SpringDroid] does each time it runs the program.
 * These are the [JumpRegister] and the [TemporaryValueRegister].
 * If the [JumpRegister] is TRUE at the end of the [SpringScriptProgram] the then [SpringDroid] will jump.
 */
class SpringScriptProgram(private val command: Command) : Encodable {
    val instructions = LinkedList<SpringScriptInstruction>()

    /**
     * Adds a single [SpringScriptInstruction] to the program.
     * @throws IllegalStateException if more than 15 instructions attempt to be added to the program.
     */
    fun addInstruction(instruction: SpringScriptInstruction) {
        if (instructions.size < 15) {
            instructions.add(instruction)
        } else {
            throw IllegalStateException("A Spring Script program cannot have more than 15 instructions")
        }
    }

    override fun encode(): List<Long> = instructions.map {
        it.encode() + listOf('\n'.toLong())
    }.flatten().toMutableList() + command.encode()

    override fun toString() = "${instructions.joinToString(separator = "\n")}\n${command}"

}