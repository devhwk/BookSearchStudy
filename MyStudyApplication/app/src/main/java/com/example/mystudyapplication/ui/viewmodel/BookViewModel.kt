package com.example.mystudyapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystudyapplication.data.model.Book
import com.example.mystudyapplication.repository.BookSearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookSearchRepo: BookSearchRepo
) : ViewModel() {
    fun addFavoriteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepo.insertBook(book)
    }
}