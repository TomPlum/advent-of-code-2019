package com.aoc.intcode.network

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.network.packet.Packet
import com.aoc.intcode.network.packet.PacketData
import org.junit.jupiter.api.Test

class NetworkComputerTest {

    private val software = InputReader.read<String>(Day.from(23)).asSingleString()

    @Test
    fun listen() {
        val computer = NetworkComputer(software)
        computer.listen(Packet(NetworkAddress(45), PacketData(93, 1243)))
        assertThat(computer.isIdle()).isFalse()
    }

    @Test
    fun isIdlePositive() {
        assertThat(NetworkComputer(software).isIdle()).isTrue()
    }

    @Test
    fun isIdleNegative() {
        val computer = NetworkComputer(software)
        computer.assignAddress(NetworkAddress(1))
        computer.listen(Packet(NetworkAddress(14), PacketData(46, 1242)))
        assertThat(computer.isIdle()).isFalse()
    }
}