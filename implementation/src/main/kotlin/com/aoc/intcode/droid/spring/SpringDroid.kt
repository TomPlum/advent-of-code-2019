package com.aoc.intcode.droid.spring

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.droid.spring.register.read.GroundSensorRegister
import com.aoc.intcode.droid.spring.script.SpringScriptProgram
import com.aoc.intcode.droid.spring.script.Command
import com.aoc.intcode.droid.spring.survey.SurveyingStrategy
import com.aoc.log.AdventLogger
import java.lang.IllegalStateException

/**
 * The [SpringDroid] is deployed on the ships hull in order to survey it for damage. This droids ability to jump
 * makes it the best choice for such as task as damage to the hull results in giant holes in the ship.
 *
 * The droid is controlled via an [IntCodeComputer] that is running a simplified assembly language called 'SpringScript'
 * The [SpringScriptProgram] is executed continuously until the droid either finishes surveying the hull, or falls down
 * a hole into empty space.
 *
 * The droid is also equipped with sensors that can detect whether there is ground at various distances in the direction
 * that it is facing; these values are stored in a [GroundSensorRegister]. When the droid is running a
 * [SpringScriptProgram] that is executed with [Command.WALK], it's ground sensors can detect up-to 4 tiles away and
 * therefore only jump 4 tiles too. If it is executed with [Command.RUN], then this range is extended to 9 tiles.
 */
class SpringDroid(instructions: String) {
    private val computer = IntCodeComputer(instructions)

    /**
     * Surveys the ship hull for damage.
     * @param strategy the [SurveyingStrategy] dictates how the robot will move along the hull
     */
    fun surveyHull(strategy: SurveyingStrategy) : HullDamageReport {
        inputAndExecuteProgram(strategy.getProgram())
        return getHullDamageReport()
    }

    private fun inputAndExecuteProgram(program: SpringScriptProgram) {
        AdventLogger.info("Inputting Program:\n$program")
        program.encode().forEach { computer.program.memory.input.add(it) }
        computer.run()
    }

    private fun getHullDamageReport(): HullDamageReport {
        val lastOutput = getOutput().getLastValue()
        if (lastOutput <= 127) {
            AdventLogger.info(getOutput().parseStringFromAscii())
            throw IllegalStateException("The droid fell down a hole into outer space!")
        }
        AdventLogger.info("Total Hull Damage: $lastOutput")
        return HullDamageReport(lastOutput)
    }

    private fun getOutput() = computer.program.memory.output

}