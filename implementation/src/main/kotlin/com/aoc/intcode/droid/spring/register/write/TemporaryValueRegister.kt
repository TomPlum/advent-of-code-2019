package com.aoc.intcode.droid.spring.register.write

class TemporaryValueRegister : WriteableRegister {

    override fun encode(): List<Long> = listOf('T'.toLong())

    override fun toString(): String = "T"

    override fun equals(other: Any?) = true
    override fun hashCode() = javaClass.hashCode()

}