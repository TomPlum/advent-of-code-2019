package com.aoc.intcode.droid.spring.register

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.computer.SystemInput
import com.aoc.intcode.droid.spring.SpringDroid
import com.aoc.intcode.droid.spring.script.SpringScriptProgram

/**
 * In the context of a [SpringScriptProgram], and [Encodable] component or argument has an ASCII representation.
 * The encoded ASCII values are to be provided to the [SystemInput] of the [IntCodeComputer] which is embedded inside
 * a [SpringDroid], hence the choice of the [Long] data type.
 *
 * For ease of union, the [encode] method returns a [List] of the encoded values as there can be one or many.
 */
interface Encodable {
    fun encode(): List<Long>
}