package com.example.mystudyapplication.data.model


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("documents")
    val books: List<Book>,
    @SerializedName("meta")
    val meta: Meta
)