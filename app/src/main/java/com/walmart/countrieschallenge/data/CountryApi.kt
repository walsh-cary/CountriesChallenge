package com.walmart.countrieschallenge.data

import com.walmart.countrieschallenge.model.CountryModel
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET(CountryNetworkConstants.API_ENDPOINT)
    suspend fun getCountries(): Response<List<CountryModel>>
}