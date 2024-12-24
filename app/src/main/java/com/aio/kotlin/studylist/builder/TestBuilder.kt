package com.aio.kotlin.studylist.builder

class TestBuilder {

    fun testBuilder(){
        SimpleBuilder.Builder("대한민국")
            .forward("박지성")
            .winger("손흥민")
            .midfielder("기성용")
            .defender("김민재")
            .build()
    }
}