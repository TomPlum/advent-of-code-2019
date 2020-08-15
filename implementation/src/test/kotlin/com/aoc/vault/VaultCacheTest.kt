package com.aoc.vault

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.aoc.math.Point2D
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class VaultCacheTest {
    @Nested
    inner class Add {
        fun add() {
            val cache = VaultCache(1)
            cache.add(Key('A', Point2D(0,0), emptyList()))
            assertThat(cache.contains(Key('A', Point2D(0,0), emptyList())))
        }
    }

    @Nested
    inner class Get {
        fun get() {
            val cache = VaultCache(1)
            val key = Key('A', Point2D(0, 0), emptyList())
            cache.add(key)
            assertThat(cache.get(key)).isEqualTo(key)
        }

        @Test
        fun getWhenCollectedKeysAreSame() {
            val key = Key('A', Point2D(12,15), listOf(Key('B', Point2D(1,9), emptyList())))
            val cache = VaultCache(1)
            cache.add(key)
            assertThat(cache.get(key)).isEqualTo(key)
        }

        @Test
        fun getWhenCollectedKeysAreDifferent() {
            val key = Key('A', Point2D(12,15), listOf(Key('B', Point2D(1,9), emptyList())))
            val cache = VaultCache(1)
            cache.add(key)
            assertThat(cache.get(Key('A', Point2D(12,15), listOf(Key('C', Point2D(2,5), emptyList()))))).isNull()
        }
    }

    @Nested
    inner class Contains {
        @Test
        fun emptyCache() {
            assertThat(VaultCache(1).contains(Key('F', Point2D(23,5), emptyList()))).isFalse()
        }

        @Test
        fun doesNotContain() {
            val cache = VaultCache(1)
            cache.add(Key('T', Point2D(12, 3), emptyList()))
            assertThat(cache.contains(Key('F', Point2D(23,5), emptyList()))).isFalse()
        }

        @Test
        fun containsKeyWithNoLinks() {
            val cache = VaultCache(1)
            cache.add(Key('V', Point2D(4, 14), emptyList()))
            assertThat(cache.contains(Key('V', Point2D(4, 14), emptyList()))).isTrue()
        }

        @Test
        fun keyFieldsMatch() {
            val cache = VaultCache(1)
            cache.add(Key('G', Point2D(12, 4), listOf(Key('F', Point2D(5, 8), emptyList()))))
            assertThat(cache.contains(Key('G', Point2D(12, 4), listOf(Key('F', Point2D(5, 8), emptyList())))))
        }

        @Test
        fun collectedKeysAreDifferent() {
            val cache = VaultCache(1)
            cache.add(Key('G', Point2D(12, 4), emptyList()))
            assertThat(cache.contains(Key('G', Point2D(12, 4), listOf(Key('F', Point2D(5, 8), emptyList())))))
        }
    }
}