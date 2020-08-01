package com.aoc.intcode.droid.cryo.security

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.intcode.droid.cryo.security.AirlockPassword
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class AirlockPasswordTest {
    @Nested
    inner class IsValid {
        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = ["1234", "abcd"])
        fun isValid(value: String) {
            assertThat(AirlockPassword(value).isValid()).isTrue()
        }

        @Test
        fun isNotValid() {
            assertThat(AirlockPassword(null).isValid()).isFalse()
        }
    }

    @Nested
    inner class ToString {
        @Test
        fun toStringWhenValid() {
            assertThat(AirlockPassword("723134").toString()).isEqualTo("723134")
        }

        @Test
        fun toStringWhenInvalid() {
            assertThat(AirlockPassword(null).toString()).isEqualTo("N/A")
        }
    }

}