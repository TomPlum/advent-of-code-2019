package com.aoc.intcode.droid.spring.register.write

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class TemporaryValueRegisterTest {
    @Test
    fun toStringTest() {
        assertThat(TemporaryValueRegister().toString()).isEqualTo("T")
    }

    @Test
    fun encode() {
        assertThat(TemporaryValueRegister().encode()).isEqualTo(listOf<Long>(84))
    }

    @Test
    fun equalityPositive() {
        assertThat(TemporaryValueRegister()).isEqualTo(TemporaryValueRegister())
    }

}