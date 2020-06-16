package com.aoc.intcode.droid.spring

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Test

class HullDamageReportTest {
    @Test
    fun toStringTest() {
        assertThat(HullDamageReport(1223234234).toString()).isEqualTo("1223234234")
    }

    @Test
    fun equalityPositive() {
        assertThat(HullDamageReport(345345234)).isEqualTo(HullDamageReport(345345234))
    }

    @Test
    fun equalityNegative() {
        assertThat(HullDamageReport(8345234234)).isNotEqualTo(HullDamageReport(345345234))
    }
}