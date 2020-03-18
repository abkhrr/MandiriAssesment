package com.adyabukihari.myapplication.ui

import android.content.Context
import com.adyabukihari.myapplication.data.callback.GetNewsCallback
import com.adyabukihari.myapplication.data.model.ArticleResponseModel
import com.adyabukihari.myapplication.utils.Injection

// Injecting Data From Repository

class DataImpl internal constructor(private val context: Context) {

    private val mRepository = Injection.createRepository(context)

    fun getNews(country: String?, category: String?, apiKey: String?,callback: GetNewsCallback){
        mRepository.getNews(
            country = country,
            category = category,
            apiKey = apiKey,
            onSuccess = {
                if(it.articles.isNotEmpty()){
                    val listItem = mutableListOf<ArticleResponseModel>()
                    if(it.articles.isNotEmpty()) {
                        listItem.addAll(it.articles)
                        callback.onSuccess(listItem)
                    }
                    callback.onSuccess(it.articles)
                }
            },onError = {
                callback.onError(it)
            }, onFinish = {

            })
    }
}