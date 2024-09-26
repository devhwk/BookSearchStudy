package com.example.mystudyapplication.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mystudyapplication.data.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * from books")
    fun getFavoritePagingBooks(): PagingSource<Int, Book>

    // for test
    @Query("SELECT * FROM books")
    fun getFavoriteBooks(): Flow<List<Book>>
}