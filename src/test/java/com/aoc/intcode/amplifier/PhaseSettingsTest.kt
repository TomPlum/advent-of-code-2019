package com.aoc.intcode.amplifier

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PhaseSettingsTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 10])
    @DisplayName("Given an invalid phase setting, when creating a Phase Settings, then it should throw an IllegalArgumentException")
    fun invalidPhaseSettingValues(value: Int) {
        val e = assertThrows<IllegalArgumentException> { PhaseSettings(setOf(0, 1, 2, 3, value)) }
        assertThat(e.message).isEqualTo("Phase settings must be between 0 and 4 (inclusive)")
    }

    @Test
    @DisplayName("Given a Phase Settings with only 4 settings, when creating one, then it should throw an IllegalArgumentException")
    fun invalidPhaseSettingsArityTooSmall() {
        val e = assertThrows<IllegalArgumentException> { PhaseSettings(setOf(0, 1, 2, 3)) }
        assertThat(e.message).isEqualTo("A PhaseSettings must have exactly 5 phase settings")
    }

    @Test
    @DisplayName("Given a Phase Settings with 6 settings, when creating one, then it should throw an IllegalArgumentException")
    fun invalidPhaseSettingsArityTooBig() {
        val e = assertThrows<IllegalArgumentException> { PhaseSettings(setOf(0, 1, 2, 3, 4, -1)) }
        assertThat(e.message).isEqualTo("A PhaseSettings must have exactly 5 phase settings")
    }

    @Test
    @DisplayName("Given a valid PhaseSettings, when getting a setting, it should return the first setting and remove it")
    fun getSetting() {
        val phaseSettings = PhaseSettings(setOf(0, 1, 2, 3, 4))
        val settingZero = phaseSettings.getSetting()
        assertThat(settingZero).isEqualTo(0)

        val settingOne = phaseSettings.getSetting()
        assertThat(settingOne).isEqualTo(1)

        val settingTwo = phaseSettings.getSetting()
        assertThat(settingTwo).isEqualTo(2)

        val settingThree = phaseSettings.getSetting()
        assertThat(settingThree).isEqualTo(3)

        val settingFour = phaseSettings.getSetting()
        assertThat(settingFour).isEqualTo(4)

    }

    @Test
    @DisplayName("Given a valid PhaseSettings, when getting a setting for the 6th time, then it should throw an exception")
    fun getSettingWhenNoneLeft() {
        val phaseSettings = PhaseSettings(setOf(0, 1, 2, 3, 4))
        val settingZero = phaseSettings.getSetting()
        assertThat(settingZero).isEqualTo(0)

        val settingOne = phaseSettings.getSetting()
        assertThat(settingOne).isEqualTo(1)

        val settingTwo = phaseSettings.getSetting()
        assertThat(settingTwo).isEqualTo(2)

        val settingThree = phaseSettings.getSetting()
        assertThat(settingThree).isEqualTo(3)

        val settingFour = phaseSettings.getSetting()
        assertThat(settingFour).isEqualTo(4)

        val e = assertThrows<IllegalCallerException> { phaseSettings.getSetting() }
        assertThat(e.message).isEqualTo("Invalid setting request. There are no phase settings remaining")
    }
}