package com.aoc.intcode.droid.repair

import java.lang.IllegalArgumentException

enum class DroidResponse {
    HIT_WALL_POSITION_NOT_CHANGED,
    SUCCESSFULLY_CHANGED_POSITION,
    LOCATED_OXYGEN_SYSTEM;

    companion object {
        fun fromCode(code: Int) = when(code) {
            0 -> HIT_WALL_POSITION_NOT_CHANGED
            1 -> SUCCESSFULLY_CHANGED_POSITION
            2 -> LOCATED_OXYGEN_SYSTEM
            else -> throw IllegalArgumentException("Invalid Droid Response Code ($code)")
        }
    }
}