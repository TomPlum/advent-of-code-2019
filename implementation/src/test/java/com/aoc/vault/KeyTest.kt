package com.aoc.vault

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import math.Point2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class KeyTest {
    private val a = Key('a', Point2D(0,0), setOf())
    private val b = Key('b', Point2D(0,5), setOf())
    private val c = Key('c', Point2D(15,1), setOf())
    private val d = Key('d', Point2D(3,12), setOf())

    @Test
    fun linkKeys() {
        val a = Key('a', Point2D(0,0), setOf())
        val b = Key('b', Point2D(0,5), setOf())
        a.linkTo(b, 5F)
        assertThat(a.linkedKeys).isEqualTo(mapOf(Pair(b, 5F)))
    }

    @Test
    fun getLinkedKeyWeight() {
        val a = Key('a', Point2D(0,0), setOf())
        val b = Key('b', Point2D(0,5), setOf())
        a.linkTo(b, 5F)
        assertThat(a.getLinkedKeyWeight('b')).isEqualTo(5F)
    }

    @Test
    @DisplayName("Given a key with 4 collected keys, when getting the quantity, it should return 5 as to include itself")
    fun collectedKeyQuantity() {
        val e = Key('e', Point2D(15, 11), setOf(a,b,c,d))
        assertThat(e.collectedKeysQuantity()).isEqualTo(5)
    }

    @Test
    fun pathString() {
        val e = Key('e', Point2D(15, 11), setOf(a,b,c,d))
        assertThat(e.pathString()).isEqualTo("[a, b, c, d, e]")
    }

    @Test
    fun equalityPositive() {
        val e1 = Key('e', Point2D(5, 17), setOf(a,b,c,d))
        val e2 = Key('e', Point2D(5, 17), setOf(a,b,c,d))
        assertThat(e1).isEqualTo(e2);
    }

    @Test
    fun equalityTestNegativeDifferentCollectedKeys() {
        val e1 = Key('e', Point2D(5, 17), setOf(a,b,c,d))
        val e2 = Key('e', Point2D(5, 17), setOf(a,b,c))
        assertThat(e1).isNotEqualTo(e2);
    }

    @Test
    fun equalityTestNegativeDifferentCoordinate() {
        val e1 = Key('e', Point2D(5, 17), setOf(a,b,c,d))
        val e2 = Key('e', Point2D(4, 1), setOf(a,b,c,d))
        assertThat(e1).isNotEqualTo(e2);
    }

    @Test
    fun equalityTestNegativeDifferentName() {
        val e1 = Key('e', Point2D(5, 17), setOf(a,b,c,d))
        val e2 = Key('E', Point2D(5, 17), setOf(a,b,c,d))
        assertThat(e1).isNotEqualTo(e2);
    }
}