package com.example.special_subject.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.special_subject.MainActivity
import com.example.special_subject.R
import com.example.special_subject.databinding.FragmentLoginBinding
import com.example.special_subject.ui.API.AuthService
import com.example.special_subject.ui.API.ErrorResponse
import com.example.special_subject.ui.API.LoginRequest
import com.example.special_subject.ui.API.LoginResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {

    private lateinit var authService: AuthService
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        // 初始化 Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://your.api.endpoint/") // 替換為實際API的base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        authService = retrofit.create(AuthService::class.java)

        // 登录按钮点击事件
        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            // 验证输入
            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(context, "請填寫帳號和密碼", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 调用登录API
            loginUser(username, password)
        }

        // 注册按钮点击事件，跳转到注册界面
        binding.registerLink.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }
private fun loginUser(username: String, password: String) {
    val loginRequest = LoginRequest(username, password)

    authService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse?.user != null) {
                    // 登录成功，跳转到HomeFragment
                    findNavController().navigate(R.id.action_loginFragment_to_navigation_home)
                } else {
                    // 若回應成功但無 user 資料，顯示登入失敗的提示
                    Toast.makeText(context, "登入失敗，無法取得用戶資料", Toast.LENGTH_SHORT).show()
                }
            } else {
                // 登录失败，显示错误提示
                val errorBody = response.errorBody()?.string()
                val errorMessage = parseErrorMessage(errorBody)
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            // 網絡錯誤或其他問題，顯示錯誤提示
            Toast.makeText(context, "登入失敗，請檢查網路或稍後再試", Toast.LENGTH_SHORT).show()
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
