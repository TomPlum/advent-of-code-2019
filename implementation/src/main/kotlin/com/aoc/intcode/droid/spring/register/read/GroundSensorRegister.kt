package com.aoc.intcode.droid.spring.register.read

data class GroundSensorRegister(private val code: DistanceCode) : ReadOnlyRegister {
    override fun encode(): List<Long> = code.toString().toCharArray().map { it.toLong() }

    override fun toString(): String = "$code"
}