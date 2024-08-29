package com.example.mystudyapplication.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mystudyapplication.R
import com.example.mystudyapplication.databinding.ActivityMainBinding
import com.example.mystudyapplication.repository.BookSearchRepoImpl
import com.example.mystudyapplication.ui.fragment.FavoriteFragment
import com.example.mystudyapplication.ui.fragment.SearchFragment
import com.example.mystudyapplication.ui.fragment.SettingsFragment
import com.example.mystudyapplication.ui.viewmodel.BookSearchViewModel

class MainActivity
    : BaseActivity<ActivityMainBinding>({ActivityMainBinding.inflate(it)}) {

    lateinit var bookSearchViewModel: BookSearchViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val topLevelFragment = setOf(R.id.fragment_search, R.id.fragment_favorite, R.id.fragment_settings)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBookSearchViewModel()
        setNavigation()
    }

    private fun initBookSearchViewModel() {
        val bookSearchRepo = BookSearchRepoImpl()
        val factory = BookSearchViewModel.Factory(bookSearchRepo)
        bookSearchViewModel = ViewModelProvider(this, factory)[BookSearchViewModel::class.java]
    }

    private fun setNavigation() {
        val host = supportFragmentManager.findFragmentById(R.id.host_nav_booksearch) as NavHostFragment ?: return
        navController = host.navController
        binding.mainBottomNavi.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(topLevelFragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}