package com.adyabukihari.myapplication.utils

import android.content.Context
import com.adyabukihari.myapplication.data.NewsRepository
import com.adyabukihari.myapplication.data.source.remote.NewsRemoteDataSource

object Injection {
    private var mRepository: NewsRepository? = null

    fun createRepository(context: Context): NewsRepository {
        mRepository = NewsRepository(NewsRemoteDataSource)
        return mRepository!!
    }
}