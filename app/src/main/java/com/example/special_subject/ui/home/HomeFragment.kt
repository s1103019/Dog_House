package com.example.special_subject.ui.home

import AnimalAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.special_subject.R
import com.example.special_subject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: AnimalAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 設置 RecyclerView 和適配器
        adapter = AnimalAdapter(getAnimalData()) { animal ->
            onAnimalClick(animal)
        }

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        // 搜索欄的文本監聽器，用於即時過濾內容
        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adapter.filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        return root
    }

    private fun onAnimalClick(animal: Animal) {
        if (animal.status == "可愛狗狗") {
            // 導航到 DashboardFragment 並設置 popUpTo 保持導航棧的正確性
            findNavController().navigate(
                R.id.action_homeFragment_to_dashboardFragment
            )
        } else {
            Toast.makeText(requireContext(), "目前尚未有直播", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getAnimalData(): List<Animal> {
        return listOf(
            Animal(R.drawable.dog, "可愛狗狗"),
            Animal(R.drawable.ic_dashboard_black_24dp, "未使用"),
            Animal(R.drawable.ic_dashboard_black_24dp, "未使用"),
            Animal(R.drawable.ic_dashboard_black_24dp, "未使用"),
            Animal(R.drawable.ic_dashboard_black_24dp, "未使用"),
            Animal(R.drawable.ic_dashboard_black_24dp, "未使用"),
            Animal(R.drawable.ic_dashboard_black_24dp, "未使用"),
            Animal(R.drawable.ic_dashboard_black_24dp, "未使用"),
            Animal(R.drawable.ic_dashboard_black_24dp, "未使用")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
