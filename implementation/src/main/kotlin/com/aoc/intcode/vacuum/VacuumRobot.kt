package com.aoc.intcode.vacuum

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.vacuum.FunctionParameter.LEFT
import com.aoc.intcode.vacuum.FunctionParameter.RIGHT

/**
 * @param instructions The Aft Scaffolding Control and Information Interface (ASCII) program.
 */
class VacuumRobot(val instructions: String) {
    private var computer = IntCodeComputer(instructions)

    fun exploreShipsExteriorScaffolding(): ScaffoldMap {
        computer.compute()
        val systemOutput = computer.getProgramMemory().output
        return ScaffoldMap(systemOutput.getValues())
    }

    fun getDustCollectionReport(): Long {
        forceWakeUp()

        val routine = getManualMovementRoutine()

        //Input Main Movement Routine
        routine.getRoutine().forEach { computer.getProgramMemory().input.add(it) }
        println("Inputting: $routine \n")
        update()

        //Input A
        val a = routine.functions.first { it.name == 'A' }
        a.getSequence().forEach { value -> computer.getProgramMemory().input.add(value.toLong()) }
        println("Inputting: $a \n")
        update()

        //Input B
        val b = routine.functions.first { it.name == 'B' }
        b.getSequence().forEach { value -> computer.getProgramMemory().input.add(value.toLong()) }
        println("Inputting: $b \n")
        update()

        //Input C
        val c = routine.functions.first { it.name == 'C' }
        c.getSequence().forEach { value -> computer.getProgramMemory().input.add(value.toLong()) }
        println("Inputting: $c \n")
        update()

        //Input Continuous Video Feed
        toggleContinuousVideoFeed(false)

        //Get Dust Report
        computer.compute()
        val dustCollected = computer.getProgramMemory().output.getLastValue()
        println("Dust Collected: $dustCollected")

        return dustCollected
    }

    private fun update() {
        computer.compute()
        print("Robot: ${computer.getProgramMemory().output.parseStringFromAscii()}")
        computer.getProgramMemory().output.clear()
    }

    /**
     * Forces the [VacuumRobot] to wake up. Doing so will cause the robot to automatically prompt the user
     * for movement instructions.
     */
    private fun forceWakeUp() {
        computer.reset()
        computer.getProgramMemory().updateInstructionAtAddress(0, 2L)
        update()
    }

    private fun toggleContinuousVideoFeed(toggle: Boolean) {
        val input = if (toggle) 'y' else 'n'
        println("Inputting: $input \n")
        computer.getProgramMemory().input.add(input.toLong())
        computer.getProgramMemory().input.add('\n'.toLong())
    }

    private fun getManualMovementRoutine(): MovementRoutine {
        val a = MovementFunction('A').add(LEFT, 6).add(RIGHT, 12).add(LEFT, 6).add(LEFT, 8).add(LEFT, 8)
        val b = MovementFunction('B').add(LEFT, 4).add(LEFT, 4).add(LEFT, 6)
        val c = MovementFunction('C').add(LEFT, 6).add(RIGHT, 12).add(RIGHT, 8).add(LEFT, 8)
        return MovementRoutine().add(a).add(a).add(c).add(b).add(c).add(a).add(b).add(c).add(b).add(a)
    }
}