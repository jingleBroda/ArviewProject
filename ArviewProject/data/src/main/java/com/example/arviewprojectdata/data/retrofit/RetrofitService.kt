package com.example.arviewprojectdata.data.retrofit

import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitService {

    @GET("https://api.twitch.tv/helix/games/top")
    fun getTopGameStartAPI(
        @Query("first") first: Int,
       // @Query("limit") limit: Int,
       // @Query("offset") offset: Int
    ): Single<ModelRetrofit>

}