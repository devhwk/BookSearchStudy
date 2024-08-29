package com.example.mystudyapplication.data.api

import com.example.mystudyapplication.data.model.SearchResponse
import com.example.mystudyapplication.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BookSearchApi {

    @Headers("Authorization: KakaoAK ${Constants.BOOK_API_KEY}")
    @GET("v3/search/book")
    suspend fun searchBooks(
        @Query("query") query: String,
        @Query("sort") sort: String, // accuracy(정확도순) 또는 latest(발간일순)
        @Query("page") page: Int, // 1~50
        @Query("size") size: Int, // 1~50
        @Query("target") target: String // 검색 필드 제한, 사용 가능한 값: title(제목), isbn (ISBN), publisher(출판사), person(인명)
    ): Response<SearchResponse>
}