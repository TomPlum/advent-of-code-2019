package com.aoc.orbit

class OrbitalMap(val input: List<String>) {

    fun readOrbits(): Int {

        val map = input.fold(HashMap<String, Body>()) { map, bodyPair ->
            val checksum = OrbitCountChecksum(bodyPair)
            val currentBarycenter = checksum.getBarycenter()
            val currentOrbital = checksum.getOrbital()

            //TODO: Must we use a map? Is there not a getOrPut equivalent for a non-map structure?
            val barycenter = map.getOrPut(currentBarycenter.name) { currentBarycenter }
            val orbital = map.getOrPut(currentOrbital.name) { currentOrbital }
            barycenter.setOrbitingBody(orbital)

            return@fold map
        }.values.toSet()

        return map.first { it.isMassCenter() }.getOrbitCount()

    }

}