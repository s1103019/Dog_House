package com.example.special_subject.ui.notifications

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.special_subject.databinding.ChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ChangePasswordBinding

    // SharedPreferences 用于存储登录信息
    private lateinit var sharedPreferences: SharedPreferences
    private var loggedInUsername: String? = null
    private var loggedInPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 初始化 SharedPreferences，存储用户信息
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        // 从 SharedPreferences 获取已保存的用户名和密码
        loggedInUsername = sharedPreferences.getString("username", null)
        loggedInPassword = sharedPreferences.getString("password", null)

        // 填充原本的用户名和密码
        binding.Username.setText(loggedInUsername)
        binding.Password.setText(loggedInPassword)

        binding.buttonSubmit.setOnClickListener {
            val currentUsername = binding.Username.text.toString()
            val currentPassword = binding.Password.text.toString()
            val newUsername = binding.NewName.text.toString()
            val newPassword = binding.NewPassword.text.toString()

            // 验证用户名和密码是否正确
            if (currentUsername == loggedInUsername && currentPassword == loggedInPassword) {
                // 如果用户名和密码正确，进行更改操作
                updateUserCredentials(newUsername, newPassword)
            } else {
                // 如果用户名或密码错误，显示错误提示
                Toast.makeText(this, "原始帳號或密碼錯誤", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUserCredentials(newUsername: String, newPassword: String) {
        // 更新 SharedPreferences 中的用户名和密码
        val editor = sharedPreferences.edit()
        editor.putString("username", newUsername)
        editor.putString("password", newPassword)
        editor.apply()  // 保存数据

        // 提示用户修改成功
        Toast.makeText(this, "帳號與密碼更改成功", Toast.LENGTH_SHORT).show()

        // 返回上一个页面
        finish()
    }
}
