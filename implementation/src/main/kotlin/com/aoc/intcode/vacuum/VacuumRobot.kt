package com.aoc.intcode.vacuum

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.vacuum.FunctionID.*
import com.aoc.intcode.vacuum.FunctionParameter.LEFT
import com.aoc.intcode.vacuum.FunctionParameter.RIGHT

/**
 * An early warning system detects an incoming solar flare and automatically activates the ship's electromagnetic shield.
 * Unfortunately, this has cut off the Wi-Fi for many small robots that, unaware of the impending danger, are now
 * trapped on exterior scaffolding on the unsafe side of the shield.
 *
 * The [VacuumRobot] has a low-quality video camera capable of streaming a continuous video feed and a bright LED
 * light that makes it easier to identify its location and surroundings.
 *
 * @param instructions The Aft Scaffolding Control and Information Interface (ASCII) program.
 */
class VacuumRobot(val instructions: String) {
    private var computer = IntCodeComputer(instructions)

    /**
     * Runs the ASCII [instructions] on the [computer] to produce the current view
     * of the ships exterior scaffolding.
     */
    fun scanShipsExteriorScaffolding(): ScaffoldMap {
        computer.compute()
        val systemOutput = computer.getProgramMemory().output
        return ScaffoldMap(systemOutput.getValues())
    }

    /**
     * Traverses the entirety of the [ScaffoldMap] and notifies each of the robots of the impending Solar Flare.
     * As the robot is a [VacuumRobot], it cleans each of the robots on the way and records the dust collected.
     *
     * 1. Forces the [VacuumRobot] to wake up.
     * 2. Inputs the Main [MovementRoutine].
     * 3. Inputs each of the three [MovementFunction] (A, B & C).
     * 4. Inputs 'Toggle Continuous Video Feed' Toggle
     *
     * Once the robot has reached the final [ScaffoldMapTile], it returns to the starting position and
     * returns the [DustCollectionReport].
     */
    fun notifyRobotsAboutSolarFlare(): DustCollectionReport {
        forceWakeUp()

        val routine = getManualMovementRoutine()

        //Input Main Movement Routine
        routine.getRoutine().forEach { computer.getProgramMemory().input.add(it) }
        println("Inputting: $routine \n")
        update()

        inputMovementFunctions(routine)

        //Input Continuous Video Feed
        toggleContinuousVideoFeed(true)

        //Get Dust Report
        computer.compute()
        val dustCollected = computer.getProgramMemory().output.getLastValue()
        println("Dust Collected: $dustCollected")

        return DustCollectionReport(dustCollected)
    }

    private fun inputMovementFunctions(routine: MovementRoutine) {
        routine.getBaseFunctions().forEach { func ->
            func.getSequence().forEach { computer.getProgramMemory().input.add(it.toLong()) }
            println("Inputting: ${func.ID} \n")
            update()
        }
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
        val a = MovementFunctionA().add(LEFT, 6).add(RIGHT, 12).add(LEFT, 6).add(LEFT, 8).add(LEFT, 8)
        val b = MovementFunctionB().add(LEFT, 4).add(LEFT, 4).add(LEFT, 6)
        val c = MovementFunctionC().add(LEFT, 6).add(RIGHT, 12).add(RIGHT, 8).add(LEFT, 8)
        return MovementRoutine(a,b,c).create(A, A, C, B, C, A, B, C, B, A)
    }
}