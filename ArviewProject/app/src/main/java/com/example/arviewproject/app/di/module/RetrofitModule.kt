package com.example.arviewproject.app.di.module

import com.example.arviewprojectdata.data.retrofit.RetrofitParam
import com.example.arviewprojectdata.data.retrofit.RetrofitService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton

@Module
class RetrofitModule() {

    @Singleton
    @Provides
    fun createClient(): OkHttpClient {

       val okHttpClient =  OkHttpClient().newBuilder().addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {

                val originalRequest: Request = chain.request()

                val builder: Request.Builder = originalRequest.newBuilder()
                    .header("Authorization", RetrofitParam.BEARER_TOKEN)
                    .header("Client-Id", RetrofitParam.CLIENT_ID)

                val newRequest: Request = builder.build()

                return chain.proceed(newRequest)
            }
        }).build()

        return okHttpClient
    }

    @Singleton
    @Provides
    fun createRetrofit(okHttpClient:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitParam.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun createRetrofitService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }


}