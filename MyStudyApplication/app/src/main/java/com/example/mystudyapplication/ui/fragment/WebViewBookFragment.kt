package com.example.mystudyapplication.ui.fragment

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mystudyapplication.databinding.FragmentWebViewBookBinding
import com.example.mystudyapplication.ui.viewmodel.BookViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewBookFragment
    : BaseFragment<FragmentWebViewBookBinding>({FragmentWebViewBookBinding.inflate(it)}) {

    private val args by navArgs<WebViewBookFragmentArgs>()
    private val bookViewModel by viewModels<BookViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = args.book
        binding.wvBook.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(book.url)
        }

        binding.fabFavorite.setOnClickListener {
            bookViewModel.addFavoriteBook(book)
            Snackbar.make(view, "added Favorite", Snackbar.LENGTH_SHORT).show()
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