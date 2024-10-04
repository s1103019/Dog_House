package com.example.special_subject.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.special_subject.MainActivity
import com.example.special_subject.R

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
