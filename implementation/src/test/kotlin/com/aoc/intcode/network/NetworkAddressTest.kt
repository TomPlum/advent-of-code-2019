package com.aoc.intcode.network

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class NetworkAddressTest {
    @Test
    fun getValue() {
        assertThat(NetworkAddress(12).value).isEqualTo(12)
    }

    @Test
    fun getCode() {
        assertThat(NetworkAddress(23).getCode()).isEqualTo(23)
    }

    @Test
    fun toStringTest() {
        assertThat(NetworkAddress(255).toString()).isEqualTo("255")
    }
}