package com.example.mystudyapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.mystudyapplication.R
import com.example.mystudyapplication.databinding.FragmentWebViewBookBinding

class WebViewBookFragment
    : BaseFragment<FragmentWebViewBookBinding>({FragmentWebViewBookBinding.inflate(it)}) {

        private val args by navArgs<WebViewBookFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = args.book
        binding.wvBook.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(book.url)
        }
    }

    override fun onPause() {
        binding.wvBook.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.wvBook.onResume()
    }
}