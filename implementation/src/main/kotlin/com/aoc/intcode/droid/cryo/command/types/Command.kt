package com.aoc.intcode.droid.cryo.command.types

import com.aoc.extensions.toAscii
import com.aoc.intcode.droid.spring.register.Encodable
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.command.CommandParser
import com.aoc.intcode.droid.cryo.command.runtime.CommandRuntime

/**
 * A command issued by the user to the [CryostasisDroid].
 * @see CommandParser
 * @see CommandRuntime
 */
abstract class Command(private val instruction: String) : Encodable {
    override fun encode(): List<Long> = instruction.toAscii() + '\n'.toLong()
}