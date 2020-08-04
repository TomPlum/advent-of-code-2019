package com.aoc.intcode.droid.cryo.security

import com.aoc.intcode.droid.cryo.droid.CryostasisDroid

/**
 * The response provided by the Security Checkpoint after the [CryostasisDroid] steps on the Pressure-Sensitive Floor.
 *
 * If the droid is too heavy relative to the other droids on the ship, the analysis returns [TOO_HEAVY].
 * If the droid is too light relative to the other droids on the ship, the analysis returns [TOO_LIGHT].
 * If the weight matches that of the other droids, then it is deemed [VALID].
 */
enum class SecurityAnalysis {
    TOO_LIGHT, TOO_HEAVY, VALID
}