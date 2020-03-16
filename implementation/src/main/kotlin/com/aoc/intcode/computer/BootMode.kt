package com.aoc.intcode.computer

enum class BootMode(val systemInputCode: Long) {
    AIR_CONDITIONER_DIAGNOSTIC_TEST(1),
    THERMAL_RADIATOR_CONTROLLER_DIAGNOSTIC_TEST(5),
    BOOST_TEST(1),
    SENSOR_BOOST(2)
}