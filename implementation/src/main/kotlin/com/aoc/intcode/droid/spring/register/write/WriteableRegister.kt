package com.aoc.intcode.droid.spring.register.write

import com.aoc.intcode.droid.spring.register.Register

abstract class WriteableRegister : Register {
    protected var value: Boolean = false

    abstract fun write(value: Long)

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is WriteableRegister) return false
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}