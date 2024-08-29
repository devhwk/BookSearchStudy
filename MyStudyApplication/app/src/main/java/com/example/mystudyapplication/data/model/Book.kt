package com.example.mystudyapplication.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    @SerializedName("authors")
    val authors: List<String>,
    @SerializedName("contents")
    val contents: String,
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("sale_price")
    val salePrice: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("translators")
    val translators: List<String>,
    @SerializedName("url")
    val url: String
) : Parcelable