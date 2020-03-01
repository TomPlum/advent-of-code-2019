package com.aoc.hull

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class HullTest {
    @Test
    @DisplayName("Given a new Hull, when painting a Panel, then it should add the Panel to the Hull with the correct coordinates and colour")
    fun paintPanel() {
        val hull = Hull()
        hull.paintPanel(0, 0, HullPaint.WHITE)
        assertThat(hull.panels).isEqualTo(mapOf(Pair(Panel(0, 0), HullPaint.WHITE)))
    }

    @Test
    @DisplayName("Given an existing Hull, when painting a Panel that has already been painted, then it should just update the colour")
    fun paintExistingPanel() {
        val hull = Hull()
        hull.paintPanel(4, 5, HullPaint.WHITE)
        hull.paintPanel(4, 5, HullPaint.BLACK)
        assertThat(hull.panels).isEqualTo(mapOf(Pair(Panel(4, 5), HullPaint.BLACK)))
    }

    @Test
    @DisplayName("Given an existing hull, when getting the colour of a painted panel, then it should return the correct colour")
    fun getPanelColour() {
        val hull = Hull()
        hull.paintPanel(6, 7, HullPaint.WHITE)
        assertThat(hull.getPanelColour(6, 7)).isEqualTo(HullPaint.WHITE)
    }

    @Test
    @DisplayName("Given a Hull, when getting the colour of an un-tracked panel, then it should be assumed to be black")
    fun getPanelColourForPanelNotTrackedByHull() {
        val hull = Hull()
        val panelColour = hull.getPanelColour(20, 60)
        assertThat(panelColour).isEqualTo(HullPaint.BLACK)
    }
}