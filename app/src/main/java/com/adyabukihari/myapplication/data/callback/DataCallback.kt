package com.adyabukihari.myapplication.data.callback

import com.adyabukihari.myapplication.data.model.ArticleResponseModel

// Callback For Articles Data Response

interface GetNewsCallback{
    fun onSuccess(data: List<ArticleResponseModel>)
    fun onError(message: String)
}

interface ViewHelper{
    fun showProgress()
    fun hideProgress()
}