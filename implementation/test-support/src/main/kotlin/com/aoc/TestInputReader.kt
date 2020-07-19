package com.aoc

import com.aoc.input.Input
import com.aoc.input.InputReader
import java.io.File

class TestInputReader : InputReader() {
    fun readInputAsString(filePath: String): Input<String> = Input(readFile(filePath).readLines())
    fun readInputAsSingleString(filePath: String): String = readInputAsString(filePath).values[0]

    private fun readFile(filePath: String) = File(javaClass.getResource(filePath).path)
}