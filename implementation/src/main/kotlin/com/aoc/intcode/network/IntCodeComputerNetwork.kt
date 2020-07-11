package com.aoc.intcode.network

import com.aoc.intcode.network.packet.Packet
import com.aoc.intcode.network.packet.PacketAnalyser

/**
 * Repair droids have reported a Category 6 disaster, destroying the ships stockpile of CAT6 network cables.
 *
 * Rebuilds the network of [NetworkComputer]s by assigning them each a unique [NetworkAddress] and booting them up.
 *
 * @param software [NetworkInterfaceController] software.
 */
class IntCodeComputerNetwork(private val software: String) {

    private val computers = mutableMapOf<NetworkAddress, NetworkComputer>()
    private var packetAnalyser = PacketAnalyser(NetworkAddress(255))

    init {
        (0..49L).forEach { computers[NetworkAddress(it)] = NetworkComputer(software) }

        computers.forEach { (address, computer) ->
            computer.assignAddress(address)
            computer.boot()
        }
    }

    /**
     * Orchestrates the transmission of [Packet]s between [NetworkComputer]s on the network.
     */
    fun boot(): Packet {
        while(true) {
            computers.forEach { (_, computer) ->
                val outgoingPackets = computer.communicate()
                if (outgoingPackets.isNotEmpty()) {
                    outgoingPackets.forEach { packet ->
                        val echo = packetAnalyser.listen(packet)
                        if (echo != null) {
                            return echo
                        }
                        computers[packet.address]!!.listen(packet)
                    }
                }
            }
        }
    }

    /**
     * Attaches a [PacketAnalyser] to the [IntCodeComputerNetwork] that listens to all transmitted packets
     * and reports any that are transmitted to the target [address].
     */
    fun attachPacketAnalyserAtTargetAddress(address: NetworkAddress) {
        packetAnalyser = PacketAnalyser(address)
    }

}