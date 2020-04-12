package log

import org.slf4j.LoggerFactory.getLogger

class Logger {
    companion object {
        @JvmStatic
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val logger = getLogger(javaClass.enclosingClass)

        fun debug(message: String) = logger.debug(message)

        fun info(message: String) = logger.info(message)

        fun warn(message: String) = logger.warn(message)

        fun error(message: String) = logger.error(message)

        fun trace(message: String) = logger.trace(message)
    }

    override fun toString() = "AoC Logger"
}