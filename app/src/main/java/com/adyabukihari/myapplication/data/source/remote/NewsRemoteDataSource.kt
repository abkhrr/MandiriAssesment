package com.adyabukihari.myapplication.data.source.remote

import com.adyabukihari.myapplication.data.model.BaseDataResponse
import com.adyabukihari.myapplication.data.source.NewsDataSource
import com.adyabukihari.myapplication.data.source.rest.NewsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object NewsRemoteDataSource: NewsDataSource {

    // API Retrofit (disposable)

    private var mApiService = NewsApiService.getApiService()

    override fun getNews(
        country: String?,
        category: String?,
        apiKey: String?,
        onSuccess: (data: BaseDataResponse) -> Unit,
        onError: (status: String) -> Unit,
        onFinish: () -> Unit
    ) {
        val disposable = mApiService.getNews(country, category, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                onSuccess(it)
            }, {
                onError(it.message ?: "")
            })
    }

}