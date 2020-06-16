package com.aoc.intcode.droid.spring.register.read

import com.aoc.intcode.droid.spring.SpringDroid

/**
 * A Distance Code is the ASCII representation of the distance, in tiles, that a [SpringDroid] can see ahead of itself
 * with the use of its sensors. They are stored in a [GroundSensorRegister].
 *
 * E.g. A = 1, B = 2, C = 3 ... I = 9.
 */
enum class DistanceCode {
    A, B, C, D, E, F, G, H, I
}