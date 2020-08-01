package com.aoc.intcode.droid.cryo.controller

import com.aoc.intcode.droid.cryo.security.AirlockPassword

interface CryostasisDroidController {
    fun findPassword(): AirlockPassword
}