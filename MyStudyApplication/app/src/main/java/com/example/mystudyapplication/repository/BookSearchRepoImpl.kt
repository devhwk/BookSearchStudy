package com.example.mystudyapplication.repository

import com.example.mystudyapplication.data.api.RetrofitInstance
import com.example.mystudyapplication.data.model.SearchResponse
import retrofit2.Response

class BookSearchRepoImpl :BookSearchRepo {
    override suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int,
        target: String
    ): Response<SearchResponse> {
        return RetrofitInstance.api.searchBooks(query, sort, page, size, target)
    }
}