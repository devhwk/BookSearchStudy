package com.example.mystudyapplication.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystudyapplication.databinding.FragmentSearchBinding
import com.example.mystudyapplication.ui.adapter.BookLoadStateAdapter
import com.example.mystudyapplication.ui.adapter.BookSearchPagingAdapter
import com.example.mystudyapplication.ui.viewmodel.SearchViewModel
import com.example.mystudyapplication.util.Constants
import com.example.mystudyapplication.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment
    : BaseFragment<FragmentSearchBinding>({ FragmentSearchBinding.inflate(it)}) {
    private lateinit var bookSearchAdapter: BookSearchPagingAdapter
    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        searchBooks()
        setViewModelObserve()
        setLoadState()
    }

    private fun setRecyclerView() {
        bookSearchAdapter = BookSearchPagingAdapter()
        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = bookSearchAdapter

            adapter = bookSearchAdapter.withLoadStateFooter(
                footer =  BookLoadStateAdapter(bookSearchAdapter::retry)
            )
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
                        searchViewModel.searchBookPaging(query)
                    }
                }
            }

            startTime = endTime
        }
    }

    private fun setViewModelObserve() {
        collectLatestStateFlow(searchViewModel.searchPagingResult) {
            bookSearchAdapter.submitData(it)
        }
    }

    private fun setLoadState() {
        bookSearchAdapter.addLoadStateListener { combinedState ->
            val loadState = combinedState.source
            val isListEmpty = bookSearchAdapter.itemCount < 1
                    && loadState.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached

            binding.tvResultEmpty.isVisible = isListEmpty
            binding.rvSearchResult.isVisible = !isListEmpty

            binding.progress.isVisible = loadState.refresh is LoadState.Loading
        }
    }
}