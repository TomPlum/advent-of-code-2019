package com.aoc.intcode.droid.cryo.command.types

import com.aoc.extensions.toAscii
import com.aoc.intcode.droid.spring.register.Encodable

abstract class Command(private val instruction: String) : Encodable {
    override fun encode(): List<Long> = instruction.toAscii() + '\n'.toLong()
}