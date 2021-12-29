package com.example.arviewproject.app

import com.example.arviewproject.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class App:DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerAppComponent.builder().bindContext(this).build()
    }

}

/*
 private val baseUrl = "https://api.twitch.tv/kraken/"

    private var retroService: RetrofiteService
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
                    .header("Authorization", RetrofirtParam.BEARER_TOKEN)
                    .header("Client-Id", RetrofirtParam.CLIENT_ID)

                val newRequest: Request = builder.build()

                return chain.proceed(newRequest)
            }
        }).build()

        return okHttpClient
    }


    fun getRetrofitService(): RetrofiteService {
        return retroService
    }

 */