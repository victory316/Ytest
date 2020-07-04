package com.example.ytest.data.remote

import com.example.ytest.data.local.QueryResult
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable

interface BasicApi {
//    @GET("/pieces")
//    fun loadList(): Observable<List<BasicData>>
//
    @GET("json/{id}.json")
    fun loadPlace( @Path("id") id: Int): Observable<QueryResult>

    @GET("json/1.json")
    fun testQuery(): Observable<QueryResult>
}