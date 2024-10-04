package com.example.special_subject.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.special_subject.R
import com.example.special_subject.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root: View = binding.root
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val changeButton: ImageButton = view.findViewById(R.id.imageButton2)
        val payButton: ImageButton = view.findViewById(R.id.imageButton3)
        val logOutButton: ImageButton = view.findViewById(R.id.imageButton4)

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
        _binding = null
    }
}