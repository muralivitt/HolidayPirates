package com.holidaypirates.network

import com.holidaypirates.network.APIService.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  NetworkController : Provides the APIService to with retrofit configuration
 */
object NetworkController {
    private var retrofit: Retrofit? = null
    private fun getClient(): Retrofit {
        val client = OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .build()

        if (null == retrofit) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
        }
        return retrofit!!
    }

    /**
     * return : APIService object to make API calls
     */
    fun getAPIService() = getClient().create(APIService::class.java)
}