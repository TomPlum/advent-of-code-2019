package com.aoc.intcode.network

import com.aoc.intcode.network.packet.PacketData
import java.util.*

class NetworkInterfaceController {
    private val incomingPacketQueue = LinkedList<PacketData>()

    fun receive(datum: PacketData) = incomingPacketQueue.add(datum)

    fun transmit(): PacketData = incomingPacketQueue.poll()

    fun hasEmptyIncomingPacketQueue() = incomingPacketQueue.isEmpty()
}