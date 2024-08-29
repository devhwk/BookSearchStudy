package com.example.mystudyapplication.util

import com.example.mystudyapplication.BuildConfig

object Constants {
    const val BASE_URL = "https://dapi.kakao.com/"
    // secrets-gradle-plugin으로 인해 local.properties에 bookApiKey 추가하면 됨.
    const val BOOK_API_KEY = BuildConfig.bookApiKey

    const val SEARCH_BOOKS_TIME_DELAY = 100L
}