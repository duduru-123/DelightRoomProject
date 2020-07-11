package com.delightroom.android.gitproject.manager

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager(private val context: Context) {

    companion object {
        const val CONNECT_TIMEOUT = 100L
        const val WRITE_TIMEOUT = 100L
        const val READ_TIMEOUT = 100L

        const val BASE_URL = ""
    }

    private lateinit var builder: Retrofit.Builder

    init {
        init(BASE_URL)
    }


    /**
     * 초기화
     */
    private fun init(baseUrl: String) {
        builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
    }


    /**
     * Api 인터페이스 반환
     */
    fun <T> createApi(apiInterface: Class<T>): T {
        val clientBuilder = createClientBuilder()
        builder.client(clientBuilder.build())

        return builder.build().create(apiInterface)
    }


    /**
     * 클라이언트 생성
     */
    private fun createClientBuilder(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
    }
}