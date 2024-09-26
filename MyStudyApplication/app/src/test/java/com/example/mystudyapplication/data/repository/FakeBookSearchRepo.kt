package com.example.mystudyapplication.data.repository

import androidx.paging.PagingData
import com.example.mystudyapplication.data.model.Book
import com.example.mystudyapplication.repository.BookSearchRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeBookSearchRepo : BookSearchRepo {
    private val bookItems = mutableListOf<Book>()
    override suspend fun insertBook(book: Book) {
        bookItems.add(book)
    }

    override suspend fun deleteBook(book: Book) {
        bookItems.remove(book)
    }

    override suspend fun saveSortMode(mode: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getSortMode(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getFavoritePagingBooks(): Flow<PagingData<Book>> {
        TODO("Not yet implemented")
    }

    override fun searchBooksPaging(
        query: String,
        sort: String,
        target: String
    ): Flow<PagingData<Book>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCacheDeleteMode(mode: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getCacheDeleteMode(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return flowOf(bookItems)
    }
}