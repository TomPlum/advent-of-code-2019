package com.aoc.monitoring.moon

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ScanningModuleTest {
    @Test
    @DisplayName("Given the valid example input data, when scanning for moons, it should parse the data into 3D Points" +
            "and the velocity should be set to the default of (0, 0, 0)")
    fun scanMoonsExampleData() {
        val data = InputReader().readInputAsString("/moon/example-moon-positions-1.txt").values
        val moonPositions = ScanningModule().scanLocalSectorForMoons(data)
        assertThat(moonPositions).isEqualTo(setOf(
                Moon("Io", Point3D(-1,0,2), Velocity3D(0,0,0)),
                Moon("Europa", Point3D(2,-10,-7), Velocity3D(0,0,0)),
                Moon("Ganymede", Point3D(4,-8,8), Velocity3D(0,0,0)),
                Moon("Callisto", Point3D(3,5,-1), Velocity3D(0,0,0))
        ))
    }
}