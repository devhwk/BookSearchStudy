package com.example.mystudyapplication.ui.viewmodel

import androidx.test.filters.MediumTest
import com.example.mystudyapplication.data.model.Book
import com.example.mystudyapplication.data.repository.FakeBookSearchRepo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@MediumTest
@ExperimentalCoroutinesApi
class BookViewModelTest {
    private lateinit var viewModel: BookViewModel

    @Before
    fun setUp() {
        viewModel = BookViewModel(FakeBookSearchRepo())
    }

    @Test
    fun add_favorite_book_test() = runTest {
        val book = Book(
            listOf("a"), "b", "c", "d", 0, "e",
            0, "f", "g", "h", listOf("i"), "j"
        )

        viewModel.addFavoriteBook(book)

        val favoriteBooks = viewModel.favoriteBooks.first()
        assertThat(favoriteBooks).contains(book)
    }
}