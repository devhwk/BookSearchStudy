package com.example.mystudyapplication.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.example.mystudyapplication.R

// 기본적인 binding처리를 중복하지 않기위한 base activity
open class BaseActivity<B: ViewBinding>(val factory: (LayoutInflater) -> B)
    : AppCompatActivity() {
    private var _binding: B? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = factory(layoutInflater)
        setContentView(binding.root)
//        initWindowEdge()
    }

    private fun initWindowEdge() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}