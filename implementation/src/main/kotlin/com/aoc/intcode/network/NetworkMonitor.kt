package com.aoc.intcode.network

import com.aoc.intcode.network.packet.Packet
import com.aoc.log.AdventLogger

class NetworkMonitor : PacketInterceptor {
    override fun listen(packet: Packet): Packet? {
        AdventLogger.info("[Network Monitor] ${packet.data} was sent to address ${packet.address.format32bit()}")
        return null
    }
}