package com.aoc.intcode.network.packet

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class PacketDataTest {
    @Test
    fun toStringTest() {
        assertThat(PacketData(4353, 98123124).toString()).isEqualTo("(4353, 98123124)")
    }
}