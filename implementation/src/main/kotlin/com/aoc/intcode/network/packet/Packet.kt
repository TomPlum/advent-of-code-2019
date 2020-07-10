package com.aoc.intcode.network.packet

import com.aoc.intcode.network.NetworkAddress

data class Packet(val address: NetworkAddress, val data: PacketData)