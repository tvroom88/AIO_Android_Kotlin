package com.aio.kotlin.utils

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Cache
import okhttp3.Call
import okhttp3.Callback
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NetworkUtils(val mContext: Context) {

    val executor = Executors.newSingleThreadExecutor()
    val cookie = ""

    // HttpUrlConnection
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
            val okHttpClient = OkHttpClient.Builder()
                .connectionPool(makeConnectionPool())
                .addInterceptor(makeHttpLoggingInterceptor()) // Logging Interceptor 추가
                .cache(null) // cache에 넣을 수는 있지만 정확히 어떻게 작용하는지 테스트가 필요하다.
                .connectTimeout(2000, TimeUnit.SECONDS)
                .readTimeout(2000, TimeUnit.SECONDS) // 읽기 시간 초과 설정
                .writeTimeout(2000, TimeUnit.SECONDS) // 쓰기 시간 초과 설정
                .build()

            val request = Request.Builder()
                .url(url)
                .build()

            // 동기로
            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO) {
                    okHttpClient.newCall(request).execute()
                }

                val connectionPool = okHttpClient.connectionPool

                Log.d(
                    "okhttp3ConnectSync",
                    "Idle connections: ${connectionPool.idleConnectionCount()}"
                ) // 현재 연결되어 있는 connection pool 숫자
                Log.d(
                    "okhttp3ConnectSync",
                    "Total connections: ${connectionPool.connectionCount()}"
                )
                Log.d("okhttp3ConnectSync", "headers: ${response.headers}")
                Log.d("okhttp3ConnectSync", "Connection header: ${response.header("Connection")}")
                Log.d("okhttp3ConnectSync", "Keep-Alive header: ${response.header("Keep-Alive")}")
                Log.d("okhttp3ConnectSync", "Cache response: ${response.cacheResponse}")
                Log.d("okhttp3ConnectSync", "Network response:  ${response.networkResponse}")

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

    // 비동기
    fun okhttp3ConnectAsync(
        url: String,
        success: (Response) -> Unit,
        error: (Exception) -> Unit,
        responseCallBack: ResponseCallBack,
        timeOut: Long = 20000
    ) {

        // OkHttpClient 생성
        val okHttpClient = OkHttpClient.Builder()
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


    /**
     * HttpLoggingInterceptor
     * (1) NONE: 로깅하지 않음
     * (2) BASIC: 메서드, URL, 응답 코드 등 기본 정보
     * (3) HEADERS: 요청/응답의 헤더 포함
     * (4) BODY: 요청/응답 본문 포함 (디버깅 시 유용)
     */
    private fun makeHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // BODY, HEADERS, BASIC 중 선택
        }
    }

    /**
     * Interceptor 인터페이스를 구현하여 Header를 조작하는 작업도 수행 가능하다.
     * 이러한 방식은 인증 토큰이나 앱이 필요로 하는 다른 해더를 추가하거나 수정할 수 있다.
     */
    private fun makeHeaderInterceptor() : Interceptor{
        return Interceptor { chain ->
            // 현재의 원본 요청을 얻어옴
            val originalRequest = chain.request()

            // 헤더를 추가하거나 수정
            val modifiedRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer YourAccessToken")
                .build()

            // 수정된 요청을 사용하여 체인을 계속 진행
            val response = chain.proceed(modifiedRequest)

            // 응답 반환
            response
        }
    }

    /**
     * ConnectionPool이란 ?
     * OkHttp 라이브러리는 'ConnectionPool' 기능을 제공한다.
     * Rest API 서버에 요청을 보낼 때, 매번 연결을 맺는 대신 Connection Pool 기능을 이용해서 동일한 URL로의 커넥션을 풀링하여 다음번 요청때에 재사용하는 기능이다.
     *
     * 장점 :
     * (1) 성능 개선 : 새 연결을 생성하지 않고 기존 연결을 재사용함으로써 연결 설정 시간(TCP Handshake, TLS Handshake)를 줄입니다.
     * (2) 리소스 절약 : 연결 생성에 필요한 시스템 리소스를 절약합니다.
     * (3) 다중 요청 처리 : 여러 스레드에서 병렬로 실행 중인 요청에 대해 기존 연결을 효율적으로 공유합니다.
     *
     * 재사용 조건 :
     * (a) 같은 주소와 포트 : 요청 URL의 Host와 포트가 기존 연결과 동일해야 합니다.
     * (b) Keep-Alive Header : 서버가 Connection: keep-alive를 지원해야 합니다.
     * (c) 유휴 연결 : 커넥션 풀에 유휴 연결이 남아 있어야 합니다.
     * (d) TLS 설정 일치 : HTTPS의 경우, 동일한 TLS 설정을 사용하는 연결이어야 합니다.
     *
     * 기본적으로는 최대 5개, 5분 동안 유지합니다.
     * *** 일반적으로는 기본 설정만으로도 충분하고, 내부적으로 okhttp는 사용하지 않는 연결을 감지하고 자동으로 닫는다. ***
     *
     * 최대 유휴 연결을 넘는다면 가장 오래된 연결먼저 순서대로 닫는다.
     *
     */
    private fun makeConnectionPool(): ConnectionPool {
        return ConnectionPool(
            maxIdleConnections = 10, // 최대 유휴 연결 수
            keepAliveDuration = 5,  // 유휴 연결 유지 시간 (분 단위)
            timeUnit = TimeUnit.MINUTES // 시간 단위
        )
    }

    /**
     *  동일한 요청에 대해 서버에 재요청을하지 않고 캐싱된 데이터를 재사용하는 기능을 말합니다. 이를 통해 네트워크 요청 횟수를 줄이고 성능을 최적화하며, 대역폭 사용을 절약 할 수 있습니다.
     *  Cache-Control: (예제에서는 cache-control: max-age=43200)
     *    - public: 응답을 캐싱 가능.
     *    - private: 응답을 특정 사용자에 대해 캐싱 가능.
     *    - max-age=<seconds>: 캐시된 응답의 유효 시간.
     *    - no-cache: 캐싱은 가능하나 서버와의 유효성 검증 필요.
     *    - no-store: 캐싱 금지.
     */
    private fun makeCache(): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10MB
        val cacheDir = File(mContext.cacheDir, "http_cache") // 캐시 저장 디렉토리
        return Cache(cacheDir, cacheSize.toLong())
    }
}

interface ResponseCallBack {
    fun success(response: Response)
    fun error(e: Exception)
}