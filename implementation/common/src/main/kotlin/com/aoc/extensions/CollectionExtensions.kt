package com.aoc.extensions

/**
 * By mathematical definition; a power set is a set of all the subsets of a set.
 */
fun <T> Collection<T>.powerSet(): Set<Set<T>> = when {
    isEmpty() -> setOf(setOf())
    else -> drop(1).powerSet().let { outer -> outer + outer.map { inner -> inner + first() } }
}