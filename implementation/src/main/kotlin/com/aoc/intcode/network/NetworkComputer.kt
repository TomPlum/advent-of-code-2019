package com.aoc.intcode.network

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.network.packet.Packet
import com.aoc.intcode.network.packet.PacketData
import com.aoc.log.AdventLogger

class NetworkComputer(software: String) {
    private val cpu = IntCodeComputer(software)
    private val nic = NetworkInterfaceController()

    fun boot() = cpu.run()

    fun assignAddress(address: NetworkAddress) = cpu.onNextBoot(address)

    fun listen(packet: Packet) {
        nic.receive(packet.data)
        AdventLogger.info("[CPU#${packet.address}] Received Packet ${packet.data}")
    }

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
            val (x, y) = nic.transmit()
            sendInputInstruction(x)
            sendInputInstruction(y)
            cpu.run()
        }
    }

    private fun sendInputInstruction(value: Long) {
        cpu.program.memory.input.add(value)
    }
}