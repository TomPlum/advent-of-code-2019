import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.orbit.OrbitalMap

fun main() {
    val inputReader = InputReader()
    val bodies = inputReader.readInputString(Day.from(6))

    val orbitCount = OrbitalMap(bodies.values).readOrbits()
    println("Solution - Part 1: $orbitCount")
}