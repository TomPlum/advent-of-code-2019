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

    /**
     * Forces the [VacuumRobot] to wake up. Doing so will cause the robot to automatically prompt the user
     * for movement instructions.
     */
    fun forceWakeUp() {
        computer.getProgramMemory().updateInstructionAtAddress(0, 2L)
    }
}