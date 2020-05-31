package com.aoc.intcode.vacuum

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DustCollectionReportTest {
    @Test
    fun toStringTest() {
        assertThat(DustCollectionReport(98234).toString()).isEqualTo("98234 Dust Collected")
    }
}