package com.walmart.countrieschallenge.domain

import com.walmart.countrieschallenge.data.CountryApi
import com.walmart.countrieschallenge.data.CountryNetworkClient
import com.walmart.countrieschallenge.model.CountryModel
import java.io.IOException

interface CountryRepository {
    suspend fun getCountries(): Result<List<CountryModel>>
}

class CountryRepositoryImpl(private val api: CountryApi = CountryNetworkClient.api): CountryRepository {
    override suspend fun getCountries(): Result<List<CountryModel>> {
        return runCatching {
            api.getCountries().run {
                if (isSuccessful) body() ?: emptyList()
                else throw IOException(errorBody()?.string() ?: "Error retrieving countries")
            }
        }
    }
}