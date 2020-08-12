package com.aoc.vault

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point2D
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class VaultCacheTest {
    @Nested
    inner class Add {
        fun add() {
            val cache = VaultCache()
            cache.add(Key('A', Point2D(0,0), emptyList()))
            assertThat(cache.contains(Key('A', Point2D(0,0), emptyList())))
        }
    }

    @Nested
    inner class Contains {
        @Test
        fun emptyCache() {
            assertThat(VaultCache().contains(Key('F', Point2D(23,5), emptyList()))).isFalse()
        }

        @Test
        fun doesNotContain() {
            val cache = VaultCache()
            cache.add(Key('T', Point2D(12, 3), emptyList()))
            assertThat(cache.contains(Key('F', Point2D(23,5), emptyList()))).isFalse()
        }

        @Test
        fun containsKeyWithNoLinks() {
            val cache = VaultCache()
            cache.add(Key('V', Point2D(4, 14), emptyList()))
            assertThat(cache.contains(Key('V', Point2D(4, 14), emptyList()))).isTrue()
        }
    }
}