package com.aoc.intcode.network

import com.aoc.intcode.network.packet.PacketData
import java.util.*

/**
 * The networking component of a [NetworkComputer] capable of maintaining incoming and outgoing [PacketData].
 */
class NetworkInterfaceController {
    private val incomingPacketQueue = LinkedList<PacketData>()

    /**
     * Receives incoming [PacketData] sent from another [NetworkComputer] over the [IntCodeComputerNetwork].
     */
    fun receive(datum: PacketData) = incomingPacketQueue.add(datum)

    /**
     * Consumes the [PacketData] that was first received.
     */
    fun consume(): PacketData = incomingPacketQueue.poll() ?: throw IllegalStateException("Empty Incoming Packet Queue")

    /**
     * @return true if the [incomingPacketQueue] is empty, else false.
     */
    fun hasEmptyIncomingPacketQueue() = incomingPacketQueue.isEmpty()
}