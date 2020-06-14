package com.aoc.intcode.droid.spring.register.read

import assertk.assertThat
import assertk.assertions.isEqualTo
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
}