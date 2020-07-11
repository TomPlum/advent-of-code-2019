package com.aoc.intcode.network

import com.aoc.intcode.network.packet.PacketData
import com.aoc.log.AdventLogger
import java.lang.IllegalStateException

class NATInterceptor {
    private var memory: PacketData? = null

    private val transmittedPackets = mutableListOf<PacketData>()

    fun receive(packetData: PacketData) {
        memory = packetData
    }

    fun transmit(): PacketData {
        val payload = memory ?: throw IllegalStateException("NAT has not received any packets")
        AdventLogger.info("[NAT] Transmitting $payload to 192.168.1.0")
        transmittedPackets.add(payload)
        if (transmittedPackets.last() == payload) AdventLogger.info("[NAT] $payload has been transmitted twice in a row")
        return payload
    }

    fun checkNetworkStatus(computers: List<NetworkComputer>): NetworkStatus {
        val allIdle = computers.all { it.isIdle() }
        return if (allIdle) NetworkStatus.IDLE else NetworkStatus.ACTIVE
    }
}