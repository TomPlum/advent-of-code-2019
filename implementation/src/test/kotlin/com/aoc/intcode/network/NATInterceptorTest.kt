package com.aoc.intcode.network

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.network.packet.Packet
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

    @Test
    fun checkNetworkStatusWhenIdle() {
        val computer = getIdleComputer()
        val nat = NATInterceptor()
        assertThat(nat.checkNetworkStatus(listOf(computer, computer, computer))).isEqualTo(NetworkStatus.IDLE)
    }

    @Test
    fun checkNetworkStatusWhenActive() {
        val nat = NATInterceptor()
        val network = listOf(getIdleComputer(), getIdleComputer(), getActiveComputer(), getIdleComputer())
        assertThat(nat.checkNetworkStatus(network)).isEqualTo(NetworkStatus.ACTIVE)
    }

    private fun getActiveComputer(): NetworkComputer {
        val active = NetworkComputer("99")
        active.listen(Packet(NetworkAddress(50), PacketData(12, 57)))
        return active
    }

    private fun getIdleComputer(): NetworkComputer {
        return NetworkComputer("99")
    }
}