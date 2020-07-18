package com.aoc.intcode.network

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.intcode.network.packet.PacketData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NetworkInterfaceControllerTest {
    @Test
    fun consumeShouldSendFirstPacketDatum() {
        val controller = NetworkInterfaceController()
        controller.receive(PacketData(0,1))
        controller.receive(PacketData(0,2))
        assertThat(controller.consume()).isEqualTo(PacketData(0,1))
    }

    @Test
    fun consumeWhenPacketQueueEmpty() {
        val e = assertThrows<IllegalStateException> { NetworkInterfaceController().consume() }
        assertThat(e.message).isEqualTo("Empty Incoming Packet Queue")
    }

    @Test
    fun hasEmptyIncomingPacketQueuePositive() {
        assertThat(NetworkInterfaceController().hasEmptyIncomingPacketQueue()).isTrue()
    }

    @Test
    fun hasEmptyIncomingPacketQueueNegative() {
        val controller = NetworkInterfaceController()
        controller.receive(PacketData(891231, 1235123))
        assertThat(controller.hasEmptyIncomingPacketQueue()).isFalse()
    }
}