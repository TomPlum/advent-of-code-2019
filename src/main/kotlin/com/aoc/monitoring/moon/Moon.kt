package com.aoc.monitoring.moon

import kotlin.math.abs

data class Moon(val name: String, val position: Point3D, val velocity: Velocity3D = Velocity3D(0,0,0)) {

    /**
     * To apply velocity, the axes of the [Velocity3D] are simply added to the corresponding axes of the [Point3D]
     * The axes of the [Velocity3D] are not affected.
     */
    fun applyVelocity() {
        position.x += velocity.x
        position.y += velocity.y
        position.z += velocity.z
    }

    /**
     * To apply gravity between two [Moon], on each axis of the [Point3D] the [Velocity3D] changes by
     * exactly +1 or -1 to pull the moons together. If this moons axis is greater than [that] axis, then the
     * respective axis in the [Velocity3D] is decreased by 1. If the axis is lesser, then it is increased by 1.
     * If any of the axes of the [Point3D] are the same, then the respective [Velocity3D] axes remain unchanged.
     */
    fun applyGravity(that: Moon) {
        if (this.position.x > that.position.x) {
            this.velocity.x--
            that.velocity.x++
        } else if (this.position.x < that.position.x) {
            this.velocity.x++
            that.velocity.x--
        }

        if (this.position.y > that.position.y) {
            this.velocity.y--
            that.velocity.y++
        } else if (this.position.y < that.position.y) {
            this.velocity.y++
            that.velocity.y--
        }

        if (this.position.z > that.position.z) {
            this.velocity.z--
            that.velocity.z++
        } else if(this.position.z < that.position.z) {
            this.velocity.z++
            that.velocity.z--
        }
    }

    /**
     * The Potential Energy (PE) of a [Moon] is equal to the sum of the absolute values of it's [Point3D] axes.
     */
    fun calculatePotentialEnergy() = abs(position.x) + abs(position.y) + abs(position.z)

    /**
     * The Kinetic Energy (KE) of a [Moon] is equal to the sum of the absolute values of it's [Velocity3D] axes.
     */
    fun calculateKineticEnergy() = abs(velocity.x) + abs(velocity.y) + abs(velocity.z)

    /**
     * The Total Energy of a [Moon] is equal to the Kinetic Energy (KE) multiplied by the Potential Energy (PE)
     */
    fun calculateTotalEnergy() = calculatePotentialEnergy() * calculateKineticEnergy()

    override fun toString() = "$position, $velocity"
}