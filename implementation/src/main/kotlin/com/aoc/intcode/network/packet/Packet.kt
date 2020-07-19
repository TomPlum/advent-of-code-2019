package com.aoc.intcode.network.packet

import com.aoc.intcode.network.IntCodeComputerNetwork
import com.aoc.intcode.network.NetworkAddress
import com.aoc.intcode.network.NetworkComputer

/**
 * Stores data that can be sent over an [IntCodeComputerNetwork].
 *
 * @param address The [NetworkAddress] of the receiving [NetworkComputer].
 * @param data The payload of data.
 */
data class Packet(val address: NetworkAddress, val data: PacketData)