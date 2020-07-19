package com.aoc

import com.aoc.input.Input
import com.aoc.input.InputReader
import com.aoc.input.StringInput
import java.io.File

class TestInputReader : InputReader() {
    fun readInputAsString(filePath: String): Input<String> = StringInput(readFile(filePath).readLines())
    private fun readFile(filePath: String) = File(javaClass.getResource(filePath).path)
}