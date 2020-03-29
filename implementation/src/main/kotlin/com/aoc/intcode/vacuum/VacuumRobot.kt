package com.aoc.intcode.vacuum

import com.aoc.intcode.computer.IntCodeComputer

/**
 * @param instructions The Aft Scaffolding Control and Information Interface (ASCII) program.
 */
class VacuumRobot(val instructions: String) {
    private val computer = IntCodeComputer(instructions)

    fun exploreShipsExteriorScaffolding(): ScaffoldMap {
        computer.compute()
        val systemOutput = computer.getProgramMemory().output
        return ScaffoldMap(systemOutput.values)
    }


}