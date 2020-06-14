package com.aoc.intcode.droid.spring

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Test

class LogicGateTest {
    @Test
    fun toStringTest() {
        assertThat(LogicGate.NOT.toString()).isEqualTo("NOT")
    }

    @Test
    fun encodeNOT() {
        assertThat(LogicGate.NOT.encode()).isEqualTo(listOf<Long>(78, 79, 84))
    }

    @Test
    fun encodeOR() {
        assertThat(LogicGate.OR.encode()).isEqualTo(listOf<Long>(79, 82))
    }

    @Test
    fun encodeAND() {
        assertThat(LogicGate.AND.encode()).isEqualTo(listOf<Long>(65, 78, 68))
    }

    @Test
    fun equalityPositive() {
        assertThat(LogicGate.NOT).isEqualTo(LogicGate.NOT)
    }

    @Test
    fun equalityNegative() {
        assertThat(LogicGate.NOT).isNotEqualTo(LogicGate.AND)
    }

}