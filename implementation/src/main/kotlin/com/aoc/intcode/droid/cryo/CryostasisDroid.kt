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
        val startingRoom = output.parse()
        map.addRoom(position, startingRoom)
        AdventLogger.info(startingRoom)
        log()
    }

    fun command(command: Command) {
        cpu.program.memory.input.add(command.encode())
        cpu.run()
        val output = DroidOutput(cpu.program.memory.output.parseStringFromAscii())

        when(command) {
            is MovementCommand -> {
                val direction = command.getDirection()
                if (map.getRoom(position).hasDoorLeading(direction)) {
                    position = position.shift(direction)
                    val room = output.parse()
                    map.addRoom(position, room)
                    map.droidPosition = position
                    AdventLogger.info(room)
                } else {
                    AdventLogger.info("There is no room to the $direction")
                }
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
        AdventLogger.info(map.display())
        output.clear()
    }

}