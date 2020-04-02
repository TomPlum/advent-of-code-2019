package com.aoc.intcode.vacuum

import java.util.*

class MovementRoutine {

    private val functions = LinkedList<MovementFunction>()

    fun add(function: MovementFunction): MovementRoutine {
        functions.add(function)
        return this
    }

    fun getFunction() = functions.pollFirst()!!

    override fun toString() = functions.joinToString(separator = ",") { it.name.toString() }
}