package com.aoc.intcode.droid.spring.survey

import com.aoc.intcode.droid.spring.script.SpringScriptParser
import com.aoc.intcode.droid.spring.script.SpringScriptProgram

abstract class SurveyingStrategy {
    protected val parser = SpringScriptParser()
    abstract fun getProgram(): SpringScriptProgram
}