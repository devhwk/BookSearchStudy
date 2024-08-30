package com.example.mystudyapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mystudyapplication.data.model.Book
import com.example.mystudyapplication.data.model.SearchResponse
import com.example.mystudyapplication.repository.BookSearchRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookSearchViewModel(
    private val bookSearchRepo: BookSearchRepo
) : ViewModel() {
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult
    val favoriteBooks: StateFlow<List<Book>> = bookSearchRepo.getFavoriteBooks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())

    fun searchBooks(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = bookSearchRepo.searchBooks(query)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }

    fun addFavoriteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepo.insertBook(book)
    }

    fun removeFavoriteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepo.deleteBook(book)
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