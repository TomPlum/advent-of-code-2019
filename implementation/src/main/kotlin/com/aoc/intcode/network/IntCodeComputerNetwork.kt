package com.aoc.intcode.network

import com.aoc.intcode.network.packet.Packet
import com.aoc.intcode.network.packet.PacketAnalyser

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

    fun boot(): Packet {
        while(true) {
            computers.forEach { (address, computer) ->
                val outgoingPackets = computer.communicate()
                if (outgoingPackets.isNotEmpty()) {
                    outgoingPackets.forEach { packet ->
                        val echo = packetAnalyser.listen(packet)
                        if (echo != null) {
                            return echo
                        }
                        computers[packet.address]?.listen(packet)
                    }
                }
            }
        }
    }

    fun attachPacketAnalyserAtTargetAddress(address: NetworkAddress) {
        packetAnalyser = PacketAnalyser(address)
    }

}