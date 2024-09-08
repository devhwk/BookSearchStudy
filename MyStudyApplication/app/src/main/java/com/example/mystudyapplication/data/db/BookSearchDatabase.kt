package com.example.mystudyapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mystudyapplication.data.model.Book

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DBTypeConverter::class)
abstract class BookSearchDatabase : RoomDatabase() {
    abstract fun bookSearchDao(): BookSearchDao
}