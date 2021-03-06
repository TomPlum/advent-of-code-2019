package com.aoc.intcode.droid.cryo.droid

import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.computer.Program
import com.aoc.intcode.droid.cryo.command.runtime.CommandRuntime
import com.aoc.intcode.droid.cryo.command.item.DropCommand
import com.aoc.intcode.droid.cryo.command.types.SystemCommand
import com.aoc.intcode.droid.cryo.command.parameterised.InventoryCommand
import com.aoc.intcode.droid.cryo.command.parameterised.MovementCommand
import com.aoc.intcode.droid.cryo.command.item.TakeCommand
import com.aoc.intcode.droid.cryo.command.types.Command
import com.aoc.intcode.droid.cryo.command.system.*
import com.aoc.intcode.droid.cryo.map.StarShipMap
import com.aoc.intcode.droid.cryo.security.AirlockPassword
import com.aoc.intcode.droid.cryo.security.SecurityAnalysis
import com.aoc.math.Direction.DOWN
import com.aoc.math.Point2D

/**
 * The [CryostasisDroid] can follow basic instructions and report on its surroundings; you can communicate with it
 * through an [IntCodeComputer] [Program] running on an ASCII-capable computer.
 *
 * As the droid moves through its environment, it will describe what it encounters. When it says Command?, you can give
 * it a single instruction issued via the [CommandRuntime].
 *
 * - Movement via north, south, east, or west.
 *   @see MovementCommand
 *
 * - To take an item the droid sees in the environment, use the command take <name of item>.
 *   For example, if the droid reports seeing a red ball, you can pick it up with take red ball.
 *   @see TakeCommand
 *
 * - To drop an item the droid is carrying, use the command drop <name of item>. For example, if the droid is
 *   carrying a green ball, you can drop it with drop green ball.
 *   @see DropCommand
 *
 * - To get a list of all of the items the droid is currently carrying, use the command inv (for "inventory").
 *   @see InventoryCommand
 *
 * The [CommandRuntime] can also accept a [SystemCommand] that is not issued to the [CryostasisDroid] and only
 * affects the runtime environment.
 *
 * - View a list of the available command words.
 *   @see QuitCommand
 *
 * - Quit the runtime environment.
 *   @see HelpCommand
 */
class CryostasisDroid(instructions: String) {
    var password = AirlockPassword()
    val inventory = Inventory()
    var logger = DroidLogger()

    private val cpu = IntCodeComputer(instructions)
    private val map = StarShipMap()
    private var position = Point2D(0, 0)

    /**
     * Boots up the [CryostasisDroid] and enters Santa's StarShip.
     * Upon booting, the droid scans the room and records its surroundings in its [map].
     */
    fun boot() {
        cpu.run()
        val output = DroidOutput(cpu.program.memory.output.parseStringFromAscii())
        val startingRoom = output.parse()
        map.addRoom(position, startingRoom)
        logger.log(startingRoom)
        log()
    }

    /**
     * Issues the given [command] to the droid.
     */
    fun command(command: Command) {
        cpu.program.memory.input.add(command.encode())
        cpu.run()
        val output = DroidOutput(cpu.program.memory.output.parseStringFromAscii())

        when(command) {
            is MovementCommand -> {
                val direction = command.getDirection()
                val currentRoom = map.getRoom(position)

                //Security Checkpoint -> Pressure-Sensitive Floor
                if (currentRoom.isSecurityCheckpoint() && direction == DOWN) {
                    val securityAnalysis = output.parsePressureSensitiveFloor()
                    if (securityAnalysis == SecurityAnalysis.VALID) {
                        logger.log("Your weight matches that of the other droids. You are permitted to enter.")
                        logger.log("The droid is carrying $inventory")
                        password = output.parsePassword()
                    } else  {
                        logger.log("You are $securityAnalysis to pass the security checkpoint!")
                    }
                } else {
                    if (currentRoom.hasDoorLeading(direction)) {
                        position = position.shift(direction)
                        val room = output.parse()
                        map.addRoom(position, room)
                        map.droidPosition = position
                        logger.log(room)
                    } else {
                        logger.log("There is no room to the $direction")
                    }
                }
            }
            is TakeCommand -> {
                val currentRoom = map.getRoom(position)
                val item = currentRoom.takeItem(command.getItem())
                if (item != null) {
                    inventory.add(item)
                    map.addRoom(position, currentRoom)
                } else {
                    logger.log("There is no ${command.getItem().name} in the ${currentRoom.name}!")
                }
            }
            is DropCommand -> {
                val item = inventory.take(command.getItem())
                if (item != null) {
                    val currentRoom = map.getRoom(position)
                    currentRoom.placeItem(item)
                    map.addRoom(position, currentRoom)
                } else {
                    logger.log("You do not have a ${command.getItem().name} in your inventory!")
                }
            }
            is InventoryCommand -> {
                logger.log(inventory)
            }
        }

        log()
    }

    private fun log() {
        logger.log(inventory)
        logger.log(map.display())
        cpu.program.memory.output.clear()
    }

}