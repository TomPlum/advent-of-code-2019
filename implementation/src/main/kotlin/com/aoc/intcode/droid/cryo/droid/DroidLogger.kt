package com.aoc.intcode.droid.cryo.droid

import com.aoc.log.AdventLogger

/**
 * Logging wrapper for the [CryostasisDroid].
 */
open class DroidLogger {
    open fun log(text: String) = AdventLogger.info(text)

    open fun <E> log(obj: E) = AdventLogger.info(obj)
}