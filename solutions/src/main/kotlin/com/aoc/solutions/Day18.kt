package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.vault.VaultMap
import com.aoc.vault.new.VaultMapNew

fun main() {
    val mapData = InputReader.read<String>(Day(18)).value
   // val vaultMap = VaultMap(mapData)
    val vaultMap = VaultMapNew(mapData)
    println("Solution Part 1: ${vaultMap.collectKeys()} steps to collect all keys.")
    //Its not 5356 or 5300
}