package com.example.special_subject.ui.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// 定义请求数据模型
data class LoginRequest(
    val username: String,
    val password: String
)

// 定义用户数据模型
data class User(
    val id: Int,
    val username: String,
    val role: String
)

// 定义登录响应模型
data class LoginResponse(
    val message: String,
    val user: User?  // 用户信息可能为空（如果登录失败或无此用户）
)

// 定义错误响应模型
data class ErrorResponse(
    val message: String
)

// 定义 API 接口
interface AuthService {
    @POST("api/auth")  // 注意不要在这里添加斜杠开头，BASE_URL 会自动处理路径
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}
