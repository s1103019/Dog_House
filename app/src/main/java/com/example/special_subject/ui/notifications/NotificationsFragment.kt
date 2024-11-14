package com.example.special_subject.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.special_subject.databinding.FragmentNotificationsBinding
import com.example.special_subject.ui.LoginActivity

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val changeButton: ImageButton = binding.imageButton2
        val payButton: ImageButton = binding.imageButton3
        val logOutButton: ImageButton = binding.imageButton4

        changeButton.setOnClickListener {
            val intent = Intent(requireContext(), ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        payButton.setOnClickListener {
            // 指定支付页面的 URL
            val paymentUrl = "https://www.paypal.com/ncp/payment/J26RUG62AHGBC"

            // 创建 Intent 打开浏览器并访问 URL
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = android.net.Uri.parse(paymentUrl)
            }

            // 检查是否有浏览器可以处理 Intent
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            } else {
                // 如果没有浏览器可以处理，显示错误信息
                Toast.makeText(requireContext(), "无法打开支付页面", Toast.LENGTH_SHORT).show()
            }
        }


        logOutButton.setOnClickListener {
            // 清除使用者資料（例如使用 SharedPreferences）
            val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", 0)
            val editor = sharedPreferences.edit()
            editor.clear()  // 清除所有登入資訊
            editor.apply()

            // 跳轉至 LoginActivity 並清除返回棧
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            Toast.makeText(requireContext(), "已成功登出", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
