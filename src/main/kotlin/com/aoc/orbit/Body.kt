package com.aoc.orbit

/**
 * A node in the tree of an [OrbitalMap]
 */
data class Body (val name: String) {
    var parent: Body? = null
    var orbitingBodies: MutableList<Body> = mutableListOf()

    fun setOrbitingBody(body: Body) {
        orbitingBodies.add(body)
        body.parent = this
    }

    fun hasNoOrbitals(): Boolean = orbitingBodies.size == 0
}