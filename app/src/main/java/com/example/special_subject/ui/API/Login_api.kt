package com.example.special_subject.ui.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// 定義資料模型
data class LoginRequest(
    val username: String,
    val password: String
)

data class User(
    val id: Int,
    val username: String,
    val role: String
)

data class LoginResponse(
    val message: String,
    val user: User?
)

data class ErrorResponse(
    val message: String
)

// 定義 API 介面
interface AuthService {
    @POST("/api/auth")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}
