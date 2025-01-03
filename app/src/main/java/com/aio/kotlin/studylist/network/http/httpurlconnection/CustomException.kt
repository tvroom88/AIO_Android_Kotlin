package com.aio.kotlin.studylist.network.http.httpurlconnection

class CustomException(
    val errorCode: Int,
    message: String
) : Exception(message)