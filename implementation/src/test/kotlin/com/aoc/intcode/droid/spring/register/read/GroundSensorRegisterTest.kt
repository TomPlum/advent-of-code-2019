package com.aoc.intcode.droid.spring.register.read

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import assertk.assertions.isNotIn
import org.junit.jupiter.api.Test

class GroundSensorRegisterTest {
    @Test
    fun toStringTest() {
        assertThat(GroundSensorRegister(DistanceCode.D).toString()).isEqualTo("D")
    }

    @Test
    fun encode() {
        assertThat(GroundSensorRegister(DistanceCode.D).encode()).isEqualTo(listOf<Long>(68))
    }

    @Test
    fun equalityPositive() {
        assertThat(GroundSensorRegister(DistanceCode.A)).isEqualTo(GroundSensorRegister(DistanceCode.A))
    }

    @Test
    fun equalityNegative() {
        assertThat(GroundSensorRegister(DistanceCode.D)).isNotEqualTo(GroundSensorRegister(DistanceCode.A))
    }
}