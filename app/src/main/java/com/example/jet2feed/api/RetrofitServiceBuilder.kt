package com.example.jet2feed.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceBuilder {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}
