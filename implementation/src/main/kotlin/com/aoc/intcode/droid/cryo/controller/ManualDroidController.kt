package com.aoc.intcode.droid.cryo.controller

import com.aoc.intcode.droid.cryo.security.AirlockPassword
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.command.MovementCommand
import com.aoc.intcode.droid.cryo.command.TakeCommand

class ManualDroidController(instructions: String) : CryostasisDroidController{
    private val droid = CryostasisDroid(instructions)

    override fun findPassword(): AirlockPassword {
        droid.boot()

        //Pick up only the correct items (Pointer, Space Heater, Wreath, Dehydrated Water)
        droid.command(MovementCommand("west"))
        droid.command(MovementCommand("south"))
        droid.command(TakeCommand("pointer"))
        droid.command(MovementCommand("north"))
        droid.command(MovementCommand("east"))
        droid.command(MovementCommand("east"))
        droid.command(MovementCommand("south"))
        droid.command(MovementCommand("south"))
        droid.command(TakeCommand("space heater"))
        droid.command(MovementCommand("north"))
        droid.command(MovementCommand("north"))
        droid.command(MovementCommand("north"))
        droid.command(TakeCommand("wreath"))
        droid.command(MovementCommand("north"))
        droid.command(MovementCommand("west"))
        droid.command(TakeCommand("dehydrated water"))
        droid.command(MovementCommand("north"))
        droid.command(MovementCommand("east"))

        //Move onto the pressure plate
        droid.command(MovementCommand("south"))

        return droid.password
    }
}