package com.aoc.droid.cryo.droid

import com.aoc.intcode.droid.cryo.droid.DroidLogger

class TestDroidLogger : DroidLogger() {
    val logs = mutableListOf<String>()

    override fun log(text: String) {
        logs.add(text)
    }

    override fun <E> log (obj: E) {
        logs.add(obj.toString())
    }
}