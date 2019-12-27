package com.aoc.orbit

data class OrbitCountChecksum(val value: String) {
    fun getBarycenter(): String = value.split(")")[0]
    fun getOrbital(): String = value.split(")")[1]
}