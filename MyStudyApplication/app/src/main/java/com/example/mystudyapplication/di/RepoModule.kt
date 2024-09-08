package com.example.mystudyapplication.di

import com.example.mystudyapplication.repository.BookSearchRepo
import com.example.mystudyapplication.repository.BookSearchRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Singleton
    @Binds
    abstract fun bindBookSearchRepo(
        bookSearchRepoImpl: BookSearchRepoImpl
    ): BookSearchRepo
}