package com.aoc.intcode.network

import java.util.*

class NetworkInterfaceController {
    private val incomingPacketQueue = LinkedList<PacketData>()

    fun receive(datum: PacketData) = incomingPacketQueue.add(datum)

    fun transmit() = incomingPacketQueue.poll()

    fun hasEmptyIncomingPacketQueue() = incomingPacketQueue.isEmpty()
}