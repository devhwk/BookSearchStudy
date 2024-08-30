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
import com.example.mystudyapplication.ui.activity.MainActivity
import com.example.mystudyapplication.ui.viewmodel.BookSearchViewModel
import com.google.android.material.snackbar.Snackbar

class WebViewBookFragment
    : BaseFragment<FragmentWebViewBookBinding>({FragmentWebViewBookBinding.inflate(it)}) {

    private val args by navArgs<WebViewBookFragmentArgs>()
    private lateinit var bookSearchViewModel: BookSearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookSearchViewModel = (activity as MainActivity).bookSearchViewModel

        val book = args.book
        binding.wvBook.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(book.url)
        }

        binding.fabFavorite.setOnClickListener {
            bookSearchViewModel.addFavoriteBook(book)
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