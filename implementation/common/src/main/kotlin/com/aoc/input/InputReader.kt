package com.aoc.input

import java.io.File

class GenericInputReader {
    companion object {
        @Suppress("UNCHECKED_CAST")
        inline fun <reified T : Any> read(day: Day): Input<T> {
            val lines = File(javaClass.getResource("/day${day.value}/input.txt").path).readLines()

            return when (val cls = T::class.java) {
                String::class.java -> StringInput(lines) as Input<T>
                Integer::class.java -> IntegerInput(lines) as Input<T>
                else -> throw UnsupportedOperationException("Input Reader does not support type: ${cls.simpleName}")
            }
        }
    }
}
