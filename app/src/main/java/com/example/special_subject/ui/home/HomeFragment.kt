package com.example.special_subject.ui.home

import AnimalAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
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

        // 初始化适配器和设置 RecyclerView
        adapter = AnimalAdapter(getAnimalData())
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        // 设置搜索栏的文本监听器，用于实时更新过滤内容
        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adapter.filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        return root
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
