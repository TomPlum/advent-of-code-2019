package com.aoc.intcode.droid.cryo.security

data class AirlockPassword(val value: String? = null) {
    fun isValid() = value != null

    override fun toString(): String = value ?: "N/A"
}