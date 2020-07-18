package com.aoc.intcode.network

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.network.packet.Packet
import com.aoc.intcode.network.packet.PacketData
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

class NATTest {
    @Test
    @DisplayName("Given a Packet w/destination address 255, when transmitting, it should send it to address 0")
    fun receiveAndTransmit() {
        val nat = NAT()
        nat.receive(Packet(NetworkAddress(255), PacketData(123, 456)))
        assertThat(nat.transmit()).isEqualTo(Packet(NetworkAddress(0), PacketData(123, 456)))
    }

    @Test
    fun receiveShouldOverwriteMemory() {
        val nat = NAT()
        nat.receive(Packet(NetworkAddress(255), PacketData(123, 456)))
        nat.receive(Packet(NetworkAddress(255), PacketData(9813, 12)))
        assertThat(nat.transmit()).isEqualTo(Packet(NetworkAddress(0), PacketData(9813, 12)))
    }

    @Test
    fun transmitWithoutHavingReceivedAnyPacketData() {
        val e = assertThrows<IllegalStateException> { NAT().transmit() }
        assertThat(e.message).isEqualTo("NAT has not received any packets")
    }

    @Test
    fun checkNetworkStatusWhenIdle() {
        val computer = getIdleComputer()
        val nat = NAT()
        assertThat(nat.checkNetworkStatus(listOf(computer, computer, computer))).isEqualTo(NetworkStatus.IDLE)
    }

    @Test
    fun checkNetworkStatusWhenActive() {
        val nat = NAT()
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