package com.example.mystudyapplication.repository

import com.example.mystudyapplication.data.model.SearchResponse
import retrofit2.Response

interface BookSearchRepo {
    suspend fun searchBooks(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 10,
        target: String = ""
    ) : Response<SearchResponse>
}