package com.aoc.math

import java.math.BigInteger

/**
 * f(x) = ax + b mod(m)
 */
class LinearCongruentialFunction(private val a: Int, private val b: Int, private val m: BigInteger, private val x: BigInteger) {
    fun apply() = (a.toBigInteger() * x) + b.toBigInteger() % m
}