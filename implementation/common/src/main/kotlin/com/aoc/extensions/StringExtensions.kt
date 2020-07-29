package com.aoc.extensions

fun String.toAscii(): List<Long> = this.map { it.toLong() }
fun String.capitaliseWords(): String = this.split(" ").joinToString(" ") { it.capitalize() }