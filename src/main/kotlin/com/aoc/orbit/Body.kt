package com.aoc.orbit

/**
 * A node in the tree of an [OrbitalMap]
 */
class Body (value: String) {
    var parent: Body? = null
    var orbitingBodies: MutableList<Body> = mutableListOf()

    fun orbitedBy(body: Body) {
        orbitingBodies.add(body )
        this.parent = this
    }
}