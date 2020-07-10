package com.aoc.intcode.network

import com.aoc.log.AdventLogger

class PacketAnalyser(private val targetAddress: NetworkAddress) {
    fun listen(packet: Packet): Packet? {
        AdventLogger.info("[Packet Analyser] ${packet.data} sent to address ${packet.address}")
        if (packet.address == targetAddress) {
            return packet
        }
        return null
    }
}