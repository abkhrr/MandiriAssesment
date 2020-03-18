package com.adyabukihari.myapplication.data.model

import com.google.gson.annotations.SerializedName

// Source Model

data class SourceModel(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null
)