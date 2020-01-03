package com.aoc.orbit

class OrbitalMap(val input: List<String>) {
    private var centre = Body("COM")
    private var mappedOrbits: MutableMap<Body, MutableList<Body>> = mutableMapOf()

    private fun addBody(body: Body, orbital: Body) {
        centre.orbitingBodies.map { if (it == body) it.setOrbitingBody(orbital) }
    }

    fun readOrbits(): Int {
        val orbitCountChecksums = input.map { OrbitCountChecksum(it) }

        orbitCountChecksums.forEach {
            mappedOrbits.getOrPut(it.getBarycenter(), ::mutableListOf) += it.getOrbital()
        }

        mappedOrbits[Body("COM")]?.map { centre.setOrbitingBody(it) }
        mappedOrbits.remove(Body("COM"))



        val count = mappedOrbits.values.map { it.size }.count()


        return centre.orbitingBodies.flatMap { it.orbitingBodies }.count() //TODO: Total Combined Depth?
    }

}