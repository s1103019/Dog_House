package com.example.special_subject

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.special_subject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // 設置 Toolbar 作為 ActionBar
//        setSupportActionBar(binding.toolbar) // 如果您使用自定義 Toolbar

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
//package com.example.special_subject
//
//import android.os.Bundle
//import android.view.View
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.setupActionBarWithNavController
//import androidx.navigation.ui.setupWithNavController
//import com.example.special_subject.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val navView: BottomNavigationView = binding.nav_view
//
//        // 設置兩個 NavController 分別處理兩個 NavHostFragment
//        val navControllerMobile = findNavController(R.id.nav_host_fragment_mobile)
//        val navControllerGraph = findNavController(R.id.nav_host_fragment_graph)
//
//        // 配置 ActionBar 跟隨 navigation，針對兩個導航控制器
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//        )
//        setupActionBarWithNavController(navControllerMobile, appBarConfiguration)
//        setupActionBarWithNavController(navControllerGraph, appBarConfiguration)
//
//        // 監聽 BottomNavigationView 的選擇事件
//        navView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navigation_home -> {
//                    binding.navHostFragmentMobile.visibility = View.VISIBLE
//                    binding.navHostFragmentGraph.visibility = View.GONE
//                    navControllerMobile.navigate(R.id.navigation_home)
//                    true
//                }
//                R.id.navigation_dashboard -> {
//                    binding.navHostFragmentMobile.visibility = View.GONE
//                    binding.navHostFragmentGraph.visibility = View.VISIBLE
//                    navControllerGraph.navigate(R.id.dashboardFragment)
//                    true
//                }
//                R.id.navigation_notifications -> {
//                    binding.navHostFragmentMobile.visibility = View.GONE
//                    binding.navHostFragmentGraph.visibility = View.VISIBLE
//                    navControllerGraph.navigate(R.id.notificationsFragment)
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//}
