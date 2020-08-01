package com.aoc.intcode.droid.cryo

class AirlockKey(val password: String? = null) {
    fun isValid() = password != null

    override fun toString(): String = password ?: "N/A"
}