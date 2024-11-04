package com.example.special_subject.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.special_subject.databinding.FragmentRegisterBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val name = binding.nameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()

            if (username.isNotBlank() && password.isNotBlank() && name.isNotBlank() && phone.isNotBlank()) {
                registerUser(username, password, name, phone)
            } else {
                Toast.makeText(context, "請填寫所有欄位", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun registerUser(username: String, password: String, name: String, phone: String) {
        val client = OkHttpClient()
        val json = JSONObject().apply {
            put("username", username)
            put("password", password)
            put("name", name)
            put("phone", phone)
        }

        val requestBody = RequestBody.create(MediaType.parse("application/json"), json.toString())
        val request = Request.Builder()
            .url("http://your.api.endpoint/api/register") // 更換為實際的 API URL
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity?.runOnUiThread {
                    Toast.makeText(context, "註冊失敗，請稍後再試", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                activity?.runOnUiThread {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "註冊成功", Toast.LENGTH_SHORT).show()
                    } else if (response.code() == 409) {
                        Toast.makeText(context, "用戶名已存在", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "註冊失敗", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
