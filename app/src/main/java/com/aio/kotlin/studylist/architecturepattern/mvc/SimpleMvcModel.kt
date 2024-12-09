package com.aio.kotlin.studylist.architecturepattern.mvc

data class SimpleMvcModel(var number:Int) {

    fun plusOne(){
        number++
    }

    fun minusOne(){
        number--
    }
}