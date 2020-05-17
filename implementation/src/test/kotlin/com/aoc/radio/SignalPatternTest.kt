package com.aoc.radio

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class SignalPatternTest {
    @Test
    fun newSignalPatternShouldDropFirstBaseValue() {
        assertThat(SignalPattern(listOf(5,1,2,6)).getValue()).isEqualTo(1)
    }

    @Test
    fun patternValuesShouldRepeatAfterExhaustingBaseValues() {
        val pattern = SignalPattern(listOf(0,1,0,-1))
        assertThat(pattern.getValue()).isEqualTo(1)
        assertThat(pattern.getValue()).isEqualTo(0)
        assertThat(pattern.getValue()).isEqualTo(-1)
        assertThat(pattern.getValue()).isEqualTo(0)
        assertThat(pattern.getValue()).isEqualTo(1)
        assertThat(pattern.getValue()).isEqualTo(0)
        assertThat(pattern.getValue()).isEqualTo(-1)
    }
}