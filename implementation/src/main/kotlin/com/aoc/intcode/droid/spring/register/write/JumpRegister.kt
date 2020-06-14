package com.aoc.intcode.droid.spring.register.write

class JumpRegister : WriteableRegister() {
    override fun write(value: Long) {
    }

    override fun encode(): List<Long> = listOf('J'.toLong())

    override fun toString(): String = "J"
}