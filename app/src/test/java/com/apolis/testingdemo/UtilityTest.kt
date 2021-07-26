package com.apolis.testingdemo

import com.apolis.testingdemo.utility.Utility
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UtilityTest {

    lateinit var utility: Utility

    @Before
    fun setUp() {
        utility = Utility()
    }

    @Test
    fun incrementNumber_validInput_returnsIncrementedNumber() {

        val number = 12

        var result = utility.incrementTheNumber(number)

        assertEquals(13, result)

    }

    @After
    fun close() {
        //
    }

}