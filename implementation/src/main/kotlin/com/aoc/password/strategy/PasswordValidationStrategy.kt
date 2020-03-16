package com.aoc.password.strategy

import com.aoc.password.Password

interface PasswordValidationStrategy {
    fun validate(password: Password): Boolean
}