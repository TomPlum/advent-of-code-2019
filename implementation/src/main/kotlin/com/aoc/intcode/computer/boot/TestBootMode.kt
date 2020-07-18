package com.aoc.intcode.computer.boot

import com.aoc.intcode.computer.IntCodeComputer

/**
 * The Thermal Environment Supervision Terminal (TEST) is capable of running diagnostic tests on an [IntCodeComputer].
 * @see IntCodeComputer.onNextBoot
 */
enum class TestBootMode : BootMode {
    AIR_CONDITIONER_DIAGNOSTIC_TEST {
        override fun getCode() = 1L
    },
    THERMAL_RADIATOR_CONTROLLER_DIAGNOSTIC_TEST {
        override fun getCode() = 5L
    },
    BOOST_TEST {
        override fun getCode() = 1L
    },
    SENSOR_BOOST {
        override fun getCode() = 2L
    }
}