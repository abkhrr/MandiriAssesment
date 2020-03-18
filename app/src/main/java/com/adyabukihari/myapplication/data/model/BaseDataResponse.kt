package com.adyabukihari.myapplication.data.model

import com.google.gson.annotations.SerializedName

// Model For Base Response From API

data class BaseDataResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val articles: List<ArticleResponseModel>
)