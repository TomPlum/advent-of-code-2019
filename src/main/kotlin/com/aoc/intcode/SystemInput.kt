package com.aoc.intcode

import java.util.*

class SystemInput {
    val values = LinkedList<Long>()

    /**
     * Adds a single [value] to the [SystemInput] internal [values]
     */
    fun add(value: Long) = values.add(value)

    /**
     * Removes and returns the value at the front of the [values]
     */
    fun getValue(): Long? = values.poll() //?: throw IllegalStateException("System input is empty!")

    override fun toString() = values.toString()
}