package com.aoc.intcode.droid.spring.register.write

import com.aoc.intcode.droid.spring.register.Register

abstract class WriteableRegister : Register {
    protected var value: Boolean = false

    abstract fun write(value: Long)
}