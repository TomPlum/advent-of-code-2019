package com.aoc.intcode.network.packet

import com.aoc.intcode.network.NetworkAddress
import com.aoc.log.AdventLogger
import com.aoc.intcode.network.IntCodeComputerNetwork
import com.aoc.intcode.network.NetworkComputer
import com.aoc.intcode.network.PacketInterceptor

/**
 * Can be attached to an [IntCodeComputerNetwork] in order to intercept [Packet] transmission.
 *
 * @param targetAddress The [NetworkAddress] of the receiving [NetworkComputer] to listen for.
 */
class PacketAnalyser(private val targetAddress: NetworkAddress) : PacketInterceptor {
    /**
     * Analyses the transmitted [packet], logs the transmission data and checks the address.
     * @return The intercepted packet if the [NetworkAddress] matches the [targetAddress], else null.
     */
    override fun listen(packet: Packet): Packet? {
        AdventLogger.info("[Packet Analyser] ${packet.data} was sent to address ${packet.address.format32bit()}")
        if (packet.address == targetAddress) {
            return packet
        }
        return null
    }
}