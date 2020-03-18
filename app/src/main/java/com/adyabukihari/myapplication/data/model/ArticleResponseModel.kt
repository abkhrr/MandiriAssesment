package com.adyabukihari.myapplication.data.model

import com.google.gson.annotations.SerializedName

// Model For Article

data class ArticleResponseModel(
    @SerializedName("source")
    val source: SourceModel?,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("content")
    val content: String? = null
)