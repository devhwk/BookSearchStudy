package com.example.mystudyapplication.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystudyapplication.databinding.FragmentSearchBinding
import com.example.mystudyapplication.ui.activity.MainActivity
import com.example.mystudyapplication.ui.adapter.BookSearchAdapter
import com.example.mystudyapplication.ui.viewmodel.BookSearchViewModel
import com.example.mystudyapplication.util.Constants

class SearchFragment
    : BaseFragment<FragmentSearchBinding>({ FragmentSearchBinding.inflate(it)}) {
    private lateinit var bookSearchViewModel: BookSearchViewModel
    private lateinit var bookSearchAdapter: BookSearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookSearchViewModel = (activity as MainActivity).bookSearchViewModel

        setRecyclerView()
        searchBooks()
        setViewModelObserve()
    }

    private fun setRecyclerView() {
        bookSearchAdapter = BookSearchAdapter()
        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = bookSearchAdapter
        }

        bookSearchAdapter.setOnItemClickListener {
            val action = SearchFragmentDirections.actionFragmentSearchToFragmentWebViewBookFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun searchBooks() {
        var startTime = System.currentTimeMillis()
        var endTime: Long

        binding.etSearch.addTextChangedListener { text: Editable? ->
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= Constants.SEARCH_BOOKS_TIME_DELAY) {
                text?.let {
                    val query = it.toString().trim()
                    if (query.isNotEmpty()) {
                        bookSearchViewModel.searchBooks(query)
                    }
                }
            }

            startTime = endTime
        }
    }

    private fun setViewModelObserve() {
        bookSearchViewModel.searchResult.observe(viewLifecycleOwner) { response ->
            val books = response.books
            bookSearchAdapter.submitList(books)
        }
    }
}