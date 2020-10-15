package com.aoc.password.strategy

import com.aoc.password.Password

/**
 * A strategy for validating a [Password].
 * @see InitialValidation
 * @see RevisedValidation
 */
interface PasswordValidationStrategy {
    fun validate(password: Password): Boolean
}