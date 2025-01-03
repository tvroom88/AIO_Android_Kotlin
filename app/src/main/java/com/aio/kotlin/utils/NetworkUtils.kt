package com.aio.kotlin.utils

import android.util.Log
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class NetworkUtils {

    val executor = Executors.newSingleThreadExecutor()
    val cookie = ""

    fun connectWithHttpURLConnection(url: String) {
        val httpUrl = URL(url)
        val connection = httpUrl.openConnection() as HttpURLConnection
        executor.execute {
            try {
                connection.requestMethod = "GET"  // Set the request method to GET
                connection.setRequestProperty("Content-Type", "application/json");   // Add any headers you want to send with the request
                connection.instanceFollowRedirects = false
                connection.connect()

                when (connection.responseCode) {
                    HttpURLConnection.HTTP_OK -> { // 정상적으로 연결 되었을 때
                        val inputStream = connection.inputStream
                        val response = inputStream.bufferedReader().use { it.readText() }
                        Log.d("makeHttpUrlConnection", "response : $response")
                    }
                    // Redirection으로 연결될때
                    HttpURLConnection.HTTP_MOVED_TEMP, HttpURLConnection.HTTP_MOVED_PERM, HttpURLConnection.HTTP_SEE_OTHER -> {
                        val newUrl = connection.getHeaderField("Location")
                        connectWithHttpURLConnection(newUrl) // 새로운 URL로 연결 시도
                        Log.d("makeHttpUrlConnection", "Redirecting to : $newUrl")
                    }
                    else -> {
                        Log.d("makeHttpUrlConnection", "connection : ${connection.responseCode}")
                    }
                }
            } catch (e: Exception) {
                Log.d("makeHttpUrlConnection", "error : ${e.message}")
            } finally {
                Log.d("makeHttpUrlConnection", "connect")
                connection.disconnect()
            }
        }
    }

    fun makeHttpUrlConnection(url: URL) {
        val connection = url.openConnection() as HttpURLConnection
    }
}