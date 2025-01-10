package com.aio.kotlin.studylist.network.http.httpurlconnection

import android.os.Handler
import android.os.Looper
import android.util.Log
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class HttpUrlConnectionBuilder private constructor(
    private var url: String,
    private val method: String,
    private val headers: Map<String, String>,
    private val body: String?,
    private val readTimeouts: Int = 20000,
    private val connectTimeouts: Int = 20000,
    private val futureCallback: FutureCallback<Any>?
) {

    class Builder(private val url: String) {
        private var method: String = "GET"
        private val headers: MutableMap<String, String> = mutableMapOf()
        private var body: String? = null
        private val map: MutableMap<String, String> = mutableMapOf()
        private var readTimeouts: Int = 20000
        private var connectTimeouts: Int = 20000
        private var futureCallback: FutureCallback<Any>? = null

        fun setMethod(method: String) = apply {
            this.method = method
        }

        fun addHeader(key: String, value: String) = apply {
            this.headers[key] = value
        }

        fun addBody(key: String, value: String): Builder {
            map[key] = value
            setBody(map)
            return this
        }

        fun setBody(params: Map<String, String>) = apply {
            this.body = params.entries.joinToString("&") {
                "${
                    URLEncoder.encode(
                        it.key,
                        StandardCharsets.UTF_8.toString()
                    )
                }=${URLEncoder.encode(it.value, StandardCharsets.UTF_8.toString())}"
            }
        }

        fun setReadTimeOut(readTimeout: Int): Builder {
            this.readTimeouts = readTimeout
            return this
        }

        fun setConnectTimeout(connectTimeout: Int): Builder {
            this.connectTimeouts = connectTimeout
            return this
        }

        fun setCallBack(futureCallback: FutureCallback<Any>): Builder {
            this.futureCallback = futureCallback
            return this
        }

        fun build(): HttpUrlConnectionBuilder {
            return HttpUrlConnectionBuilder(
                url,
                method,
                headers,
                body,
                readTimeouts,
                connectTimeouts,
                futureCallback
            )
        }
    }

    fun execute() {
        try {
            val httpUrl = URL(url)
            val connection = httpUrl.openConnection() as HttpURLConnection

            Thread {
                connection.apply {
                    requestMethod = method
                    readTimeout = readTimeouts
                    connectTimeout = connectTimeouts

                    // 헤더 설정
                    for ((key, value) in headers) {
                        setRequestProperty(key, value)
                    }

                    // POST로 통신할 경우 보낼내용을 body에 넣어서 보냄
                    if (body != null && (method == "POST" || method == "PUT")) {
                        setRequestProperty(
                            "Content-Type",
                            "application/x-www-form-urlencoded; charset=UTF-8"
                        )
                        doOutput = true
                        setChunkedStreamingMode(0)
                        outputStream.use { os ->
                            os.write(body.toByteArray(StandardCharsets.UTF_8)) // 인코딩된 바디를 바이트 배열로 전송
                        }
                    }
                }

                // 응답 처리
                try {
                    val inputStream = connection.inputStream
                    val response = inputStream.bufferedReader().use { it.readText() }

                    when (connection.responseCode) {
                        in 200..299 -> { // 정상적으로 연결 되었을 때
                            onComplete(null, response)
                        }
                        // Redirection으로 연결될때
                        HttpURLConnection.HTTP_MOVED_TEMP, HttpURLConnection.HTTP_MOVED_PERM, HttpURLConnection.HTTP_SEE_OTHER -> {
                            url = connection.getHeaderField("Location")
                            execute() // 새로운 URL로 연결 시도
                        }

                        else -> {
                            val errorMsg = "ResponseCode : ${connection.responseCode}"
                            onComplete(Exception(errorMsg), null)
                        }
                    }
                } catch (e: Exception) {
                    val errorStream = connection.errorStream
                    Log.d("errorStream", "errorStream : $errorStream")
                    onComplete(e, null)
                } finally {
                    connection.disconnect()
                }
            }.start()
        } catch (e: Exception) {
            onComplete(e, null)
        }
    }

    private fun onComplete(e: Exception?, result: String?) {
        Handler(Looper.getMainLooper()).post {
            futureCallback?.onCompleted(e, result)
        }
    }

    interface FutureCallback<T> {
        fun onCompleted(e: Exception?, result: T?)
    }
}
