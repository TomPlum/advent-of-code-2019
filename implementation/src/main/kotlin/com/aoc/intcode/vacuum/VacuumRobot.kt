package com.aoc.intcode.vacuum

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.computer.SystemOutput
import com.aoc.intcode.vacuum.function.*
import com.aoc.intcode.vacuum.function.FunctionID.*
import com.aoc.intcode.vacuum.function.FunctionParameter.LEFT
import com.aoc.intcode.vacuum.function.FunctionParameter.RIGHT
import com.aoc.intcode.vacuum.scaffold.ScaffoldMap
import com.aoc.intcode.vacuum.scaffold.ScaffoldMapTile
import com.aoc.log.AdventLogger

/**
 * An early warning system detects an incoming solar flare and automatically activates the ships electromagnetic shield.
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
        computer.run()
        val systemOutput = computer.program.memory.output
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
        //Force Wake
        forceWakeUp()

        val routine = getManualMovementRoutine()

        //Input Main Movement Routine
        inputMainMovementRoutine(routine)

        //Input Movement Functions (A, B, C)
        inputMovementFunctions(routine)

        //Input Continuous Video Feed
        toggleContinuousVideoFeed(true)

        //Get Dust Report
        return getDustCollectionReport()
    }

    /**
     * Inputs the Main [MovementRoutine] into the [IntCodeComputer]
     */
    private fun inputMainMovementRoutine(routine: MovementRoutine) {
        routine.getRoutine().forEach { computer.program.memory.input.add(it) }
        AdventLogger.debug("Inputting: $routine \n")
        update()
    }

    /**
     * Runs the [IntCodeComputer] and returns the final [SystemOutput] value.
     * This Non-ASCII values represent the quantity of dust collected by the [VacuumRobot].
     */
    private fun getDustCollectionReport(): DustCollectionReport {
        computer.run()
        val quantity = computer.program.memory.output.getLastValue()
        return DustCollectionReport(quantity)
    }

    /**
     * Iterates over the [MovementRoutine.getBaseFunctions] and inputs each of them into the [IntCodeComputer]
     */
    private fun inputMovementFunctions(routine: MovementRoutine) {
        routine.getBaseFunctions().forEach { func ->
            func.getSequence().forEach { computer.program.memory.input.add(it.toLong()) }
            AdventLogger.debug("Inputting: $func \n")
            update()
        }
    }

    /**
     * Runs the [IntCodeComputer], prints the response and clears the [SystemOutput]
     */
    private fun update() {
        computer.run()
        AdventLogger.debug("Robot: ${computer.program.memory.output.parseStringFromAscii()}")
        computer.program.memory.output.clear()
    }

    /**
     * Forces the [VacuumRobot] to wake up. Doing so will cause the robot to automatically prompt the user
     * for movement instructions.
     */
    private fun forceWakeUp() {
        computer.reset()
        computer.program.memory.updateInstructionAtAddress(0, 2L)
        update()
    }

    /**
     * Enables the [VacuumRobot] continuous video feed. The robot will respond with the current view of
     * the [ScaffoldMap] every times it changes [ScaffoldMapTile]. Although its useful to see what is going on,
     * it uses a lot of processing power and can cause the [IntCodeComputer] to overheat.
     */
    private fun toggleContinuousVideoFeed(toggle: Boolean) {
        val input = if (toggle) 'y' else 'n'
        AdventLogger.debug("Inputting: $input \n")
        computer.program.memory.input.add(input.toLong())
        computer.program.memory.input.add('\n'.toLong())
    }

    /**
     * Defines the 3 [MovementFunction] and creates the [MovementRoutine] that will guide the [VacuumRobot]
     * across the [ScaffoldMap] so that it traverses every [ScaffoldMapTile].
     */
    private fun getManualMovementRoutine(): MovementRoutine {
        val a = MovementFunctionA().add(LEFT, 6).add(RIGHT, 12).add(LEFT, 6).add(LEFT, 8).add(LEFT, 8)
        val b = MovementFunctionB().add(LEFT, 4).add(LEFT, 4).add(LEFT, 6)
        val c = MovementFunctionC().add(LEFT, 6).add(RIGHT, 12).add(RIGHT, 8).add(LEFT, 8)
        return MovementRoutine(a, b, c).create(A, A, C, B, C, A, B, C, B, A)
    }
}