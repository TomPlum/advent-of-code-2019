package com.aoc.vault

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotEqualTo
import assertk.assertions.isTrue
import com.aoc.math.Point2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class KeyTest {
    private val a = Key('a', Point2D(0,0), mutableListOf())
    private val b = Key('b', Point2D(0,5), mutableListOf(a))
    private val c = Key('c', Point2D(15,1), mutableListOf(a,b))
    private val d = Key('d', Point2D(3,12), mutableListOf(a,b,c))

    @Nested
    inner class LinkTo {
        @Test
        fun linkKeys() {
            val a = Key('a', Point2D(0,0), mutableListOf())
            val b = Key('b', Point2D(0,5), mutableListOf())
            a.linkTo(b, 5F)
            assertThat(a.linkedKeys).isEqualTo(mapOf(Pair(b, 5F)))
        }
    }

    @Nested
    inner class GetLinkedKeyWeight {
        @Test
        fun getLinkedKeyWeight() {
            val a = Key('a', Point2D(0,0), mutableListOf())
            val b = Key('b', Point2D(0,5), mutableListOf())
            a.linkTo(b, 5F)
            assertThat(a.getLinkedKeyWeight(b)).isEqualTo(5F)
        }
    }

    @Nested
    inner class HasCompletePath {
        @Test
        fun hasCompletePath() {

        }
    }

    @Nested
    inner class CollectedKeysQuantity {
        @Test
        @DisplayName("Given a key with 4 collected keys, when getting the quantity, it should return 5 as to include itself")
        fun collectedKeyQuantity() {
            val e = Key('e', Point2D(15, 11), mutableListOf(a,b,c,d))
            assertThat(e.collectedKeysQuantity()).isEqualTo(5)
        }
    }

    @Nested
    inner class PathString {
        @Test
        fun pathString() {
            val e = Key('e', Point2D(15, 11), mutableListOf(a,b,c,d))
            assertThat(e.pathString()).isEqualTo("[a, b, c, d, e]")
        }
    }

    @Nested
    inner class HasTransitivelyLinkedKey {
        @Test
        fun hasTransitivelyLinkedKeyPositive() {
            a.linkTo(b, 10F)
            b.linkTo(c, 5F)
            c.linkTo(d, 1F)
            assertThat(a.hasTransitivelyLinkedKey(d)).isTrue()
        }

        @Test
        fun hasTransitivelyLinkedKeyNegative() {
            val unlinkedKey = Key('e', Point2D(0, 1), mutableListOf())
            a.linkTo(b, 10F)
            b.linkTo(c, 5F)
            c.linkTo(d, 1F)
            assertThat(a.hasTransitivelyLinkedKey(unlinkedKey)).isFalse()
        }
    }

    @Nested
    inner class GetAllChildren {
        @Test
        fun getAllChildren() {
            a.linkTo(b, 10F)
            b.linkTo(c, 5F)
            c.linkTo(d, 1F)
            assertThat(a.getAllChildren()).isEqualTo(mutableListOf(b,c,d))
        }
    }

    @Test
    fun toStringTest() {
        assertThat(Key('a', Point2D(0,0), mutableListOf()).toString()).isEqualTo("a")
    }

    @Nested
    inner class Equality {
        @Test
        fun equal() {
            val e1 = Key('e', Point2D(5, 17), mutableListOf(a,b,c,d))
            val e2 = Key('e', Point2D(5, 17), mutableListOf(a,b,c,d))
            assertThat(e1).isEqualTo(e2)
        }

        @Test
        fun differentCollectedKeys() {
            val e1 = Key('e', Point2D(5, 17), mutableListOf(a,b,c,d))
            val e2 = Key('e', Point2D(5, 17), mutableListOf(a,b,c))
            assertThat(e1).isNotEqualTo(e2)
        }

        @Test
        fun differentCoordinate() {
            val e1 = Key('e', Point2D(5, 17), mutableListOf(a,b,c,d))
            val e2 = Key('e', Point2D(4, 1), mutableListOf(a,b,c,d))
            assertThat(e1).isNotEqualTo(e2)
        }

        @Test
        fun differentName() {
            val e1 = Key('e', Point2D(5, 17), mutableListOf(a,b,c,d))
            val e2 = Key('E', Point2D(5, 17), mutableListOf(a,b,c,d))
            assertThat(e1).isNotEqualTo(e2)
        }
    }

}