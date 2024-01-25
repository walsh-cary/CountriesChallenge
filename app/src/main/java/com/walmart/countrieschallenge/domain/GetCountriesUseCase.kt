package com.walmart.countrieschallenge.domain

import com.walmart.countrieschallenge.model.CountryModel

fun interface GetCountriesUseCase : suspend () -> Result<List<CountryModel>>

fun getCountriesUseCase(repository: CountryRepository = CountryRepositoryImpl()) =
    GetCountriesUseCase(repository::getCountries)