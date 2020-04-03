package com.aoc.intcode.vacuum

abstract class MovementFunction(val ID: FunctionID) {
    private val sequence = mutableListOf<Int>()

    /**
     * Adds two values to the [MovementFunction] sequence for the [VacuumRobot].
     * The first commands the robot to turn for the given [FunctionParameter].
     * The second commands the robot to move forward for the given number of [units].
     */
    fun add(parameter: FunctionParameter, units: Int): MovementFunction {
        if (sequence.isNotEmpty()) addDelimiter()
        sequence.add(parameter.code.toInt())
        addDelimiter()
        if (units > 9) {
            units.toString().forEach { sequence.add(it.toInt()) }
        } else {
            sequence.add(units.toString().first().toInt())
        }
        return this
    }

    fun getSequence(): MutableList<Int> {
        sequence.add(10)
        return sequence
    }

    private fun addDelimiter() = sequence.add(44)

    override fun toString() = getSequence().toString()
}