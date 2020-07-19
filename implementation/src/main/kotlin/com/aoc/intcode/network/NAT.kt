package com.aoc.intcode.network

import com.aoc.intcode.network.packet.Packet
import com.aoc.intcode.network.packet.PacketData
import com.aoc.log.AdventLogger

class NAT {
    private var memory: PacketData? = null

    private val transmittedPackets = mutableListOf<PacketData>()

    fun receive(packet: Packet) {
        if (packet.address.value == 255L) {
            memory = packet.data
        }
    }

    fun transmit(): Packet {
        val payload = memory ?: throw IllegalStateException("NAT has not received any packets")
        AdventLogger.info("[NAT] Transmitting $payload to 192.168.1.0")
        transmittedPackets.add(payload)
        if (sentConsecutivePayloads()) AdventLogger.info("[NAT] $payload has been transmitted twice in a row")
        return Packet(NetworkAddress(0), payload)
    }

    fun checkNetworkStatus(computers: List<NetworkComputer>): NetworkStatus {
        val status = when {
            computers.all { it.isIdle() } -> NetworkStatus.IDLE
            sentConsecutivePayloads() -> NetworkStatus.HALTED
            else -> NetworkStatus.ACTIVE
        }
        if (status != NetworkStatus.ACTIVE) AdventLogger.info("[NAT] IntCode Network is $status")
        return status
    }

    private fun sentConsecutivePayloads(): Boolean {
        if (transmittedPackets.size >= 2) {
            return transmittedPackets.takeLast(2).distinct().size == 1
        }
        return false
    }

}