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
        val output = DroidOutput(cpu.program.memory.output.parseStringFromAscii())

        when(command) {
            is MovementCommand -> {
                position = position.shift(command.getDirection())
                map.addRoom(position, output.parse())
            }
            is TakeCommand -> {
                val currentRoom = map.getRoom(position)
                val item = currentRoom.takeItem(command.getItem())
                if (item != null) {
                    inventory.add(item)
                    map.addRoom(position, currentRoom)
                } else {
                    AdventLogger.info("There is no ${command.getItem().name} in the ${currentRoom.name}!")
                }
            }
            is DropCommand -> {
                val currentRoom = map.getRoom(position)
                val item = currentRoom.takeItem(command.getItem())
                if (item != null) {
                    inventory.take(item)
                    map.addRoom(position, currentRoom)
                } else {
                    AdventLogger.info("You do not have a ${command.getItem().name} in your inventory!")
                }
            }
        }
        log()
    }

    private fun log() {
        val output = cpu.program.memory.output
        AdventLogger.info(output.parseStringFromAscii())
        AdventLogger.info(inventory)
        AdventLogger.info(map.print())
        AdventLogger.debug("Current Droid Position: $position")
        output.clear()
    }

}