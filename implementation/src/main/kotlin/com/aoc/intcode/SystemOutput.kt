package com.aoc.intcode

import java.util.*

class SystemOutput {
    val values = LinkedList<Long>()

    /**
     * Adds a single [value] to the [SystemOutput] internal [values]
     */
    fun add(value: Long) = values.add(value)


    /**
     * Gets the last value from the [SystemOutput] internal [values] and consumes it.
     */
    fun getLastValue(): Long {
        if (values.size > 0) return values.pollLast()
        throw IllegalStateException("System output is empty!")
    }


    /**
     * Returns the last two values from the [SystemOutput]. The [values] are returned as a [Pair] in the format
     * Pair(output[n-2], output[n-1]) where n is the length of the System Output.
     * The values are removed from the system output when they are read via this function.
     * @return The last two values of the [SystemOutput.values]
     * @throws IllegalStateException if [values] has a length less than 2
     */
    fun getLastTwoValues(): Pair<Long, Long> {
        if (values.size > 1) {
            val lastValue = values.pollLast()
            val secondToLastValue = values.pollLast()
            return Pair(secondToLastValue, lastValue)
        }
        throw IllegalStateException("System output must have at least two values")
    }

    /**
     * Returns the last three values from the [SystemOutput]. The [values] are returned as a [Triple] in the format
     * Triple(output[n-3], output[n-2], output[n-1]) where n is the length of the System Output.
     * The values are removed from the system output when they are read via this function.
     * @return The last two values of the [SystemOutput.values]
     * @throws IllegalStateException if [values] has a length less than 3
     */
    fun getLastThreeValues(): Triple<Long, Long, Long> {
        if (values.size > 2) {
            //val lastValue = values.pollLast()
            //val secondToLastValue = values.pollLast()
            //val thirdLastValue = values.pollLast()
            return Triple(values.pollFirst(), values.pollFirst(), values.pollFirst())
        }
        throw IllegalStateException("System output must have at least three values")
    }

    override fun toString() = values.toString()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is SystemOutput) return false
        return values == other.values
    }

    override fun hashCode() = Objects.hash(values)
}