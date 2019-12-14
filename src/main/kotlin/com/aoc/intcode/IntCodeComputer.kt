package com.aoc.intcode

class IntCodeComputer {
     fun compute(programString: String): String {
          val program = parseProgram(programString)
          for ((i, v) in program.withIndex()) {

          }
          return ""
     }

     private fun parseProgram(program: String): List<OpCode> = program.split(",").map { OpCode.from(it.toInt()) }
}