package com.aoc.orbit

class OrbitalMap(input: List<String>) {
    private val map: Set<Body>

    init {
        map = input.fold(HashMap<String, Body>()) { map, bodyPair ->
            val checksum = OrbitCountChecksum(bodyPair)
            val currentBarycenter = checksum.getBarycenter()
            val currentOrbital = checksum.getOrbital()

            //TODO: Must we use a map? Is there not a getOrPut equivalent for a non-map structure?
            val barycenter = map.getOrPut(currentBarycenter.name) { currentBarycenter }
            val orbital = map.getOrPut(currentOrbital.name) { currentOrbital }
            barycenter.setOrbitingBody(orbital)

            return@fold map
        }.values.toSet()
    }

    fun readOrbits(): Int = map.first { it.isMassCenter() }.getOrbitCount()

    fun orbitalTransfersRequiredToReachSanta(): Int {
        val you = map.first { it.isYou() }.getAncestralBodies()
        val santa = map.first { it.isSanta() }.getAncestralBodies()

        val intersection = you.intersect(santa)

        return (you.size - intersection.size) + (santa.size - intersection.size) - 2
    }

}