package com.aoc.intcode.network

import com.aoc.intcode.network.packet.PacketData
import java.lang.IllegalStateException
import java.util.*

class NetworkInterfaceController {
    private val incomingPacketQueue = LinkedList<PacketData>()

    fun receive(datum: PacketData) = incomingPacketQueue.add(datum)

    fun transmit(): PacketData = incomingPacketQueue.poll() ?: throw IllegalStateException("Empty Incoming Packet Queue")

    fun hasEmptyIncomingPacketQueue() = incomingPacketQueue.isEmpty()
}