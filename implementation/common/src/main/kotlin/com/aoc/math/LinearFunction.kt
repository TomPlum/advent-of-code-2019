package com.aoc.math

import java.math.BigInteger

/**
 * Represents a linear function in the form; f(x) = ax + b
 */
data class LinearFunction(val k: BigInteger, val m: BigInteger) {
    fun apply(x: BigInteger): BigInteger = k.multiply(x).add(m)

    fun compose(f: LinearFunction) = LinearFunction(k.times(f.k), k.times(f.m).plus(m))
}