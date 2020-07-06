package com.example.ytest.data.remote

import com.example.ytest.data.local.QueryResult
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable

/**
 *  REST API 통신을 위한 Api class
 */
interface BasicApi {

    // 페이징 구현을 위해 페이지 count를 파라미터로 넘겨 요청하는 GET Function
    @GET("json/{id}.json")
    fun loadPlace( @Path("id") id: Int): Observable<QueryResult>
}