package com.aio.kotlin.utils

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NetworkUtils {

    val executor = Executors.newSingleThreadExecutor()
    val cookie = ""

    fun connectWithHttpURLConnection(url: String) {
        val httpUrl = URL(url)
        val connection = httpUrl.openConnection() as HttpURLConnection
        executor.execute {
            try {
                connection.requestMethod = "GET"  // Set the request method to GET
                connection.setRequestProperty(
                    "Content-Type",
                    "application/json"
                );   // Add any headers you want to send with the request
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

    /**
     * okhttp3 연결하는 네트워크 부분
     *
     * 성공이나 에러시 해야하는 내용이 짧다면 success나 error로
     * 그게 아니라면 interface(responseCallBack)을 넘겨서 처리
     */
    // 동기
    fun okhttp3ConnectSync(
        url: String,
        success: (Response) -> Unit,
        error: (Exception) -> Unit,
        responseCallBack: ResponseCallBack
    ) {

        try {
            val okHttpClient = OkHttpClient();
            val request = Request.Builder()
                .url(url)
                .build()

            // 동기로
            CoroutineScope(Dispatchers.Main).launch {
                val response = async(Dispatchers.IO) {
                    okHttpClient.newCall(request).execute()
                }.await()

                response.let {
                    if (it.isSuccessful) {
                        success(it)
                    } else {
                        val errorBody = it.body?.string() ?: "Unknown Error"
                        val errorCode = it.code
                        error(Exception("HTTP $errorCode: $errorBody"))
                    }

                    if (it.isSuccessful) {
                        responseCallBack.success(it)
                    } else {
                        val errorBody = it.body?.string() ?: "Unknown Error"
                        val errorCode = it.code
                        responseCallBack.error(Exception("HTTP $errorCode: $errorBody"))
                    }
                }
            }

        } catch (e: Exception) {
            error(e)
            responseCallBack.error(e)
            Log.d("goodgoodgood", "1 ${e.toString()}")

        }
    }

    /**
     * HttpLoggingInterceptor
     * (1) NONE: 로깅하지 않음
     * (2) BASIC: 메서드, URL, 응답 코드 등 기본 정보
     * (3) HEADERS: 요청/응답의 헤더 포함
     * (4) BODY: 요청/응답 본문 포함 (디버깅 시 유용)
     */
    // 비동기
    fun okhttp3ConnectAsync(
        url: String,
        success: (Response) -> Unit,
        error: (Exception) -> Unit,
        responseCallBack: ResponseCallBack,
        timeOut: Long = 20000
    ) {

        // HttpLoggingInterceptor 생성 및 레벨 설정
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // BODY, HEADERS, BASIC 중 선택
        }

        // OkHttpClient 생성
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS) // 읽기 시간 초과 설정
            .writeTimeout(timeOut, TimeUnit.SECONDS) // 쓰기 시간 초과 설정
            .addInterceptor(loggingInterceptor) // Logging Interceptor 추가
            .build()

        val request = Request.Builder()
            .url(url)
            .build()

        CoroutineScope(Dispatchers.IO).launch {
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {

                    CoroutineScope(Dispatchers.Main).launch {
                        // 받을 Response로 처리해야 하는 코드가 짧을 경우
                        if (response.isSuccessful) {
                            success(response)
                        } else {
                            val errorBody = response.body?.string() ?: "Unknown Error"
                            val errorCode = response.code
                            error(Exception("HTTP $errorCode: $errorBody"))
                        }

                        // 받을 Response로 처리해야 하는 코드가 길경우
                        if (response.isSuccessful) {
                            responseCallBack.success(response)
                        } else {
                            val errorBody = response.body?.string() ?: "Unknown Error"
                            val errorCode = response.code
                            responseCallBack.error(Exception("HTTP $errorCode: $errorBody"))
                        }
                    }

                }

                override fun onFailure(call: Call, e: IOException) {
                    CoroutineScope(Dispatchers.Main).launch {
                        error(e)
                        responseCallBack.error(e)
                    }
                }
            })
        }
    }
}

interface ResponseCallBack {
    fun success(response: Response)
    fun error(e: Exception)
}