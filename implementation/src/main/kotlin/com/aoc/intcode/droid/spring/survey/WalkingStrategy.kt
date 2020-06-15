package com.aoc.intcode.droid.spring.survey

import com.aoc.intcode.droid.spring.script.Command
import com.aoc.intcode.droid.spring.script.SpringScriptProgram

class WalkingStrategy : SurveyingStrategy() {
    override fun getProgram(): SpringScriptProgram {
        val program = SpringScriptProgram(Command.WALK)

        //If the tile 1 ahead is empty, set jump to true
        program.addInstruction(parser.parseInstruction("NOT A J"))

        //If the tile 2 ahead is empty, set temp true
        program.addInstruction(parser.parseInstruction("NOT B T"))

        //If temp or jump registers are true, set jump to true
        program.addInstruction(parser.parseInstruction("OR T J"))

        //If the tile 3 ahead is empty, set temp to true
        program.addInstruction(parser.parseInstruction("NOT C T"))

        //If temp or jump registers are true, set jump to true
        program.addInstruction(parser.parseInstruction("OR T J"))

        //If the tile 4 ahead is ground and we've not previously set jump to false, set (keep) jump to true
        program.addInstruction(parser.parseInstruction("AND D J"))

        return program
    }
}