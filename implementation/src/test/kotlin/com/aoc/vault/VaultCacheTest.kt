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
            val cache = VaultCache()
            cache.add(Key('A', Point2D(0,0), mutableListOf()))
            assertThat(cache.contains(Key('A', Point2D(0,0), mutableListOf())))
        }
    }

    @Nested
    inner class Get {
        fun get() {
            val cache = VaultCache()
            val key = Key('A', Point2D(0, 0), mutableListOf())
            cache.add(key)
            assertThat(cache.get(key)).isEqualTo(key)
        }

        @Test
        fun getWhenCollectedKeysAreSame() {
            val key = Key('A', Point2D(12,15), mutableListOf(Key('B', Point2D(1,9), mutableListOf())))
            val cache = VaultCache()
            cache.add(key)
            assertThat(cache.get(key)).isEqualTo(key)
        }

        @Test
        fun getWhenCollectedKeysAreDifferent() {
            val key = Key('A', Point2D(12,15), mutableListOf(Key('B', Point2D(1,9), mutableListOf())))
            val cache = VaultCache()
            cache.add(key)
            assertThat(cache.get(Key('A', Point2D(12,15), mutableListOf(Key('C', Point2D(2,5), mutableListOf()))))).isNull()
        }
    }

    @Nested
    inner class Contains {
        @Test
        fun emptyCache() {
            assertThat(VaultCache().contains(Key('F', Point2D(23,5), mutableListOf()))).isFalse()
        }

        @Test
        fun doesNotContain() {
            val cache = VaultCache()
            cache.add(Key('T', Point2D(12, 3), mutableListOf()))
            assertThat(cache.contains(Key('F', Point2D(23,5), mutableListOf()))).isFalse()
        }

        @Test
        fun containsKeyWithNoLinks() {
            val cache = VaultCache()
            cache.add(Key('V', Point2D(4, 14), mutableListOf()))
            assertThat(cache.contains(Key('V', Point2D(4, 14), mutableListOf()))).isTrue()
        }

        @Test
        fun keyFieldsMatch() {
            val cache = VaultCache()
            cache.add(Key('G', Point2D(12, 4), mutableListOf(Key('F', Point2D(5, 8), mutableListOf()))))
            assertThat(cache.contains(Key('G', Point2D(12, 4), mutableListOf(Key('F', Point2D(5, 8), mutableListOf()))))).isTrue()
        }

        @Test
        fun collectedKeysAreDifferent() {
            val cache = VaultCache()
            cache.add(Key('G', Point2D(12, 4), mutableListOf()))
            assertThat(cache.contains(Key('G', Point2D(12, 4), mutableListOf(Key('F', Point2D(5, 8), mutableListOf()))))).isFalse()
        }

        @Test
        fun collectionKeysSameButTheyHaveNestedKeysToo() {
            val cache = VaultCache()
            val cachedKey = Key('G', Point2D(12, 4), mutableListOf(Key('F', Point2D(5, 8), mutableListOf(Key('K', Point2D(1,2), mutableListOf())))))
            cache.add(cachedKey)
            assertThat(cache.contains(Key('G', Point2D(12,4), mutableListOf(Key('F', Point2D(5,8), mutableListOf()))))).isTrue()
        }
    }
}