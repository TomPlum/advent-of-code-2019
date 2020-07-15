package com.aoc.monitoring.eris

import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class ErisPlanetLayoutTest {
    @Test
    fun exampleScanDataParsedCorrectly() {
        val scanData = InputReader().readInputAsString("/eris/example-scan.txt")
        val scan = ErisPlanetLayout(scanData.values)
    }
}
