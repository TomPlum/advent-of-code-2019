package com.aoc.solutions

import com.aoc.vault.VaultMap
import input.Day
import input.InputReader

fun main() {
    val mapData = InputReader().readInputString(Day.from(18)).values
    val vaultMap = VaultMap(mapData)
    println("Solution Part 1: ${vaultMap.collectKeys()} steps to collect all keys.")
    //Its not 5356 or 5300
}