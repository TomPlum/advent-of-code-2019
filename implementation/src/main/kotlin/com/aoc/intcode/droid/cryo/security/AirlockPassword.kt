package com.aoc.intcode.droid.cryo.security

/**
 * The password to the Airlock on Santa's Reindeer-class Starship.
 */
data class AirlockPassword(val value: String? = null) {
    /**
     * @return true if the passwords if valid, else false.
     */
    fun isValid() = value != null

    override fun toString(): String = value ?: "N/A"
}