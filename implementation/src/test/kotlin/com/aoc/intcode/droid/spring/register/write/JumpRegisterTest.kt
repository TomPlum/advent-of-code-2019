package com.aoc.intcode.droid.spring.register.write

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class JumpRegisterTest {
    @Test
    fun toStringTest() {
        assertThat(JumpRegister().toString()).isEqualTo("J")
    }

    @Test
    fun encode() {
        assertThat(JumpRegister().encode()).isEqualTo(listOf<Long>(74))
    }
}