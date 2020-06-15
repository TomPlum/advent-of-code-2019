package com.aoc.intcode.droid.spring.script

import com.aoc.intcode.droid.spring.register.Encodable

enum class Command : Encodable {
    WALK {
        override fun encode(): List<Long> = "WALK".toCharArray().map { it.toLong() } + '\n'.toLong()
    },
    RUN {
        override fun encode(): List<Long> = "RUN".toCharArray().map { it.toLong() } + '\n'.toLong()
    };
}