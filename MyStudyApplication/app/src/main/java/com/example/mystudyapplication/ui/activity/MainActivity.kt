package com.example.mystudyapplication.ui.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mystudyapplication.R
import com.example.mystudyapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity
    : BaseActivity<ActivityMainBinding>({ActivityMainBinding.inflate(it)}) {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val topLevelFragment = setOf(R.id.fragment_search, R.id.fragment_favorite, R.id.fragment_settings)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
    }

    private fun setNavigation() {
        val host = supportFragmentManager.findFragmentById(R.id.host_nav_booksearch) as NavHostFragment
        navController = host.navController
        binding.mainBottomNavi.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(topLevelFragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}