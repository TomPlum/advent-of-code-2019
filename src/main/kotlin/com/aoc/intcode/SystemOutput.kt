package com.aoc.intcode

import java.util.*

class SystemOutput {
    val values = LinkedList<Long>()

    fun add(value: Long) = values.add(value)

    fun getLastValue(): Long {
        if (values.size > 0) return values.last
        throw IllegalStateException("System output is empty!")
    }


    /**
     * Returns the last two values from the [SystemOutput]. The [values] are returned as a [Pair] in the format
     * Pair(output[n-2], output[n-1]) where n is the length of the System Output. The values are removed from the
     * system output when they are read via this function.
     * @return The last two values of the [SystemOutput.values]
     */
    fun getLastTwoValues(): Pair<Long, Long> {
        if (values.size > 0) {
            val lastValue = values.pollLast()
            val secondToLastValue = values.pollLast()
            return Pair(secondToLastValue, lastValue)
        }
        throw IllegalStateException("System output is empty!")
    }

    override fun toString() = values.toString()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is SystemOutput) return false
        return values == other.values
    }

    override fun hashCode() = Objects.hash(values)
}