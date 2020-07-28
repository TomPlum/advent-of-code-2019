package com.aoc.extensions

fun String.toAscii(): List<Long> = this.map { it.toLong() }