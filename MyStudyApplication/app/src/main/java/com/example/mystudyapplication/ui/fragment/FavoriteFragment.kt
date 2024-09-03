package com.example.mystudyapplication.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudyapplication.databinding.FragmentFavoriteBinding
import com.example.mystudyapplication.ui.activity.MainActivity
import com.example.mystudyapplication.ui.adapter.BookSearchPagingAdapter
import com.example.mystudyapplication.ui.viewmodel.BookSearchViewModel
import com.example.mystudyapplication.util.collectLatestStateFlow
import com.google.android.material.snackbar.Snackbar

class FavoriteFragment
    : BaseFragment<FragmentFavoriteBinding>({FragmentFavoriteBinding.inflate(it)}) {

    private lateinit var bookSearchViewModel: BookSearchViewModel
    private lateinit var bookSearchAdapter: BookSearchPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookSearchViewModel = (activity as MainActivity).bookSearchViewModel

        setRecyclerView()
        setSwipeItem(view)

        collectLatestStateFlow(bookSearchViewModel.favoritePagingBooks) {
            bookSearchAdapter.submitData(it)
        }
    }

    private fun setRecyclerView() {
        bookSearchAdapter = BookSearchPagingAdapter()
        binding.rvFavoriteBooks.apply {
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

    private fun setSwipeItem(view: View) {
        val itemSwipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.bindingAdapterPosition
                val book = bookSearchAdapter.peek(pos)
                book?.let {
                    bookSearchViewModel.removeFavoriteBook(book)
                    Snackbar.make(view, "remove Favorite book", Snackbar.LENGTH_SHORT).apply {
                        setAction("Undo") {
                            bookSearchViewModel.addFavoriteBook(book)
                        }
                    }.show()
                }
            }
        }

        ItemTouchHelper(itemSwipeCallback).apply {
            attachToRecyclerView(binding.rvFavoriteBooks)
        }
    }
}