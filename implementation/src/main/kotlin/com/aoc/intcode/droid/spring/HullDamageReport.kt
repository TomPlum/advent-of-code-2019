package com.aoc.intcode.droid.spring

/**
 * A value object representing the amount of damage done to hull.
 * @see SpringDroid
 */
data class HullDamageReport(val damage: Long) {
    override fun toString() = damage.toString()
}