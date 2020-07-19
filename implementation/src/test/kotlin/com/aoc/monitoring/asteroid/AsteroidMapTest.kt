package com.aoc.monitoring.asteroid

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.TestInputReader
import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.math.Point2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AsteroidMapTest {

    @Test
    fun optimalMappingStationSectorExampleOne() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-1.txt").values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", Point2D(3, 4)), 8))
    }

    @Test
    fun optimalMappingStationSectorExampleTwo() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-2.txt").values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", Point2D(5, 8)), 33))
    }

    @Test
    fun optimalMappingStationSectorExampleThree() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-3.txt").values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", Point2D(1, 2)), 35))
    }

    @Test
    fun optimalMappingStationSectorExampleFour() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-4.txt").values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", Point2D(6, 3)), 41))
    }

    @Test
    fun optimalMappingStationSectorExampleFive() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", Point2D(11, 13)), 210))
    }

    @Test
    @DisplayName("Given Day 10 Part 1 puzzle input, when calculating the number of asteroids in the direct line of sight" +
    "of a monitoring station placed in the optimal position, then it should return 263 at (23, 29)")
    fun solutionPartOne() {
        val map = AsteroidMap(InputReader().readInputString(Day.from(10)).values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", Point2D(23, 29)), 263))
    }

    @Test
    @DisplayName("Given Day 10 Example 5 input, when calculating the coordinates of the 1st asteroid to be destroyed," +
    "then it should return (11, 12)")
    fun exampleFiveAsteroidVaporisedNumber1() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val twoHundredthAsteroid = map.vaporiseAsteroidBelt(1)
        assertThat(twoHundredthAsteroid).isEqualTo(MapSector("#", Point2D(11, 12)))
    }

    @Test
    @DisplayName("Given Day 10 Example 5 input, when calculating the coordinates of the 2nd asteroid to be destroyed," +
    "then it should return (12, 1)")
    fun exampleFiveAsteroidVaporisedNumber2() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val twoHundredthAsteroid = map.vaporiseAsteroidBelt(2)
        assertThat(twoHundredthAsteroid).isEqualTo(MapSector("#", Point2D(12, 1)))
    }

    @Test
    @DisplayName("Given Day 10 Example 5 input, when calculating the coordinates of the 3rd asteroid to be destroyed," +
    "then it should return (12, 2)")
    fun exampleFiveAsteroidVaporisedNumber3() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val twoHundredthAsteroid = map.vaporiseAsteroidBelt(3)
        assertThat(twoHundredthAsteroid).isEqualTo(MapSector("#", Point2D(12, 2)))
    }

    @Test
    @DisplayName("Given Day 10 Example 5 input, when calculating the coordinates of the 10th asteroid to be destroyed," +
    "then it should return (12, 8)")
    fun exampleFiveAsteroidVaporisedNumber10() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val twoHundredthAsteroid = map.vaporiseAsteroidBelt(10)
        assertThat(twoHundredthAsteroid).isEqualTo(MapSector("#", Point2D(12, 8)))
    }

    @Test
    @DisplayName("Given Day 10 Example 5 input, when calculating the coordinates of the 20th asteroid to be destroyed," +
    "then it should return (16, 0)")
    fun exampleFiveAsteroidVaporisedNumber20() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val twoHundredthAsteroid = map.vaporiseAsteroidBelt(20)
        assertThat(twoHundredthAsteroid).isEqualTo(MapSector("#", Point2D(16, 0)))
    }

    @Test
    @DisplayName("Given Day 10 Example 5 input, when calculating the coordinates of the 50th asteroid to be destroyed," +
    "then it should return (16, 9)")
    fun exampleFiveAsteroidVaporisedNumber50() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val twoHundredthAsteroid = map.vaporiseAsteroidBelt(50)
        assertThat(twoHundredthAsteroid).isEqualTo(MapSector("#", Point2D(16, 9)))
    }

    @Test
    @DisplayName("Given Day 10 Example 5 input, when calculating the coordinates of the 100th asteroid to be destroyed," +
    "then it should return (10, 16)")
    fun exampleFiveAsteroidVaporisedNumber100() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val twoHundredthAsteroid = map.vaporiseAsteroidBelt(100)
        assertThat(twoHundredthAsteroid).isEqualTo(MapSector("#", Point2D(10, 16)))
    }

    @Test
    @DisplayName("Given Day 10 Example 5 input, when calculating the coordinates of the 199th asteroid to be destroyed," +
    "then it should return (9, 6)")
    fun exampleFiveAsteroidVaporisedNumber199() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val twoHundredthAsteroid = map.vaporiseAsteroidBelt(199)
        assertThat(twoHundredthAsteroid).isEqualTo(MapSector("#", Point2D(9, 6)))
    }

    @Test
    @DisplayName("Given Day 10 Example 5 input, when calculating the coordinates of the 200th asteroid to be destroyed," +
            "then it should return (8, 2)")
    fun exampleFiveAsteroidVaporisedNumber200() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val twoHundredthAsteroid = map.vaporiseAsteroidBelt(200)
        assertThat(twoHundredthAsteroid).isEqualTo(MapSector("#", Point2D(8, 2)))
    }

    @Test
    @DisplayName("Given Day 10 Example 5 input, when calculating the coordinates of the 201st asteroid to be destroyed," +
    "then it should return (10, 9)")
    fun exampleFiveAsteroidVaporisedNumber201() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val twoHundredthAsteroid = map.vaporiseAsteroidBelt(201)
        assertThat(twoHundredthAsteroid).isEqualTo(MapSector("#", Point2D(10, 9)))
    }

    @Test
    @DisplayName("Given Day 10 Example 5 input, when calculating the coordinates of the 299th asteroid to be destroyed," +
    "then it should return (11, 1)")
    fun exampleFiveAsteroidVaporisedNumber299() {
        val map = AsteroidMap(TestInputReader().readInputAsString("/asteroid/example-5.txt").values)
        val twoHundredthAsteroid = map.vaporiseAsteroidBelt(299)
        assertThat(twoHundredthAsteroid).isEqualTo(MapSector("#", Point2D(11, 1)))
    }

    @Test
    @DisplayName("Given Day 10 Puzzle Input, when finding the 200th vaporised asteroid and adding the factor of its x-ordinate" +
    "by 10 to the y-ordinate, then it should return 1110")
    fun dayTenPartTwoSolution() {
        val map = AsteroidMap(InputReader().readInputString(Day.from(10)).values)
        val answer = map.winBetWithElves()
        assertThat(answer).isEqualTo(1110)
    }


}