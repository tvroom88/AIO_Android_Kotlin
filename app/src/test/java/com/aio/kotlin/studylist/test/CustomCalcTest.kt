package com.aio.kotlin.studylist.test

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times

/**
 * UnitTest 네이밍 컨벤션 :
 * 1) MethodName_StateUnderTest_ExpectedBehavior
 * example: isAdult_AgeLessThan18_False
 *
 * 2) FeatureToBeTested
 * example: IsNotAnAdultIfAgeLessThan18
 *
 * Unit Test 함수는 하나의 동작 또는 기능만 테스트하는 것이 권장됩니다.
 */
class CustomCalcTest {
    private lateinit var myCalculation: CustomCalc
    private lateinit var myCalculationMock: CustomCalc // Mockito 사용

    @BeforeEach
    fun setUp() {
        myCalculation = CustomCalc()

        // mockit
        myCalculationMock = Mockito.mock(CustomCalc::class.java)
        Mockito.`when`(myCalculationMock.sum(3, 5)).thenReturn(8)
    }

    @Test
    fun sum() {
        val result = myCalculation.sum(22, 22)
        assertEquals(result, 44) // JUnit 라이브러리
        assertThat(result).isEqualTo(44) // truth 라이브러리 사용

        val mockitResult = myCalculationMock.sum(3, 5)
        assertEquals(8, mockitResult)

        Mockito.verify(myCalculationMock, times(1)).sum(3,5)
    }

    @Test
    fun minus() {
        val result = myCalculation.minus(22, 22)
        assertEquals(result, 0)
    }
}