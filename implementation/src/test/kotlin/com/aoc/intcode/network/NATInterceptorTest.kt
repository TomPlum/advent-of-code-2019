package com.aoc.intcode.network

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.network.packet.PacketData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

class NATInterceptorTest {
    @Test
    fun receiveAndTransmit() {
        val nat = NATInterceptor()
        nat.receive(PacketData(123, 456))
        assertThat(nat.transmit()).isEqualTo(PacketData(123, 456))
    }

    @Test
    fun receiveShouldOverwriteMemory() {
        val nat = NATInterceptor()
        nat.receive(PacketData(123, 456))
        nat.receive(PacketData(9813, 12))
        assertThat(nat.transmit()).isEqualTo(PacketData(9813, 12))
    }

    @Test
    fun transmitWithoutHavingReceivedAnyPacketData() {
        val e = assertThrows<IllegalStateException> { NATInterceptor().transmit() }
        assertThat(e.message).isEqualTo("NAT has not received any packets")
    }
}