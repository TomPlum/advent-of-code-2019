package com.aoc.math

import java.math.BigInteger

/**
 * f(x) = ax + b
 */
class LinearFunction(val a: BigInteger, val b: BigInteger) {
    fun apply(x: BigInteger): BigInteger = a.multiply(x).add(b)

    fun compose(f: LinearFunction) = LinearFunction(a.times(f.a), a.times(f.b).plus(b))
}