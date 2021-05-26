package com.aoc.orbit

/**
 * A node in the tree of an [OrbitalMap]
 */
data class Body(val name: String) {
    private var parent: Body? = null
    private var orbitingBodies: MutableList<Body> = mutableListOf()

    fun setOrbitingBody(body: Body) {
        orbitingBodies.add(body)
        body.parent = this
    }

    /**
     * Returns the number of parents of the given [Body]
     */
    private fun getAncestralBodyCount(): Int = if (parent != null) 1 + parent!!.getAncestralBodyCount() else 0

    /**
     * Returns the sum of all the direct and indirect orbits of the given [Body]
     */
    fun getOrbitCount(): Int = getAncestralBodyCount() + orbitingBodies.map { it.getOrbitCount() }.sum()

    /**
     * Return a Set of all the parent [Body] objects from the given body up to the Center of Mass (COM)
     */
    fun getAncestralBodies(): Set<Body> = (parent?.getAncestralBodies() ?: setOf()) + this

    fun isMassCenter() = name == "COM"

    fun isYou() = name == "YOU"

    fun isSanta() = name == "SAN"

    fun getOrbitingBodies() = orbitingBodies

    fun getParent() = parent

}