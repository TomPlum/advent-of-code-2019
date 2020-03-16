package com.aoc.hull

import org.junit.jupiter.api.Test

class RegistrationIdentifierTest {
    @Test
    fun renderRegistrationIdentifier() {
        val registration = RegistrationIdentifier(mapOf(Pair(Panel(3, 5), HullPaint.WHITE)))
        registration.toString()
    }
}