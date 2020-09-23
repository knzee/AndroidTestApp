package com.example.testapp.model.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private fun createCatImageRetrofit(): CatImageApi {
        return Retrofit.Builder()
            .baseUrl("https://aws.random.cat")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CatImageApi::class.java)
    }

    private fun createCatFactRetrofit(): CatFactApi {
        return Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CatFactApi::class.java)

    }

    fun buildCatImageService(): CatImageApi {
        return createCatImageRetrofit()
    }

    fun buildCatFactService(): CatFactApi {
        return createCatFactRetrofit()
    }
}
