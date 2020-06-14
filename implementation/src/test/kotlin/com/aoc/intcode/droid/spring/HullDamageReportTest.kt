package com.aoc.intcode.droid.spring

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class HullDamageReportTest {
    @Test
    fun toStringTest() {
        assertThat(HullDamageReport(1223234234).toString()).isEqualTo("1223234234")
    }
}