package com.example.mystudyapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mystudyapplication.data.model.Book
import com.example.mystudyapplication.repository.BookSearchRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookSearchViewModel(
    private val bookSearchRepo: BookSearchRepo
) : ViewModel() {
    val favoritePagingBooks: StateFlow<PagingData<Book>> =
        bookSearchRepo.getFavoritePagingBooks()
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())

    private val _searchPagingResult = MutableStateFlow<PagingData<Book>>(PagingData.empty())
    val searchPagingResult: StateFlow<PagingData<Book>> = _searchPagingResult.asStateFlow()

    fun searchBookPaging(query: String) {
        viewModelScope.launch {
            bookSearchRepo.searchBooksPaging(query, sort = getSortMode())
                .cachedIn(viewModelScope)
                .collect {
                    _searchPagingResult.value = it
                }
        }
    }

    fun addFavoriteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepo.insertBook(book)
    }

    fun removeFavoriteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepo.deleteBook(book)
    }

    fun saveSortMode(value: String) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepo.saveSortMode(value)
    }

    suspend fun getSortMode() = withContext(Dispatchers.IO) {
        bookSearchRepo.getSortMode().first()
    }

    class Factory(
        private val bookSearchRepo: BookSearchRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)) {
                return BookSearchViewModel(bookSearchRepo) as T
            }
            throw IllegalArgumentException("ViewModel class not found")
        }
    }
}