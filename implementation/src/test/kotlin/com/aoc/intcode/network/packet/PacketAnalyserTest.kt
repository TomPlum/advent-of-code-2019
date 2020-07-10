package com.aoc.intcode.network.packet

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.aoc.intcode.network.NetworkAddress
import org.junit.jupiter.api.Test

class PacketAnalyserTest {
    @Test
    fun listenWhenIncomingPacketAddressMatchesTarget() {
        val packet = Packet(NetworkAddress(5), PacketData(126, 678))
        val interceptedPacket = PacketAnalyser(NetworkAddress(5)).listen(packet)
        assertThat(interceptedPacket).isEqualTo(packet)
    }

    @Test
    fun listenWhenIncomingPacketAddressDoesNotMatchTarget() {
        val packet = Packet(NetworkAddress(12), PacketData(834534, 12312))
        val interceptedPacket = PacketAnalyser(NetworkAddress(5)).listen(packet)
        assertThat(interceptedPacket).isNull()
    }
}