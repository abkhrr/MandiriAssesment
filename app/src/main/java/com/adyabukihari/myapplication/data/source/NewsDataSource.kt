package com.adyabukihari.myapplication.data.source

import com.adyabukihari.myapplication.data.model.BaseDataResponse

// Data Source For Remote and Repository

interface NewsDataSource {

    fun getNews(
        country: String?,
        category: String?,
        apiKey: String?,
        onSuccess: (data: BaseDataResponse) -> Unit = {},
        onError: (status: String) -> Unit = {},
        onFinish: () -> Unit = {}
    )
}