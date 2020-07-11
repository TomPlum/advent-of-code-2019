package com.aoc.intcode.network

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class IntCodeComputerNetworkTest {

    private val software = InputReader().readInputAsSingleString(Day.from(23))

    @Test
    fun solutionPartOne() {
        val network = IntCodeComputerNetwork(software)
        network.attachPacketAnalyserAtTargetAddress(NetworkAddress(255))
        val packet = network.boot()
        assertThat(packet.data.y).isEqualTo(23815)
    }
}