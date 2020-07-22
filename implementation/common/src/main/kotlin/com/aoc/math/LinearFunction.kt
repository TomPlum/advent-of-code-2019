package com.aoc.math

import java.math.BigInteger

/**
 * f(x) = ax + b
 */
class LinearFunction(private val a: BigInteger, private val b: BigInteger) {
    fun apply(x: BigInteger) = (a * x) + b

    fun compose(f: LinearFunction) = LinearFunction(a.times(f.a), a.times(f.b).plus(b))
}