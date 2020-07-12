package com.aoc.intcode.network

import com.aoc.intcode.network.packet.Packet
import com.aoc.intcode.network.packet.PacketAnalyser
import com.aoc.log.AdventLogger

/**
 * Repair droids have reported a Category 6 disaster, destroying the ships stockpile of CAT6 network cables.
 *
 * Rebuilds the network of [NetworkComputer]s by assigning them each a unique [NetworkAddress] and booting them up.
 *
 * @param software [NetworkInterfaceController] software.
 */
class IntCodeComputerNetwork(private val software: String) {

    private val computers = mutableMapOf<NetworkAddress, NetworkComputer>()
    private var interceptor: PacketInterceptor = NetworkMonitor()
    private val nat = NAT()

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
                if (outgoingPackets.isNotEmpty()) outgoingPackets.forEach { packet ->
                    val echo = interceptor.listen(packet)
                    if (echo != null) {
                        return echo
                    }

                    nat.receive(packet)
                    computers[packet.address]?.listen(packet)
                }

                val status = nat.checkNetworkStatus(computers.values.toList())
                if (status == NetworkStatus.IDLE) {
                    val packet = nat.transmit()
                    computers[packet.address]!!.listen(packet)
                }
                if (status == NetworkStatus.HALTED) {
                    return nat.transmit()
                }
            }
        }
    }

    /**
     * Attaches a [PacketAnalyser] to the [IntCodeComputerNetwork] that listens to all transmitted packets
     * and reports any that are transmitted to the target [address].
     */
    fun attachPacketAnalyserAtTargetAddress(address: NetworkAddress) {
        interceptor = PacketAnalyser(address)
    }

}