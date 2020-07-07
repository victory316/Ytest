package com.example.ytest.data.remote

import com.example.ytest.data.local.QueryResult
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable

/**
 *  CRUD 오퍼레이션을 위한 API class
 *
 *  - Paging 구현을 위해 파라미터로 page id를 입력하도록 구현
 */
interface BasicApi {
    @GET("json/{id}.json")
    fun loadPlace( @Path("id") id: Int): Observable<QueryResult>
}