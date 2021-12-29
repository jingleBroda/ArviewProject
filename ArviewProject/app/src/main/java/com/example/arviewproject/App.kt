package com.example.arviewproject

import android.app.Application
import com.example.arviewproject.data.RetrofiteService
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import java.io.IOException


class App:Application() {

    private val baseUrl = "https://api.twitch.tv/kraken/"

    private val BEARER_TOKEN = "7hq7mo22fniilrcud3dwml32mxchj9"
    private val CLIENT_ID = "ahuoi1tl0qmqbyi8jo8nitbmuaad7w"

    private var retroService:RetrofiteService
    private var okHttpClient: OkHttpClient = OkHttpClient()

    init{

       createClient()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        retroService = retrofit.create(RetrofiteService::class.java)

    }

    private fun createClient(): OkHttpClient {

        okHttpClient =  OkHttpClient().newBuilder().addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {

                val originalRequest: Request = chain.request()

                val builder: Request.Builder = originalRequest.newBuilder()
                    .header("Authorization", BEARER_TOKEN)
                    .header("Client-Id", CLIENT_ID)

                val newRequest: Request = builder.build()

                return chain.proceed(newRequest)
            }
        }).build()

        return okHttpClient
    }


    fun getRetrofiteService():RetrofiteService{
        return retroService
    }

}