package com.aoc.intcode.network

import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class IntCodeComputerNetworkTest {

    private val software = InputReader().readInputAsSingleString(Day.from(23))

    @Test
    fun firstComputerNetworkAddress() {
        val network = IntCodeComputerNetwork(software)
        network.boot()
    }
}