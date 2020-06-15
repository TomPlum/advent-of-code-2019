package com.aoc.intcode.droid.spring.register.read

import com.aoc.intcode.droid.spring.register.Register

/**
 * A [Register] that once instantiated, its value cannot be changed and only read from.
 */
interface ReadOnlyRegister : Register