package com.aoc.log

import org.slf4j.LoggerFactory.getLogger

class Logger {
    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = getLogger(javaClass.enclosingClass)
    }
}