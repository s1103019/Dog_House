package com.example.special_subject.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.special_subject.R
import com.example.special_subject.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 使用 View Binding 进行布局膨胀
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root // 返回绑定的视图
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 通过绑定访问视图元素
        val changeButton: ImageButton = binding.imageButton2
        val payButton: ImageButton = binding.imageButton3
        val logOutButton: ImageButton = binding.imageButton4

        changeButton.setOnClickListener {
            Toast.makeText(requireContext(), "ImageButton 1 点击", Toast.LENGTH_SHORT).show()
        }

        payButton.setOnClickListener {
            Toast.makeText(requireContext(), "ImageButton 2 点击", Toast.LENGTH_SHORT).show()
        }

        logOutButton.setOnClickListener {
            Toast.makeText(requireContext(), "ImageButton 3 点击", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 清理绑定，以避免内存泄漏
    }
}
