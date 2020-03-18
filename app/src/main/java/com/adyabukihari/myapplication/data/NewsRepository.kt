package com.adyabukihari.myapplication.data

import com.adyabukihari.myapplication.data.model.BaseDataResponse
import com.adyabukihari.myapplication.data.source.NewsDataSource
import com.adyabukihari.myapplication.data.source.remote.NewsRemoteDataSource

// Repository For Injecting View

class NewsRepository(
    private val remoteDataSource: NewsRemoteDataSource
) : NewsDataSource {

    override fun getNews(
        country: String?,
        category: String?,
        apiKey: String?,
        onSuccess: (data: BaseDataResponse) -> Unit,
        onError: (status: String) -> Unit,
        onFinish: () -> Unit
    ) {
        return remoteDataSource.getNews(country, category, apiKey, onSuccess, onError, onFinish)
    }


}