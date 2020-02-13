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

    private fun getAncestralBodyCount(): Int = if (parent != null) 1 + parent!!.getAncestralBodyCount() else 0

    fun getOrbitCount(): Int {
        return getAncestralBodyCount() + orbitingBodies.map { it.getOrbitCount() }.sum()
    }

    fun isMassCenter() = name == "COM"

    fun isYou() = name == "YOU"

    fun isSanta() = name == "SAN"

}