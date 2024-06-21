package com.evarela.apirestlogin3.utils

import org.junit.Assert.*
import org.junit.Test

class CalculatorTest{
    @Test
    fun TestingClassCalculatorFunctionAdd(): Unit {
        //Given
        val calculator = Calculator()

        // WHen
        val result = calculator.add(2, 3)

        // Then
        assertEquals(5, result)

    }
}