package com.aoc.intcode.droid.spring.script

import com.aoc.intcode.droid.spring.SpringDroid
import com.aoc.intcode.droid.spring.register.Encodable
import com.aoc.intcode.droid.spring.register.read.DistanceCode

/**
 * A [Command] is used to terminate a list of [SpringScriptInstruction] in a [SpringScriptProgram].
 *
 * It dictates how the [SpringDroid] behaves when surveying the hull.
 */
enum class Command : Encodable {
    /**
     * When the [SpringDroid] is walking, the maximum length of it sensors and jump is 4, denoted by [DistanceCode.D].
     */
    WALK {
        override fun encode(): List<Long> = "WALK".toCharArray().map { it.toLong() } + '\n'.toLong()
    },

    /**
     * When the [SpringDroid] is running, the maximum length of it sensors and jump is 9, denoted by [DistanceCode.I].
     */
    RUN {
        override fun encode(): List<Long> = "RUN".toCharArray().map { it.toLong() } + '\n'.toLong()
    };
}