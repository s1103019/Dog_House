package com.example.special_subject.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.special_subject.MainActivity
import com.example.special_subject.databinding.ActivityLoginBinding
import com.example.special_subject.ui.API.ErrorResponse
import com.example.special_subject.ui.API.LoginRequest
import com.example.special_subject.ui.API.LoginResponse
import com.example.special_subject.ui.API.AuthService
import com.example.special_subject.ui.notifications.RegisterActivity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // 创建 Retrofit 实例
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://your.api.endpoint/") // 使用实际的 BASE_URL
        .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析 JSON
        .build()

    // 使用 Retrofit 实例创建 AuthService
    private val authService = retrofit.create(AuthService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 登录按钮的点击事件
        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (username.isNotBlank() && password.isNotBlank()) {
                // 调用 loginUser 函数进行登录处理
                loginUser(username, password)
            } else {
                Toast.makeText(this, "請填寫帳號和密碼", Toast.LENGTH_SHORT).show()
            }
        }

        // 注册按钮的点击事件，跳转到 RegisterActivity
        binding.registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(username: String, password: String) {
        // 创建登录请求对象
        val loginRequest = LoginRequest(username, password)

        // 使用 Retrofit 进行登录请求
        authService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.user != null) {
                        // 登录成功，跳转到 MainActivity
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish() // 结束 LoginActivity
                    } else {
                        // 若响应成功但没有 user 数据，显示登录失败的提示
                        Toast.makeText(this@LoginActivity, "登入失敗，無法取得用戶資料", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // 登录失败，显示错误提示
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = parseErrorMessage(errorBody)
                    Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // 网络错误或其他问题，显示错误提示
                Toast.makeText(this@LoginActivity, "登入失敗，請檢查網路或稍後再試", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // 解析错误信息
    private fun parseErrorMessage(errorBody: String?): String {
        return try {
            val gson = Gson()
            val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
            errorResponse.message
        } catch (e: Exception) {
            "登入失敗"
        }
    }
}
