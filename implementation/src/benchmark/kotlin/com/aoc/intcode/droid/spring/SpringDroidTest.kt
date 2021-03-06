package com.aoc.intcode.droid.spring

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.intcode.droid.spring.survey.WalkingStrategy
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
class SpringDroidTest {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(warmups = 5, value = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    fun walkingSurveyingStrategy() {
        SpringDroid(InputReader.read<String>(Day(21)).asSingleString()).surveyHull(WalkingStrategy())
    }

}