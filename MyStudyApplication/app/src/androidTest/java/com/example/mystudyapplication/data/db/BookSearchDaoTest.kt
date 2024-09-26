package com.example.mystudyapplication.data.db

import androidx.test.filters.SmallTest
import com.example.mystudyapplication.data.model.Book
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
@ExperimentalCoroutinesApi
class BookSearchDaoTest {

    @Inject
    @Named("test_db")
    lateinit var db: BookSearchDatabase
    private lateinit var dao: BookSearchDao

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = db.bookSearchDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insert_book_to_db() = runTest {
        val book = Book(
            listOf("a"), "b", "c", "d", 0, "e",
            0, "f", "g", "h", listOf("i"), "j"
        )

        dao.insertBook(book)

        val favoriteBooks = dao.getFavoriteBooks().first()
        assertThat(favoriteBooks).contains(book)
    }

    @Test
    fun delete_book_to_db() = runTest {
        val book = Book(
            listOf("a"), "b", "c", "d", 0, "e",
            0, "f", "g", "h", listOf("i"), "j"
        )

        dao.insertBook(book)
        dao.deleteBook(book)

        val favoriteBooks = dao.getFavoriteBooks().first()
        assertThat(favoriteBooks).doesNotContain(book)
    }
}