package com.example.arviewprojectdata.data.retrofit

import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitService {

    @GET("https://api.twitch.tv/helix/games/top")
    fun getTopGameStartAPI(
        @Query("first") first: Int,
    ): Single<ModelRetrofit>

    @GET("https://api.twitch.tv/helix/games/top")
    fun getNewListTopGame(
        @Query("after") pagination: String,
        @Query("first") first: Int,
    ): Single<ModelRetrofit>

}