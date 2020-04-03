package com.aoc.intcode.vacuum

import java.util.*

class MovementRoutine : Iterable<MovementFunction> {

    val functions = LinkedList<MovementFunction>()

    fun add(function: MovementFunction): MovementRoutine {
        functions.add(function)
        return this
    }

    fun getFunction() = functions.pollFirst()!!

    fun getRoutine() = functions.map { it.name }.joinToString(",").map { it.toLong() }.toMutableList().also { it.add(10) }

    override fun toString() = functions.joinToString(",") { it.name.toString() }

    override fun iterator() = functions.iterator()
}