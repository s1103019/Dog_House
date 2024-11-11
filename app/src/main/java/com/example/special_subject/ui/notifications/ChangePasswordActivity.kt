// ChangePasswordActivity.kt
package com.example.special_subject.ui.notifications

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.special_subject.databinding.ChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener {
            // 返回 NotificationsFragment
            Toast.makeText(this,"完成更改", Toast.LENGTH_SHORT).show()

            finish() // 結束當前 Activity，即返回上一頁
        }
    }
}
