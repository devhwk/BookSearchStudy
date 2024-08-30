package com.example.mystudyapplication.repository

import com.example.mystudyapplication.data.api.RetrofitInstance
import com.example.mystudyapplication.data.db.BookSearchDatabase
import com.example.mystudyapplication.data.model.Book
import com.example.mystudyapplication.data.model.SearchResponse
import retrofit2.Response

class BookSearchRepoImpl(
    private val db: BookSearchDatabase
) :BookSearchRepo {
    override suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int,
        target: String
    ): Response<SearchResponse> {
        return RetrofitInstance.api.searchBooks(query, sort, page, size, target)
    }

    override suspend fun insertBook(book: Book) = db.bookSearchDao().insertBook(book)
    override suspend fun deleteBook(book: Book) = db.bookSearchDao().deleteBook(book)
    override fun getFavoriteBooks() = db.bookSearchDao().getFavoriteBooks()
}