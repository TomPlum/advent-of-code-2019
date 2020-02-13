package com.aoc.orbit

class OrbitalMap(val input: List<String>) {

    fun readOrbits(): Int {

        val map = input.fold(HashMap<String, Body>()) { map, input ->
            val checksum = OrbitCountChecksum(input)
            val currentBarycenter = checksum.getBarycenter()
            val currentOrbital = checksum.getOrbital()

            val barycenter = map.getOrPut(currentBarycenter.name) { currentBarycenter }
            val orbital = map.getOrPut(currentOrbital.name) { currentOrbital }
            barycenter.setOrbitingBody(orbital)

            return@fold map
        }.values.toSet()

        return map.first { it.name == "COM" }.getOrbitCount()

    }

}