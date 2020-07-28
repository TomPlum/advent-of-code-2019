package com.aoc.math

import java.math.BigInteger

/**
 * Represents a linear function in the form; f(x) = ax + b
 */
data class LinearFunction(val a: BigInteger, val b: BigInteger) {
    /**
     * Applies the given value [x] to the function and returns the output value for f(x).
     */
    fun apply(x: BigInteger): BigInteger = a.multiply(x).add(b)

    /**
     * Composes the given function g(x) with f(x) to find the product of the two f(g(x))
     */
    fun compose(f: LinearFunction) = LinearFunction(a.times(f.a), a.times(f.b).plus(b))
}