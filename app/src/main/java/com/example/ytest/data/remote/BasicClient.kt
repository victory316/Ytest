package com.example.ytest.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.gccompany.co.kr/App/"

/**
 * REST API 통신을 위한 Client
 *
 * - Retrofit2, GSON 사용
 */
class BasicClient {
    fun getApi(): BasicApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BasicApi::class.java)
}