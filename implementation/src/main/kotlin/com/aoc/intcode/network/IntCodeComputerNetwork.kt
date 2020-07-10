package com.aoc.intcode.network

class IntCodeComputerNetwork(private val software: String) {

    private val computers = mutableMapOf<NetworkAddress, NetworkComputer>()
    private val packetAnalyser = PacketAnalyser(NetworkAddress(255))

    init {
        repeat(50) { computers[NetworkAddress(it.toLong())] = NetworkComputer(software) }

        computers.forEach { (address, computer) ->
            computer.assignAddress(address)
            computer.boot()
        }

    }

    fun boot() {
        while(true) {
            computers.forEach { (address, computer) ->
                val outgoingPackets = computer.communicate()
                if (outgoingPackets.isNotEmpty()) {
                    outgoingPackets.forEach { packet ->
                        packetAnalyser.listen(packet)
                        computers[packet.address]?.listen(packet)
                    }
                }
            }
        }
    }

}