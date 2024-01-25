package com.walmart.countrieschallenge.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountryNetworkClient {
    private var interceptor: HttpLoggingInterceptor? = null
    private var client: OkHttpClient? = null

    val api: CountryApi by lazy {
        initRetrofit(CountryNetworkConstants.BASE_URL)
    }

    private fun initRetrofit(
        baseUrl: String
    ):CountryApi {
        interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        client = OkHttpClient.Builder().apply {
            interceptor?.let { this.addInterceptor(it) }
        }.build()

        client?.let {
            return getApi(baseUrl, it)
        } ?: throw Exception("Fail to create Network client")
    }

    private fun getApi(baseUrl: String, client: OkHttpClient): CountryApi =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
            .create(CountryApi::class.java)
}