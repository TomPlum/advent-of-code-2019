package com.aoc.intcode.vacuum

import java.util.*

class MovementRoutine(val a: MovementFunction, val b: MovementFunction, val c: MovementFunction) : Iterable<MovementFunction> {

    private val functions = LinkedList<MovementFunction>()

    fun create(vararg order: FunctionID): MovementRoutine {
        order.forEach { add(it) }
        return this
    }

    fun getFunction() = functions.pollFirst()!!

    fun getBaseFunctions() = setOf(a,b,c)

    fun getRoutine() = functions.map { it.ID }.joinToString(",").map { it.toLong() }.toMutableList().also { it.add(10) }

    private fun add(function: FunctionID) = when (function) {
        FunctionID.A -> functions.add(a)
        FunctionID.B -> functions.add(b)
        FunctionID.C -> functions.add(c)
    }

    override fun toString() = functions.joinToString(",") { it.ID.toString() }

    override fun iterator() = functions.iterator()
}