package com.aoc.orbit

class OrbitalMap {
    val centreOfMass: Body? = null

    constructor(input: List<String>) {
        val orbitCountChecksums = input.map { OrbitCountChecksum(it) }
    }

    fun readOrbits(): Int = 0

    private fun readDirectOrbits(): Int = 0
    private fun readIndirectOrbits(): Int = 0
}