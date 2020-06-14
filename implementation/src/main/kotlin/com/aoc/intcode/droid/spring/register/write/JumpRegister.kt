package com.aoc.intcode.droid.spring.register.write

class JumpRegister : WriteableRegister {

    override fun encode(): List<Long> = listOf('J'.toLong())

    override fun toString(): String = "J"

    override fun equals(other: Any?) = true
    override fun hashCode() = javaClass.hashCode()
}