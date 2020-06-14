package com.aoc.intcode.droid.spring.script

import com.aoc.intcode.droid.spring.register.Encodable
import kotlin.streams.toList

enum class LogicGate : Encodable {
    NOT, AND, OR;

    override fun encode(): List<Long> = this.toString().chars().toList().map { it.toLong() }
}