package com.aoc.intcode.network.packet

import com.aoc.intcode.network.NetworkAddress
import com.aoc.log.AdventLogger

class PacketAnalyser(private val targetAddress: NetworkAddress) {
    fun listen(packet: Packet): Packet? {
        AdventLogger.info("[Packet Analyser] ${packet.data} was sent to address 192.168.1.${packet.address}")
        if (packet.address == targetAddress) {
            return packet
        }
        return null
    }
}