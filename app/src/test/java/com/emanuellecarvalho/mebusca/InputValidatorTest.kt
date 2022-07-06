package com.emanuellecarvalho.mebusca

import org.junit.Assert.*

import org.junit.Test

class InputValidatorTest {

    private val inputValidator: InputValidator = InputValidator()

    @Test
    fun emptyInput() {
        val emptyResult = inputValidator.validateInput("")
        assertFalse(emptyResult)
    }

    @Test
    fun stringLessThreeCharacters() {
        val strLessThree = inputValidator.validateInput("a")
        assertFalse(strLessThree)
    }

    @Test
    fun stringLongerThreeCharacters() {
        val strLongerThree = inputValidator.validateInput("aba")
        assertTrue(strLongerThree)
    }
}