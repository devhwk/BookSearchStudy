package com.example.mystudyapplication.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mystudyapplication.data.api.RetrofitInstance
import com.example.mystudyapplication.data.db.BookSearchDatabase
import com.example.mystudyapplication.data.model.Book
import com.example.mystudyapplication.data.model.SearchResponse
import com.example.mystudyapplication.util.Constants
import com.example.mystudyapplication.util.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import retrofit2.Response

class BookSearchRepoImpl(
    private val db: BookSearchDatabase,
    private val dataStore: DataStore<Preferences>
) :BookSearchRepo {
    override suspend fun insertBook(book: Book) = db.bookSearchDao().insertBook(book)
    override suspend fun deleteBook(book: Book) = db.bookSearchDao().deleteBook(book)

    private object PreferencesKeys {
        val SORT_MODE = stringPreferencesKey("sort_mode")
    }
    override suspend fun saveSortMode(mode: String) {
        dataStore.edit { prefs ->
            prefs[PreferencesKeys.SORT_MODE] = mode
        }
    }

    override suspend fun getSortMode(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[PreferencesKeys.SORT_MODE] ?: Sort.ACCURACY.value
            }
    }

    override fun getFavoritePagingBooks(): Flow<PagingData<Book>> {
        val factory = { db.bookSearchDao().getFavoritePagingBooks() }

        return Pager(
            config = getNewPagingConfig(),
            pagingSourceFactory = factory
        ).flow
    }

    override fun searchBooksPaging(
        query: String,
        sort: String,
        target: String
    ): Flow<PagingData<Book>> {
        val factory = { BookSearchPagingSource(query, sort, target) }

        return Pager(
            config = getNewPagingConfig(),
            pagingSourceFactory = factory
        ).flow
    }

    private fun getNewPagingConfig() = PagingConfig (
        pageSize = Constants.PAGING_SIZE,
        enablePlaceholders = false,
        maxSize = Constants.PAGING_SIZE * 3
    )
}