package com.example.mystudyapplication.repository

import androidx.paging.PagingData
import com.example.mystudyapplication.data.model.Book
import com.example.mystudyapplication.data.model.SearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface BookSearchRepo {
    // Room
    suspend fun insertBook(book: Book)
    suspend fun deleteBook(book: Book)

    // DataStore
    suspend fun saveSortMode(mode: String)
    suspend fun getSortMode(): Flow<String>

    // Paging
    fun getFavoritePagingBooks(): Flow<PagingData<Book>>
    fun searchBooksPaging(query: String, sort: String, target: String = ""): Flow<PagingData<Book>>

    // workManager : cache manage
    suspend fun saveCacheDeleteMode(mode: Boolean)
    suspend fun getCacheDeleteMode(): Flow<Boolean>
}