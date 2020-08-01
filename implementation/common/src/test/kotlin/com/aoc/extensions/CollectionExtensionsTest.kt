package com.aoc.extensions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CollectionExtensionsTest {
    @Nested
    inner class PowerSet {
        @Test
        fun emptyCollection() {
            assertThat(emptyList<String>().powerSet()).isEqualTo(setOf(emptySet()))
        }

        @Test
        fun singleElement() {
            assertThat(listOf("1").powerSet()).isEqualTo(setOf(emptySet(), setOf("1")))
        }

        @Test
        fun twoElements() {
            assertThat(listOf(1, 2).powerSet()).isEqualTo(setOf(emptySet(), setOf(1), setOf(2), setOf(1,2)))
        }
    }
}