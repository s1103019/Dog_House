package com.example.special_subject.ui.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://3327-2001-b400-e4a6-1567-a7-c05d-2fdb-49a2.ngrok-free.app" // 替換為您的伺服器基本 URL

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
