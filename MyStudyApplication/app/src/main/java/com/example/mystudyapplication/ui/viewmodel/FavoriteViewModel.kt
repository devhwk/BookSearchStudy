package com.example.mystudyapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mystudyapplication.data.model.Book
import com.example.mystudyapplication.repository.BookSearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val bookSearchRepo: BookSearchRepo
) : ViewModel() {
    val favoritePagingBooks: StateFlow<PagingData<Book>> =
        bookSearchRepo.getFavoritePagingBooks()
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())

    fun addFavoriteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepo.insertBook(book)
    }

    fun removeFavoriteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepo.deleteBook(book)
    }
}