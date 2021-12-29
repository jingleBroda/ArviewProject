package com.example.arviewproject.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofiteService {

    @GET("games/top")
    fun getTopGames(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<ModelRetrofit>



}