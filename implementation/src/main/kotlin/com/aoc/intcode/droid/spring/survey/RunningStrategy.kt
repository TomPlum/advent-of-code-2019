package com.aoc.intcode.droid.spring.survey

import com.aoc.intcode.droid.spring.script.Command
import com.aoc.intcode.droid.spring.script.SpringScriptProgram

class RunningStrategy : SurveyingStrategy() {
    override fun getProgram(): SpringScriptProgram {
        val program = SpringScriptProgram(Command.RUN)

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

        //If the tile 5 ahead is empty, set temporary register to true
        program.addInstruction(parser.parseInstruction("NOT E T"))

        //Invert Temporary Register if false
        program.addInstruction(parser.parseInstruction("NOT T T"))

        //Sets Temporary Register to true if the tile 8 away is ground
        program.addInstruction(parser.parseInstruction("OR H T"))

        //If Temporary & Jump Register are true, jump
        program.addInstruction(parser.parseInstruction("AND T J"))

        return program
    }
}