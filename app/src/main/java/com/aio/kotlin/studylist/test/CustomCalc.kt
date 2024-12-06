package com.aio.kotlin.studylist.test

class CustomCalc : Calculations {
    override fun sum(num1: Int, num2: Int): Int {
        return num1 + num2
    }

    override fun minus(num1: Int, num2: Int): Int {
        return num1 - num2
    }
}

interface Calculations {
    fun sum(num1: Int, num2: Int): Int
    fun minus(num1: Int, num2: Int): Int
}