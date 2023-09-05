package com.iagocarvalho.activelife.network

import com.iagocarvalho.activelife.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://exercisedb.p.rapidapi.com/"
    private const val API_KEYS = BuildConfig.API_KEY

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor {
                chain -> val originalRequest = chain.request()

                // Adicione a chave privada à solicitação.
                val newRequest = originalRequest.newBuilder()
                    .addHeader("X-RapidAPI-Key", API_KEYS)
                    .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
                    .build()

                // Retorne a solicitação modificada.
                chain.proceed(newRequest)
            }.build()).build()
    }
    val ExerciseDB: ExerciseDBService by lazy {
        retrofit.create(ExerciseDBService::class.java)
    }
}
