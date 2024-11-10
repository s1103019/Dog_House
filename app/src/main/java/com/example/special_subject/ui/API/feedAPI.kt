package com.example.special_subject.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

// 定義 API 請求的資料模型
data class FeedRequest(val action: String = "feed")
data class FeedResponse(val message: String)

// 定義 API 介面
interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/api/feed")
    fun feed(@Body request: FeedRequest): Call<FeedResponse>
}
