package com.example.special_subject.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.special_subject.MainActivity
import com.example.special_subject.R
import com.example.special_subject.ui.API.AuthService
import com.example.special_subject.ui.API.ErrorResponse
import com.example.special_subject.ui.API.LoginRequest
import com.example.special_subject.ui.API.LoginResponse
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 修改类名，继承 AppCompatActivity
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 获取输入框和按钮
        val usernameInput = findViewById<EditText>(R.id.username)
        val passwordInput = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // 设置点击事件
        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            // 验证固定的账号和密码
            if (username == "1111" && password == "2222") {
                // 如果账号密码正确，跳转到另一个 Activity
                val intent = Intent(this, MainActivity::class.java) // 替换为你的目标 Activity
                startActivity(intent)
                finish() // 结束当前 Activity
            } else {
                // 如果账号或密码错误，显示错误提示
                Toast.makeText(this, "帳號或密碼錯誤", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

/*

// 修改类名，继承 AppCompatActivity
class LoginActivity : AppCompatActivity() {

    // Retrofit 相关变量
    private lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 获取输入框和按钮
        val usernameInput = findViewById<EditText>(R.id.username)
        val passwordInput = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // 初始化 Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") // 替换为您的 API 基础 URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authService = retrofit.create(AuthService::class.java)

        // 设置点击事件
        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            login(username, password)
        }
    }

    private fun login(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)

        // 发出登录请求
        authService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // 登录成功
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        Toast.makeText(this@LoginActivity, loginResponse.message, Toast.LENGTH_SHORT).show()
                        // 登录成功后，启动 MainActivity
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish() // 结束当前 Activity
                    }
                } else {
                    // 登录失败
                    response.errorBody()?.let {
                        val errorResponse = Gson().fromJson(it.charStream(), ErrorResponse::class.java)
                        Toast.makeText(this@LoginActivity, errorResponse.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // 请求失败
                Toast.makeText(this@LoginActivity, "登录失败: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

 */
