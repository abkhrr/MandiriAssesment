package com.adyabukihari.myapplication.data.source.rest

import com.adyabukihari.myapplication.BuildConfig
import com.adyabukihari.myapplication.data.model.BaseDataResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface NewsApiService {

    /*
     *   Get All News API
    */

    @GET("/v2/top-headlines")
    @Headers("Content-Type: Application/json")
    fun getNews(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String?
    ): Observable<BaseDataResponse>

    companion object Factory {

        fun getApiService(): NewsApiService {

            // Set the Retrofit 2.0 and Logging Interceptor

            val mLoggingInterceptor = HttpLoggingInterceptor()
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val mClient = if (BuildConfig.DEBUG) {
                OkHttpClient.Builder()
                    .addInterceptor(mLoggingInterceptor)
                    .addInterceptor { chain ->
                        val request = chain.request()
                        val newRequest = request.newBuilder().build()
                        chain.proceed(newRequest)
                    }
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .build()
            } else {
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request()
                        val newRequest = request.newBuilder().build()
                        chain.proceed(newRequest)
                    }
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .build()
            }

            val mRetrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mClient)
                .build()

            return mRetrofit.create(NewsApiService::class.java)
        }
    }

}