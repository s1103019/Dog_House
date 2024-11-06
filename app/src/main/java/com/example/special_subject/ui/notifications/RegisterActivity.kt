package com.example.special_subject.ui.notifications

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.special_subject.databinding.ActivityRegisterBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

class RegisterActivity : AppCompatActivity() {

    // 使用 ViewBinding 访问布局视图
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化绑定类
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 注册按钮点击事件
        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val name = binding.nameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()

            if (username.isNotBlank() && password.isNotBlank() && name.isNotBlank() && phone.isNotBlank()) {
                registerUser(username, password, name, phone)
            } else {
                Toast.makeText(this, "請填寫所有欄位", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(username: String, password: String, name: String, phone: String) {
        val client = OkHttpClient()
        val json = JSONObject().apply {
            put("username", username)
            put("password", password)
            put("name", name)
            put("phone", phone)
        }

        val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json.toString())
        val request = Request.Builder()
            .url("http://your.api.endpoint/api/register") // 更換為實際的 API URL
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(applicationContext, "註冊失敗，請稍後再試", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "註冊成功", Toast.LENGTH_SHORT).show()
                    } else if (response.code == 409) {
                        Toast.makeText(applicationContext, "用戶名已存在", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "註冊失敗", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}