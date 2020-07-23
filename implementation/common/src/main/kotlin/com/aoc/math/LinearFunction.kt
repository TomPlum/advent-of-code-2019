package com.aoc.math

import java.math.BigInteger

/**
 * f(x) = ax + b
 */
class LinearFunction(val k: BigInteger, val m: BigInteger) {
    fun apply(x: BigInteger): BigInteger = x.multiply(k).add(m)

    fun compose(f: LinearFunction) = LinearFunction(k.times(f.k), k.times(f.m).plus(m))
}