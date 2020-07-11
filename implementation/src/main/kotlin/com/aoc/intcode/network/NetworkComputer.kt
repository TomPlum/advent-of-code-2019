package com.aoc.intcode.network

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.network.packet.Packet
import com.aoc.intcode.network.packet.PacketData
import com.aoc.log.AdventLogger
import com.aoc.intcode.computer.SystemOutput

/**
 * An [IntCodeComputer] with networking enabled.
 *
 * @param software Instructions for the [NetworkInterfaceController].
 */
class NetworkComputer(software: String) {
    private val cpu = IntCodeComputer(software)
    private val nic = NetworkInterfaceController()

    /**
     * Starts the computer.
     */
    fun boot() = cpu.run()

    /**
     * Connects the computer to the [IntCodeComputerNetwork] by assigning it a unique [NetworkAddress].
     */
    fun assignAddress(address: NetworkAddress) = cpu.onNextBoot(address)

    /**
     * Receives a [Packet] sent over the [IntCodeComputerNetwork].
     */
    fun listen(packet: Packet) {
        nic.receive(packet.data)
        AdventLogger.info("[CPU#${packet.address}] Received Packet ${packet.data}")
    }

    /**
     * Communicates with other [NetworkComputer] instances on the [IntCodeComputerNetwork].
     * Resolves and consumes any pending incoming [Packet]s queued by the [NetworkInterfaceController].
     * Transmits any outgoing [Packet]s waiting in the [IntCodeComputer] [SystemOutput].
     *
     * @see handleIncomingPackets
     * @see handleOutgoingPackets
     * @return [Packet]s to be transmitted over the [IntCodeComputerNetwork]
     */
    fun communicate(): List<Packet> {
        handleIncomingPackets()
        return handleOutgoingPackets()
    }

    private fun handleOutgoingPackets(): MutableList<Packet> {
        val output = cpu.program.memory.output
        val outgoingPackets = mutableListOf<Packet>()
        while (output.isNotEmpty()) {
            val (addr, x, y) = output.getFirstThreeValues()
            outgoingPackets.add(Packet(NetworkAddress(addr), PacketData(x, y)))
        }
        return outgoingPackets
    }

    private fun handleIncomingPackets() {
        if (nic.hasEmptyIncomingPacketQueue()) {
            sendInputInstruction(-1L)
            cpu.run()
        } else {
            val (x, y) = nic.consume()
            sendInputInstruction(x)
            sendInputInstruction(y)
            cpu.run()
        }
    }

    private fun sendInputInstruction(value: Long) {
        cpu.program.memory.input.add(value)
    }
}