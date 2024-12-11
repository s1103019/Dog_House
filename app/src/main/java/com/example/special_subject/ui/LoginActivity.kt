//package com.example.special_subject.ui
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.special_subject.MainActivity
//import com.example.special_subject.databinding.ActivityLoginBinding
//import com.example.special_subject.ui.notifications.RegisterActivity
//
//class LoginActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityLoginBinding
//
//    // 开发模式的用户名和密码
//    private val storedUsername = "testuser"  // 固定的用户名
//    private val storedPassword = "testpass"  // 固定的密码
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // 登录按钮的点击事件
//        binding.loginButton.setOnClickListener {
//            val username = binding.username.text.toString()
//            val password = binding.password.text.toString()
//
//            if (username.isNotBlank() && password.isNotBlank()) {
//                // 检查用户名和密码是否正确
//                if (username == storedUsername && password == storedPassword) {
//                    // 登录成功，跳转到 MainActivity
//                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(intent)
//                    finish() // 结束 LoginActivity
//                } else {
//                    // 登录失败，提示错误
//                    Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                // 用户名或密码为空，提示用户
//                Toast.makeText(this, "请填写用户名和密码", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        // 注册按钮的点击事件，跳转到 RegisterActivity
//        binding.registerLink.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//    }
//}

package com.example.special_subject.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.special_subject.MainActivity
import com.example.special_subject.databinding.ActivityLoginBinding
import com.example.special_subject.ui.notifications.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // 开发模式的用户名和密码
    private val storedUsername = "1111"  // 固定的用户名
    private val storedPassword = "2222"  // 固定的密码

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 登录按钮的点击事件
        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (username.isNotBlank() && password.isNotBlank()) {
                // 检查用户名和密码是否正确
                if (username == storedUsername && password == storedPassword) {
                    // 登录成功，跳转到 MainActivity
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish() // 结束 LoginActivity
                } else {
                    // 登录失败，提示错误
                    Toast.makeText(this, "帳號或密碼錯誤", Toast.LENGTH_SHORT).show()
                }
            } else {
                // 用户名或密码为空，提示用户
                Toast.makeText(this, "請填寫帳號或密碼", Toast.LENGTH_SHORT).show()
            }
        }

        // 注册按钮的点击事件，跳转到 RegisterActivity
        binding.registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

