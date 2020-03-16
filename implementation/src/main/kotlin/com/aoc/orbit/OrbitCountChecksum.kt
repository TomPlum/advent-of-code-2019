package com.aoc.orbit

data class OrbitCountChecksum(val value: String) {
    fun getBarycenter(): Body = Body(value.split(")")[0])
    fun getOrbital(): Body = Body(value.split(")")[1])
}