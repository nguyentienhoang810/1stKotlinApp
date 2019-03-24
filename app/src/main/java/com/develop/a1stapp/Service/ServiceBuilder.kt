package com.develop.a1stapp.Service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    //constant base URL
    private const val URL = "https://pokeapi.co/api/v2/"

    //create OkHttp Client
    private val okHttp = OkHttpClient.Builder()

    //create Retrofit Builder
    private val builder = Retrofit.Builder().baseUrl(URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .client(okHttp.build())

    //create Retrofit instance
    private val retrofit = builder.build()


    fun<T> build(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}