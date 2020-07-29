package com.aoc.extensions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class StringExtensionsTest {
    @Nested
    inner class ToAscii {
        @Test
        fun singleAlphaCharacterString() {
            assertThat("J".toAscii()).isEqualTo(listOf<Long>(74))
        }

        @Test
        fun singleNumericalCharacterString() {
            assertThat("3".toAscii()).isEqualTo(listOf<Long>(51))
        }

        @Test
        fun singleSpecialCharacterString() {
            assertThat("~".toAscii()).isEqualTo(listOf<Long>(126))
        }

        @Test
        fun multipleCharacterAlphaNumericalString() {
            assertThat("Hello123".toAscii()).isEqualTo(listOf<Long>(72,101,108,108,111,49,50,51))
        }
    }

    @Nested
    inner class CapitaliseWords {
        @Test
        fun singleWordLowercase() {
            assertThat("hello".capitaliseWords()).isEqualTo("Hello")
        }

        @Test
        fun singleWordUppercaseFirst() {
            assertThat("Hello".capitaliseWords()).isEqualTo("Hello")
        }

        @Test
        fun singleWordUppercase() {
            assertThat("HELLO".capitaliseWords()).isEqualTo("HELLO")
        }

        @Test
        fun emptyString() {
            assertThat("".capitaliseWords()).isEqualTo("")
        }

        @Test
        fun twoWordsLowercase() {
            assertThat("hello there".capitaliseWords()).isEqualTo("Hello There")
        }
    }
}
