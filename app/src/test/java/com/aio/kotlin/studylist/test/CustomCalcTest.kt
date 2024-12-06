package com.aio.kotlin.studylist.test

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
//
import org.junit.jupiter.api.Test
import org.mockito.Mockito

//https://heegs.tistory.com/73
class CustomCalcTest {
    private lateinit var myCalculation: CustomCalc
    private lateinit var myCalculationMock: CustomCalc

    @Before
    fun setUp() {
        myCalculation = CustomCalc()
        myCalculationMock = Mockito.mock(CustomCalc::class.java)

    }

    @Test
    fun sum() {
        val result = myCalculation.sum(22, 22)
        assertEquals(result, 44)
        assertThat(result).isEqualTo(44)
    }

    @Test
    fun minus() {
        val result = myCalculation.minus(22, 22)
        assertEquals(result, 0)
    }
}