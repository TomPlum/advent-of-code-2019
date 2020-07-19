package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.vault.VaultMap

fun main() {
    val mapData = InputReader.read<String>(Day(18)).value
    val vaultMap = VaultMap(mapData)
    println("Solution Part 1: ${vaultMap.collectKeys()} steps to collect all keys.")
    //Its not 5356 or 5300
}