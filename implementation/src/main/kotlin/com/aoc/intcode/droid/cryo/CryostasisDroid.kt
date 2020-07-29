package com.aoc.intcode.droid.cryo

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.droid.cryo.command.*
import com.aoc.log.AdventLogger
import com.aoc.math.Point2D

class CryostasisDroid(instructions: String) {
    private val cpu = IntCodeComputer(instructions)
    private val inventory = Inventory()
    private val map = StarShipMap()
    private var position = Point2D(0, 0)

    fun boot() {
        cpu.run()
        val output = DroidOutput(cpu.program.memory.output.parseStringFromAscii())
        map.addRoom(position, output.parse())
        log()
    }

    fun command(command: Command) {
        cpu.program.memory.input.add(command.encode())
        cpu.run()
        val room = DroidOutput(cpu.program.memory.output.parseStringFromAscii()).parse()

        when(command) {
            is MovementCommand -> {
                position = position.shift(command.getDirection())
                map.addRoom(position, room)
            }
            is TakeCommand -> {
                room.items.forEach { item -> inventory.add(item) }
            }
            is DropCommand -> {
                room.items.forEach { item -> inventory.take(item) }
            }
        }
        log()
    }

    private fun log() {
        val output = cpu.program.memory.output
        AdventLogger.info(output.parseStringFromAscii())
        AdventLogger.info(map.print())
        output.clear()
    }

}