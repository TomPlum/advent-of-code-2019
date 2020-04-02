package com.aoc.intcode.computer

import java.util.*

class SystemOutput {
    private val values = LinkedList<Long>()

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
     * Returns the first three values from the [SystemOutput]. The [values] are returned as a [Triple] in the format
     * Triple(output[0], output[1], output[2]) where [values] is zero-based indexed.
     * The values are removed from the system output when they are read via this function.
     * @return The first three values of the [SystemOutput.values]
     * @throws IllegalStateException if [values] has a length less than 3
     */
    fun getFirstThreeValues(): Triple<Long, Long, Long> {
        if (values.size > 2) {
            return Triple(values.pollFirst(), values.pollFirst(), values.pollFirst())
        }
        throw IllegalStateException("System output must have at least three values")
    }

    /**
     * Consumes all values in FIFO order.
     * @return List of all [SystemOutput] values.
     */
    fun getValues(): List<Long> {
        if (values.isEmpty()) throw IllegalStateException("System Output is empty!")
        val consumed = mutableListOf<Long>()
        while(values.iterator().hasNext()) consumed.add(values.pollFirst())
        return consumed
    }

    /**
     * @return True if the [SystemOutput] is empty, false if it is not.
     */
    fun isEmpty() = values.isEmpty()

    /**
     * @return True if the [SystemOutput] has no values left, false if it does.
     */
    fun isNotEmpty() = values.isNotEmpty()

    override fun toString() = values.toString()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is SystemOutput) return false
        return values == other.values
    }

    override fun hashCode() = Objects.hash(values)
}