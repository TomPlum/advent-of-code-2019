package com.aoc.monitoring.moon

import kotlin.math.abs

data class Moon(val name: String, var position: Point3D, var velocity: Velocity3D = Velocity3D(0,0,0)) {

    /**
     * To apply velocity, the axes of the [Velocity3D] are simply added to the corresponding axes of the [Point3D]
     * The axes of the [Velocity3D] are not affected.
     */
    fun applyVelocity() {
        position = position.copy(x = position.x + velocity.x, y = position.y + velocity.y, z= position.z + velocity.z)
    }

    /**
     * To apply gravity between two [Moon], on each axis of the [Point3D] the [Velocity3D] changes by
     * exactly +1 or -1 to pull the moons together. If this moons axis is greater than [that] axis, then the
     * respective axis in the [Velocity3D] is decreased by 1. If the axis is lesser, then it is increased by 1.
     * If any of the axes of the [Point3D] are the same, then the respective [Velocity3D] axes remain unchanged.
     * Gravity affects the [Point3D] of both moons when applied.
     */
    fun applyGravity(that: Moon) {
        //TODO: Clean this up
        if (this.position.x > that.position.x) {
            this.velocity = velocity.copy(x = this.velocity.x - 1)
            that.velocity = that.velocity.copy(x = that.velocity.x + 1)
        } else if (this.position.x < that.position.x) {
            this.velocity = velocity.copy(x = this.velocity.x + 1)
            that.velocity = that.velocity.copy(x = that.velocity.x - 1)
        }

        if (this.position.y > that.position.y) {
            this.velocity = velocity.copy(y = this.velocity.y - 1)
            that.velocity = that.velocity.copy(y = that.velocity.y + 1)
        } else if (this.position.y < that.position.y) {
            this.velocity = velocity.copy(y = this.velocity.y + 1)
            that.velocity = that.velocity.copy(y = that.velocity.y - 1)
        }

        if (this.position.z > that.position.z) {
            this.velocity = velocity.copy(z = this.velocity.z - 1)
            that.velocity = that.velocity.copy(z = that.velocity.z + 1)
        } else if(this.position.z < that.position.z) {
            this.velocity = velocity.copy(z = this.velocity.z + 1)
            that.velocity = that.velocity.copy(z = that.velocity.z - 1)
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

    /**
     * @return true if this and [that] have the same [Point3D.x] and [Velocity3D.x] values. false if not.
     */
    fun hasSamePositionVelocityX(that: Moon) = this.position.x == that.position.x || this.velocity.x == that.velocity.x

    /**
     * @return true if this and [that] have the same [Point3D.y] and [Velocity3D.y] values. false if not.
     */
    fun hasSamePositionVelocityY(that: Moon) = this.position.y == that.position.y || this.velocity.y == that.velocity.y

    /**
     * @return true if this and [that] have the same [Point3D.z] and [Velocity3D.z] values. false if not.
     */
    fun hasSamePositionVelocityZ(that: Moon) = this.position.z == that.position.z || this.velocity.z == that.velocity.z

    
    override fun toString() = "$position, $velocity"
}