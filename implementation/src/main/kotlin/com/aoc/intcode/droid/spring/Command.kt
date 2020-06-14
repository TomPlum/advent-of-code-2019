package com.aoc.intcode.droid.spring

import com.aoc.intcode.droid.spring.register.Encodable

enum class Command : Encodable {
    WALK {
        override fun encode(): List<Long> = this.toString().toCharArray().map { it.toLong() } + '\n'.toLong()
    };
}