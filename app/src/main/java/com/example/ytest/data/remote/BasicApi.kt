package com.example.ytest.data.remote

import com.example.ytest.data.local.AnswerData
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable
import java.util.*

interface BasicApi {
//    @GET("/pieces")
//    fun loadList(): Observable<List<BasicData>>
//
    @GET("/pieces/{id}")
    fun loadAnswer( @Path("id") id: Int): Observable<AnswerData>
}