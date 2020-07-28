package com.aoc.intcode.computer

import java.util.*

class SystemInput {
    val values = LinkedList<Long>()

    /**
     * Adds a single [value] to the [SystemInput] internal [values].
     */
    fun add(value: Long) = values.add(value)

    /**
     * Adds multiple [values] to the [SystemInput] internal [values].
     */
    fun add(values: Collection<Long>) = values.forEach { add(it) }

    /**
     * Removes and returns the value at the front of the [values].
     */
    fun getValue(): Long? = values.poll() //?: throw IllegalStateException("System input is empty!")

    /**
     * Removes all [values].
     */
    fun clear() = values.clear()

    /**
     * Checks if the [SystemInput] contains any [values].
     * @return true if empty, false if contains 1 or more values.
     */
    fun isEmpty() = values.isEmpty()

    override fun toString() = values.toString()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is SystemInput) return false
        return values == other.values
    }

    override fun hashCode() = Objects.hash(values)
}