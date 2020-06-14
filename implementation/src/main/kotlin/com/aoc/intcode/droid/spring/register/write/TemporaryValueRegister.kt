package com.aoc.intcode.droid.spring.register.write

class TemporaryValueRegister : WriteableRegister() {
    override fun write(value: Long) {
    }

    override fun encode(): List<Long> = listOf('T'.toLong())

    override fun toString(): String = "T"

}