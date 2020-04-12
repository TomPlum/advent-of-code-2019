package log

import org.junit.jupiter.api.Test

class LoggerTest {
     @Test
     fun debug() {
         Logger.debug("DEBUG LOG!")
     }

    @Test
    fun info() {
        Logger.info("INFO LOG!")
    }

    @Test
    fun warn() {
        Logger.warn("WARN LOG!")
    }

    @Test
    fun error() {
        Logger.error("ERROR LOG!")
    }

    @Test
    fun trace() {
        Logger.trace("TRACE LOG!")
    }
}