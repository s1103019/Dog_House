package com.example.special_subject.ui.home

import AnimalAdapter
import android.os.Bundle
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 設置 RecyclerView 並使用 GridLayoutManager
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2) // 2列網格佈局
        recyclerView.adapter = AnimalAdapter(getAnimalData())

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
            Animal(R.drawable.ic_dashboard_black_24dp, "未使用"),
            Animal(R.drawable.ic_dashboard_black_24dp, "未使用")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
